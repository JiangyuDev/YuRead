package pers.jiangyu.yuread.view;


import pers.jiangyu.yuread.R;
import pers.jiangyu.yuread.base.BaseFragment;
import pers.jiangyu.yuread.base.BasePresenterImpl;
import pers.jiangyu.yuread.contract.ForumContract;
import pers.jiangyu.yuread.databinding.FragmentForumBinding;

public class ForumFragment extends BaseFragment<FragmentForumBinding, BasePresenterImpl>implements ForumContract.View{

    @Override
    protected BasePresenterImpl getPresenter() {
        return null;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_forum;
    }
}