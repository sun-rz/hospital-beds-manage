package graduation.project.hospitalbedsmanage.controller;

import graduation.project.hospitalbedsmanage.service.BigDataService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 大数据查询
 */
@Controller
@RequestMapping("/bigData")
public class BigDataController {
    @Autowired
    BigDataService bigDataService;

    @ResponseBody
    @RequestMapping(value = "/getHospitalCount", method = RequestMethod.GET)
    public String getHospitalCount() {
        //更新患者出院日期
        List<Integer> list = bigDataService.getHospitalCount();
        System.out.println(list);
        JSONObject obj = new JSONObject();
        obj.put("getHospitalCount", list);
        return obj.toString();
    }

}
