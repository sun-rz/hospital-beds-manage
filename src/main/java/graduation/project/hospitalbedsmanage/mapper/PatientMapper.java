package graduation.project.hospitalbedsmanage.mapper;


import graduation.project.hospitalbedsmanage.entity.Patient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientMapper {


    List<Patient> getPatientInFo(String name, String deptNo, String bedNo, String in_hospital_date);

    List getPatientInfo(Patient patient);

    int addPatientInfo(Patient patient);

    int updatePatientInfo(Patient patient);

    int deletePatient(String patientID);

    List getSameDoctorForPatient(int doctorID);

    List<Patient> getLateOutHospital(int doctorID);

    List getPatientInfoByBedNo(int bedNo);

    int updateOutHospitalDate(int patientID, String out_hospital_date,int patient_status);
}
