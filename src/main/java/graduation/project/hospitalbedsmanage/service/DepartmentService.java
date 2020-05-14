package graduation.project.hospitalbedsmanage.service;

import graduation.project.hospitalbedsmanage.entity.Department;
import graduation.project.hospitalbedsmanage.entity.Doctor;
import graduation.project.hospitalbedsmanage.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Transactional
    public List<Map<String, Doctor>> getDeptInfo() {
        return departmentMapper.getDeptInfo();
    }

    @Transactional
    public Department getDeptInfoById(String id) {
        return departmentMapper.getDeptInfoById(id);
    }

    @Transactional
    public List getJobInfo() {
        return departmentMapper.getJobInfo();
    }

    @Transactional
    public int deleteDept(String deptNo) {
        return departmentMapper.deleteDept(deptNo);
    }

    @Transactional
    public int updateDeptInfo(Department dept) {
        return departmentMapper.updateDeptInfo(dept);
    }

    @Transactional
    public int addDeptInfo(Department dept) {
        return departmentMapper.addDeptInfo(dept);
    }

    @Transactional
    public Department getDeptInfoByName(Department dept) {
        return departmentMapper.getDeptInfoByName(dept);
    }

    @Transactional
    public List getDeptInfoByCondition(String condition) {
        return departmentMapper.getDeptInfoByCondition(condition);
    }

    @Transactional
    public int updateDeptBeds(int deptNo) {
        return departmentMapper.updateDeptBeds(deptNo);
    }
}
