package pers.jiangyu.yuread.presenter;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import pers.jiangyu.yuread.base.BasePresenter;
import pers.jiangyu.yuread.bean.RegisterBean;
import pers.jiangyu.yuread.contract.RegisterContract;
import pers.jiangyu.yuread.model.RegisterModel;

public class RegisterPresenter extends BasePresenter<RegisterContract.View,RegisterContract.Model>
        implements RegisterContract.Presenter {

    @Override
    public void onDestroy() {

    }

    public RegisterPresenter(RegisterContract.View view){
       super(view);
       model = new RegisterModel(this);
    }

    /**
     *
     * @param phone  注册用的手机号
     * @param code   验证码
     * @return
     */
    @Override
    public void  verifyCode(String phone, String  code,String userToken) {

        BmobSMS.verifySmsCode(phone, code, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    RegisterBean per = new RegisterBean();
                    per.setPhoneNumber(phone);
                    per.setUserToken(userToken);
                    per.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if(e == null){
                                view.registerSucceeded();
                            }else {
                                view.registerFailed();
                                e.printStackTrace();
                            }
                        }
                    });

                }else {
                    view.registerFailed();
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void  getSMSCode(String phone){
                BmobSMS.requestSMSCode(phone, "", new QueryListener<Integer>() {
                    @Override
                    public void done(Integer smsId, BmobException e) {
                        if (e == null) {
                            view.getSMSCodeSucceeded();
                        } else {
                            view.getSMSCodeFailed();
                        }
                    }
                });

        }

    }



