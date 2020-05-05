package graduation.project.hospitalbedsmanage.service;

import graduation.project.hospitalbedsmanage.entity.CaseHistory;
import graduation.project.hospitalbedsmanage.mapper.CaseHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CaseHistoryService {
    @Autowired
    private CaseHistoryMapper caseHistoryMapper;

    @Transactional
    public List getCaseHistory(int patientID) {
        return caseHistoryMapper.getCaseHistory(patientID);
    }


   @Transactional
    public int addCaseHistory(CaseHistory caseHistory) {
        return caseHistoryMapper.addCaseHistory(caseHistory);
    }

    @Transactional
    public int updateCaseHistory(CaseHistory caseHistory) {
        return caseHistoryMapper.updateCaseHistory(caseHistory);
    }

    @Transactional
    public int deleteCaseHistory(String caseHistoryID) {
        return caseHistoryMapper.deleteCaseHistory(caseHistoryID);
    }
}
