package graduation.project.hospitalbedsmanage.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import graduation.project.hospitalbedsmanage.entity.Beds;
import graduation.project.hospitalbedsmanage.entity.Patient;
import graduation.project.hospitalbedsmanage.service.BedsService;
import graduation.project.hospitalbedsmanage.service.HospitalizedService;
import graduation.project.hospitalbedsmanage.service.PatientService;
import graduation.project.hospitalbedsmanage.util.CommonTools;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/hospitalized")
public class HospitalizedController {
    @Autowired
    HospitalizedService hospitalizedService;

    @Autowired
    PatientService patientService;

    @Autowired
    BedsService bedsService;

    /**
     * 获取病床及患者信息
     *
     * @param deptNo
     * @return
     */
    @ResponseBody
    @RequestMapping("/getHospitalizedPaient")
    public String getBeds(String deptNo) {
        List paient = hospitalizedService.getHospitalizedPaient(CommonTools.ToInt(deptNo));
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        JSONObject obj = new JSONObject();
        obj.put("paientList", JSONArray.fromObject(gson.toJson(paient)));
        return obj.toString();
    }

    /**
     * 出院管理
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/outHospital")
    public String outHospital(String bedNo, String patientID, String out_hospital_date) {
        //更新患者出院日期
        int result = patientService.updateOutHospitalDate(CommonTools.ToInt(patientID), out_hospital_date);
        if (result > 0) {
            //更新病房状态
            result = bedsService.updateBedStatus(new Beds(bedNo, 0));
            if (result > 0) {
                return CommonTools.getReturnMsg("操作成功", true);
            }
        }
        return CommonTools.getReturnMsg("操作失败", false);
    }
}
