package pers.jiangyu.yuread.contract;

import pers.jiangyu.yuread.base.BasePresenterImpl;

public interface LoginContract {

     interface Model {

    }

    interface Presenter extends BasePresenterImpl {

         void doLogin(String phone ,String psword); //实现登陆的具体方法

    }

    interface View {

         void loginSucceeded(String phone);  //去执行登陆方法

         void loginFailed();  //登录失败
    }
}
