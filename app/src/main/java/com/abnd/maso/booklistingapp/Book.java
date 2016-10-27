package com.abnd.maso.booklistingapp;

import android.graphics.Bitmap;

/**
 * Created by mariosoberanis on 9/26/16.
 */

public class Book {


    private Bitmap mBookThumbnail;
    private String mBookTitle;
    private String mBookDescription;
    private String mBookAuthor;

    private String mBooKPublishDate;

    public Book(String bookTitle, String bookDescription, String bookAuthor, String booKPublishDate, Bitmap bookThumbnail) {
        this.mBookTitle = bookTitle;
        this.mBookDescription = bookDescription;
        this.mBookAuthor = bookAuthor;
        this.mBooKPublishDate = booKPublishDate;
        this.mBookThumbnail = bookThumbnail;
    }

    public Bitmap getBookImageURL() {
        return mBookThumbnail;
    }

    public String getBookTitle() {
        return mBookTitle;
    }

    public String getmBookDescription() {
        return mBookDescription;
    }

    public String getBookAuthor() {
        return mBookAuthor;
    }

    public String getBooKPublishDate() {
        return mBooKPublishDate;
    }
}
