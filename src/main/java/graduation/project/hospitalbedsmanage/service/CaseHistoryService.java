package graduation.project.hospitalbedsmanage.service;

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
    public List getCaseHistory() {
        return caseHistoryMapper.getCaseHistory();
    }
}
