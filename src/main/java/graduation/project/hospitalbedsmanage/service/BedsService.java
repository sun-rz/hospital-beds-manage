package graduation.project.hospitalbedsmanage.service;

import graduation.project.hospitalbedsmanage.entity.Beds;
import graduation.project.hospitalbedsmanage.entity.Department;
import graduation.project.hospitalbedsmanage.mapper.BedsMapper;
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

    StringBuilder sb=null;

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

    public int deleteBed(Beds bed) {
        return bedsMapper.deleteBed(bed);
    }

    public int deleteBedByBedNo(Beds bed) {
        return bedsMapper.deleteBedByBedNo(bed);
    }

    /**
     * 自动分配床位
     *
     * @param deptNo
     */
    public void autoModeBeds(int deptNo) {
        sb=new StringBuilder();
        List<Department> depts = bedsMapper.getBedsRooms(deptNo);
        for (Department dept : depts) {
            getBedsNumber(dept);
        }

        //删除多余的房间
        bedsMapper.deleteBeds(sb.append("0").toString());
    }

    /**
     * 获得每个病房的床位数
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

        for (int i = 0; i < bedNumber.length; i++) {
            int count = 0;
            for (int j = 0; j < bedNumber[i].length; j++) {
                count += bedNumber[i][j];
            }
           // System.out.println("病房" + (i + 1) + "：床位数" + count);
            String roomno = i + 1 > 9 ? "" + dept.getID() + (i + 1) : dept.getID() + "0" + (i + 1);
            getRoomBeds(dept.getID(), roomno, count);
        }
    }

    /**
     * @param bedNo 床位编号
     * @param beds  床位数
     * @return
     */
    private void getRoomBeds(int deptNo, String bedNo, int beds) {
        JSONObject obj;
        for (int i = 1; i <= beds; i++) {
            Beds bed = new Beds(bedNo + i, deptNo, bedNo + "号病房-" + i + "号床");
            List getbeds = getBed(bed);//查询
            if (getbeds.size() < 1) {
                addBed(bed);//添加
                sb.append(bed.getBedNo()).append(",");
            }else{
                for (int m=0;m< getbeds.size();m++){
                   obj=JSONObject.fromObject(getbeds.get(m));
                    sb.append(obj.getString("bedNo")).append(",");
                }
            }
        }
    }
}
