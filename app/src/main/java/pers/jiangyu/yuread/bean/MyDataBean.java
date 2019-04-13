package pers.jiangyu.yuread.bean;

import cn.bmob.v3.BmobObject;

/**
 * create by Jy on 2019/4/4 16:39
 * email：jiangyu9804@163.com
 */
public class MyDataBean extends BmobObject {

    private String phoneNum;

    private String nickname;

    private String address;

    private String motto;

    private String myIcon;

    public void setMyIcon(String myIcon) {
        this.myIcon = myIcon;
    }

    public String getMyIcon() {
        return myIcon;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getNickname() {
        return nickname;
    }

    public String getAddress() {
        return address;
    }

    public String getMotto() {
        return motto;
    }
}
