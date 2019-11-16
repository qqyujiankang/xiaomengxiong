package com.example.et;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
//        //assertEquals(4, 2 + 2);
//        System.out.println("=======" + calculateProfit1(1564.41544123));
//        if (Double.parseDouble("0.0000") >= 100) {
//
//        }
        List<String> strings=new ArrayList<>();
        List<String> strings1=new ArrayList<>();
        strings.add("sdfas");
        strings.add("yut");
        strings.add("gjgf");
        strings.add("32462");
        strings.add("646745");
        strings.add("trtre");
       //strings1=createRandomList(strings,3);
         System.out.println(  "===========" +strings1.get(2) );
    }


    /**
     * 保留double类型小数后两位，不四舍五入，直接取小数后两位 比如：10.1269 返回：10.12
     *
     * @param doubleValue
     * @return
     */
    public static String calculateProfit1(double doubleValue) {
        // 保留4位小数
        java.text.DecimalFormat df = new java.text.DecimalFormat("0.0000");
        String result = df.format(doubleValue);
        System.out.println(result);
        ;
        // 截取第一位
        String index = result.substring(0, 1);
        System.out.println(index);
        if (".".equals(index)) {
            result = "0" + result;
        }

        // 获取小数 . 号第一次出现的位置
        int inde = firstIndexOf(result, ".");

        // 字符串截断
        return result.substring(0, inde + 3);
    }


    /**
     * 获取小数点的位数
     *
     * @param str
     * @param pattern
     * @return i , -1
     */
    public static int firstIndexOf(String str, String pattern) {
        for (int i = 0; i < (str.length() - pattern.length()); i++) {
            int j = 0;
            while (j < pattern.length()) {
                if (str.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
                j++;
            }
            if (j == pattern.length()) {
                return i;
            }
        }
        return -1;
    }
}