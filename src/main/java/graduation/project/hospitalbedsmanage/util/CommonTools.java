package graduation.project.hospitalbedsmanage.util;

import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public static int ToInt(String str) {
        if(isBlank(str)) return 0;
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获得格式化的事件字符串
     *
     * @param date   日期
     * @param format 格式
     * @return
     */
    public static String parseDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 格式化日期
     *
     * @param dateStr String 字符型日期
     * @param format  String 格式
     * @return Date 日期
     */
    public static Date parseDate(String dateStr, String format) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.parse(dateStr);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * StringUtil.getSqlInStrByStrArray()<BR>
     * <P>Author :  wyp </P>
     * <P>Date : 2016年6月15日下午6:14:05</P>
     * <P>Desc : 数组字符串转换为SQL in 字符串拼接 </P>
     * @return  SQL in 字符串
     */
    public static String getSqlInStrByStrArray(String str) {
        StringBuffer temp = new StringBuffer();
        if (isBlank(str)) {
            return "('')";
        }
        temp.append("(");
        if (!isBlank(str)) {
            String[] strArray = str.split(",");
            if (strArray != null && strArray.length > 0) {
                for (int i = 0; i < strArray.length; i++) {
                    temp.append("'");
                    temp.append(strArray[i]);
                    temp.append("'");
                    if (i != (strArray.length - 1)) {
                        temp.append(",");
                    }
                }
            }
        }
        temp.append(")");
        return temp.toString();
    }
}
