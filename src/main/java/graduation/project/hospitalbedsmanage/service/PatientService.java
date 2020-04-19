package graduation.project.hospitalbedsmanage.service;

import graduation.project.hospitalbedsmanage.entity.Patient;
import graduation.project.hospitalbedsmanage.mapper.PatientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PatientService {

    @Autowired
    PatientMapper patientMapper;

    @Transactional
    public List<Patient> getPatientInFo(String name, String deptNo, String bedNo, String in_hospital_date) {
        return patientMapper.getPatientInFo(name,deptNo,bedNo,in_hospital_date);
    }


}
