package graduation.project.hospitalbedsmanage.mapper;

import graduation.project.hospitalbedsmanage.entity.Doctor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DoctorMapper {

    Doctor getUserInfoById(int id);

    Doctor login(Doctor doctor);

    int register(Doctor doctor);

    Doctor getUserInfo(Doctor doctor);

    int updateUserInfo(Doctor doctor);

    List<Map<String,Doctor>> getUserInfoByLoginName(Doctor doctor);

    Doctor getUserInfoByPassword(String password,int id);

    int updatePassword(String password, int id);

    List<Map<String,Doctor>> getDoctorByDeptNo(int deptNo);
}
