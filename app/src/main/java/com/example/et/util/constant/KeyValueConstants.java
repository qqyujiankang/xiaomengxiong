package com.example.et.util.constant;

/**
 * Created by Administrator on 2018/2/2 0002.
 */

public class KeyValueConstants {
    public static final String CODE = "code";
    public static final String MSG = "msg";
    public static final String DATA = "data";
    public static final String COUNT = "count";
    public static final String LIST = "list";

    //支付宝数据
    /**
     * {\"out_trade_no\":\"" + out_trade_no + "\",\"total_amount\":\""
     * + total + "\",\"subject\":\"" + subject + "\",\"body\":\"" + body")
     * + "\",\"product_code\":\"" + PRODUCT_CODE + "\",\""+TIMEOUT+"\"}
     */
    public static final String merchantPrivateKey = "merchantPrivateKey";

    public static final String biz_content = "biz_content";

    public static final String appid = "appid";
    public static final String pid = "pid";
    public static final String target_id = "target_id";
    public static final String timestamp = "timestamp";
    public static final String notifyUrl = "notifyUrl";
    public static final String outTradeNo = "out_trade_no";//订单号
    public static final String total_amount = "total_amount";//总金额
    public static final String product_code = "product_code";//支付类型
    public static final String timeout_express = "timeout_express";//超时时间
    public static final String body = "body";//名称
    public static final String subject = "subject";//标题

    public static final String SUCCESS = "1";//code的value为200表示成功
    public static final String fail = "0";//code的value为200表示成功

    //机具管理
    public static final String notActivate = "notActivate";//未激活的机具数量
    public static final String totality = "totality";//所有的机具数量


    /**
     * 盟友的详情
     */
    public static final String ally_details_levelID = "levelID";    //String	 等级	必须
    public static final String ally_details_headImgUrl = "headImgUrl";//String	头像地址	必须
    public static final String ally_details_realName = "realName";    //	String	真实姓名	必须
    public static final String ally_details_phoneNo = "phoneNo";    //	String	手机号	必须
    public static final String ally_details_ynRealname = "ynRealname";    //	String	是否实名	必须
    public static final String ally_details_pushCode = "pushCode";    //	Double	编码	必须
    public static final String ally_details_createDate = "createDate";    //	Timestamp	创建时间	必须


    /***
     *
     *
     * nonce_str-----随机字串动态值
     appid------动态值
     sign------签名
     trade_pay----支付方式
     return_msg----预订单返回信息
     result_code-----预订单返回结果码
     mch_id----商户号
     return_code-----预订单返回码
     prepay_id------预订单ID
     ={"package":"Sign=WXPay",
     "appid":"wx45d9da477e2b96ee",
     "sign":"F55001EFEBF749D4610CC667C92C0D52",
     "partnerid":"1501646001",
     "prepayid":"wx250909268842814a9189318c2375607116","noncestr":"yg3bSZwUGyUUpbLt","timestamp":"1524618567448"}
     └────
     */
    public static final String NONCE_STR = "noncestr";//随机字串动态值
    public static final String SIGN = "sign";
    public static final String prepay_id = "prepayid";//预订单ID
    public static final String partnerId = "partnerid";
    public static final String PACKAGE = "package";
    public static final String timestampW = "timestamp";

    ///
    public static final String access_token = "access_token";
    public static final String expires_in = "expires_in";
    public static final String refresh_token = "refresh_token";
    public static final String openid = "openid";
    public static final String scope = "scope";
    public static final String unionid = "unionid";







}
