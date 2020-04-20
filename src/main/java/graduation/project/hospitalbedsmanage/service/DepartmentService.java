package graduation.project.hospitalbedsmanage.service;

import graduation.project.hospitalbedsmanage.entity.Department;
import graduation.project.hospitalbedsmanage.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    public Department getDeptInfoById(String id) {
        return departmentMapper.getDeptInfoById(id);
    }
}
