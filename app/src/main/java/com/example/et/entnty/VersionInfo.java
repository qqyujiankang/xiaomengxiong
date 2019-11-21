package com.example.et.entnty;

public class VersionInfo {


    /**
     * code : 200
     * msg :
     * data : {"id":7,"text":"不着调更塞得进爱死","ios":"12123","iosurl":"1231231","android":"121321","androidurl":"13131313131","time":"2019-11-18 11:26:34"}
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
         * id : 7
         * text : 不着调更塞得进爱死
         * ios : 12123
         * iosurl : 1231231
         * android : 121321
         * androidurl : 13131313131
         * time : 2019-11-18 11:26:34
         */

        private int id;
        private String text;
        private String ios;
        private String iosurl;
        private String android;
        private String androidurl;
        private String time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getIos() {
            return ios;
        }

        public void setIos(String ios) {
            this.ios = ios;
        }

        public String getIosurl() {
            return iosurl;
        }

        public void setIosurl(String iosurl) {
            this.iosurl = iosurl;
        }

        public String getAndroid() {
            return android;
        }

        public void setAndroid(String android) {
            this.android = android;
        }

        public String getAndroidurl() {
            return androidurl;
        }

        public void setAndroidurl(String androidurl) {
            this.androidurl = androidurl;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
