package com.thanh.android.quiz_app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 25/01/2018.
 */

public class Tab_in_class_bk extends Fragment {
    Question_class myQuestionClass;
    View result;
    LinearLayout ln_answer;
    WebView myWebView;

    //Overriden method onCreateView
    public static Tab newInstance(int position){
        Tab result = new Tab();
        Bundle args = new Bundle();
        args.putInt("put_int",position);
//        Question_class question_class = MainActivity.currArrayListQuesstionGroupClass.get(MainActivity.currQuesstionGroupClass).getQuestionClassArrayList().get(position);
//        args.putString("this_question",question_class.getQuestion());
//        args.putStringArrayList("this_answer",question_class.getAnswer());
        result.setArguments(args);
        return result;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
//        Toast.makeText(getContext(), "dsfsdf", Toast.LENGTH_SHORT).show();
        final int pos = getArguments().getInt("put_int");
        result = inflater.inflate(R.layout.fragment_main, container, false);

        myQuestionClass = MainActivity.currArrayListQuesstionGroupClass.get(MainActivity.currQuesstionGroupClass).getQuestionClassArrayList().get(pos);

        myWebView = (WebView) result.findViewById(R.id.webview_question) ;
        myWebView.loadData(myQuestionClass.getQuestion(),"text/html; charset=UTF-8",null);

        ln_answer = (LinearLayout) result.findViewById(R.id.ln_answer);
        // add các câu trả lời.
        for (int i=0;i< myQuestionClass.getAnswer().size();i++ ) {
//            Toast.makeText(getContext(), "test thoi", Toast.LENGTH_SHORT).show();
//            ln_answer.addView(myQuestionClass.getAnswer_as_TextView(getContext(), getResources()).get(i));
        }
        return result;
    }
}
