package graduation.project.hospitalbedsmanage.controller;


import graduation.project.hospitalbedsmanage.entity.Patient;
import graduation.project.hospitalbedsmanage.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/patientinfo")
public class PatientController {
    @Autowired
    private PatientService patientservice;
    @RequestMapping(value="/patientsearch",method= RequestMethod.GET)
     public @ResponseBody List<Patient> getPatientInFo(String name, String deptNo, String bedNo, String in_hospital_date){

         List<Patient> list =new ArrayList<>();
         list = patientservice.getPatientInFo(name,deptNo,bedNo,in_hospital_date);
         return list;

     }







}
