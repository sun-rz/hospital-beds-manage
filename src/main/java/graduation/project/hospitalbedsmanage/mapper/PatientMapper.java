package graduation.project.hospitalbedsmanage.mapper;


import graduation.project.hospitalbedsmanage.entity.Patient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientMapper {


    List<Patient> getPatientInFo(String name,String deptNo,String bedNo,String in_hospital_date);

    List getPatientInfo(Patient patient);
}
