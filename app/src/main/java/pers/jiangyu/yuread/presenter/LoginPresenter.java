package pers.jiangyu.yuread.presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import pers.jiangyu.yuread.base.BasePresenter;
import pers.jiangyu.yuread.bean.RegisterBean;
import pers.jiangyu.yuread.contract.LoginContract;
import pers.jiangyu.yuread.model.LoginModel;

public class LoginPresenter extends BasePresenter<LoginContract.View,LoginContract.Model> implements LoginContract.Presenter{
    @Override
    public void doLogin(String phone ,String psword) {
        String Token = RegisterPresenter.encryptionPassword(psword);
        //查找Person表里面id为6b6c11c537的数据
        BmobQuery<RegisterBean> query1 = new BmobQuery<>();
        query1.addWhereEqualTo("phoneNumber",phone);
        BmobQuery<RegisterBean>query2 = new BmobQuery<>();
        query2.addWhereEqualTo("userToken",Token);
        //最后组装完整的and条件
        List<BmobQuery<RegisterBean>> andQuerys = new ArrayList<BmobQuery<RegisterBean>>();
        andQuerys.add(query1);
        andQuerys.add(query2);
        BmobQuery<RegisterBean> query = new BmobQuery<RegisterBean>();
        query.and(andQuerys);
        query.findObjects(new FindListener<RegisterBean>() {
            @Override
            public void done(List<RegisterBean> object, BmobException e) {
                if(e==null){
//
//                    Log.d("doLogin",object.get(0).getPhoneNumber());
                    Log.d("doLogin",object.get(0).getUserToken());
                    view.loginSucceeded(phone);
                }else{
                    view.loginFailed();
                }
            }
        });
    }

    @Override
    public void onDestroy() {

    }

    public LoginPresenter(LoginContract.View view) {
        super(view);
        model = new LoginModel(this);
    }

}
