package graduation.project.hospitalbedsmanage.mapper;

import graduation.project.hospitalbedsmanage.entity.Department;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentMapper {
    Department getDeptInfoById(String id);
}
