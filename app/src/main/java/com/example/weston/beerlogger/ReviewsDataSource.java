package com.example.weston.beerlogger;

/**
 * Created by weston on 3/9/15.
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class ReviewsDataSource {
    private SQLiteDatabase database;
    private MySQLiteHelper dbhelper;
    private String[] allColumns = {MySQLiteHelper.COL_REVIEWS_ID,MySQLiteHelper.COL_REVIEWS_NAME,
                        MySQLiteHelper.COL_REVIEWS_REVIEW, MySQLiteHelper.COL_REVIEWS_PRICE,
                        MySQLiteHelper.COL_REVIEWS_DATE_UPDATED,MySQLiteHelper.COL_REVIEWS_DATE_CREATED};
    public ReviewsDataSource(Context context){
        dbhelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException{
        database = dbhelper.getWritableDatabase();
    }
    public void close(){
        dbhelper.close();
    }
    public Review createReview(String name,String review,float price){
        Date date = new Date();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MySQLiteHelper.COL_REVIEWS_NAME,name);
        contentValues.put(MySQLiteHelper.COL_REVIEWS_REVIEW,review);
        contentValues.put(MySQLiteHelper.COL_REVIEWS_PRICE,price);
        contentValues.put(MySQLiteHelper.COL_REVIEWS_DATE_CREATED, date.getTime());
        contentValues.put(MySQLiteHelper.COL_REVIEWS_DATE_UPDATED,date.getTime());
        long insertId = database.insert(MySQLiteHelper.TABLE_REVIEWS,null,contentValues);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_REVIEWS, allColumns,
                MySQLiteHelper.COL_REVIEWS_ID + " = " + insertId,null,null,null,null);
        cursor.moveToFirst();
        Review newReview = cursorToReview(cursor);
        cursor.close();
        return newReview;
    }

    public Review getReview(long id){
        Cursor cursor = database.query(MySQLiteHelper.TABLE_REVIEWS,allColumns,
                MySQLiteHelper.COL_REVIEWS_ID + " = " + id,null,null,null,null);
        return cursorToReview(cursor);

    }

    private Review cursorToReview(Cursor cursor){
        Review review = new Review(cursor.getLong(cursor.getColumnIndex(MySQLiteHelper.COL_REVIEWS_ID)));
        review.setName(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COL_REVIEWS_NAME)));
        review.setReview(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COL_REVIEWS_REVIEW)));
        review.setPrice(cursor.getFloat(cursor.getColumnIndex(MySQLiteHelper.COL_REVIEWS_PRICE)));
        review.setDateCreated(cursor.getLong(cursor.getColumnIndex(MySQLiteHelper.COL_REVIEWS_DATE_CREATED)));
        review.setDateUpdated(cursor.getLong(cursor.getColumnIndex(MySQLiteHelper.COL_REVIEWS_DATE_UPDATED)));
        return review;
    }

    public void deleteReview(Review review){
        long id = review.getId();
        System.out.println("Review deleted with id: "+id);
        database.delete(MySQLiteHelper.TABLE_REVIEWS,MySQLiteHelper.COL_REVIEWS_ID + " = " +id,null);
    }

    public List<Review> getAllReviews(){
        List<Review> reviews = new ArrayList<Review>();
        Cursor cursor = database.query(MySQLiteHelper.TABLE_REVIEWS,allColumns,null,null,null,null,MySQLiteHelper.COL_REVIEWS_NAME);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Review review = cursorToReview(cursor);
            reviews.add(review);
            cursor.moveToNext();
        }
        cursor.close();
        return reviews;
    }
}
