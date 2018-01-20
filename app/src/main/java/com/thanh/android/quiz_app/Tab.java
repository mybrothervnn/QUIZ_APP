package com.thanh.android.quiz_app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Belal on 2/3/2016.
 */

//    todo use FragmentStatePagerAdapter 12:Our class extending fragment -> MainActivity.java
public class Tab extends Fragment {
    int position;
    static Question_class tab_question;
    //Overriden method onCreateView
    public static Tab newInstance(int position, Question_class question_class){
        Tab result = new Tab();
        Bundle args = new Bundle();
        tab_question = question_class;
        args.putInt("put_int",position);
        args.putString("this_question",question_class.getQuestion());
        args.putStringArrayList("this_answer",question_class.getAnswer());
        result.setArguments(args);
        return result;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        View result = inflater.inflate(R.layout.fragment_main, container, false);
        WebView tmp = (WebView) result.findViewById(R.id.webview_question) ;
        tmp.loadData(getArguments().getString("this_question"),"text/html; charset=UTF-8",null);

        //todo: truyền nội dung câu trả lời cho mỗi fragment khi create
        LinearLayout ln_answer = (LinearLayout) result.findViewById(R.id.ln_answer);

        // for mỗi câu trả lời
        for (int i=0;i< getArguments().getStringArrayList("this_answer").size();i++){
            // create text view
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            TextView tmp_TextView = new TextView(getContext());
            tmp_TextView.setLayoutParams(params);

            // padding
            tmp_TextView.setPadding(20,50,20,50);
            String tmp_title=null;
            switch (i){
                case 0:
                    tmp_title = "A - ";
                    break;
                case 1:
                    tmp_title = "B - ";
                    break;
                case 2:
                    tmp_title = "C - ";
                    break;
                case 3:
                    tmp_title = "D - ";
                    break;
                case 4:
                    tmp_title = "E - ";
                    break;
                case 5:
                    tmp_title = "F - ";
                    break;
            }
            tmp_TextView.setText(tmp_title + getArguments().getStringArrayList("this_answer").get(i) + "");
            tmp_TextView.setBackground(getResources().getDrawable(R.drawable.my_border_answer));
//            // set câu trả lời đúng
//            if (tab_question.getAnswer_true() == i){
//                tmp_TextView.setBackground(getResources().getDrawable(R.drawable.my_border_answer_true));
//            }
            ln_answer.addView(tmp_TextView);
        }
        return result;
    }
}
