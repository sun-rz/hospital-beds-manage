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
    public String getDeptInfo(){
        List deptInfo = departmentService.getDeptInfo();
        JSONArray jsonArray = JSONArray.fromObject(deptInfo);
        System.out.println(jsonArray);
        JSONObject obj = new JSONObject();
        obj.put("deptList",jsonArray);
        return obj.toString();
    }

    @ResponseBody
    @RequestMapping("/getDeptInfoById")
    public String getDeptInfoById(String id){
        return departmentService.getDeptInfoById(id).toString();
    }
}
