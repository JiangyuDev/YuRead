package pers.jiangyu.yuread.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import cn.bmob.v3.Bmob;


public abstract class BaseActivity <D extends ViewDataBinding,P extends BasePresenterImpl>extends AppCompatActivity {

    protected D dataBinding;

    protected P presenter;

    protected Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this, "42386c376c519dff99e9ac386fca3e0f");
        initFiled();
        initView();
    }
    protected void initFiled(){
        dataBinding = DataBindingUtil.setContentView(this,getLayoutId());
        presenter = getPresenter();
        context = this;
    }

    protected void showSnackBar(View view,String message){
        showSnackBar(view,message,"",null);
    }

    protected void showSnackBar(View view, String message, String cue, View.OnClickListener listener){
        Snackbar.make(view,message, Snackbar.LENGTH_SHORT).setAction(cue,listener).show();
    }


    /**
     * 初始化所有的view
     */
    protected abstract void initView();

    /**
     *
     * @return 当前活动的presenter
     */
    protected abstract P getPresenter();

    /**
     *
     * @return 视图布局id
     */
    protected abstract @LayoutRes int getLayoutId();
}
