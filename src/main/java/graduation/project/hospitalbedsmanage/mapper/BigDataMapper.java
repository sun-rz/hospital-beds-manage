package graduation.project.hospitalbedsmanage.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BigDataMapper {

    List getHospitalCount();
}
