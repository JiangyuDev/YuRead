package pers.jiangyu.yuread.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import pers.jiangyu.yuread.R;
import pers.jiangyu.yuread.base.BaseActivity;
import pers.jiangyu.yuread.contract.RegisterContract;
import pers.jiangyu.yuread.databinding.ActivityRegisterBinding;
import pers.jiangyu.yuread.presenter.RegisterPresenter;

public class RegisterActivity extends BaseActivity<ActivityRegisterBinding, RegisterContract.Presenter > implements RegisterContract.View{

    private String phone;

    private String SMSCode;

    private String password;

    public static void  actionStart(Activity activity){
        Intent intent = new Intent(activity,RegisterActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

    }

    @Override
    protected void initView() {
        dataBinding.butSendCode.setOnClickListener(view->{
            phone = dataBinding.textNumber.getText().toString();
            presenter.getSMSCode(phone); });
        dataBinding.butRegister.setOnClickListener(view1 ->{
            SMSCode = dataBinding.textCode.getText().toString();
            password = RegisterPresenter.encryptionPassword(dataBinding.textPassword1.getText().toString());
            presenter.verifyCode(phone,SMSCode,password);
        } );

    }

//    @Override
//    protected RegisterPresenter getPresenter() {
//        return new RegisterPresenter(this);
//    }

    @Override
    protected RegisterContract.Presenter getPresenter() {
        return new RegisterPresenter(this);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }




}
