package pers.jiangyu.yuread.presenter;

import android.util.Log;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.security.MessageDigest;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import pers.jiangyu.yuread.base.BasePresenter;
import pers.jiangyu.yuread.bean.RegisterBean;
import pers.jiangyu.yuread.contract.RegisterContract;
import pers.jiangyu.yuread.model.RegisterModel;
import pers.jiangyu.yuread.util.MyApplication;

public class RegisterPresenter extends BasePresenter<RegisterContract.View,RegisterContract.Model> implements RegisterContract.Presenter {

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
                                Toast.makeText(MyApplication.getContext(),"恭喜你注册成功",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(MyApplication.getContext(),"请稍后尝试",Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        }
                    });

                }else {
                    Toast.makeText(MyApplication.getContext(),"验证码错误",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }

    @NotNull
    public static String encryptionPassword(String password) {
            MessageDigest md5 = null;
            try {
                md5 = MessageDigest.getInstance("MD5");
            } catch (Exception e) {
                System.out.println(e.toString());
                e.printStackTrace();
                return "";
            }
            char[] charArray = password.toCharArray();
            byte[] byteArray = new byte[charArray.length];
            for (int i = 0; i < charArray.length; i++)
                byteArray[i] = (byte) charArray[i];
            byte[] md5Bytes = md5.digest(byteArray);
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16)
                    hexValue.append("0");
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();

    }

    @Override
    public void  getSMSCode(String phone){
        if(phone!= null){
            if (phone.length() ==11){
                BmobSMS.requestSMSCode(phone, "", new QueryListener<Integer>() {
                    @Override
                    public void done(Integer smsId, BmobException e) {
                        if (e == null) {
                            Toast.makeText(MyApplication.getContext(),"验证码发送成功",Toast.LENGTH_SHORT).show();
                        } else {
                            Log.d("SMSCode", e.getErrorCode() + "    " + e.getMessage());
                            Toast.makeText(MyApplication.getContext(),"验证码发送失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }else {
                Toast.makeText(MyApplication.getContext(),"请输入正确的手机号",Toast.LENGTH_SHORT).show();
            }


        }else {
            Toast.makeText(MyApplication.getContext(),"请输入手机号",Toast.LENGTH_SHORT).show();
        }

    }


}
