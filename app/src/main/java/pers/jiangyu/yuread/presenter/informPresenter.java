package pers.jiangyu.yuread.presenter;

import pers.jiangyu.yuread.base.BasePresenter;
import pers.jiangyu.yuread.contract.InformContract;

/**
 * create by Jy on 2019/4/8 11:30
 * email：jiangyu9804@163.com
 */
public class informPresenter extends BasePresenter<InformContract.View,InformContract.View>implements InformContract.Presenter {

    @Override
    public void submitInform(String phone, String name, String address, String motto) {

    }

    @Override
    public void onDestroy() {

    }

    public informPresenter(InformContract.View view) {
        super(view);
    }
}
