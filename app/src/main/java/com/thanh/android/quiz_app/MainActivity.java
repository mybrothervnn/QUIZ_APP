package com.thanh.android.quiz_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    // Danh sach tên các BỘ câu hỏi dùng để cho người dùng chọn
    public static ArrayList<String> arrList_question_group = new ArrayList<>();
    //Danh sách dữ liệu bộ các câu hỏi.
    public static ArrayList<Quesstion_group_class> currArrayListQuesstionGroupClass;

    public static String DB_NAME = "TRAC_NGHIEM_LOP_8.s3db";
    // Bộ câu hỏi hiển thị
    public static int currQuesstionGroupClass;
    public static int currQuesstionGroupClass_old;
    // Câu hỏi hiển thị
    public static int currQuesstionClass;

    //    todo use FragmentStatePagerAdapter 4: Declare TabLayout and ViewPager
    public static TabLayout tabLayout;
    public FrameLayout mFrameLayout;
    MyFragmentClass my_fragment_class;
    //    todo notification 3: declare layout
    RelativeLayout notificationCount1;
    TextView notificationCount_number;

    TextView txt_question_group_tittle;
    TextView fab;
    ProgressBar progressBar;
    private Handler progressBarHandler = new Handler();
    private int status = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //todo toolbar 2: init object toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //todo toolbar 3: set for Actionbar of AppCompatActivity
        setSupportActionBar(toolbar);
        init();
    }

    private void init() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.lime_500));
        tabLayout.setSelectedTabIndicatorHeight(View.MEASURED_HEIGHT_STATE_SHIFT);

        //Process bar
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //-- fab
        fab = (TextView) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update_process_bar(150);
            }
//                Snackbar.make(view, "Chúc mừng bạn đã vượt qua kỷ lục của câu hỏi này !", Snackbar.LENGTH_LONG).show();
        });


        mFrameLayout = (FrameLayout) findViewById(R.id.frame_container);
//        my_fragment_class = new MyFragmentClass();
        txt_question_group_tittle = (TextView) findViewById(R.id.txt_question_tittle);

        AI_request_class ai_request_class = new AI_request_class(getApplicationContext());

        // Truyền danh sách cho danh sách hiển thị bộ câu hỏi
        arrList_question_group = new ArrayList<>();
        arrList_question_group.addAll(ai_request_class.getCTG_CD_FV_fromListURL());
        // Danh sách toàn bộ các bộ câu hỏi mà hệ thống yêu cầu phải làm
        currArrayListQuesstionGroupClass = null;
        currArrayListQuesstionGroupClass = new ArrayList<>();
        for (String url:ai_request_class.getListURL()
                ) {
            currArrayListQuesstionGroupClass.add(ai_request_class.getQuesstion_group_class_byURL(url));
        }

        // Bộ câu hỏi hiển thị
        currQuesstionGroupClass = 0;
        // Câu hỏi hiển thị
        currQuesstionClass = 0;
        loadQuestionToDisplay(currQuesstionGroupClass);
    }

    private void update_process_bar(final int i) {
        progressBar.setMax(i);
        //tạo 1 thread để tăng trang thái status của progress
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(status <i){
                    //tăng trạng thái của progress
                    status = status +1 ;
                    try {
                        //nghỉ 1 giây trước khi update giá trị mới của progressbar
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // sử dụng Handler để set lại giá trị mới cho progressbar trong 1 tiến trình khác
                    progressBarHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            //update giá trị progressbar
                            progressBar.setProgress(status);
                            // update giá trị ở TextView
                            fab.setText(status+"/"+i+" giây");
                        }
                    });
                }
                if (status >= 100){
                    try {
                        //nghỉ 1 giây sau khi kết thúc update progressbar
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //set lại trạng thái cho progressbar để load lại khi bấm vào nút lần tiếp theo
                    status = 0;

                }
            }

        }).start();//start Thread
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //        todo notification 11: mutiply data return from  QuestionGroupDisplay.java ->
        //        todo notification 12_END: edit theme for popup menu to diaglog in AndroidManifest.xml
        if(currQuesstionGroupClass != currQuesstionGroupClass_old) {
//            Toast.makeText(this, String.valueOf(data.getIntExtra("position_return", 0)), Toast.LENGTH_SHORT).show();
            loadQuestionToDisplay(currQuesstionGroupClass);
        }
    }

    private void loadQuestionToDisplay(final int currQuesstionGroupClass) {
        //hiển thị tiêu đề
        txt_question_group_tittle.setText(currArrayListQuesstionGroupClass.get(currQuesstionGroupClass).get_questionGroup_tittle());
        // Tạo ra 10 tab
        tabLayout.removeAllTabs();
        for (int i=0;i<currArrayListQuesstionGroupClass.get(currQuesstionGroupClass).getQuestionClassArrayList().size();i++){
            tabLayout.addTab(
                    tabLayout.newTab()
                            .setText("" + (i + 1)));
            //đánh dấu chưa làm
            if (currArrayListQuesstionGroupClass.get(currQuesstionGroupClass).getQuestionClassArrayList().get(i).isAnswered()) {
                if (currArrayListQuesstionGroupClass.get(currQuesstionGroupClass).getQuestionClassArrayList().get(i).isChoose_true()) {
                    tabLayout.getTabAt(i).setIcon(R.drawable.star_ok);
                }else{
                    tabLayout.getTabAt(i).setIcon(R.drawable.star_ng);
                }
            }else{
                tabLayout.getTabAt(i).setIcon(R.drawable.star_null);
            }
            tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    setCurrentTabFragment(tab.getPosition());
                    currQuesstionClass = tab.getPosition();
                    final TabLayout.Tab tmp_tab = tab;
                    //todo:xử lý breaking recorded 1
                    // Sau n giây nếu còn ở tab cũ thì có nghĩa là đang làm bài tab đó, không thì chỉ là lướt qua thôi.
                    int n = 3;
                    //tiến trình đếm.
                    progressBarHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Do something after 5s = 5000ms
                            if (currQuesstionClass == tmp_tab.getPosition())
                                check_if_breaking_records();
                        }
                    }, 1000*n);
                    check_if_breaking_records();
                }

                private void check_if_breaking_records() {
                    if (currArrayListQuesstionGroupClass.get(currQuesstionGroupClass).getQuestionClassArrayList().get(currQuesstionClass).isAnswered())
                        Snackbar.make(null,"Câu này đã trả lời rồi!",Snackbar.LENGTH_SHORT).show();
                    // if (đã trả lời)
                        // hiển thị processbar với độ dài là thời gian đã trả lời trước đó. (-> get_recorded)
                        // sẽ được dừng lại hoặc thay đổi khi (kích chọn câu trả lời) hoặc (hết thời gian)

                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                }
                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                }
            });
        }
        // kich hoạt onTabSelected
//        tabLayout.getTabAt(1).select();
        setCurrentTabFragment(0);
        tabLayout.getTabAt(0).select();
//        tabLayout.notify();

    }

    private void setCurrentTabFragment(int position) {
        replaceFragment(position);
    }

    private void replaceFragment(int position) {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        my_fragment_class = MyFragmentClass.newInstance(position);
        ft.replace(R.id.frame_container, my_fragment_class);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
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
                Intent intent  = new Intent(getApplicationContext(), activity_question_group_display.class);
                startActivityForResult(intent,1);
            }
        });
        notificationCount_number = (TextView)  MenuItemCompat.getActionView(item1).findViewById(R.id.badge_notification_1);
        notificationCount_number.setText(String.valueOf(currArrayListQuesstionGroupClass.size()));
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
            DB_NAME = "TRAC_NGHIEM_IQ.s3db";
            recreate();
            return true;
        }
        if (id == R.id.action_test2) {
            DB_NAME = "TRAC_NGHIEM_EN_KID.s3db";
            recreate();
            return true;
        }
        if (id == R.id.action_test3) {
            DB_NAME = "TRAC_NGHIEM_EN_CB.s3db";
            recreate();
            return true;
        }
        if (id == R.id.action_test4) {
            DB_NAME = "TRAC_NGHIEM_TOAN_2.s3db";
            recreate();
            return true;
        }
        if (id == R.id.action_test5) {
            DB_NAME = "TRAC_NGHIEM_TOAN_3.s3db";
            recreate();
            return true;
        }
        if (id == R.id.action_test6) {
            DB_NAME = "TRAC_NGHIEM_TOAN_4.s3db";
            recreate();
            return true;
        }
        if (id == R.id.action_test7) {
            DB_NAME = "TRAC_NGHIEM_TOAN_5.s3db";
            recreate();
            return true;
        }
        if (id == R.id.action_test8) {
            DB_NAME = "TRAC_NGHIEM_LOP_8.s3db";
            recreate();
            return true;
        }
        if (id == R.id.action_test9) {
            DB_NAME = "TRAC_NGHIEM.s3db";
            recreate();
            return true;
        }
        if (id == R.id.action_test10) {
            currArrayListQuesstionGroupClass.get(currQuesstionGroupClass).delete_choose_Action_info();
            recreate();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
