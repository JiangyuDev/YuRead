package pers.jiangyu.yuread.view;


import pers.jiangyu.yuread.R;
import pers.jiangyu.yuread.base.BaseFragment;
import pers.jiangyu.yuread.base.BasePresenterImpl;
import pers.jiangyu.yuread.contract.MeContract;
import pers.jiangyu.yuread.databinding.FragmentMeBinding;


/**
 *
 */
public class MeFragment extends BaseFragment<FragmentMeBinding, BasePresenterImpl>implements MeContract.View {
    @Override
    protected BasePresenterImpl getPresenter() {
        return null;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    public MeFragment() {
        // Required empty public constructor
    }


}
