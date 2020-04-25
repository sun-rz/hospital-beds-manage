package graduation.project.hospitalbedsmanage.controller;

import graduation.project.hospitalbedsmanage.entity.Doctor;
import graduation.project.hospitalbedsmanage.service.DoctorService;
import graduation.project.hospitalbedsmanage.util.CommonTools;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/user")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;


    //登录操作
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Doctor doctor, HttpServletRequest request, HttpServletResponse response) {

        String captcha_code = (String) request.getSession().getAttribute("captcha_code");
        String captchacode = request.getParameter("captchacode");
        String action = request.getParameter("action");

        if (CommonTools.isBlank(captchacode) || !(captchacode.equalsIgnoreCase(captcha_code))) {
            return CommonTools.getReturnMsg("验证码错误", false);
        }
        if (CommonTools.isBlank(doctor.getName())) {
            return CommonTools.getReturnMsg("用户名不能为空", false);
        }

        if (CommonTools.isBlank(doctor.getPassword())) {
            return CommonTools.getReturnMsg("密码不能为空", false);
        }

        if ("login".equals(action)) {
            //获得MD5密码并赋值
            doctor.setPassword(CommonTools.getMD5Encode(doctor.getPassword()));
            //不知道是用户手机号还是邮箱登录,先记住登录名
            String loginName = doctor.getName();

            doctor = doctorService.login(doctor);
            if (doctor == null) {
                return CommonTools.getReturnMsg("用户名或密码错误", false);
            } else {
                request.getSession().setAttribute("session_user", doctor);//登录成功后将用户放入session中
                // 存到cookie
                Cookie cookie = new Cookie("doctor", "{'loginName':'" + loginName + "'='name':'" + doctor.getName() + "'}");
                cookie.setMaxAge(30 * 24 * 60 * 60);//一月
                cookie.setPath("/");
                response.addCookie(cookie);

                return CommonTools.getReturnMsg("登录成功", true);
            }
        }
        return CommonTools.getReturnMsg("无效请求", false);
    }

    //注册操作
    @ResponseBody
    @RequestMapping("/register")
    public String register(Doctor doctor, HttpServletRequest request, HttpServletResponse response) {
        String captcha_code = (String) request.getSession().getAttribute("captcha_code");
        String captchacode = request.getParameter("captchacode");
        String action = request.getParameter("action");
        System.out.println(doctor);
        if (!"NotVerification".equals(captchacode)) {//是否手动新增
            if (CommonTools.isBlank(captchacode) || !(captchacode.equalsIgnoreCase(captcha_code))) {
                return CommonTools.getReturnMsg("验证码错误", false);
            }

            if (CommonTools.isBlank(doctor.getPassword())) {
                return CommonTools.getReturnMsg("密码不能为空", false);
            }
            doctor.setDeptNo("6");
        } else {
            //默认密码
            doctor.setPassword("123456");
        }
        if (CommonTools.isBlank(doctor.getName())) {
            return CommonTools.getReturnMsg("用户名不能为空", false);
        }

        if ("register".equals(action)) {
            Doctor d = doctorService.getUserInfo(doctor);
            if (null != d) {
                return CommonTools.getReturnMsg("用户已存在", false);
            }
            //获得MD5密码并赋值
            doctor.setPassword(CommonTools.getMD5Encode(doctor.getPassword()));
            int result = doctorService.register(doctor);
            if (result == 0) {
                return CommonTools.getReturnMsg("注册失败", false);
            } else {
                // 存到cookie
                Cookie cookie = new Cookie("loginName", doctor.getMobile());
                cookie.setMaxAge(30 * 24 * 60 * 60);//一月
                cookie.setPath("/");
                response.addCookie(cookie);

                return CommonTools.getReturnMsg("注册成功", true);
            }
        }
        return CommonTools.getReturnMsg("无效请求", false);
    }

    //退出登录
    @RequestMapping("/logout")
    public void outUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("session_user");
        response.sendRedirect("/login.html");
    }

    //个人信息查询
    @ResponseBody
    @RequestMapping("/getUserInfo")
    public String getUserInfo(String userId, HttpServletRequest request) throws IOException {
        int uid = 0;

        if (!CommonTools.isBlank(userId)) {
            CommonTools.ToInt(userId);
        }
        Doctor doctor = (Doctor) request.getSession().getAttribute("session_user");
        uid = uid > 0 ? uid : doctor.getId();
        doctor = doctorService.getUserInfoById(uid);
        doctor.setPassword(null);
        request.getSession().setAttribute("session_user", doctor);
        JSONObject object = new JSONObject();
        object.put("doctor", JSONObject.fromObject(doctor));
        return object.toString();
    }

    //修改信息
    @ResponseBody
    @RequestMapping("/updateUserInfo")
    public String updateUserInfo(Doctor doctor, HttpServletRequest request) throws IOException {
        List<Map<String, Doctor>> list_doctor = doctorService.getUserInfoByLoginName(doctor);
        if (list_doctor.size() > 0) {
            return CommonTools.getReturnMsg("手机号或邮箱已经存在", false);
        }
        //修改
        doctorService.updateUserInfo(doctor);
        return CommonTools.getReturnMsg("修改成功", true);
    }

    //删除信息
    @ResponseBody
    @RequestMapping("/deleteUserInfo")
    public String deleteUserInfo(String userID) throws IOException {
        if (CommonTools.isBlank(userID)) {
            return CommonTools.getReturnMsg("参数错误", false);
        }
        userID = userID.substring(1, userID.length() - 1);
        int result = doctorService.deleteUserInfo(userID);
        if (result > 0) {
            return CommonTools.getReturnMsg("删除成功", true);
        } else {
            return CommonTools.getReturnMsg("删除失败", false);
        }
    }

    //修改密码
    @ResponseBody
    @RequestMapping("/updatePassword")
    public String updatePassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String password = request.getParameter("password");
        String password1 = request.getParameter("password1");
        if (CommonTools.isBlank(password) || CommonTools.isBlank(password1)) {
            return CommonTools.getReturnMsg("密码不能为空", false);
        }
        Doctor doctor = (Doctor) request.getSession().getAttribute("session_user");
        doctor = doctorService.getUserInfoByPassword(CommonTools.getMD5Encode(password), doctor.getId());
        if (null == doctor) {
            return CommonTools.getReturnMsg("原始密码不正确", false);
        }
        doctorService.updatePassword(CommonTools.getMD5Encode(password1), doctor.getId());
        return CommonTools.getReturnMsg("修改成功", true);
    }


    //修改密码
    @ResponseBody
    @RequestMapping("/getDoctorByDeptNo")
    public String getDoctorByDeptNo(String deptNo) {
        List<Map<String, Doctor>> list = doctorService.getDoctorByDeptNo(CommonTools.ToInt(deptNo));
        JSONObject obj = new JSONObject();
        obj.put("doctorList", JSONArray.fromObject(list));

        return obj.toString();
    }

}
