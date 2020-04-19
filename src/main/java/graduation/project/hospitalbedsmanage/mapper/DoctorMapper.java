package graduation.project.hospitalbedsmanage.mapper;

import graduation.project.hospitalbedsmanage.entity.Doctor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorMapper {

    Doctor getUserInfoById(int id);

    Doctor getUserInfoByName(String name);

    Doctor login(Doctor doctor);

    int register(Doctor doctor);

    Doctor getUserInfo(Doctor doctor);
}
