package com.example.ccd;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class Tab_Main extends AppCompatActivity {
    TabLayout tabLayout;
    PagerAdapter adapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab__main);

        initViewPager();
    }

    private void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        //프래그먼트 리스트에 추가
        List<Fragment> listFragment = new ArrayList<>();
        listFragment.add(new homeFragment());
        listFragment.add(new bookStatusFragment());
        listFragment.add(new chatbotFragment());

        //탭 어댑터에 리스트 넘겨준 후 뷰페이저 연결
        PagerAdapter fragmentPagerAdapter = new PagerAdapter(getSupportFragmentManager(), listFragment);
        viewPager.setAdapter(fragmentPagerAdapter);

        //각 탭 이름 지정
        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("홈"));
        tabLayout.addTab(tabLayout.newTab().setText("독서 상황"));
        tabLayout.addTab(tabLayout.newTab().setText("챗봇"));

        //뷰페이저 이동했을 때 & 탭 눌렸을 때 해당 위치로 이동
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        viewPager.setCurrentItem(tab.getPosition());
                        break;
                    case 1:
                        viewPager.setCurrentItem(tab.getPosition());
                        break;
                    case 2:
                        viewPager.setCurrentItem(tab.getPosition());
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
