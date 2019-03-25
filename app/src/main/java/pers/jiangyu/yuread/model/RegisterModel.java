package pers.jiangyu.yuread.model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import pers.jiangyu.yuread.base.BaseModel;
import pers.jiangyu.yuread.contract.RegisterContract;
import pers.jiangyu.yuread.presenter.RegisterPresenter;
import pers.jiangyu.yuread.view.RegisterActivity;

public class RegisterModel extends BaseModel<RegisterPresenter> implements RegisterContract.Model {

    public RegisterModel(RegisterPresenter presenter) {
        super(presenter);
    }



}
