package pers.jiangyu.yuread.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import pers.jiangyu.yuread.R;
import pers.jiangyu.yuread.base.BaseActivity;
import pers.jiangyu.yuread.base.BasePresenterImpl;
import pers.jiangyu.yuread.contract.MainContract;
import pers.jiangyu.yuread.databinding.ActivityMainBinding;

public class  MainActivity extends BaseActivity <ActivityMainBinding, BasePresenterImpl>implements MainContract.View {

    private String userId;

    public String getUserId(){
        return userId;
    }

    public static void actionStart(Activity activity, String userId){
        Intent intent = new Intent(activity,MainActivity.class);
        intent.putExtra("userId",userId);
        activity.startActivity(intent);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

    }


    @Override
    protected void initView() {
        userId = getIntent().getStringExtra("userId");
        dataBinding.bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        dataBinding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                dataBinding.bottomNavigation.getMenu().getItem(position).setChecked(true);
                //写滑动页面后做的事，使每一个fragmen与一个page相对应

            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        final ArrayList<Fragment> fgLists = new ArrayList<>(3);
        fgLists.add(new ForumFragment());
        fgLists.add(new FoundFragment());
        fgLists.add(new OthersFragment());

        FragmentPagerAdapter mPageAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fgLists.get(position);
            }

            @Override
            public int getCount() {
                return fgLists.size();
            }
        };

        dataBinding.viewPager.setAdapter(mPageAdapter);
        dataBinding.viewPager.setOffscreenPageLimit(2);

    }

    @Override
    protected BasePresenterImpl getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.item_news:
                    dataBinding.viewPager.setCurrentItem(0);
                    return true;
                case R.id.item_find:
                    dataBinding.viewPager.setCurrentItem(1);
                    return true;
                case R.id.item_lib:
                    dataBinding.viewPager.setCurrentItem(2);
                    return true;

            }
            return false;
        }
    };
}
