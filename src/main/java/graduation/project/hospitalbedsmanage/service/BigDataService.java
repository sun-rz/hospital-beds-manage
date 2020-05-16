package graduation.project.hospitalbedsmanage.service;

import graduation.project.hospitalbedsmanage.mapper.BigDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BigDataService {
    @Autowired
    BigDataMapper bigDataMapper;

    public List getHospitalCount() {
        return bigDataMapper.getHospitalCount();
    }
}
