package com.example.et.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ywx on 2018/4/28.
 */

public class ListSelectDatas {
    /**
     * pos 的类型
     */

    public static List<Map<String, Boolean>> getPosType() {
        List<Map<String, Boolean>> mapList = new ArrayList<>();
        HashMap<String, Boolean> maps0 = new HashMap<String, Boolean>();
        maps0.put("全部", false);
        mapList.add(0, maps0);
        HashMap<String, Boolean> maps1 = new HashMap<String, Boolean>();
        maps1.put("MPOS", false);
        mapList.add(1, maps1);
        HashMap<String, Boolean> maps2 = new HashMap<String, Boolean>();
        maps2.put("传统大POS", false);
        mapList.add(2, maps2);
        HashMap<String, Boolean> maps3 = new HashMap<String, Boolean>();
        maps3.put("智能POS", false);
        mapList.add(3, maps3);
        return mapList;
    }

    /**
     * 交易类型 的类型
     */
    public static List<Map<String, Boolean>> gettradingclassification() {
        List<Map<String, Boolean>> mapList = new ArrayList<>();
        HashMap<String, Boolean> maps0 = new HashMap<String, Boolean>();
        maps0.put("全部", false);
        mapList.add(0, maps0);
        HashMap<String, Boolean> maps1 = new HashMap<String, Boolean>();
        maps1.put("交易奖", false);
        mapList.add(1, maps1);
        HashMap<String, Boolean> maps2 = new HashMap<String, Boolean>();
        maps2.put("推荐奖", false);
        mapList.add(2, maps2);
        HashMap<String, Boolean> maps3 = new HashMap<String, Boolean>();
        maps3.put("划拨奖", false);
        mapList.add(3, maps3);
        return mapList;


    }

    /**
     * time 的类型
     */
    public static List<Map<String, Boolean>> getTimeType() {
        List<Map<String, Boolean>> mapList = new ArrayList<>();
        HashMap<String, Boolean> maps0 = new HashMap<String, Boolean>();
        maps0.put("全部", false);
        mapList.add(0, maps0);
        HashMap<String, Boolean> maps1 = new HashMap<String, Boolean>();
        maps1.put("昨日", false);
        mapList.add(1, maps1);
        HashMap<String, Boolean> maps2 = new HashMap<String, Boolean>();
        maps2.put("本周", false);
        mapList.add(2, maps2);
        HashMap<String, Boolean> maps3 = new HashMap<String, Boolean>();
        maps3.put("本月", false);
        mapList.add(3, maps3);
        return mapList;


    }

    /**
     * time 的类型
     */
    public static List<Map<String, Boolean>> getTimeType1() {
        List<Map<String, Boolean>> mapList = new ArrayList<>();
        HashMap<String, Boolean> maps0 = new HashMap<String, Boolean>();
        maps0.put("昨日", false);
        mapList.add(0, maps0);
        HashMap<String, Boolean> maps1 = new HashMap<String, Boolean>();
        maps1.put("本周", false);
        mapList.add(1, maps1);
        HashMap<String, Boolean> maps2 = new HashMap<String, Boolean>();
        maps2.put("本月", false);
        mapList.add(2, maps2);
        HashMap<String, Boolean> maps3 = new HashMap<String, Boolean>();
        maps3.put("全部", false);
        mapList.add(3, maps3);
        return mapList;


    }

    /**
     * money是否到账
     */
    public static List<Map<String, Boolean>> getMoneyType() {
        List<Map<String, Boolean>> mapList = new ArrayList<>();
        HashMap<String, Boolean> maps0 = new HashMap<String, Boolean>();
        maps0.put("全部", false);
        mapList.add(0, maps0);
        HashMap<String, Boolean> maps1 = new HashMap<String, Boolean>();
        maps1.put("已到账", false);
        mapList.add(1, maps1);
        HashMap<String, Boolean> maps2 = new HashMap<String, Boolean>();
        maps2.put("未到账", false);
        mapList.add(2, maps2);
        return mapList;
    }

    /**
     * money是否提现
     */
    public static List<Map<String, Boolean>> getdeposit() {
        List<Map<String, Boolean>> mapList = new ArrayList<>();
        HashMap<String, Boolean> maps0 = new HashMap<String, Boolean>();
        maps0.put("全部", false);
        mapList.add(0, maps0);
        HashMap<String, Boolean> maps1 = new HashMap<String, Boolean>();
        maps1.put("未提现", false);
        mapList.add(1, maps1);
        HashMap<String, Boolean> maps2 = new HashMap<String, Boolean>();
        maps2.put("已提现", false);
        mapList.add(2, maps2);
        return mapList;
    }

    /**
     * 交易额
     *
     * @return
     */
    public static List<Map<String, Boolean>> getBourse() {
        List<Map<String, Boolean>> mapList = new ArrayList<>();
        HashMap<String, Boolean> maps1 = new HashMap<String, Boolean>();
        maps1.put("交易额升序", false);
        mapList.add(0, maps1);
        HashMap<String, Boolean> maps2 = new HashMap<String, Boolean>();
        maps2.put("交易额降序", false);
        mapList.add(1, maps2);
        return mapList;
    }
    /**
     * pos 激活的类型
     */

  /*  public static List<Map<String, Boolean>> getPosType1() {
        List<Map<String, Boolean>> mapList = new ArrayList<>();
        HashMap<String, Boolean> maps0 = new HashMap<String, Boolean>();
        maps0.put("全部", false);
        mapList.add(0, maps0);
        HashMap<String, Boolean> maps1 = new HashMap<String, Boolean>();
        maps1.put("MPOS", false);
        mapList.add(1, maps1);
        HashMap<String, Boolean> maps2 = new HashMap<String, Boolean>();
        maps2.put("传统大POS", false);
        mapList.add(2, maps2);
        HashMap<String, Boolean> maps3 = new HashMap<String, Boolean>();
        maps3.put("智能POS", false);
        mapList.add(3, maps3);
        return mapList;
    }*/

    public static List<Map<String, Boolean>> getPosactivateType() {
        List<Map<String, Boolean>> mapList = new ArrayList<>();
        HashMap<String, Boolean> maps0 = new HashMap<String, Boolean>();
        maps0.put("全部", false);
        mapList.add(0, maps0);
        HashMap<String, Boolean> maps1 = new HashMap<String, Boolean>();
        maps1.put("未激活", false);
        mapList.add(1, maps1);
        HashMap<String, Boolean> maps2 = new HashMap<String, Boolean>();
        maps2.put("已激活", false);
        mapList.add(2, maps2);

        return mapList;
    }
    public static List<Map<String, Boolean>> getPosactivateType1() {
        List<Map<String, Boolean>> mapList = new ArrayList<>();
        HashMap<String, Boolean> maps0 = new HashMap<String, Boolean>();
        maps0.put("全部", false);
        mapList.add(0, maps0);
        HashMap<String, Boolean> maps1 = new HashMap<String, Boolean>();
        maps1.put("未激活", false);
        mapList.add(1, maps1);
        HashMap<String, Boolean> maps2 = new HashMap<String, Boolean>();
        maps2.put("已激活", false);
        mapList.add(2, maps2);
        HashMap<String, Boolean> maps3 = new HashMap<String, Boolean>();
        maps3.put("已登记", false);
        mapList.add(3, maps3);

        return mapList;
    }
}
