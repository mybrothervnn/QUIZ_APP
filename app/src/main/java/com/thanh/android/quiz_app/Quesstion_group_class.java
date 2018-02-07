package com.thanh.android.quiz_app;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by d_thanh on 2018/01/11.
 */

public class Quesstion_group_class {
    private String question_URL_VALUE;
    private ArrayList<Question_class> questionClassArrayList;
    QuestionDataBaseHelper questionDataBaseHelper;
    Context myContext;
    //

    public Quesstion_group_class(String question_URL_VALUE,Context myContext) {
        this.myContext = myContext;
        this.question_URL_VALUE = question_URL_VALUE;
        this.questionDataBaseHelper = new QuestionDataBaseHelper(myContext);
        this.questionClassArrayList = new ArrayList<>();
        questionClassArrayList.addAll(questionDataBaseHelper.get_questionClassArrayList_from_db(question_URL_VALUE));
        questionDataBaseHelper.update_status_from_ACTION_INFO(this);
    }





    public int count_question(){
        return questionClassArrayList.size();
    }

    public String getQuestion_URL_VALUE() {
        return question_URL_VALUE;
    }

    public void setQuestion_URL_VALUE(String question_URL_VALUE) {
        this.question_URL_VALUE = question_URL_VALUE;
    }

    public ArrayList<Question_class> getQuestionClassArrayList() {
        return questionClassArrayList;
    }

    public void setQuestionClassArrayList(ArrayList<Question_class> questionClassArrayList) {
        this.questionClassArrayList = questionClassArrayList;
    }
    public void insert_ACTION_INFO(String question_id,String action_type, String action_value){
        questionDataBaseHelper.insert_ACTION_INFO(question_URL_VALUE,question_id,action_type,action_value);
    }
    public String get_choose_from_ACTION_INFO(String question_id,String action_type){
        return questionDataBaseHelper.get_choose_from_ACTION_INFO(question_URL_VALUE,question_id,action_type);
    }
    public String toString(){
        String tmp_string = "_____Quesstion_group_class_____\n";
        tmp_string =tmp_string +"question_URL_VALUE="+ question_URL_VALUE + "\n";
        for (int i=0;i<questionClassArrayList.size();i++){
            tmp_string = tmp_string + "Question_class___" + String.valueOf(i)+ ":\n";
            tmp_string = tmp_string + questionClassArrayList.get(i).toString();
        }
        return tmp_string;
    }

    public void delete_choose_Action_info() {
        questionDataBaseHelper.delete_choose_Action_info(question_URL_VALUE);
    }
    public String get_questionGroup_tittle(){
        return questionDataBaseHelper.getCTG_NAME_from_URL(question_URL_VALUE);
    }
}
