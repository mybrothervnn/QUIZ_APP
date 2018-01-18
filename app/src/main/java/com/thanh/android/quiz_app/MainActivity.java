package com.thanh.android.quiz_app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //todo toolbar 2: init object toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //todo toolbar 3: set for Actionbar of AppCompatActivity
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
//todo toolbar 4: override method when Create menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
//todo toolbar 6_END: override method for event onOptionsItemSelected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_test1) {
            Toast.makeText(this, "action_test1", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.action_test2) {
            Toast.makeText(this, "action_test2", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.action_test3) {
            return true;
        }
        if (id == R.id.action_test4) {
            return true;
        }
        if (id == R.id.action_test5) {
            return true;
        }
        if (id == R.id.action_test6) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
