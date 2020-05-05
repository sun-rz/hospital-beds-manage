package graduation.project.hospitalbedsmanage.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import graduation.project.hospitalbedsmanage.service.CaseHistoryService;
import graduation.project.hospitalbedsmanage.util.CommonTools;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;

@Controller
@RequestMapping("/casehistory")
public class CaseHistoryController {

    @Autowired
    private CaseHistoryService caseHistoryService;

    /**
     * 获得病历信息
     * @param patientID 患者编号
     * @return
     */
    @ResponseBody
    @RequestMapping("/getCaseHistory")
    public String getCaseHistory(String patientID){

        List list=caseHistoryService.getCaseHistory(CommonTools.ToInt(patientID));
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        JSONObject obj = new JSONObject();
        obj.put("casehistoryList", JSONArray.fromObject(gson.toJson(list)));
        return obj.toString();
    }

    /**
     * 删除病历信息
     * @param caseHistoryID
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteCaseHistory")
    public String deleteCaseHistory(String caseHistoryID){
        if (CommonTools.isBlank(caseHistoryID)) {
            return CommonTools.getReturnMsg("参数错误", false);
        }
        caseHistoryID=caseHistoryID.replace("[","(").replace("]",")");
        int result=caseHistoryService.deleteCaseHistory(caseHistoryID);
        if (result > 0) {
            return CommonTools.getReturnMsg("删除成功", true);
        }
        return CommonTools.getReturnMsg("删除失败", false);
    }
}
