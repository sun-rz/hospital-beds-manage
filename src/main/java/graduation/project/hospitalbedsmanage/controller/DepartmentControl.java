package graduation.project.hospitalbedsmanage.controller;

import graduation.project.hospitalbedsmanage.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/dept")
public class DepartmentControl {

    @Autowired
    private DepartmentService departmentService;

    @ResponseBody
    @RequestMapping("/getDeptInfoById")
    public String getDeptInfoById(String id){
        return departmentService.getDeptInfoById(id).toString();
    }
}
