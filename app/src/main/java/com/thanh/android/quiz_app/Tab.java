package com.thanh.android.quiz_app;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Belal on 2/3/2016.
 */

//    todo use FragmentStatePagerAdapter 12:Our class extending fragment -> MainActivity.java
public class Tab extends Fragment {
    boolean hidden = true;
    boolean chosse_flag = false;
    TextView true_view;
    ArrayList<Integer> listId;
    View result;
    //Overriden method onCreateView
    public static Tab newInstance(int position){
        Tab result = new Tab();
        Bundle args = new Bundle();
        args.putInt("put_int",position);
        Question_class question_class = MainActivity.currArrayListQuesstionGroupClass.get(MainActivity.currQuesstionGroupClass).getQuestionClassArrayList().get(position);
        args.putString("this_question",question_class.getQuestion());
        args.putStringArrayList("this_answer",question_class.getAnswer());
        result.setArguments(args);
        return result;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final int pos = getArguments().getInt("put_int");
        final Question_class tab_question = MainActivity.currArrayListQuesstionGroupClass.get(MainActivity.currQuesstionGroupClass).getQuestionClassArrayList().get(pos);
        listId = new ArrayList<>();
        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        result = inflater.inflate(R.layout.fragment_main, container, false);
        WebView tmp = (WebView) result.findViewById(R.id.webview_question) ;
        tmp.loadData(getArguments().getString("this_question"),"text/html; charset=UTF-8",null);

        //todo: truyền nội dung câu trả lời cho mỗi fragment khi create
        LinearLayout ln_answer = (LinearLayout) result.findViewById(R.id.ln_answer);

        // for mỗi câu trả lời
        for (int i=0;i< getArguments().getStringArrayList("this_answer").size();i++) {
            // create text view
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            final TextView tmp_TextView = new TextView(getContext());
            listId.add(tmp_TextView.getId());
            tmp_TextView.setLayoutParams(params);
            tmp_TextView.setBackground(getResources().getDrawable(R.drawable.my_border_answer));

            // padding
            tmp_TextView.setPadding(20, 50, 20, 50);
            String tmp_title = null;
            switch (i) {
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
//            tmp_TextView.setText(tmp_TextView.getText() + "____" + String.valueOf(tab_question.getAnswer_true()));

            //Câu trả lời đúng
            if (tab_question.getAnswer_true() == i){
                true_view = tmp_TextView;
            }

            //Nếu câu này đã trả lời thì hiển thị câu trả lời
            if (tab_question.getAnswer_choose() == i) {
                tmp_TextView.setBackground(getResources().getDrawable(R.drawable.my_border_answer_choose));
                if(tab_question.getAnswer_true() ==i){
                    tmp_TextView.setBackground(getResources().getDrawable(R.drawable.my_border_answer_choose_true));
                }else{
                    true_view.setBackground(getResources().getDrawable(R.drawable.my_border_answer_true));
                }
            }
            // set event on click
            final int finalI = i;
            tmp_TextView.setOnClickListener(new TextView.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if (tab_question.getAnswer_choose() == 999) {
                        v.setBackground(getResources().getDrawable(R.drawable.my_border_answer_choose));
                        tab_question.setAnswer_choose(finalI);
                        true_view.setBackground(getResources().getDrawable(R.drawable.my_border_answer_true));
                        if (tmp_TextView == true_view){
                            true_view.setBackground(getResources().getDrawable(R.drawable.my_border_answer_choose_true));
                            MainActivity.tabLayout.getTabAt(pos).setIcon(R.drawable.star_ok);
                        }else {
                            MainActivity.tabLayout.getTabAt(pos).setIcon(R.drawable.star_ng);
                        }
                    }
                }
            });

            ln_answer.addView(tmp_TextView);
            if (tab_question.getAnswer_choose() != 999) {
                true_view.setBackground(getResources().getDrawable(R.drawable.my_border_answer_true));
                result.findViewById(listId.get(tab_question.getAnswer_choose())).setBackground(getResources().getDrawable(R.drawable.my_border_answer_choose));

                if (tab_question.isChoose_true()){
                    true_view.setBackground(getResources().getDrawable(R.drawable.my_border_answer_choose_true));
                    MainActivity.tabLayout.getTabAt(pos).setIcon(R.drawable.star_ok);
                }else {
                    MainActivity.tabLayout.getTabAt(pos).setIcon(R.drawable.star_ng);
                }
            }
        }
        return result;
    }

    private void display_answer() {
        TextView tmp_view = (TextView)result.findViewById(listId.get(1));
        tmp_view.setBackground(getResources().getDrawable(R.drawable.my_border_answer_true));
    }
}
