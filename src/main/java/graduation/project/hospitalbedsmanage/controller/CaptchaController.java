package graduation.project.hospitalbedsmanage.controller;

import graduation.project.hospitalbedsmanage.util.Captcha.Captcha;
import graduation.project.hospitalbedsmanage.util.Captcha.GifCaptcha;
import graduation.project.hospitalbedsmanage.util.Captcha.SpecCaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/captcha")
public class CaptchaController {
    /**
     * 获取验证码（jpg版本）
     * @param response
     */
    @RequestMapping(value="captcha1",method= RequestMethod.GET)
    public void getJPGCode(HttpServletResponse response, HttpServletRequest request){
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpg");
            /**
             * jgp格式验证码
             * 宽，高，位数。
             */
            HttpSession session = request.getSession(true);

            Captcha captcha = new SpecCaptcha(146,45,4);
            //输出
            captcha.out(response.getOutputStream());
            //存入Session
            session.setAttribute("captcha_code",captcha.text());
            log.info("验证码:"+captcha.text());
        } catch (IOException e) {
            log.error(e.toString());
            e.printStackTrace();
        }
    }

    /**
     * 获取验证码（Gif版本）
     * @param response
     */
    @RequestMapping(value="captcha2",method=RequestMethod.GET)
    public void getGifCode(HttpServletResponse response,HttpServletRequest request){
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/gif");
            /**
             * gif格式动画验证码
             * 宽，高，位数。
             */
            HttpSession session = request.getSession(true);

            Captcha captcha = new GifCaptcha(146,45,4);
            //输出
            captcha.out(response.getOutputStream());
            //存入Session
            session.setAttribute("captcha_code",captcha.text());
            log.info("验证码:"+captcha.text());
        } catch (IOException e) {
            log.error(e.toString());
            e.printStackTrace();
        }
    }

}
