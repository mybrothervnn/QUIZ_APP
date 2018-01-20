package com.thanh.android.quiz_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener{
    public static String arr_question_group[] = {
            "Intel",
            "SamSung",
            "Nokia",
            "Simen",
            "AMD",
            "KIC",
            "ECD"
    };
    // Danh sach tên các BỘ câu hỏi dùng để cho người dùng chọn
    public static ArrayList<String> arrList_question_group = new ArrayList<>();
    //Danh sách dữ liệu bộ các câu hỏi.
    public static ArrayList<Quesstion_group_class> currArrayListQuesstionGroupClass;
    // Bộ câu hỏi hiển thị
    public static int currQuesstionGroupClass;
    public static int currQuesstionGroupClass_old;
    // Câu hỏi hiển thị
    public static int currQuesstionClass;

    //    todo use FragmentStatePagerAdapter 4: Declare TabLayout and ViewPager
    private TabLayout tabLayout;
    private ViewPager viewPager;
    Pager adapter;
    //    todo notification 3: declare layout
    RelativeLayout notificationCount1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //todo toolbar 2: init object toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //todo toolbar 3: set for Actionbar of AppCompatActivity
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        init();
    }

    private void init() {
        AI_request_class ai_request_class = new AI_request_class(getApplicationContext());
        init_FragmentStatePager();
        // Truyền danh sách cho danh sách hiển thị bộ câu hỏi
        arrList_question_group.addAll(ai_request_class.getCTG_CD_FV_fromListURL());
        // Danh sách toàn bộ các bộ câu hỏi mà hệ thống yêu cầu phải làm
        currArrayListQuesstionGroupClass = new ArrayList<>();
        for (String url:ai_request_class.getListURL()
                ) {
            currArrayListQuesstionGroupClass.add(ai_request_class.getQuesstion_group_class_byURL(url));
        }
        // Bộ câu hỏi hiển thị
        currQuesstionGroupClass = 10;
        // Câu hỏi hiển thị
        currQuesstionClass = 1;
        loadQuestionToDisplay(currQuesstionGroupClass);
    }

    private void loadQuestionToDisplay(int currQuesstionGroupClass) {
        //remove tab
        //remove viewPager

        //create tab
        //create pager
        //------------------------
        //remove tab
        tabLayout.removeAllTabs();
        //remove viewPager
        viewPager.removeAllViews();

        //create tab
        int tmp_index = 0;
        for (Question_class tmp:currArrayListQuesstionGroupClass.get(currQuesstionGroupClass).getQuestionClassArrayList()
                ) {
            //tạo 1 tab với tiêu đề ""
            tabLayout.addTab(tabLayout.newTab().setText("_"+String.valueOf(tmp_index)));
            //đánh dấu chưa làm
            tabLayout.getTabAt(tmp_index).setIcon(R.drawable.star_null);
            tmp_index +=1;
            //---------
            //view pager
//            adapter.addTab();
//            adapter.notifyDataSetChanged();
        }
        //create pager
        adapter = new Pager(getSupportFragmentManager(),tmp_index);
        viewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    private void init_FragmentStatePager() {
        //    todo use FragmentStatePagerAdapter 5:Initializing the tablayout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        //Adding the tabs using addTab() method
//        tabLayout.addTab(tabLayout.newTab().setText("Tab1"));
//        tabLayout.addTab(tabLayout.newTab().setText("Tab2"));
//        tabLayout.addTab(tabLayout.newTab().setText("Tab3"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

//    todo use FragmentStatePagerAdapter 6:Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.pager);

//    todo use FragmentStatePagerAdapter 7:Creating our pager adapter
        adapter = new Pager(getSupportFragmentManager(), tabLayout.getTabCount());

//    todo use FragmentStatePagerAdapter 8:Adding adapter to pager
        viewPager.setAdapter(adapter);

//    todo use FragmentStatePagerAdapter 9:Adding onTabSelectedListener to swipe views
        tabLayout.setOnTabSelectedListener(this);
//    todo use FragmentStatePagerAdapter 10:Adding addOnPageChangeListener to swipe Tab -> Pager.java
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //        todo notification 11: mutiply data return from  QuestionGroupDisplay.java ->
        //        todo notification 12_END: edit theme for popup menu to diaglog in AndroidManifest.xml
        if(currQuesstionGroupClass != currQuesstionGroupClass_old) {
            Toast.makeText(this, String.valueOf(data.getIntExtra("position_return", 0)), Toast.LENGTH_SHORT).show();
            loadQuestionToDisplay(currQuesstionGroupClass);
        }
    }

    //todo toolbar 4: override method when Create menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //        todo notification 4: inflafe my menu into activity menu
        getMenuInflater().inflate(R.menu.menu_main, menu);
//        todo notification 5: set item1 for display my layout (button display)
        MenuItem item1 = menu.findItem(R.id.action_settings);
        MenuItemCompat.setActionView(item1, R.layout.notification_count_badge_in_actionbar);
//        todo notification 6: get layout for get button
        notificationCount1 = (RelativeLayout) MenuItemCompat.getActionView(item1);
//        todo notification 7: get button from layout
        Button tmp = (Button) notificationCount1.findViewById(R.id.button1);
//        todo notification 8: set event click for display -> QuestionGroupDisplay.java
        tmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "Tim hoai eo ra, buc qua- cuoi cung cung phai ra cho :D", Toast.LENGTH_SHORT).show();
                Intent intent  = new Intent(getApplicationContext(),activity_question_group_display.class);
                startActivityForResult(intent,1);
            }
        });
        return true;
    }
//todo toolbar 6_END: override method for event onOptionsItemSelected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_test1) {
            //    todo use FragmentStatePagerAdapter 14_END: test remove tab.
            tabLayout.removeTab(tabLayout.getTabAt(1));
            return true;
        }
        if (id == R.id.action_test2) {
            //    todo use FragmentStatePagerAdapter 15: test add tab
            tabLayout.addTab(tabLayout.newTab().setText("added tab OK"));
            return true;
        }
        if (id == R.id.action_test3) {
            //    todo use FragmentStatePagerAdapter 16: test get item
            adapter.getItem(tabLayout.getTabCount()-1);
            adapter.notifyDataSetChanged();
            return true;
        }
        if (id == R.id.action_test4) {
            //    todo use FragmentStatePagerAdapter 17: test add fragment
            adapter.addTab();
            adapter.notifyDataSetChanged();
            return true;
        }
        if (id == R.id.action_test5) {
            return true;
        }
        if (id == R.id.action_test6) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
