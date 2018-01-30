package com.thanh.android.quiz_app;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by d_thanh on 2018/01/11.
 */

public class Question_class {
    private String question_id;
    private String question;
    private ArrayList<String> answer;
//    public ArrayList<TextView> textViewArrayList;
    //------------
    private int answer_true = 999;
    private int answer_choose = 999;

    public TextView getTrue_view() {
        return true_view;
    }

    public void setTrue_view(TextView true_view) {
        this.true_view = true_view;
    }

    //------------
    private TextView true_view;

    public Question_class(String question_id) {
        this.question_id = question_id;
    }

    public Question_class(String question_id, String question) {
        this.question_id = question_id;
        this.question = question;
//        this.textViewArrayList = new ArrayList<>();
    }

    public Question_class(String question, ArrayList<String> answer) {
        this.question = question;
        this.answer = answer;
    }

    public Question_class(String question, ArrayList<String> answer, int answer_true) {
        this.question = question;
        this.answer = answer;
        this.answer_true = answer_true;
    }

    public Question_class(String question, ArrayList<String> answer, int answer_true, int answer_choose) {
        this.question = question;
        this.answer = answer;
        this.answer_true = answer_true;
        this.answer_choose = answer_choose;
    }
//-------------------------------------------

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getQuestion() {
        return question;
    }

    public WebView getQuestion_as_Webview(Context context){
        WebView result = new WebView(context);
        result.loadData(question,"text/html; charset=UTF-8",null);
        return result;
    }
//    public ArrayList<TextView> getAnswer_as_TextView(final Context context, final Resources resources){
//        for (int i=0;i< getAnswer().size();i++) {
//            final TextView tmp_TextView = new TextView(context);
//            // layout fill_parent
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//            tmp_TextView.setLayoutParams(params);
//            //background
//            tmp_TextView.setBackground(resources.getDrawable(R.drawable.my_border_answer));
//            // padding
//            tmp_TextView.setPadding(20, 50, 20, 50);
//            // add title
//            String tmp_title = null;
//            switch (i) {
//                case 0:
//                    tmp_title = "A   -   ";
//                    break;
//                case 1:
//                    tmp_title = "B   -    ";
//                    break;
//                case 2:
//                    tmp_title = "C   -    ";
//                    break;
//                case 3:
//                    tmp_title = "D   -    ";
//                    break;
//                case 4:
//                    tmp_title = "E   -    ";
//                    break;
//                case 5:
//                    tmp_title = "F   -    ";
//                    break;
//            }
//            tmp_TextView.setText(tmp_title + getAnswer().get(i));
//
////            //Nếu câu này đã trả lời thì hiển thị câu trả lời
////            if (getAnswer_choose() == i) {
////                tmp_TextView.setBackground(resources.getDrawable(R.drawable.my_border_answer_choose));
////                if(getAnswer_true() ==i){
////                    tmp_TextView.setBackground(resources.getDrawable(R.drawable.my_border_answer_choose_true));
////                }else{
////                    tmp_TextView.setBackground(resources.getDrawable(R.drawable.my_border_answer_true));
////                }
////            }
//
//            //event
//            final int finalI = i;
////            tmp_TextView.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View view) {
////                    if (getAnswer_choose() == 999) {
////                        setAnswer_choose(finalI);
////                        //set câu đã chọn
////                        textViewArrayList.get(finalI).setBackground(resources.getDrawable(R.drawable.my_border_answer_choose));
////                        //hiển thị câu đúng
////                        textViewArrayList.get(getAnswer_true()).setBackground(resources.getDrawable(R.drawable.my_border_answer_true));
////
////                        if (isChoose_true()){
////                            textViewArrayList.get(finalI).setBackground(resources.getDrawable(R.drawable.my_border_answer_choose_true));
////                            MainActivity.tabLayout.getTabAt(MainActivity.tabLayout.getSelectedTabPosition()).setIcon(R.drawable.star_ok);
////                        }else {
////                            textViewArrayList.get(finalI).setBackground(resources.getDrawable(R.drawable.my_border_answer_choose));
////                            MainActivity.tabLayout.getTabAt(MainActivity.tabLayout.getSelectedTabPosition()).setIcon(R.drawable.star_ng);
////                        }
////                    }
////                }
////            });
//            //end_ add for result
//            textViewArrayList.add(tmp_TextView);
//        }
//        return textViewArrayList;
//    }

    private void display_answer(Context context, final Resources resources) {
        for (int i=0;i < answer.size();i++ ){

        }
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<String> getAnswer() {
        return answer;
    }

    public void setAnswer(ArrayList<String> answer) {
        this.answer = answer;
    }

    public int getAnswer_true() {
        return answer_true;
    }

    public void setAnswer_true(int answer_true) {
        this.answer_true = answer_true;
    }

    public int getAnswer_choose() {
        return answer_choose;
    }

    public void setAnswer_choose(int answer_choose) {
        this.answer_choose = answer_choose;
    }
    //ADD FUNCTION
    public boolean isAnswered(){
        return answer_choose < 999;
    }
    public int coun_answer(){
        return answer.size();
    }
    public boolean isChoose_true(){
        return isAnswered() && answer_true == answer_choose;
    }
    public String toString(){
        String tmp_string = "_____Question_class_____\n";
        tmp_string =tmp_string +"question_id="+ question_id + "\n";
        tmp_string =tmp_string + "question=" +question+ "\n";
        tmp_string =tmp_string + "ArrayList<String> answer=" +String.valueOf(answer) +"\n";
        return tmp_string;
    }
}
