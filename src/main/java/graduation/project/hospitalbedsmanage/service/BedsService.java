package graduation.project.hospitalbedsmanage.service;

import graduation.project.hospitalbedsmanage.entity.Beds;
import graduation.project.hospitalbedsmanage.entity.Department;
import graduation.project.hospitalbedsmanage.entity.Patient;
import graduation.project.hospitalbedsmanage.mapper.BedsMapper;
import graduation.project.hospitalbedsmanage.mapper.PatientMapper;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class BedsService {
    @Autowired
    BedsMapper bedsMapper;
    @Autowired
    PatientMapper patientMapper;

    StringBuilder sb = null;

    /**
     * 自动分配床位
     *
     * @param deptNo
     */
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

    public Beds getBedsByRule(int deptNo, int level, int doctorID) {
        JSONObject obj;
        int docID = 0, roomNo = 0;
        //先查询本科室空闲的病床
        List<Beds> getFreeBeds = bedsMapper.getFreeBeds(deptNo);
        if (getFreeBeds.size() > 0) {

            //查询主治医师相同的患者
            List getInHospitalPatient = patientMapper.getInHospitalPatient(doctorID);
            System.out.println(getInHospitalPatient);
            if (getInHospitalPatient.size() == 0) {//如果没有主治医师相同的,就返回第一个病房
                return (Beds) getFreeBeds.get(0);
            }
            for (int i = 0; i < getInHospitalPatient.size(); i++) {
                obj = JSONObject.fromObject(getInHospitalPatient.get(i));
                if (obj.has("roomNo")) {
                    roomNo = obj.getInt("roomNo");

                    
                }

            }
            for (Beds b : getFreeBeds) {
                //System.out.println(b);
                //if(doctorID==b.)
            }


        } else {
            //外借
            Beds bed = borrowBed();
        }
        bedsMapper.getBedsByRule();
        return null;
    }

    //外借病床
    private Beds borrowBed() {

        return null;
    }


    public List getBeds(int deptNo) {
        return bedsMapper.getBeds(deptNo);
    }

    public List getBed(Beds bed) {
        return bedsMapper.getBed(bed);
    }

    public int updateBedStatus(Beds bed) {
        return bedsMapper.updateBedStatus(bed);
    }

    public int addBed(Beds bed) {
        return bedsMapper.addBed(bed);
    }

    public int deleteBedByBedNo(Beds bed) {
        return bedsMapper.deleteBedByBedNo(bed);
    }

}
