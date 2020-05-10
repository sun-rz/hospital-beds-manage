package graduation.project.hospitalbedsmanage.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import graduation.project.hospitalbedsmanage.entity.Beds;
import graduation.project.hospitalbedsmanage.service.BedsService;
import graduation.project.hospitalbedsmanage.util.CommonTools;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/beds")
public class BedsController {

    @Autowired
    BedsService bedsService;
    /**
     * 自动分配病房
     * @param deptNo
     * @return
     */
    @ResponseBody
    @RequestMapping("/autoModeBeds")
    public String autoModeBeds(String deptNo){
        bedsService.autoModeBeds(0);
        return CommonTools.getReturnMsg("ok",true);
    }

    @ResponseBody
    @RequestMapping("/getBeds")
    public String getBeds(String deptNo){
        List beds=bedsService.getBeds(CommonTools.ToInt(deptNo));
        System.out.println(beds);
        JSONObject obj = new JSONObject();
        obj.put("bedList", JSONArray.fromObject(beds));
        return obj.toString();
    }
}
