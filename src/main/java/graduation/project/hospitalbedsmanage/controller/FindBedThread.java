package graduation.project.hospitalbedsmanage.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import graduation.project.hospitalbedsmanage.entity.Beds;
import graduation.project.hospitalbedsmanage.entity.Patient;
import graduation.project.hospitalbedsmanage.service.BedsService;
import graduation.project.hospitalbedsmanage.service.PatientService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Future;

@Component
public class FindBedThread {
    @Autowired
    BedsService bedsService;

    @Autowired
    PatientService patientService;

    @Async
    public Future<JSONObject> getBedsByRule(int deptNo, int level, int doctorID,int gender) {
        JSONObject obj = new JSONObject();

        Beds bed = bedsService.getBedsByRule(deptNo, level, doctorID,gender);
        if (null != bed) {
            obj.put("success", true);
            obj.put("msg", "床位分配成功");
            obj.put("bed", bed);
            return new AsyncResult<>(obj);
        } else {
            //没有找到病床怎么办?排队？人命关天啊！建议换一家医院

            // 返回最近要出院的患者的时间信息
            List<Patient> patients = patientService.getLateOutHospital(doctorID);
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            obj.put("success", false);
            obj.put("msg", "床位分配失败，原因：无空闲病床");
            obj.put("patientList", JSONArray.fromObject(gson.toJson(patients)));
            return new AsyncResult<>(obj);
        }
    }
}
