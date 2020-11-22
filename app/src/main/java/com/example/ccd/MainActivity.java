package com.example.ccd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
//import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;


import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    PagerAdapter adapter;
    private ViewPager viewPager;
    Toolbar my_ToolBar;
    Bundle mBundle; //main bundle
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        my_ToolBar = (Toolbar)findViewById(R.id.myToolBar);
        setSupportActionBar(my_ToolBar);
        setTitle("");
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        initViewPager();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    //추가된 소스, ToolBar에 추가된 항목의 select 이벤트를 처리하는 함수

    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.menuMyInfo:
                Intent intent = new Intent(this, MyInfo.class);
                startActivity(intent);
                break;
            case R.id.menuBookStatus:
                Intent intent2 = new Intent(this, bookStatus.class);
                startActivity(intent2);
                break;
            case R.id.menuNotice:
                Intent intent3 = new Intent(this, notice.class);
                startActivity(intent3);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

//    public void changeFragment(int index){
//        switch(index){
//            case 1:
////                getSupportFragmentManager().beginTransaction().replace(R.id.viewPager, bsFragment).commit();
//                break;
//            case 2:
//                fragmentTransaction.replace(R.id.viewPager,new bookReadFragment());
//                fragmentTransaction.commit();
//                getSupportFragmentManager().beginTransaction().replace(R.id.viewPager, brFragment).commit();
//                break;
//        }
//    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.viewPager, fragment);
        fragmentTransaction.commit();
    }

    private void initViewPager() {
        //프래그먼트 리스트에 추가
        List<Fragment> listFragment = new ArrayList<>();
        listFragment.add(new homeFragment());
        listFragment.add(new bookLookupFragment());
        listFragment.add(new chatbotFragment());
        listFragment.add(new likeFragment());
        listFragment.add(new bestsellerLookupFragment());
        listFragment.add(new ForumFragment());

//        //탭 어댑터에 리스트 넘겨준 후 뷰페이저 연결
//        PagerAdapter fragmentPagerAdapter = new PagerAdapter(getSupportFragmentManager(), listFragment);
//        viewPager.setAdapter(fragmentPagerAdapter);

        //각 탭 이름 지정
        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //뷰페이저 이동했을 때 & 탭 눌렸을 때 해당 위치로 이동
        adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
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
                    case 3:
                        viewPager.setCurrentItem(tab.getPosition());
                        break;
                    case 4:
                        viewPager.setCurrentItem(tab.getPosition());
                        break;
                    case 5:
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

//    public void fragBtnClick(Bundle bundle) {
//        this.mBundle = bundle;
//    }
}