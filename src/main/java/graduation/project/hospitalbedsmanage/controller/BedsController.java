package graduation.project.hospitalbedsmanage.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import graduation.project.hospitalbedsmanage.entity.Beds;
import graduation.project.hospitalbedsmanage.entity.Patient;
import graduation.project.hospitalbedsmanage.service.BedsService;
import graduation.project.hospitalbedsmanage.service.DepartmentService;
import graduation.project.hospitalbedsmanage.service.PatientService;
import graduation.project.hospitalbedsmanage.util.CommonTools;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/beds")
public class BedsController {

    @Autowired
    BedsService bedsService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    PatientService patientService;

    /**
     * 自动分配病房
     *
     * @param deptNo
     * @return
     */
    @ResponseBody
    @RequestMapping("/autoModeBeds")
    public String autoModeBeds(String deptNo) {
        bedsService.autoModeBeds(0);
        return CommonTools.getReturnMsg("ok", true);
    }

    /**
     * 根据算法查找病床
     * @param deptNo
     * @param level
     * @param doctorID
     * @return
     */
    @ResponseBody
    @RequestMapping("/getBedsByRule")
    public String getBedsByRule(String deptNo,String level,String doctorID) {
        JSONObject obj=new JSONObject();

        Beds bed = bedsService.getBedsByRule(CommonTools.ToInt(deptNo),CommonTools.ToInt(level),CommonTools.ToInt(doctorID));
        System.out.println(bed);
        if(null!=bed){
            obj.put("success",true);
            obj.put("bed",bed);
            return obj.toString();
        }else{
            //没有找到病床怎么办?排队？人命关天啊！建议换一家医院

            // 返回10个最近要出院的患者的时间信息
           List<Patient> patients= patientService.getLateOutHospital(CommonTools.ToInt(doctorID));
           obj.put("success",false);
           obj.put("patientList",patients);
            return obj.toString();
        }
    }

    /**
     * 获取单个病床信息
     *
     * @param bed
     * @return
     */
    @ResponseBody
    @RequestMapping("/getBed")
    public String getBed(Beds bed) {
        List beds = bedsService.getBed(bed);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        JSONObject obj = new JSONObject();
        obj.put("bed", JSONArray.fromObject(gson.toJson(beds)));
        return obj.toString();
    }

    /**
     * 获取病床列表
     *
     * @param deptNo
     * @return
     */
    @ResponseBody
    @RequestMapping("/getBeds")
    public String getBeds(String deptNo) {
        List beds = bedsService.getBeds(CommonTools.ToInt(deptNo));
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        JSONObject obj = new JSONObject();
        obj.put("bedList", JSONArray.fromObject(gson.toJson(beds)));
        return obj.toString();
    }

    /**
     * 更新病床状态
     * @param bed
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateBedStatus")
    public String updateBedStatus(Beds bed) {
        int result = bedsService.updateBedStatus(bed);
        if (result > 0) {
            return CommonTools.getReturnMsg("修改成功", true);
        }
        return CommonTools.getReturnMsg("修改失败", false);
    }

    @ResponseBody
    @RequestMapping("/deleteBedByBedNo")
    public String deleteBedByBedNo(Beds bed) {
        int result = bedsService.deleteBedByBedNo(bed);
        if (result > 0) {
            departmentService.updateDeptBeds(bed.getDeptNo());
            return CommonTools.getReturnMsg("删除成功", true);
        }
        return CommonTools.getReturnMsg("删除失败", false);
    }
}
