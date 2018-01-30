//package com.thanh.android.quiz_app;
//
//import android.content.DialogInterface;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
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
// * Created by Belal on 2/3/2016.
// */
//
////    todo use FragmentStatePagerAdapter 12:Our class extending fragment -> MainActivity.java
//public class Tab extends Fragment {
//    Question_class myQuestionClass;
//    //↓
//    // bao gom
//    LinearLayout ln_answer;
//    WebView myWebView;
//    ArrayList<TextView> myTextViewArrayList;
//
//    boolean hidden = true;
//    boolean chosse_flag = false;
//    TextView true_view;
//    ArrayList<Integer> listId;
//
//    //Overriden method onCreateView
//    public static Tab newInstance(int position){
//        Tab result = new Tab();
//        Bundle args = new Bundle();
//        args.putInt("put_int",position);
//        Question_class question_class = MainActivity.currArrayListQuesstionGroupClass.get(MainActivity.currQuesstionGroupClass).getQuestionClassArrayList().get(position);
//        args.putString("this_question",question_class.getQuestion());
//        args.putStringArrayList("this_answer",question_class.getAnswer());
//
////        args.putSerializable("curr_Question_class",MainActivity.currArrayListQuesstionGroupClass.get(MainActivity.currQuesstionGroupClass).getQuestionClassArrayList());
//        result.setArguments(args);
//        return result;
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
//        final int pos = getArguments().getInt("put_int");
//        View result = inflater.inflate(R.layout.fragment_main, container, false);
//
//        myQuestionClass = MainActivity.currArrayListQuesstionGroupClass.get(MainActivity.currQuesstionGroupClass).getQuestionClassArrayList().get(pos);
//
//        myWebView = (WebView) result.findViewById(R.id.webview_question) ;
//        myWebView.loadData(myQuestionClass.getQuestion(),"text/html; charset=UTF-8",null);
////        Toast.makeText(getContext(), String.valueOf(MainActivity.currArrayListQuesstionGroupClass.get(MainActivity.currQuesstionGroupClass).getQuestionClassArrayList().get(pos).getAnswer_choose()), Toast.LENGTH_SHORT).show();
//        ln_answer = (LinearLayout) result.findViewById(R.id.ln_answer);
//
//        for (int i=0;i< myQuestionClass.getAnswer().size();i++ ) {
//            ln_answer.addView(get_default_answer(i,myQuestionClass.isAnswered(),pos));
//        }
//        return result;
//    }
//
//    @Override
//    public void onResume() {
////        Toast.makeText(getContext(), "i'm in  onResume() ", Toast.LENGTH_SHORT).show();
////        Toast.makeText(getContext(), "chosse_flag = "+String.valueOf(this.chosse_flag), Toast.LENGTH_SHORT).show();
//        super.onResume();
//    }
//
//    private View get_default_answer(final int i, Boolean checked, final int curr_pos) {
////        Toast.makeText(getContext(), "i'm here get_default_answer", Toast.LENGTH_SHORT).show();
//        TextView result_view = new TextView(getContext());
//        // format text view
//        // layout fill_parent
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        result_view.setLayoutParams(params);
//        //background
//        //if choose
//        if (MainActivity.currArrayListQuesstionGroupClass.get(MainActivity.currQuesstionGroupClass).getQuestionClassArrayList().get(curr_pos).getAnswer_choose()<999) {
////            TextView tmp_true = (TextView) ln_answer.getChildAt(myQuestionClass.getAnswer_true());
////            tmp_true.setBackground(getResources().getDrawable(R.drawable.my_border_answer_true));
////            result.setBackground(getResources().getDrawable(R.drawable.my_border_answer_choose));////
//            if (myQuestionClass.isChoose_true()) {
//                result_view.setBackground(getResources().getDrawable(R.drawable.my_border_answer_choose_true));
//                MainActivity.tabLayout.getTabAt(MainActivity.tabLayout.getSelectedTabPosition()).setIcon(R.drawable.star_ok);
//            } else {
//                result_view.setBackground(getResources().getDrawable(R.drawable.my_border_answer_choose));
//                //hien thi cau dung
//                if (result_view.getRootView() != null) {
//                    Toast.makeText(getContext(), result_view.getRootView().toString(), Toast.LENGTH_SHORT).show();
//                }
////                Toast.makeText(getContext(), result_view.getParent().toString(), Toast.LENGTH_SHORT).show();
////                Toast.makeText(getContext(), String.valueOf(ln_true_tmp.getChildCount()), Toast.LENGTH_SHORT).show();
//                MainActivity.tabLayout.getTabAt(MainActivity.tabLayout.getSelectedTabPosition()).setIcon(R.drawable.star_ng);
//            }
//        }else{
//            result_view.setBackground(getResources().getDrawable(R.drawable.my_border_answer));
//        }
//        // padding
//        result_view.setPadding(20, 50, 20, 50);
//        // add title
//        String tmp_title = null;
//        switch (i) {
//            case 0:
//                tmp_title = "A   -   ";
//                break;
//            case 1:
//                tmp_title = "B   -    ";
//                break;
//            case 2:
//                tmp_title = "C   -    ";
//                break;
//            case 3:
//                tmp_title = "D   -    ";
//                break;
//            case 4:
//                tmp_title = "E   -    ";
//                break;
//            case 5:
//                tmp_title = "F   -    ";
//                break;
//        }
//        result_view.setText(tmp_title + myQuestionClass.getAnswer().get(i));
//        //if checked
//        if (checked) {
////            TextView tmp_true = (TextView) ln_answer.getChildAt(myQuestionClass.getAnswer_true());
////            TextView tmp_choose = (TextView) ln_answer.getChildAt(myQuestionClass.getAnswer_choose());
////
////            tmp_true.setBackground(getResources().getDrawable(R.drawable.my_border_answer_true));
////            tmp_choose.setBackground(getResources().getDrawable(R.drawable.my_border_answer_choose));
////
////            if (myQuestionClass.isChoose_true()) {
////                tmp_choose.setBackground(getResources().getDrawable(R.drawable.my_border_answer_choose_true));
////                MainActivity.tabLayout.getTabAt(MainActivity.tabLayout.getSelectedTabPosition()).setIcon(R.drawable.star_ok);
////            } else {
////                MainActivity.tabLayout.getTabAt(MainActivity.tabLayout.getSelectedTabPosition()).setIcon(R.drawable.star_ng);
////            }
//        }
//
//
//        //add event
//        result_view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                chosse_flag = true;
//                //set choose to Main question
//                MainActivity.currArrayListQuesstionGroupClass.get(MainActivity.currQuesstionGroupClass).getQuestionClassArrayList().get(curr_pos).setAnswer_choose(i);
////
//                //set choose
//                view.setBackground(getResources().getDrawable(R.drawable.my_border_answer_choose));
//                TextView tmp_text_view_true = (TextView)ln_answer.getChildAt(myQuestionClass.getAnswer_true());
//                tmp_text_view_true.setBackground(getResources().getDrawable(R.drawable.my_border_answer_true));
//                if (myQuestionClass.isChoose_true()){
//                    view.setBackground(getResources().getDrawable(R.drawable.my_border_answer_choose_true));
//                    MainActivity.tabLayout.getTabAt(MainActivity.tabLayout.getSelectedTabPosition()).setIcon(R.drawable.star_ok);
//                }else{
//                    MainActivity.tabLayout.getTabAt(MainActivity.tabLayout.getSelectedTabPosition()).setIcon(R.drawable.star_ng);
//                }
////                Toast.makeText(getContext(), String.valueOf(isStateSaved()), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        return result_view;
//    }
//}
