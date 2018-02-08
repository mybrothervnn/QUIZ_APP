package com.thanh.android.quiz_app;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Created by d_thanh on 2018/01/09.
 */

public class QuestionDataBaseHelper extends SQLiteOpenHelper{
    //The Android's default system path of your application database.
    private static String DB_PATH = "/data/data/com.thanh.android.quiz_app/databases/";

    private static String DB_NAME = "TRAC_NGHIEM.s3db";

    private SQLiteDatabase myDataBase;

    private final Context myContext;
//BEGIN  _ SQLITE FORM ----------------------------------------------------

    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     *
     * @param context
     */
    public QuestionDataBaseHelper(Context context) {

        super(context, DB_NAME, null, 1);
        this.myContext = context;
        //        //CONNECT  DB
        try {
            this.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        openDataBase();
    }

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     */
    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if (dbExist) {
            //do nothing - database already exist
        } else {

            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase() {

        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        } catch (SQLiteException e) {

            //database does't exist yet.

        }

        if (checkDB != null) {

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     */
    private void copyDataBase() throws IOException {

        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {

        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {

        if (myDataBase != null)
            myDataBase.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    //END  _ SQLITE FORM ----------------------------------------------------
    // Add your public helper methods to access and get content from the database.
    // You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
    // to you to create adapters for your views.
    public ArrayList<String> getCtgList() {
        ArrayList<String> result = new ArrayList<>();
        // load all word from db then add to ListWord
        String selectSQL = "SELECT VALUE from CTG_MST where PARENT_CTG_CD_FV = '83' and LEVEL_CD_FV = '4' limit 20";
        Cursor cursor = myDataBase.rawQuery(selectSQL, null);
        while (cursor.moveToNext()) {
            result.add(cursor.getString(0));
        }
        cursor.close();
        return result;
    }

    public ArrayList<Question_class> get_questionClassArrayList_from_db(String question_URL_VALUE) {
        ArrayList<Question_class> result = new ArrayList<>();
        // Tạo ra 10 question theo câu hỏi - add id, question
        String selectSQL = "  select ORDER_VALUE,VALUE from QUESTION_TBL where URL_VALUE = '"+question_URL_VALUE+"'";
        Cursor cursor = myDataBase.rawQuery(selectSQL, null);
        while (cursor.moveToNext()) {
            Question_class tmpQuestion_class = new Question_class(cursor.getString(0),cursor.getString(1));
            result.add(tmpQuestion_class);
        }
        cursor.close();
        // với mỗi câu hỏi -> add danh sách câu trả lời vào.
        int tmp_num = 1;
        for (Question_class tmpQuestion_class:result) {
            ArrayList<String> answerArrayList = new ArrayList<>();
            String getQuestionSQL = " select VALUE,RESULT_FLAG from ANSWER_TBL where URL_VALUE = '"+question_URL_VALUE+"' and QUESTION_ORDER = '"+String.valueOf(tmp_num)+"'";
            Cursor cursor1 = myDataBase.rawQuery(getQuestionSQL,null);
            int tmp_true=0;
            while(cursor1.moveToNext()){
                //add câu trả lời
                answerArrayList.add(cursor1.getString(0));
                //add câu đúng (câu hỏi nào có số thứ tự = Answer_true thì câu đó đúng.)
                if (Objects.equals(cursor1.getString(1), "1")){
                    tmpQuestion_class.setAnswer_true(tmp_true);
                }
                tmp_true +=1;
            }
            tmp_num +=1;
            tmpQuestion_class.setAnswer(answerArrayList);
            cursor1.close();
        }

        return result;
    }
    public String getCTG_NAME_from_URL(String url){
        String result = null;
        String selectSQL = "SELECT VALUE from CTG_MST where CTG_CD_FV = '"+url+"'";
        Cursor cursor = myDataBase.rawQuery(selectSQL, null);
        while (cursor.moveToNext()) {
            result = cursor.getString(0);
        }
        cursor.close();
        return result;
    }

}
