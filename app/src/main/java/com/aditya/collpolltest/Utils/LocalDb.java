package com.aditya.collpolltest.Utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.aditya.collpolltest.Model.Saved;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class LocalDb extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "local.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_Feeds_saved = "feedsaved";
    public static final String TABLE_FEED_SAVED =
            "CREATE TABLE IF NOT EXISTS  feedsaved " +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "feedId VACHAR," +
                    "likes VACHAR," +
                    "name VACHAR ," +
                    "posted VACHAR," +
                    "author INTEGER," +
                    "image VACHAR)";


    public LocalDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.i("Database", "DATABASE_VERSION==>" + DATABASE_VERSION);
    }


    public void onCreate(SQLiteDatabase database) {
        Log.i("Database", "Table is got populated==>" + DATABASE_NAME);
        database.execSQL(TABLE_FEED_SAVED);
        Log.i("Database", "Table is got populated==>" + TABLE_FEED_SAVED);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            // use this if any db chnages

            db.execSQL(TABLE_FEED_SAVED);
        }
        Log.i("CVDatabase", "Data base got upgraded");
        onCreate(db);
    }


    public void insertAddtocart(String feedId, String name, String likes, String posted, String author, String image) {


        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        if (sqLiteDatabase != null) {
            try {
                String query = "insert into " + TABLE_Feeds_saved + " ("
                        + "feedId" + ","
                        + "name" + ","
                        + "likes" + ","
                        + "posted" + ","
                        + "author" + ","
                        + "image" + ""
                        + ") values (?,?,?,?,?,?)";
                SQLiteStatement insertStmt = sqLiteDatabase.compileStatement(query);
                insertStmt.clearBindings();
                insertStmt.bindString(1, feedId);
                insertStmt.bindString(2, name);
                insertStmt.bindString(3, likes);
                insertStmt.bindString(4, posted);
                insertStmt.bindString(5, author);
                insertStmt.bindString(6, image);
                insertStmt.executeInsert();
                Log.i("Mydatabase", "Added Successfully");

            } catch (Exception ex) {
                // Logger.LogDebug(LOG_TAG, ex.getMessage());
            }
            // dataBaseHelper.close();
            sqLiteDatabase.close();
        }
    }

    public boolean isRecordExistInDatabase(String value) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_Feeds_saved + " where feedId= + '"+value+"'";
       // Cursor c = db.rawQuery("SELECT * FROM  +TABLE_Fe eds_saved WHERE name = '"+name+"'", null);

        Cursor c = db.rawQuery(query, null);
        if (c.moveToFirst()) {
            //Record exist
            c.close();
            return true;
        }
        //Record available
        c.close();
        return false;
    }


    public void deleteAllRecordsProducts() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_Feeds_saved);
        Log.i(TAG, "All records deleted");
    }


    public ArrayList<Saved> getAllData() {

        ArrayList<Saved> list = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_Feeds_saved;

        SQLiteDatabase db = this.getWritableDatabase();
        try {

            Cursor cursor = db.rawQuery(selectQuery, null);
            try {


                // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    do {
                        Saved obj = new Saved();
                        //only one column
                        obj.setId(cursor.getString(cursor.getColumnIndex("feedId")));
                        obj.setName(cursor.getString(cursor.getColumnIndex("name")));
                        obj.setLikes(cursor.getString(cursor.getColumnIndex("likes")));
                        obj.setPublishDate(cursor.getString(cursor.getColumnIndex("posted")));
                        obj.setAuthor(cursor.getString(cursor.getColumnIndex("author")));
                        obj.setImage(cursor.getString(cursor.getColumnIndex("image")));
                        list.add(obj);
                    } while (cursor.moveToNext());
                }

            } finally {
                try {
                    cursor.close();
                } catch (Exception ignore) {
                }
            }

        } finally {
            try {
                db.close();
            } catch (Exception ignore) {
            }
        }

        return list;
    }

    public void deleteFeed(String feedId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String qry = "DELETE FROM " + TABLE_Feeds_saved + " where feedId= + '"+feedId+"'";
        Log.i("UpdateTable", qry);
        db.execSQL(qry);

    }

}
