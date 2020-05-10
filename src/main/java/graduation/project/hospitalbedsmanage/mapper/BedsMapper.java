package graduation.project.hospitalbedsmanage.mapper;

import graduation.project.hospitalbedsmanage.entity.Beds;
import graduation.project.hospitalbedsmanage.entity.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BedsMapper {
    List<Department> getBedsRooms(int deptNo);

    int addBed(Beds bed);

    List<Beds> getBed(Beds bed);

    int deleteBed(Beds bed);

    List getBeds(int deptNo);
}
