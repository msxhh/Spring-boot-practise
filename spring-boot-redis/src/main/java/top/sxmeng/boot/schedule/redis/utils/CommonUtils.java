package top.sxmeng.boot.redis.utils;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {

    /**
     * 校验手机号码
     */
    public static boolean checkPhone(String phone) {
        if (phone == null || phone.length() != 11) {
            return false;
        }
        // 中国大陆手机号码的正则表达式，可能需要根据实际情况进行调整
        String regex="^1[3-9]\\d{9}$";
        Pattern pattern= Pattern.compile(regex);
        Matcher matcher=pattern.matcher(phone);
        return matcher.matches();
    }

    /**
     * 生成4位短信验证码
     */
    public static int generateCode() {
        return (int) ((Math.random() * 9000) + 1000);
    }

    public static String generateToken() {
        return UUID.randomUUID().toString().replace("-","") + System.currentTimeMillis();
    }
}
