package com.example.et.entnty;

import com.google.gson.annotations.SerializedName;

public class Contractyu {


    /**
     * code : 200
     * msg : 请求成功
     * data : {"id":118896,"money":"300.0000","day_shouyilv":"1","dingjin":30,"weikuan":270,"yytime":"11-21 01:33:50","yytime_daojishi":1574273930,"sjsy_day":1,"zx_gold_type":"","zx_gold_miney":"","zx_gold_time":"","zx_usdt":0,"pay_number":30,"okdays":0,"static":"3.0000","pre_cur_money":"0.3221900000","pre_cur":"HOPE","pre_cur_number":93.1127595518,"pre_cur_time":"11-21 01:38:03","pp_cur_time":"01-01 08:00:00","yhzx_cur_time":"01-01 08:00:00","ok_cur_time":"01-01 08:00:00","sup_cur_number":0,"sup_cur_money":0,"sup_cur":0,"hash":"","state":1,"hour24":null}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 118896
         * money : 300.0000
         * day_shouyilv : 1
         * dingjin : 30
         * weikuan : 270
         * yytime : 11-21 01:33:50
         * yytime_daojishi : 1574273930
         * sjsy_day : 1
         * zx_gold_type :
         * zx_gold_miney :
         * zx_gold_time :
         * zx_usdt : 0
         * pay_number : 30
         * okdays : 0
         * static : 3.0000
         * pre_cur_money : 0.3221900000
         * pre_cur : HOPE
         * pre_cur_number : 93.1127595518
         * pre_cur_time : 11-21 01:38:03
         * pp_cur_time : 01-01 08:00:00
         * yhzx_cur_time : 01-01 08:00:00
         * ok_cur_time : 01-01 08:00:00
         * sup_cur_number : 0
         * sup_cur_money : 0
         * sup_cur : 0
         * hash :
         * state : 1
         * hour24 : null
         */

        private int id;
        private String money;
        private String day_shouyilv;
        private int dingjin;
        private int weikuan;
        private String yytime;
        private int yytime_daojishi;
        private int sjsy_day;
        private String zx_gold_type;
        private String zx_gold_miney;
        private String zx_gold_time;
        private int zx_usdt;
        private int pay_number;
        private int okdays;
        @SerializedName("static")
        private String staticX;
        private String pre_cur_money;
        private String pre_cur;
        private double pre_cur_number;
        private String pre_cur_time;
        private String pp_cur_time;
        private String yhzx_cur_time;
        private String ok_cur_time;
        private int sup_cur_number;
        private int sup_cur_money;
        private int sup_cur;
        private String hash;
        private int state;
        private Object hour24;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getDay_shouyilv() {
            return day_shouyilv;
        }

        public void setDay_shouyilv(String day_shouyilv) {
            this.day_shouyilv = day_shouyilv;
        }

        public int getDingjin() {
            return dingjin;
        }

        public void setDingjin(int dingjin) {
            this.dingjin = dingjin;
        }

        public int getWeikuan() {
            return weikuan;
        }

        public void setWeikuan(int weikuan) {
            this.weikuan = weikuan;
        }

        public String getYytime() {
            return yytime;
        }

        public void setYytime(String yytime) {
            this.yytime = yytime;
        }

        public int getYytime_daojishi() {
            return yytime_daojishi;
        }

        public void setYytime_daojishi(int yytime_daojishi) {
            this.yytime_daojishi = yytime_daojishi;
        }

        public int getSjsy_day() {
            return sjsy_day;
        }

        public void setSjsy_day(int sjsy_day) {
            this.sjsy_day = sjsy_day;
        }

        public String getZx_gold_type() {
            return zx_gold_type;
        }

        public void setZx_gold_type(String zx_gold_type) {
            this.zx_gold_type = zx_gold_type;
        }

        public String getZx_gold_miney() {
            return zx_gold_miney;
        }

        public void setZx_gold_miney(String zx_gold_miney) {
            this.zx_gold_miney = zx_gold_miney;
        }

        public String getZx_gold_time() {
            return zx_gold_time;
        }

        public void setZx_gold_time(String zx_gold_time) {
            this.zx_gold_time = zx_gold_time;
        }

        public int getZx_usdt() {
            return zx_usdt;
        }

        public void setZx_usdt(int zx_usdt) {
            this.zx_usdt = zx_usdt;
        }

        public int getPay_number() {
            return pay_number;
        }

        public void setPay_number(int pay_number) {
            this.pay_number = pay_number;
        }

        public int getOkdays() {
            return okdays;
        }

        public void setOkdays(int okdays) {
            this.okdays = okdays;
        }

        public String getStaticX() {
            return staticX;
        }

        public void setStaticX(String staticX) {
            this.staticX = staticX;
        }

        public String getPre_cur_money() {
            return pre_cur_money;
        }

        public void setPre_cur_money(String pre_cur_money) {
            this.pre_cur_money = pre_cur_money;
        }

        public String getPre_cur() {
            return pre_cur;
        }

        public void setPre_cur(String pre_cur) {
            this.pre_cur = pre_cur;
        }

        public double getPre_cur_number() {
            return pre_cur_number;
        }

        public void setPre_cur_number(double pre_cur_number) {
            this.pre_cur_number = pre_cur_number;
        }

        public String getPre_cur_time() {
            return pre_cur_time;
        }

        public void setPre_cur_time(String pre_cur_time) {
            this.pre_cur_time = pre_cur_time;
        }

        public String getPp_cur_time() {
            return pp_cur_time;
        }

        public void setPp_cur_time(String pp_cur_time) {
            this.pp_cur_time = pp_cur_time;
        }

        public String getYhzx_cur_time() {
            return yhzx_cur_time;
        }

        public void setYhzx_cur_time(String yhzx_cur_time) {
            this.yhzx_cur_time = yhzx_cur_time;
        }

        public String getOk_cur_time() {
            return ok_cur_time;
        }

        public void setOk_cur_time(String ok_cur_time) {
            this.ok_cur_time = ok_cur_time;
        }

        public int getSup_cur_number() {
            return sup_cur_number;
        }

        public void setSup_cur_number(int sup_cur_number) {
            this.sup_cur_number = sup_cur_number;
        }

        public int getSup_cur_money() {
            return sup_cur_money;
        }

        public void setSup_cur_money(int sup_cur_money) {
            this.sup_cur_money = sup_cur_money;
        }

        public int getSup_cur() {
            return sup_cur;
        }

        public void setSup_cur(int sup_cur) {
            this.sup_cur = sup_cur;
        }

        public String getHash() {
            return hash;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public Object getHour24() {
            return hour24;
        }

        public void setHour24(Object hour24) {
            this.hour24 = hour24;
        }
    }
}
