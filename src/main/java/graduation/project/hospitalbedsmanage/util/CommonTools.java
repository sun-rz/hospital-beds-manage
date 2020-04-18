package graduation.project.hospitalbedsmanage.util;

import net.sf.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CommonTools {
    /**
     * 判断对象是否为空
     *
     * @param obj
     * @return
     */
    public static boolean isBlank(Object obj) {
        return null == obj || "".equals(obj) || "null".equals(obj);
    }

    /**
     * 获取JSON格式的返回数据
     *
     * @param msg
     * @param success
     * @return
     */
    public static String getReturnMsg(String msg, boolean success) {
        JSONObject obj = new JSONObject();
        obj.put("msg", msg);
        obj.put("success", success);
        return obj.toString();
    }

    /**
     * 获得MD5加密字符
     *
     * @param src
     * @return
     */
    public static String getMD5Encode(String src) {

        if (isBlank(src)) {
            return "";
        }
        // 需要加密的字符串
        try {
            // 加密对象，指定加密方式
            MessageDigest md5 = MessageDigest.getInstance("md5");
            // 准备要加密的数据
            byte[] b = src.getBytes();
            // 加密
            byte[] digest = md5.digest(b);
            // 十六进制的字符
            char[] chars = new char[]{'0', '1', '2', '3', '4', '5',
                    '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
            StringBuffer sb = new StringBuffer();
            // 处理成十六进制的字符串(通常)
            for (byte bb : digest) {
                sb.append(chars[(bb >> 4) & 15]);
                sb.append(chars[bb & 15]);
            }
            // 打印加密后的字符串
            //System.out.println(sb);
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
