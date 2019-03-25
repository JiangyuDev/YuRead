package pers.jiangyu.yuread.bean;

import cn.bmob.v3.BmobObject;

public class RegisterBean extends BmobObject {

    private String phoneNumber;

    private String userToken;

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getUserToken() {
        return userToken;
    }
}
