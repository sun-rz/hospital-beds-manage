package graduation.project.hospitalbedsmanage.service;

import graduation.project.hospitalbedsmanage.entity.Doctor;
import graduation.project.hospitalbedsmanage.mapper.DoctorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DoctorService {
    @Autowired
    DoctorMapper doctorMapper;

    @Transactional
    public Doctor getUserInfoById(int id) {
        return doctorMapper.getUserInfoById(id);
    }

    @Transactional
    public Doctor login(Doctor doctor) {
        return doctorMapper.login(doctor);
    }

    @Transactional
    public int register(Doctor doctor) {
        return doctorMapper.register(doctor);
    }

    @Transactional
    public Doctor getUserInfo(Doctor doctor) {
        return doctorMapper.getUserInfo(doctor);
    }

    @Transactional
    public int updateUserInfo(Doctor doctor) {
        return doctorMapper.updateUserInfo(doctor);
    }

    @Transactional
    public List<Map<String, Doctor>> getUserInfoByLoginName(String loginName,int userId) {
        return doctorMapper.getUserInfoByLoginName(loginName,userId);
    }

    @Transactional
    public Doctor getUserInfoByPassword(String password, int id) {
        return doctorMapper.getUserInfoByPassword(password, id);
    }

    @Transactional
    public int updatePassword(String password, int id) {
        return doctorMapper.updatePassword(password, id);
    }

    @Transactional
    public List<Map<String, Doctor>> getDoctorByDeptNo(int deptNo) {
        return doctorMapper.getDoctorByDeptNo(deptNo);
    }

    @Transactional
    public int deleteUserInfo(String userID) {
        return doctorMapper.deleteUserInfo(userID);
    }

    public List<Map<String, Doctor>> searchDoctor(int deptNo, String keywords) {
        return doctorMapper.searchDoctor(deptNo, keywords);
    }
}
