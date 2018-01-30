//package com.thanh.android.quiz_app;
//
//import android.content.Context;
//import android.content.res.Resources;
//import android.support.v4.view.PagerAdapter;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.webkit.WebView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.util.ArrayList;
//
///**
// * Created by d_thanh on 2018/01/30.
// */
//
//public class ViewPagerAdapter extends PagerAdapter {
//
//    private Context mContext;
//    private Resources mResources;
//    private ArrayList<Question_class> mQuestion_classes;
//
//    public ViewPagerAdapter(Context mContext, Resources mResources, ArrayList<Question_class> mQuestion_classes) {
//        this.mContext = mContext;
//        this.mQuestion_classes = mQuestion_classes;
//        this.mResources = mResources;
//    }
//
//    @Override
//    public int getCount() {
//        return mQuestion_classes.size();
//    }
//    @Override
//    public Object instantiateItem(ViewGroup container, final int position) {
//        View itemView = LayoutInflater.from(mContext).inflate(R.layout.fragment_main, container, false);
//        final Question_class myQuestionClass = MainActivity.currArrayListQuesstionGroupClass.get(MainActivity.currQuesstionGroupClass).getQuestionClassArrayList().get(position);
//
//        WebView mywebView = (WebView) itemView.findViewById(R.id.webview_question);
//        mywebView.loadData(myQuestionClass.getQuestion(),"text/html; charset=UTF-8",null);
//        final LinearLayout ln_answer = (LinearLayout)itemView.findViewById(R.id.ln_answer);
//
//        //khoi tao 4 cau hoi
//        for (int i=0; i<myQuestionClass.getAnswer().size();i++){
//            TextView mTextView = new TextView(itemView.getContext());
//            // layout fill_parent
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//            mTextView.setLayoutParams(params);
//            // padding
//            mTextView.setPadding(20, 50, 20, 50);
//            // add title
//            String tmp_title = null;
//            switch (i) {
//                case 0: tmp_title = "A   -   "; break;
//                case 1: tmp_title = "B   -    "; break;
//                case 2: tmp_title = "C   -    "; break;
//                case 3: tmp_title = "D   -    ";break;
//                case 4: tmp_title = "E   -    "; break;
//                case 5:  tmp_title = "F   -    "; break;
//            }
//            mTextView.setText(tmp_title + myQuestionClass.getAnswer().get(i));
//            final int finalI = i;
//            mTextView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    //set choose
//                    MainActivity.currArrayListQuesstionGroupClass.get(MainActivity.currQuesstionGroupClass).getQuestionClassArrayList().get(position).setAnswer_choose(finalI);
//
//                    //set choose
//                    view.setBackground(mResources.getDrawable(R.drawable.my_border_answer_choose));
//                    //set true
//                    TextView tmp_text_view_true = (TextView)ln_answer.getChildAt(myQuestionClass.getAnswer_true());
//                    tmp_text_view_true.setBackground(mResources.getDrawable(R.drawable.my_border_answer_true));
//
//                    //set tab icon
//                    if (myQuestionClass.isChoose_true()){
//                        view.setBackground(mResources.getDrawable(R.drawable.my_border_answer_choose_true));
//                        MainActivity.tabLayout.getTabAt(MainActivity.tabLayout.getSelectedTabPosition()).setIcon(R.drawable.star_ok);
//                    }else{
//                        MainActivity.tabLayout.getTabAt(MainActivity.tabLayout.getSelectedTabPosition()).setIcon(R.drawable.star_ng);
//                    }
//                }
//            });
//            //if choose
//            if (MainActivity.currArrayListQuesstionGroupClass.get(MainActivity.currQuesstionGroupClass).getQuestionClassArrayList().get(position).getAnswer_choose()<999) {
//                if (myQuestionClass.isChoose_true()) {
//                    mTextView.setBackground(mResources.getDrawable(R.drawable.my_border_answer_choose_true));
//                    MainActivity.tabLayout.getTabAt(MainActivity.tabLayout.getSelectedTabPosition()).setIcon(R.drawable.star_ok);
//                } else {
//                    mTextView.setBackground(mResources.getDrawable(R.drawable.my_border_answer_choose));
//                    //hien thi cau dung
//////                  //set true
////                    TextView tmp_text_view_true = (TextView)ln_answer.getChildAt(myQuestionClass.getAnswer_true());
////                    tmp_text_view_true.setBackground(mResources.getDrawable(R.drawable.my_border_answer_true));
//
//                    MainActivity.tabLayout.getTabAt(MainActivity.tabLayout.getSelectedTabPosition()).setIcon(R.drawable.star_ng);
//                }
//            }else{
//                mTextView.setBackground(mResources.getDrawable(R.drawable.my_border_answer));
//            }
//            ln_answer.addView(mTextView);
//        }
//        //add to container
//        container.addView(itemView);
//
//        return itemView;
//    }
//
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView((View) object);
//    }
//
//    @Override
//    public boolean isViewFromObject(View view, Object object) {
//        return view == object;
//    }
//}