package com.example.et.entnty;

import java.util.List;

/**
 * Created by Administrator on 2019/11/13.
 */

public class UserModel {



    private List<UserBean> data_list;

    public List<UserBean> getData_list() {
        return data_list;
    }

    public void setData_list(List<UserBean> data_list) {
        this.data_list = data_list;
    }

    public static class UserBean {

        private String phoneOremail;

        private String passWord;

        public String getPhoneOremail() {
            return phoneOremail;
        }

        public void setPhoneOremail(String phoneOremail) {
            this.phoneOremail = phoneOremail;
        }

        public String getPassWord() {
            return passWord;
        }

        public void setPassWord(String passWord) {
            this.passWord = passWord;
        }
    }


}
