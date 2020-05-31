package graduation.project.hospitalbedsmanage.service;

import graduation.project.hospitalbedsmanage.entity.Beds;
import graduation.project.hospitalbedsmanage.entity.CaseHistory;
import graduation.project.hospitalbedsmanage.entity.Department;
import graduation.project.hospitalbedsmanage.entity.Patient;
import graduation.project.hospitalbedsmanage.mapper.BedsMapper;
import graduation.project.hospitalbedsmanage.mapper.CaseHistoryMapper;
import graduation.project.hospitalbedsmanage.mapper.PatientMapper;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@Slf4j
public class BedsService {
    @Autowired
    BedsMapper bedsMapper;
    @Autowired
    PatientMapper patientMapper;

    @Autowired
    private CaseHistoryMapper caseHistoryMapper;

    StringBuilder sb = null;

    /**
     * 自动分配床位
     *
     * @param deptNo
     */
    @Transactional
    public void autoModeBeds(int deptNo) {
        sb = new StringBuilder();
        List<Department> depts = bedsMapper.getBedsRooms(deptNo);
        for (Department dept : depts) {
            getBedsNumber(dept);
        }

        //删除多余的房间
        bedsMapper.deleteBeds(sb.append("0").toString());
    }

    /**
     * 获得每个病房的床位数并生成床位号
     *
     * @param dept
     */
    private void getBedsNumber(Department dept) {
        int totalBeds = dept.getTotalBeds();
        int rooms = dept.getRooms();
        rooms = rooms > 1 ? rooms : 1;
        int beds_avg = totalBeds / rooms;//每个病房的床位数
        int bdes_free = totalBeds % rooms;//余数
        //System.out.println(totalBeds+"除以"+rooms+"，商："+beds_avg+",余数："+bdes_free);
        int[][] bedNumber = new int[rooms][beds_avg];

        for (int i = 0; i < rooms; i++) {//先平均分配
            for (int j = 0; j < beds_avg; j++) {
                bedNumber[i][j] = 1;
            }
        }

        if (bdes_free < beds_avg) {//如果余数小于商
            for (int k = 0; k < 1; k++) {
                for (int m = 0; m < bdes_free; m++) {
                    bedNumber[k][m] += 1;
                }
            }
        } else {//否则
            for (int k = 0; k < bdes_free; k++) {
                for (int m = 0; m < 1; m++) {
                    bedNumber[k][m] += 1;
                }
            }
        }

        //先全部放到数组里面再遍历,可以取到第几个房间号
        for (int i = 0; i < bedNumber.length; i++) {
            int count = 0, roomNo = 0;
            for (int j = 0; j < bedNumber[i].length; j++) {
                count += bedNumber[i][j];
            }
            // System.out.println("病房" + (i + 1) + "：床位数" + count);
            roomNo = i + 1;
            String roomno = roomNo > 9 ? "" + dept.getID() + roomNo : dept.getID() + "0" + roomNo;
            getRoomBeds(dept.getID(), roomno, roomNo, count);
        }
    }

    /**
     * @param bedNo 床位编号
     * @param beds  床位数
     * @return
     */
    private void getRoomBeds(int deptNo, String bedNo, int roomNo, int beds) {
        JSONObject obj;
        for (int i = 1; i <= beds; i++) {
            Beds bed = new Beds(bedNo + i, deptNo, roomNo, bedNo + "号病房-" + i + "号床");
            List getbeds = getBed(bed);//先查询
            if (getbeds.size() < 1) {//如果不存在
                addBed(bed);//添加
                sb.append(bed.getBedNo()).append(",");//把deptNo添加到sb中
            } else {
                obj = JSONObject.fromObject(getbeds.get(0));
                sb.append(obj.getString("bedNo")).append(",");//
            }
        }
    }

    //查找病床
    @Transactional
    public Beds getBedsByRule(int deptNo, int level, int doctorID, int gender) {

        //查询本科室空闲的病床
        List<Beds> getFreeBeds = bedsMapper.getBedsByStatus(deptNo, 0);
        //查询本科室使用的病床
        List<Beds> getUsedBeds = bedsMapper.getBedsByStatus(deptNo, 1);

        //查询主治医师相同的患者
        List sameDoctorForPatient = patientMapper.getSameDoctorForPatient(doctorID);

        if (getFreeBeds.size() > 0) {
            return findBed(getFreeBeds, getUsedBeds, sameDoctorForPatient, gender);
        } else { //外借病床
            //查询其他室空闲的病床
            List<Beds> otherDeptFreeBeds = bedsMapper.getOtherDeptBedsByStatus(0);
            //查询其他室使用的病床
            List<Beds> otherDeptUsedBeds = bedsMapper.getOtherDeptBedsByStatus(1);
            return findBed(otherDeptFreeBeds, otherDeptUsedBeds, sameDoctorForPatient, gender);
        }
    }

    public synchronized Beds findBed(List<Beds> deptFreeBeds, List<Beds> deptUsedBeds, List sameDoctorForPatient, int gender) {
        JSONObject obj;
        int pid = 0;
        List<JSONObject> patients = new ArrayList<>();
        if (deptFreeBeds.size() > 0) {
            if (sameDoctorForPatient.size() == 0) {//如果没有主治医师相同
                List<Beds> unsameRoom = new ArrayList();
                List<Beds> sameRoom_unsameGender = new ArrayList();
                List<Beds> unsameRoom_sameGender = new ArrayList();
                for (Beds dub : deptUsedBeds) {
                    pid = dub.getPatientID();
                    if (pid == 0) continue;//病床信息数据错误
                    int roomNo = dub.getRoomNo();
                    List<Patient> patientInfo = patientMapper.getPatientInfo(new Patient(dub.getPatientID()));

                    if (patientInfo.size() == 0) {//患者信息数据错误
                        log.error("患者信息数据错误");
                        return null;
                    }
                    obj = JSONObject.fromObject(patientInfo.get(0));
                    patients.add(obj);

                    for (Beds fb : deptFreeBeds) {
                        if (fb.getRoomNo() != roomNo && gender == obj.getInt("gender")) {//不同房间\同性别
                            //同性别
                            return fb;
                        } else if (fb.getRoomNo() == roomNo && gender != obj.getInt("gender")) {//同房间\不同性别
                            sameRoom_unsameGender.add(fb);
                        } else if (fb.getRoomNo() == roomNo && gender == obj.getInt("gender")) {//同房间\同性别
                            unsameRoom_sameGender.add(fb);
                        } else {//不同房间不同性别
                            unsameRoom.add(fb);
                        }
                    }
                }

                if (unsameRoom.size() > 0) {
                    return unsameRoom.get(0);
                }
                if (unsameRoom_sameGender.size() > 0) {
                    return unsameRoom_sameGender.get(0);
                } else if (sameRoom_unsameGender.size() > 0) {
                    return sameRoom_unsameGender.get(0);
                }
            } else {

                List<Beds> unsameRoom = new ArrayList();
                List<Beds> sameRoom_unsameGender = new ArrayList();
                List<Beds> unsameRoom_sameGender = new ArrayList();

                //主治医生相同
                for (int i = 0; i < sameDoctorForPatient.size(); i++) {
                    obj = JSONObject.fromObject(sameDoctorForPatient.get(i));
                    for (Beds dub : deptUsedBeds) {
                        pid = dub.getPatientID();
                        if (pid == 0) continue;//病床信息数据错误

                        List<Patient> patientInfo = patientMapper.getPatientInfo(new Patient(dub.getPatientID()));
                        if (patientInfo.size() == 0) {//患者信息数据错误
                            log.error("患者信息数据错误");
                            return null;
                        }
                        obj = JSONObject.fromObject(patientInfo.get(0));
                        patients.add(obj);
                        int roomNo = dub.getRoomNo();

                        for (Beds dfb : deptFreeBeds) {
                            if (dfb.getRoomNo() == roomNo && gender == obj.getInt("gender")) {//同房间\同性别
                                //同性别
                                return dfb;
                            } else if (dfb.getRoomNo() == roomNo && gender != obj.getInt("gender")) {//同房间\不同性别
                                sameRoom_unsameGender.add(dfb);
                            } else if (dfb.getRoomNo() != roomNo && gender == obj.getInt("gender")) {//不同房间\同性别
                                unsameRoom_sameGender.add(dfb);
                            } else {//不同房间不同性别
                                unsameRoom.add(dfb);
                            }
                        }

                        if (unsameRoom.size() > 0) {
                            return unsameRoom.get(0);
                        }
                        if (unsameRoom_sameGender.size() > 0) {
                            return unsameRoom_sameGender.get(0);
                        } else if (sameRoom_unsameGender.size() > 0) {
                            return sameRoom_unsameGender.get(0);
                        }
                    }
                }
            }
            //如果病床都是空的
            return deptFreeBeds.get(0);
        }
        return null;
    }

    @Transactional
    public List getBeds(int deptNo) {
        return bedsMapper.getBeds(deptNo);
    }

    @Transactional
    public List getBed(Beds bed) {
        return bedsMapper.getBed(bed);
    }

    @Transactional
    public int updateBedStatus(Beds bed) {
        return bedsMapper.updateBedStatus(bed);
    }

    @Transactional
    public int addBed(Beds bed) {
        return bedsMapper.addBed(bed);
    }

    @Transactional
    public int deleteBedByBedNo(Beds bed) {
        return bedsMapper.deleteBedByBedNo(bed);
    }

    @Transactional
    public int patientUseBed(Patient patient, int status) {
        return bedsMapper.patientUseBed(patient, status);
    }
}
