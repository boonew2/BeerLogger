package com.example.weston.beerlogger;

/**
 * Created by weston on 3/9/15.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "BeerLogger";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_REVIEWS = "Reviews";
    public static final String TABLE_BYOB = "BYOB";
    public static final String COL_REVIEWS_ID = "_id";
    public static final String COL_REVIEWS_DATE_CREATED = "date_created";
    public static final String COL_REVIEWS_DATE_UPDATED = "date_updated";
    public static final String COL_REVIEWS_NAME = "name";
    public static final String COL_REVIEWS_REVIEW = "review";
    public static final String COL_REVIEWS_PRICE = "price";

    private static final String DATABASE_CREATE = "create table "
            + TABLE_REVIEWS            + " ("
            + COL_REVIEWS_ID           + " integer primary key autoincrement,"
            + COL_REVIEWS_NAME         + " text not null,"
            + COL_REVIEWS_PRICE        + " real,"
            + COL_REVIEWS_DATE_CREATED + " integer not null,"
            + COL_REVIEWS_DATE_UPDATED + " integer not null,"
            + COL_REVIEWS_REVIEW       + " text"
            + " );";
    public MySQLiteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database){
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                + newVersion + " which will destroy all old data");
        database.execSQL("drop table if exists "+ TABLE_REVIEWS);
        onCreate(database);
    }
}
