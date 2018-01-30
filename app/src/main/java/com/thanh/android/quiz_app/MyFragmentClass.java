package com.thanh.android.quiz_app;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MyFragmentClass extends Fragment {
    int position;
    public View rootView;
    //Overriden method onCreateView
    public static MyFragmentClass newInstance(int position){
        MyFragmentClass result = new MyFragmentClass();
        Bundle args = new Bundle();
        args.putInt("put_int",position);
        result.setArguments(args);
        return result;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final int pos = getArguments().getInt("put_int");
        final Question_class myQuestionClass = MainActivity.currArrayListQuesstionGroupClass.get(MainActivity.currQuesstionGroupClass).getQuestionClassArrayList().get(pos);

        rootView = inflater.inflate(R.layout.fragment_main, null);

        WebView myWebView = (WebView) rootView.findViewById(R.id.webview_question) ;
        myWebView.loadData(myQuestionClass.getQuestion(),"text/html; charset=UTF-8",null);

        final LinearLayout ln_layout = (LinearLayout) rootView.findViewById(R.id.ln_answer);
        for (int i=0;i<myQuestionClass.getAnswer().size();i++){
            TextView result_view = new TextView(getContext());
            // format text view
            // layout fill_parent
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            result_view.setLayoutParams(params);
            //background
            result_view.setBackground(getResources().getDrawable(R.drawable.my_border_answer));
            // padding
            result_view.setPadding(20, 50, 20, 50);
            // add title
            String tmp_title = null;
            switch (i) {
                case 0:
                    tmp_title = "A   -   ";
                    break;
                case 1:
                    tmp_title = "B   -    ";
                    break;
                case 2:
                    tmp_title = "C   -    ";
                    break;
                case 3:
                    tmp_title = "D   -    ";
                    break;
                case 4:
                    tmp_title = "E   -    ";
                    break;
                case 5:
                    tmp_title = "F   -    ";
                    break;
            }
            result_view.setText(tmp_title + myQuestionClass.getAnswer().get(i));
            final int finalI = i;
            result_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myQuestionClass.setAnswer_choose(finalI);
                    //set choose
                    view.setBackground(getResources().getDrawable(R.drawable.my_border_answer_choose));
                    TextView tmp_text_view_true = (TextView)ln_layout.getChildAt(myQuestionClass.getAnswer_true());
                    tmp_text_view_true.setBackground(getResources().getDrawable(R.drawable.my_border_answer_true));
                    if (myQuestionClass.isChoose_true()){
                        view.setBackground(getResources().getDrawable(R.drawable.my_border_answer_choose_true));
                        MainActivity.tabLayout.getTabAt(MainActivity.tabLayout.getSelectedTabPosition()).setIcon(R.drawable.star_ok);
                    }else{
                        MainActivity.tabLayout.getTabAt(MainActivity.tabLayout.getSelectedTabPosition()).setIcon(R.drawable.star_ng);
                    }

                }
            });
            ln_layout.addView(result_view);
        }
        if(myQuestionClass.getAnswer_choose() < 999) {
            TextView tmp_text_vView = (TextView) ln_layout.getChildAt(myQuestionClass.getAnswer_true());
            tmp_text_vView.setBackground(getResources().getDrawable(R.drawable.my_border_answer_true));
        }

        return rootView;
    }
}
