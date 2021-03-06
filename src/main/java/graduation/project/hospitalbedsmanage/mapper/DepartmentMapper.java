package graduation.project.hospitalbedsmanage.mapper;

import graduation.project.hospitalbedsmanage.entity.Department;
import graduation.project.hospitalbedsmanage.entity.Doctor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DepartmentMapper {
    List<Map<String, Doctor>> getDeptInfo();

    Department getDeptInfoById(String id);

    List getJobInfo();

    int deleteDept(String deptNo);

    int updateDeptInfo(Department dept);

    int addDeptInfo(Department dept);

    Department getDeptInfoByName(Department dept);

    List getDeptInfoByCondition(String condition);

    int updateDeptBeds(int deptNo);
}
