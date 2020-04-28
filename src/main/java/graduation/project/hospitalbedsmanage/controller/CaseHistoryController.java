package graduation.project.hospitalbedsmanage.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import graduation.project.hospitalbedsmanage.service.CaseHistoryService;
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

    @ResponseBody
    @RequestMapping("/getCaseHistory")
    public String getCaseHistory(){
        List list=caseHistoryService.getCaseHistory();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
        JSONObject obj = new JSONObject();
        obj.put("casehistoryList", JSONArray.fromObject(gson.toJson(list)));
        return obj.toString();
    }
}
