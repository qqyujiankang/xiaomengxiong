package com.example.et.entnty;

import java.io.Serializable;

/**
 * author: Dogal
 * data: 2018/8/16
 */

public class YanZhengMaBean implements Serializable {

    /**
     * retcode : 2000
     * msg : 发送成功!
     * data : 94
     */
    private int status;
    private String msg;
    private String ceode;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCeode() {
        return ceode;
    }

    public void setCeode(String ceode) {
        this.ceode = ceode;
    }
}