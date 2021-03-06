package graduation.project.hospitalbedsmanage.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import graduation.project.hospitalbedsmanage.entity.CaseHistory;
import graduation.project.hospitalbedsmanage.entity.Patient;
import graduation.project.hospitalbedsmanage.service.BedsService;
import graduation.project.hospitalbedsmanage.service.CaseHistoryService;
import graduation.project.hospitalbedsmanage.service.PatientService;
import graduation.project.hospitalbedsmanage.util.CommonTools;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientService patientservice;

    @Autowired
    private CaseHistoryService caseHistoryService;

    @Autowired
    private BedsService bedsService;

    /**
     * 获得患者信息
     *
     * @param patient
     * @return
     */
    @ResponseBody
    @RequestMapping("/getPatientInfo")
    public String getPatientInfo(Patient patient) {
        List list = patientservice.getPatientInfo(patient);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        JSONObject obj = new JSONObject();
        obj.put("patientList", JSONArray.fromObject(gson.toJson(list)));
        return obj.toString();
    }


    /**
     * 搜索患者信息
     *
     * @param keywords
     * @return
     */
    @ResponseBody
    @RequestMapping("/searchPatientInfo")
    public String searchPatientInfo(String keywords) {
        List list = patientservice.searchPatientInfo(keywords);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        JSONObject obj = new JSONObject();
        obj.put("patientList", JSONArray.fromObject(gson.toJson(list)));
        return obj.toString();
    }

    /**
     * 根据床位查找患者
     *
     * @param bedNo
     * @return
     */
    @ResponseBody
    @RequestMapping("/getPatientInfoByBedNo")
    public String getPatientInfoByBedNo(String bedNo) {
        List list = patientservice.getPatientInfoByBedNo(CommonTools.ToInt(bedNo));
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        JSONObject obj = new JSONObject();
        obj.put("patient", JSONArray.fromObject(gson.toJson(list)));
        return obj.toString();
    }


    /**
     * 添加患者信息
     *
     * @param patient
     * @param casehistory
     * @return
     */
    @ResponseBody
    @RequestMapping("/addPatientInfo")
    public String addPatientInfo(Patient patient, String casehistory) {
        String getPatientID=patientservice.getPatientID(patient.getBedNo());
        int pid = CommonTools.ToInt(getPatientID);
        if(pid>0){
            return CommonTools.getReturnMsg("床位已被占用，新增失败", false);
        }
        int result = patientservice.addPatientInfo(patient);

        JSONObject object = JSONObject.fromObject(casehistory);
        if (result > 0) {
            //更新病床状态
            bedsService.patientUseBed(patient, 1);
            getPatientID=patientservice.getPatientID(patient.getBedNo());
            pid = CommonTools.ToInt(getPatientID);
            CaseHistory caseHistory = new CaseHistory(pid, patient.getDeptNo(), object.getInt("doctorID"), object.getInt("status"), object.getString("description"), object.getString("treatmentPlan"), new Date());
            result = caseHistoryService.addCaseHistory(caseHistory);
            if (result > 0) {

                return CommonTools.getReturnMsg("新增成功", true);
            }
            return CommonTools.getReturnMsg("新增病历信息失败", false);
        }
        return CommonTools.getReturnMsg("新增患者信息失败", false);
    }

    /**
     * 修改患者信息
     *
     * @param patient
     * @param casehistory
     * @return
     */
    @ResponseBody
    @RequestMapping("/updatePatientInfo")
    public String updatePatientInfo(Patient patient, String casehistory) {
        String getPatientID=patientservice.getPatientID(patient.getBedNo());
        int pid = CommonTools.ToInt(getPatientID);
        if(pid>0&&pid!=patient.getId()){
            return CommonTools.getReturnMsg("床位已被占用，修改失败", false);
        }
        System.out.println(pid);
        int result = patientservice.updatePatientInfo(patient);
        JSONObject object = JSONObject.fromObject(casehistory);

        if (result > 0) {
            CaseHistory caseHistory;
            if (object.has("id")) {
                //修改
                caseHistory = new CaseHistory(object.getInt("id"), patient.getId(), patient.getDeptNo(), object.getInt("doctorID"), object.getInt("status"), object.getString("description"), object.getString("treatmentPlan"), new Date());
                result = caseHistoryService.updateCaseHistory(caseHistory);
            } else {
                //病历被删除了,新增
                caseHistory = new CaseHistory(patient.getId(), patient.getDeptNo(), object.getInt("doctorID"), object.getInt("status"), object.getString("description"), object.getString("treatmentPlan"), new Date());
                result = caseHistoryService.addCaseHistory(caseHistory);
            }
            if (result > 0) {
                //更新病床状态
                bedsService.patientUseBed(patient, 1);
                return CommonTools.getReturnMsg("修改成功", true);
            }
            return CommonTools.getReturnMsg("修改病历信息失败", false);
        }
        return CommonTools.getReturnMsg("修改失败", false);
    }

    /**
     * 删除患者信息
     *
     * @param patientID
     * @return
     */
    @ResponseBody
    @RequestMapping("/deletePatient")
    public String deletePatient(String patientID) {
        if (CommonTools.isBlank(patientID)) {
            return CommonTools.getReturnMsg("参数错误", false);
        }
        //patientID = patientID.substring(1, patientID.length() - 1);
        patientID = patientID.replace("[", "(").replace("]", ")");
        int result = patientservice.deletePatient(patientID);
        if (result > 0) {
            return CommonTools.getReturnMsg("删除成功", true);
        }
        return CommonTools.getReturnMsg("删除失败", false);
    }

}
