package graduation.project.hospitalbedsmanage.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BigDataMapper {

    List getHospitalCount();

    List getPatientMonthCount();

    List getPatientDayCount(String time08, String time10, String time12, String time14, String time16, String time18,String time24,int deptNo);

    List getDeptNoByCount();
}
