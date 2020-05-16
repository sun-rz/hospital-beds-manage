package graduation.project.hospitalbedsmanage.controller;

import graduation.project.hospitalbedsmanage.mapper.BigDataMapper;
import graduation.project.hospitalbedsmanage.service.BigDataService;
import graduation.project.hospitalbedsmanage.util.CommonTools;
import javafx.scene.input.DataFormat;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 大数据查询
 */
@Controller
@RequestMapping("/bigData")
public class BigDataController {
    @Autowired
    BigDataService bigDataService;

    @Autowired
    BigDataMapper bigDataMapper;

    @ResponseBody
    @RequestMapping(value = "/getHospitalCount", method = RequestMethod.GET)
    public String getHospitalCount() {
        //查询人数统计
        List<Integer> list = bigDataService.getHospitalCount();
        //按月份查询
        List<Map<String,String>> month = bigDataService.getPatientMonthCount();
        Map<String,Integer> map=bigDataService.get_PatientMonthCount(month);

        String now = CommonTools.parseDate(new Date(), "yyyy-MM-dd");
        //按天统计查询
        //'8:00','10:00','12:00','14:00','16:00','18:00，24：00'
        List<HashMap> getDeptNoByCount = bigDataMapper.getDeptNoByCount();
        List day=bigDataService.getPatientDayCount(now+" 8:00:00",now+" 10:00:00",now+" 12:00:00",now+" 14:00:00",now+" 16:00:00",now+" 18:00:00",now+" 24:00:00",getDeptNoByCount);
        JSONObject obj = new JSONObject();
        obj.put("getHospitalCount", JSONArray.fromObject(list));
        obj.put("getPatientMonthCount", JSONObject.fromObject(map));
        obj.put("getPatientDayCount", JSONArray.fromObject(day));
        obj.put("getDeptNoByCount", JSONArray.fromObject(getDeptNoByCount));
        return obj.toString();
    }

}
