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
        return patientMapper.getPatientInFo(name, deptNo, bedNo, in_hospital_date);
    }

    @Transactional
    public List getPatientInfo(Patient patient) {
        return patientMapper.getPatientInfo(patient);
    }

    @Transactional
    public int addPatientInfo(Patient patient) {
        return patientMapper.addPatientInfo(patient);
    }

    @Transactional
    public int updatePatientInfo(Patient patient) {
        return patientMapper.updatePatientInfo(patient);
    }

    @Transactional
    public int deletePatient(String patientID) {
        return patientMapper.deletePatient(patientID);
    }

    @Transactional
    public List<Patient> getLateOutHospital(int doctorID) {
        return patientMapper.getLateOutHospital(doctorID);
    }

    @Transactional
    public List getPatientInfoByBedNo(int bedNo) {
        return patientMapper.getPatientInfoByBedNo(bedNo);
    }

    public int updateOutHospitalDate(int patientID,String out_hospital_date,int patient_status) {
        return patientMapper.updateOutHospitalDate(patientID,out_hospital_date,patient_status);
    }
}
