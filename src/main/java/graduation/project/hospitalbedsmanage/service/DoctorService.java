package graduation.project.hospitalbedsmanage.service;

import graduation.project.hospitalbedsmanage.entity.Doctor;
import graduation.project.hospitalbedsmanage.mapper.DoctorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
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
}
