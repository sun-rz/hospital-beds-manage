package graduation.project.hospitalbedsmanage.mapper;

import graduation.project.hospitalbedsmanage.entity.CaseHistory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaseHistoryMapper {
    List getCaseHistory(int patientID);

    int deleteCaseHistory(String caseHistoryID);

    int addCaseHistory(CaseHistory caseHistory);

    int updateCaseHistory(CaseHistory caseHistory);

    List searchCasehistory(String keywords);
}
