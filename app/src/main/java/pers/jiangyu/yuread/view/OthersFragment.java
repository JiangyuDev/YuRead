package pers.jiangyu.yuread.view;


import androidx.fragment.app.Fragment;
import pers.jiangyu.yuread.R;
import pers.jiangyu.yuread.base.BaseFragment;
import pers.jiangyu.yuread.base.BasePresenterImpl;
import pers.jiangyu.yuread.contract.ForumContract;
import pers.jiangyu.yuread.databinding.FragmentOthersBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class OthersFragment extends BaseFragment<FragmentOthersBinding, BasePresenterImpl>implements ForumContract.View{
    @Override
    protected BasePresenterImpl getPresenter() {
        return null;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_others;
    }
}