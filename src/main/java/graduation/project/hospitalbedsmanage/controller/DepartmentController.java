package graduation.project.hospitalbedsmanage.controller;

import graduation.project.hospitalbedsmanage.entity.Department;
import graduation.project.hospitalbedsmanage.service.DepartmentService;
import graduation.project.hospitalbedsmanage.util.CommonTools;
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

    /**
     * 获得所有部门信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getDeptInfo")
    public String getDeptInfo() {
        List deptInfo = departmentService.getDeptInfo();
        JSONObject obj = new JSONObject();
        obj.put("deptList", JSONArray.fromObject(deptInfo));
        return obj.toString();
    }

    /**
     * 根据id获得部门信息
     *
     * @param deptNo
     * @return
     */
    @ResponseBody
    @RequestMapping("/getDeptInfoById")
    public String getDeptInfoById(String deptNo) {
        Department dept = departmentService.getDeptInfoById(deptNo);
        JSONObject obj = new JSONObject();
        obj.put("dept", JSONObject.fromObject(dept));
        return obj.toString();
    }

    /**
     * 新增部门
     *
     * @param dept
     * @return
     */
    @ResponseBody
    @RequestMapping("/addDeptInfo")
    public String addDeptInfo(Department dept) {
        Department deptInfoByName = departmentService.getDeptInfoByName(dept);
        if (null != deptInfoByName) {
            return CommonTools.getReturnMsg("名称已存在", true);
        }
        int result = departmentService.addDeptInfo(dept);
        if (result > 0) {
            return CommonTools.getReturnMsg("新增成功", true);
        }
        return CommonTools.getReturnMsg("新增失败", false);
    }

    /**
     * 编辑部门
     *
     * @param dept
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateDeptInfo")
    public String updateDeptInfo(Department dept) {
        int result = departmentService.updateDeptInfo(dept);
        if (result > 0) {
            return CommonTools.getReturnMsg("修改成功", true);
        }
        return CommonTools.getReturnMsg("修改失败", false);
    }

    /**
     * 删除部门
     *
     * @param deptNo
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteDept")
    public String deleteDept(String deptNo) {
        int result = departmentService.deleteDept(deptNo);
        if (result > 0) {
            return CommonTools.getReturnMsg("删除成功", true);
        }
        return CommonTools.getReturnMsg("删除失败", false);
    }

    /**
     * 获取职务信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getJobInfo")
    public String getJobInfo() {
        List jobInfo = departmentService.getJobInfo();
        JSONObject obj = new JSONObject();
        obj.put("jobInfo", JSONArray.fromObject(jobInfo));
        return obj.toString();
    }

}
