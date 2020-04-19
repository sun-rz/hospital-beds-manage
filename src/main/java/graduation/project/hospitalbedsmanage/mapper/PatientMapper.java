package graduation.project.hospitalbedsmanage.mapper;


import graduation.project.hospitalbedsmanage.entity.Patient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientMapper {


    List<Patient> getPatientInFo(String name,int deptNo,int bedNo,String in_hospital_date);

}
