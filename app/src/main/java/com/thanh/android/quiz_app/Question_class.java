package com.thanh.android.quiz_app;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by d_thanh on 2018/01/11.
 */

public class Question_class {
    private String question_id;
    private String question;
    private ArrayList<String> answer;
    //------------
    private int answer_true = 999;
    private int answer_choose = 999;

    public Question_class(String question_id) {
        this.question_id = question_id;
    }

    public Question_class(String question_id, String question) {
        this.question_id = question_id;
        this.question = question;
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
        return answer_choose > 0;
    }
    public int coun_answer(){
        return answer.size();
    }
    public boolean isChoose_true(){
        return isAnswered() && answer_true ==answer_choose;
    }
    public String toString(){
        String tmp_string = "_____Question_class_____\n";
        tmp_string =tmp_string +"question_id="+ question_id + "\n";
        tmp_string =tmp_string + "question=" +question+ "\n";
        tmp_string =tmp_string + "ArrayList<String> answer=" +String.valueOf(answer) +"\n";
        return tmp_string;
    }
}
