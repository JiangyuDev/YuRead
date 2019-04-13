package pers.jiangyu.yuread.presenter;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import pers.jiangyu.yuread.base.BasePresenter;
import pers.jiangyu.yuread.bean.MyDataBean;
import pers.jiangyu.yuread.contract.InformContract;

/**
 * create by Jy on 2019/4/8 11:30
 * email：jiangyu9804@163.com
 */
public class InformPresenter extends BasePresenter<InformContract.View,InformContract.View>implements InformContract.Presenter {

    @Override
    public void submitInform(String phone, String name, String address, String motto, String myIcon) {
        MyDataBean dataBean = new MyDataBean();
        dataBean.setMyIcon(myIcon);
        dataBean.setPhoneNum(phone);
        dataBean.setAddress(address);
        dataBean.setMotto(motto);
        dataBean.setNickname(name);
        dataBean.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e == null){
                    view.isSucceeded();
                }else {
                    view.isFailed();
                }
            }
        });
    }


    @Override
    public void onDestroy() {

    }

    public InformPresenter(InformContract.View view) {
        super(view);
    }
}
