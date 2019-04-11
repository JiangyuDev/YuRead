package pers.jiangyu.yuread.view;


import android.app.Activity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import pers.jiangyu.yuread.R;
import pers.jiangyu.yuread.base.BaseFragment;
import pers.jiangyu.yuread.base.BasePresenterImpl;
import pers.jiangyu.yuread.bean.MySettings;
import pers.jiangyu.yuread.contract.MeContract;
import pers.jiangyu.yuread.databinding.FragmentMeBinding;
import pers.jiangyu.yuread.view.adapter.MySettingsAdapter;


/**
 *
 */
public class MeFragment extends BaseFragment<FragmentMeBinding, BasePresenterImpl>implements MeContract.View {

    private List<MySettings> mySettingsList = new ArrayList<>();

    private String userId;

    @Override
    protected BasePresenterImpl getPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        Activity activity = (MainActivity)getActivity();
        userId = ((MainActivity) activity).getUserId();
        initMeSet();//初始化列表数据
        MySettingsAdapter adapter = new MySettingsAdapter(getContext(),R.layout.my_settings_item,mySettingsList);
        ListView listView = (ListView)dataBinding.getRoot().findViewById(R.id.list_me);// 先确定碎片的对象，才可以绑定对应的id
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((adapterView, view, i, l) ->{
            switch (i){
                case 3: InformationActivity.actionStart(getActivity(),userId);
        }

        } );
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    public MeFragment() {
        // Required empty public constructor
    }

    private void initMeSet(){

            MySettings personPage = new MySettings("个人中心",R.drawable.my_page);
            mySettingsList.add(personPage);

            MySettings favorites = new MySettings("我的收藏",R.drawable.my_favorites);
            mySettingsList.add(favorites);
            MySettings box = new MySettings("草稿箱",R.drawable.draft_box);
            mySettingsList.add(box);
            MySettings information = new MySettings("资料",R.drawable.infor);
            mySettingsList.add(information);
            MySettings exit = new MySettings("退出",R.drawable.exit);
            mySettingsList.add(exit);


//            MySettings favorites = new MySettings("我的收藏",);

    }


}
