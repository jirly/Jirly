package com.xuanwu.cmp.util;

import java.util.regex.Pattern;

/**
 * Validator, use regular expressions check mail, phone number, etc.
 *
 * @Author <a href="zhangjinxiu@wxchina.com">Drizzt</a>
 * @Date 2016-08-11
 * @Version 1.0.0
 */
public class Validator {

    /**
     * regex of validat mobile
     */
    public static final String REGEX_MOBILE = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    /**
     * regex of validat email
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)" +
            "+[a-zA-Z]{2,}$";

    /**
     * regex of validat chinese
     */
    public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";

    /**
     * regex of validat personal id card
     */
    public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";

    /**
     * regex of validat URL
     */
    public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

    /**
     * regex of validat IP address
     */
    public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";

    /**
     * validat mobile
     *
     * @param mobile
     * @return return true if valid，or false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    /**
     * validat email
     *
     * @param email
     * @return return true if valid，or false
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }

    /**
     * validat chinese
     *
     * @param chinese
     * @return return true if valid，or false
     */
    public static boolean isChinese(String chinese) {
        return Pattern.matches(REGEX_CHINESE, chinese);
    }

    /**
     * validat idCard
     *
     * @param idCard
     * @return return true if valid，or false
     */
    public static boolean isIDCard(String idCard) {
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }

    /**
     * validat url
     *
     * @param url
     * @return return true if valid，or false
     */
    public static boolean isUrl(String url) {
        return Pattern.matches(REGEX_URL, url);
    }

    /**
     * validat ipAddr
     *
     * @param ipAddr
     * @return return true if valid，or false
     */
    public static boolean isIPAddr(String ipAddr) {
        return Pattern.matches(REGEX_IP_ADDR, ipAddr);
    }

}