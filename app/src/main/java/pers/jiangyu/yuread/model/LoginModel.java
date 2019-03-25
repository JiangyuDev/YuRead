package pers.jiangyu.yuread.model;

import pers.jiangyu.yuread.base.BaseModel;
import pers.jiangyu.yuread.contract.LoginContract;
import pers.jiangyu.yuread.presenter.LoginPresenter;

public class LoginModel extends BaseModel<LoginPresenter>implements LoginContract.Model {
    public LoginModel(LoginPresenter presenter) {
        super(presenter);
    }
}
