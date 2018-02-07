package com.thanh.android.quiz_app;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by d_thanh on 2018/01/11.
 */

public class AI_request_class {
    private Context myContext;
    private QuestionDataBaseHelper questionDataBaseHelper;

    public AI_request_class(Context myContext) {
        this.myContext = myContext;
        questionDataBaseHelper = new QuestionDataBaseHelper(myContext);
        //create ACTION_INFO
        questionDataBaseHelper.createTable_ACTION_INFO();
    }

    public ArrayList<String> getListURL(){
        ArrayList<String> result = questionDataBaseHelper.getListURL();
        return result;
    }
    public ArrayList<String> getCTG_CD_FV_fromListURL(){
        ArrayList<String> result = new ArrayList<>();
        for (String url:getListURL()
             ) {
            result.add(questionDataBaseHelper.getCTG_NAME_from_URL(url));
        }
        return result;
    }
    public String getSingle_question() {
        return "14030";
    }
    public Quesstion_group_class getQuesstion_group_class_byURL(String URL){
        return new Quesstion_group_class(URL,myContext);
    }

}
