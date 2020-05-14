package graduation.project.hospitalbedsmanage.service;

import graduation.project.hospitalbedsmanage.mapper.HospitalizedMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HospitalizedService {
    @Autowired
    HospitalizedMapper hospitalizedMapper;

    public List getHospitalizedPaient(int deptNo) {
        return hospitalizedMapper.getHospitalizedPaient(deptNo);
    }
}
