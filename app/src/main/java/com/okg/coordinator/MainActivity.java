package com.okg.coordinator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> dataList = new ArrayList<>();
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ImageView ivBackground, ivTitleBack;
    private AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int x = 0; x < 10; x++) {
            dataList.add("x" + x);
        }
        mTabLayout = findViewById(R.id.tablayout);
        mViewPager = findViewById(R.id.viewpager);
        ivBackground = findViewById(R.id.iv_background);
        ivTitleBack = findViewById(R.id.btn_title_back);
        appBarLayout = findViewById(R.id.layout_appbar);
        initViewPager();
    }

    private void initViewPager() {
        // 创建一个集合,装填Fragment
        ArrayList<Fragment> fragments = new ArrayList<>();
        ArrayList<String> titles = new ArrayList<>();
        // 装填
        fragments.add(new ListFragment());
        fragments.add(new ListFragment());
        fragments.add(new ListFragment());
        titles.add("头条");
        titles.add("热点");
        titles.add("娱乐");
        // 创建ViewPager适配器
        CommonFragmentPagerAdapter myPagerAdapter = new CommonFragmentPagerAdapter(getSupportFragmentManager(), fragments, titles);
        // 给ViewPager设置适配器
        mViewPager.setAdapter(myPagerAdapter);
        // 使用 TabLayout 和 ViewPager 相关联
        mTabLayout.setupWithViewPager(mViewPager);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                // verticalOffset==0,完全展开状态，verticalOffset==-totalScrollRange,完全收起状态
                Log.d("CBaymax", "verticalOffset:" + verticalOffset);
                Log.d("CBaymax", "totalScrollRange:" + appBarLayout.getTotalScrollRange());
                if (verticalOffset == 0) {
                    Log.d("CBaymax", "完全展开");
                } else if ((appBarLayout.getTotalScrollRange() + verticalOffset) <= 0) {
                    Log.d("CBaymax", "完全收起");
                }
                if (Math.abs(verticalOffset) < appBarLayout.getTotalScrollRange() / 2) {
                    ivTitleBack.setVisibility(View.GONE);
                } else {
                    ivTitleBack.setVisibility(View.VISIBLE);
                }

                float alpha = Math.abs(verticalOffset) * 1.0f / appBarLayout.getTotalScrollRange();
                ivTitleBack.setAlpha(alpha);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) ivBackground.getLayoutParams();
                layoutParams.topMargin = verticalOffset;
                ivBackground.setLayoutParams(layoutParams);
            }
        });
    }
}