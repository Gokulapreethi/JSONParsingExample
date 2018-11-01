package com.example.preethi.jsonparsingexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Preethi on 3/28/2018.
 */

public class sqlitedatabase extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 3;
    /**
     * DB Inside COMMedia Folder //
     */
    public static final String DATABASE_NAME = Environment
            .getExternalStorageDirectory().getAbsolutePath()
            + "/MyJsonDatabase.db";
    public static SQLiteDatabase db = null;
    private static sqlitedatabase dbHelper = null;
    Context context;
    public static final String EULATABLE = "eulaagree";
    public static final String EULACREATE = "create table if not exists '" + EULATABLE + "'(name varchar (1),id varchar(1),mobile varchar(1))";


    public sqlitedatabase(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    public static sqlitedatabase getDB(Context cntxt) {
        if (dbHelper == null) {
            dbHelper = new sqlitedatabase(cntxt);
            dbHelper.openDatabase();
        }
        return dbHelper;
    }

    /**
     * Open writable database when database is in null.
     */
    public void openDatabase() {
        if (db == null) {
            try {
                db = dbHelper.getWritableDatabase();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void openReadableDatabase() {
        try {
            if (db == null)
                db = getReadableDatabase();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(EULACREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public synchronized void close() {
        super.close();
        db.close();
        db = null;
    }

    public void insertChat_history(String name,String id,String mobile) {
        try {
            int row_id = 0;
            if (!db.isOpen())
                openDatabase();
            ContentValues cv = new ContentValues();
            cv.put("name", name);
            cv.put("id", id);
            cv.put("mobile", mobile);
            row_id = (int) db.insert("eulaagree", null, cv);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }


    public ArrayList<jsonBean> getAllRow(String query) {
        ArrayList<jsonBean> bean = new ArrayList<>();
        Cursor cur;
        try {
            if (db == null)
                db = getReadableDatabase();

            try {
                if (db != null) {
                    if (!db.isOpen())
                        openDatabase();
                    cur = db.rawQuery(query, null);
                    cur.moveToFirst();
                    while (!cur.isAfterLast()) {
                        jsonBean chatBean =new jsonBean();
                        chatBean.setClientName(cur.getString(cur.getColumnIndex("name")));
                        chatBean.setClientMail(cur.getString(cur.getColumnIndex("id")));
                        chatBean.setClientNo(cur.getString(cur.getColumnIndex("mobile")));
                        bean.add(chatBean);
                        cur.moveToNext();
                    }
                    cur.close();
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return bean;
        }
    }
}
