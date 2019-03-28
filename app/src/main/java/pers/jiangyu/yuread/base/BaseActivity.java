package pers.jiangyu.yuread.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import cn.bmob.v3.Bmob;
import pers.jiangyu.yuread.R;


public abstract class BaseActivity <D extends ViewDataBinding,P extends BasePresenterImpl>extends AppCompatActivity {

    protected D dataBinding;

    protected P presenter;

    protected Context context;

    private Toolbar toolbar;

    private String toolBarTitle = "";

    private TextView tv_title;

    private boolean pic_back = false;

    private boolean pic_menu = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this, "42386c376c519dff99e9ac386fca3e0f");
        initFiled();
        initToolBar();
        initView();

    }

    private void initToolBar(){
        if(toolbar !=null){

            tv_title = toolbar.findViewById(R.id.tb_title);
            tv_title.setText(toolBarTitle);
            if(pic_back){
                ImageView back = toolbar.findViewById(R.id.tb_back);
                 back.setVisibility(View.VISIBLE);
                 back.setOnClickListener(view->finish());

            }
            if(pic_menu){
                ImageView menu = toolbar.findViewById(R.id.tb_menu);
                menu.setVisibility(View.VISIBLE);
//                menu.setOnClickListener(view -> );
            }

        }

    }

    protected void setUseBackAndMenu(boolean pic_back, boolean pic_menu) {
        this.pic_back = pic_back;
        this.pic_menu = pic_menu;
    }

    protected void setToolBarTittle(int tittleResId){
        toolBarTitle = getString(tittleResId);
    }

    protected void setToolBarTittle(String tittle){
        toolBarTitle = tittle;
    }

    protected void initFiled(){
        dataBinding = DataBindingUtil.setContentView(this,getLayoutId());
        presenter = getPresenter();
        context = this;
        toolbar = findViewById(R.id.tool_bar);
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
