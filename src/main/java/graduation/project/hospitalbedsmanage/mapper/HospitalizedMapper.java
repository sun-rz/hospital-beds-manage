package graduation.project.hospitalbedsmanage.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalizedMapper {
    List getHospitalizedPaient(int deptNo);
}
