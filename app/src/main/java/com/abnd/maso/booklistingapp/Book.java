package com.abnd.maso.booklistingapp;

import android.graphics.Bitmap;

/**
 * Created by mariosoberanis on 9/26/16.
 */

public class Book {

    // TODO: 9/26/16  Well implement the image using PICASO or GLIDE in future version
    private Bitmap mBookThumbnail;

    private String mBookTitle;
    private String mBookDescription;
    private String mBookAuthor;
    private String mBookPublisher;
    private String mBooKPublishDate;

    public Book(String mBookTitle, String mBookAuthor, String mBooKPublishDate) {
        this.mBookTitle = mBookTitle;
        this.mBookAuthor = mBookAuthor;
        this.mBooKPublishDate = mBooKPublishDate;

    }

    public Book(String bookTitle, String bookDescription, String bookAuthor, String booKPublishDate) {
        this.mBookTitle = bookTitle;
        this.mBookDescription = bookDescription;
        this.mBookAuthor = bookAuthor;
        this.mBooKPublishDate = booKPublishDate;
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

    public String getBookPublisher() {
        return mBookPublisher;
    }

    public String getBooKPublishDate() {
        return mBooKPublishDate;
    }
}
