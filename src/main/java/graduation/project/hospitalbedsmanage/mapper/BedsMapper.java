package graduation.project.hospitalbedsmanage.mapper;

import graduation.project.hospitalbedsmanage.entity.Beds;
import graduation.project.hospitalbedsmanage.entity.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BedsMapper {
    List<Department> getBedsRooms(int deptNo);

    int addBed(Beds bed);

    List getBed(Beds bed);

    int deleteBed(Beds bed);

    int deleteBedByBedNo(Beds bed);

    int deleteBeds(String bedNo);

    List getBeds(int deptNo);

    int updateBedStatus(Beds bed);

}
