package pers.jiangyu.yuread.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import pers.jiangyu.yuread.R;
import pers.jiangyu.yuread.base.BaseActivity;
import pers.jiangyu.yuread.contract.LoginContract;
import pers.jiangyu.yuread.databinding.ActivityLoginBinding;
import pers.jiangyu.yuread.presenter.LoginPresenter;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginContract.Presenter>implements LoginContract.View {

    private String phone;

    private String psword;

    private Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    public void loginSucceeded(String phone) {
        Toast.makeText(context,"登录成功",Toast.LENGTH_SHORT).show();
        MainActivity.actionStart(LoginActivity.this,phone);
    }

    @Override
    public void loginFailed() {
        Toast.makeText(context,"登录失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void initView() {
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
