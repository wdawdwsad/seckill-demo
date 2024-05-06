package com.lxy.seckill.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;


/**
 * MD5工具类
 */
@Component
public class MD5Util {

    //md5加密
    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    private static final String salt = "1a2b3c4d";

    //第一次从前端拿到密码，进行加密
    public static String inputPassToFromPass(String inputPass) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    //第二次加密,后端拿到密码，在进入数据库之前再进行加密
    public static String fromPassToDBPass(String fromPass, String salt) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + fromPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }


    public static String inputPassToDBPass(String inputPass, String salt) {
        String fromPass = inputPassToFromPass(inputPass);
        String dbPass = fromPassToDBPass(fromPass, salt);
        return dbPass;
    }


    public static void main(String[] args) {
        //d3b1294a61a07da9b49b6e22b2cbd7f9
        System.out.println(inputPassToFromPass("123456"));

        //1897a69ef451f0991bb85c6e7c35aa31
        System.out.println(fromPassToDBPass("d3b1294a61a07da9b49b6e22b2cbd7f9", "1a2b3c4d"));

        //1897a69ef451f0991bb85c6e7c35aa31
        System.out.println(inputPassToDBPass("123456", "1a2b3c4d"));
    }
}
