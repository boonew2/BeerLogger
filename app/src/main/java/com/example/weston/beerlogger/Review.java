package com.example.weston.beerlogger;

import java.util.Date;

/**
 * Created by weston on 3/9/15.
 */
public class Review {
    private long id;
    private String name;
    private String review;
    private Date dateCreated;
    private Date dateUpdated;
    private float price;

    public Review(long id){
        this.id = id;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setDateCreated(long dateCreated) {
        this.dateCreated = new Date(dateCreated);
    }

    public void setDateUpdated(long dateUpdated) {
        this.dateUpdated = new Date(dateUpdated);
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }
    public long getId(){
        return this.id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public String getReview(){
        return this.review;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public float getPrice(){
        return this.price;
    }
    @Override
    public String toString(){
        return this.name;
    }
}
