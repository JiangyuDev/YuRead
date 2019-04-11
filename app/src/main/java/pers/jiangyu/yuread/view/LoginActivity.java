package pers.jiangyu.yuread.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import pers.jiangyu.yuread.R;
import pers.jiangyu.yuread.base.BaseActivity;
import pers.jiangyu.yuread.contract.LoginContract;
import pers.jiangyu.yuread.databinding.ActivityLoginBinding;
import pers.jiangyu.yuread.presenter.LoginPresenter;
import pers.jiangyu.yuread.util.ScreenSet;


public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginContract.Presenter>implements LoginContract.View {

    private String phone;

    private String psword;

    private Button signIn;

    public static void actionStart(Activity activity){
        Intent intent = new Intent(activity,LoginActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    public void loginSucceeded(String phone) {
        showSnackBar(dataBinding.loginBut,"登录成功");
        MainActivity.actionStart(LoginActivity.this,phone);
    }

    @Override
    public void loginFailed() {
        Toast.makeText(context,"登录失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void initView() {
        ScreenSet.changeFullScreen(LoginActivity.this);
        dataBinding.loginRegister.setOnClickListener(view -> {
            RegisterActivity.actionStart(LoginActivity.this);
        });

        dataBinding.loginBut.setOnClickListener(view -> {
            phone = dataBinding.editPhoneNumber.getText().toString();
            psword = dataBinding.editPassword.getText().toString();
            presenter.doLogin(phone,psword);         //去presenter执行登录逻辑
        });

    }

    @Override
    protected LoginContract.Presenter getPresenter() {
        return new LoginPresenter(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }
}
