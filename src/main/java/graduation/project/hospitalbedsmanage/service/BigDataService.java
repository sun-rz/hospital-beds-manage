package graduation.project.hospitalbedsmanage.service;

import com.google.gson.Gson;
import graduation.project.hospitalbedsmanage.mapper.BigDataMapper;
import graduation.project.hospitalbedsmanage.util.CommonTools;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BigDataService {
    @Autowired
    BigDataMapper bigDataMapper;

    public List getHospitalCount() {
        return bigDataMapper.getHospitalCount();
    }

    public List getPatientMonthCount() {
        return bigDataMapper.getPatientMonthCount();
    }

    public Map<String, Integer> get_PatientMonthCount(List<Map<String, String>> month) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        String submitTime = "";
        for (Map<String, String> m : month) {
            submitTime = m.get("submitTime");
            if (null == map.get(submitTime)) {
                map.put(submitTime, 1);
            } else {
                map.put(submitTime, map.get(submitTime) + 1);
            }
        }
        return map;
    }

    public List getPatientDayCount(String time08, String time10, String time12, String time14, String time16, String time18, String time24,List<HashMap> list_dept) {
        List list_count;
        List list=new ArrayList();
        JSONObject obj = null;
        String deptNo = "", deptName = "";

        for (HashMap map : list_dept) {
            obj= new JSONObject();
            deptNo = map.get("deptNo") + "";
            deptName = map.get("deptName") + "";
            list_count = bigDataMapper.getPatientDayCount(time08, time10, time12, time14, time16, time18, time24, CommonTools.ToInt(deptNo));

            obj.put("name", deptName);
            obj.put("counts",list_count);
        }
        list.add(obj);
        return list;
    }
}
