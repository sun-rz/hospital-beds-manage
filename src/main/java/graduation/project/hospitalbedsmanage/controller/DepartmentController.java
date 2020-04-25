package graduation.project.hospitalbedsmanage.controller;

import graduation.project.hospitalbedsmanage.entity.Department;
import graduation.project.hospitalbedsmanage.service.DepartmentService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/dept")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @ResponseBody
    @RequestMapping("/getDeptInfo")
    public String getDeptInfo() {
        List deptInfo = departmentService.getDeptInfo();
        JSONObject obj = new JSONObject();
        obj.put("deptList", JSONArray.fromObject(deptInfo));
        return obj.toString();
    }

    @ResponseBody
    @RequestMapping("/getDeptInfoById")
    public String getDeptInfoById(String id) {
        return departmentService.getDeptInfoById(id).toString();
    }

    @ResponseBody
    @RequestMapping("/getJobInfo")
    public String getJobInfo() {
        List jobInfo = departmentService.getJobInfo();
        JSONObject obj=new JSONObject();
        obj.put("jobInfo",JSONArray.fromObject(jobInfo));
        return obj.toString();
    }

}
