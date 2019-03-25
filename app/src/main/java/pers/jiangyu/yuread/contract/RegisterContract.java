package pers.jiangyu.yuread.contract;

import pers.jiangyu.yuread.base.BasePresenterImpl;

public interface RegisterContract  {

    interface Model {
    }

    interface View {

        void registerSucceeded();

        void registerFailed();

        void getSMSCodeSucceeded();

        void getSMSCodeFailed();
    }

    interface Presenter extends BasePresenterImpl {


        void  getSMSCode(String phone);

        void verifyCode(String phone,String SMSCode,String S);


    }
}
