package graduation.project.hospitalbedsmanage.controller;

import graduation.project.hospitalbedsmanage.entity.Doctor;
import graduation.project.hospitalbedsmanage.service.DoctorService;
import graduation.project.hospitalbedsmanage.util.CommonTools;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/user")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;


    //登录操作
    @ResponseBody
    @RequestMapping("/login")
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
            doctor = doctorService.login(doctor);
            if (doctor == null) {
                return CommonTools.getReturnMsg("用户名或密码错误", false);
            } else {
                request.getSession().setAttribute("session_user", doctor);//登录成功后将用户放入session中

                // 存到cookie
                Cookie cookie = new Cookie("username", doctor.getName());
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

        if (CommonTools.isBlank(captchacode) || !(captchacode.equalsIgnoreCase(captcha_code))) {
            return CommonTools.getReturnMsg("验证码错误", false);
        }
        if (CommonTools.isBlank(doctor.getName())) {
            return CommonTools.getReturnMsg("用户名不能为空", false);
        }

        if (CommonTools.isBlank(doctor.getPassword())) {
            return CommonTools.getReturnMsg("密码不能为空", false);
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
                Cookie cookie = new Cookie("username", doctor.getName());
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
    public String getUserInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {

        return "";
    }

}
