package pers.jiangyu.yuread.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment<D extends ViewDataBinding, P extends BasePresenterImpl> extends Fragment {

    protected  D dataBinding;

    protected  P presenter;

    protected Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(inflater,getLayoutId(),null,false);
        presenter = getPresenter();
        context = getContext();
        initView();
        return dataBinding.getRoot();
    }

    protected abstract P getPresenter();

    protected abstract void initView();

    protected abstract @LayoutRes int getLayoutId();
}
