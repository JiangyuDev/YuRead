package pers.jiangyu.yuread.contract;

import pers.jiangyu.yuread.base.BasePresenterImpl;

public interface InformContract {
    interface Model{

    }

    interface Presenter extends BasePresenterImpl {
        void submitInform(String phone , String name ,String address, String motto,String myIcon );

    }

    interface View {
        void isSucceeded();
        void isFailed();

    }
}
