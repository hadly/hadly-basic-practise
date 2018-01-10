package com.hadly.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则的两个用途：搜索、替换
 * <p>
 * Created by lizhinian on 2017/11/30.
 */
public class RegexTest {
    public static void main(String[] args) {
        String src = "";
        String regex = "";

        //1.匹配电子邮件
//        src = "service@xsoftlab.net";
//        regex = "[a-zA-Z_]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}";
//        matches(src, regex);

        matchSingleWorld();
    }

    private static void matchSingleWorld() {
//        find("nihao.", "nihao123");//用.匹配任意一个单个字符
//        find("nihao.", "nihao.123");//用.可以匹配到其本身
        find("nihao\\.", "nihao34", "nihao.12");//找.本身，第二个能匹配到

    }

    /**
     * 是否匹配
     *
     * @param src
     * @param regex
     */
    private static void matches(String src, String regex) {
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(src);
        // 字符串是否与正则表达式相匹配
        boolean isMatch = matcher.matches();
        System.out.println("isMatch=" + isMatch);
    }

    /**
     * 寻找多个
     *
     * @param regex
     * @param srcList
     */
    private static void find(String regex, String... srcList) {
        for (String src : srcList) {
            findOne(src, regex);
        }
    }

    /**
     * 找到并打印
     *
     * @param src
     * @param regex
     */
    private static void findOne(String src, String regex) {
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(src);
        boolean found = matcher.find();
        System.out.println("src=" + src + " -- regex=" + regex);
        if (found) {
            System.out.println("start=" + matcher.start() + ", end=" + matcher.end());
        } else {
            System.out.println("not found");
        }
    }//
}
