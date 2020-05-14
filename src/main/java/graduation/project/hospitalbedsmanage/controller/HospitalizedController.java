package graduation.project.hospitalbedsmanage.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import graduation.project.hospitalbedsmanage.service.HospitalizedService;
import graduation.project.hospitalbedsmanage.util.CommonTools;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/hospitalized")
public class HospitalizedController {
    @Autowired
    HospitalizedService hospitalizedService;

    @ResponseBody
    @RequestMapping("/getHospitalizedPaient")
    public String getBeds(String deptNo) {
        List paient = hospitalizedService.getHospitalizedPaient(CommonTools.ToInt(deptNo));
        System.out.println(paient);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        JSONObject obj = new JSONObject();
        obj.put("paientList", JSONArray.fromObject(gson.toJson(paient)));
        return obj.toString();
    }
}
