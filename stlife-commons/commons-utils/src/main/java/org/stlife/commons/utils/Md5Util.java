package org.stlife.commons.utils;

import org.springframework.util.DigestUtils;

/**
 * Md5Util
 * 描述：TODO
 *
 * @author Stlife
 * @version 1.0
 * @since 2020-09-19 15:54
 **/
public class Md5Util {
    public static void main(String[] args) {
        String encode = DigestUtils.md5DigestAsHex("admin".toString().getBytes());
        boolean res = "21232f297a57a5a743894a0e4a801fc3".equals( encode );
        String str = "21232f297a57a5a743894a0e4a801fc3";
        System.out.println(encode);
        System.out.println(res);
    }
}
