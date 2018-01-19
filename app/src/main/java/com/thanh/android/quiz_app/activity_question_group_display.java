package com.thanh.android.quiz_app;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class activity_question_group_display extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_group_display);
        setTitle("Hãy lựa chọn bộ câu hỏi sau:");
        init();

    }
    private void init() {
//        lv_question_gr = (ListView) findViewById(R.id.lv_question_gr);
        //todo notification 9: set data adapter for this ListActivity.

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1
                ,MainActivity.arr_question_group);
        setListAdapter(adapter);
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
//        todo notification 10: override event when ListActivity item click. -> MainActivity.java
        Intent intent = getIntent();
        intent.putExtra("position_return", position);
        setResult(1, intent);
        finish();
    }

}
