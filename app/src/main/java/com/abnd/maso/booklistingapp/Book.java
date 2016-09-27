package com.abnd.maso.booklistingapp;

/**
 * Created by mariosoberanis on 9/26/16.
 */

public class Book {

    // TODO: 9/26/16  Well implement the image using PICASO or GLIDE in future version
    private String mBookImageURL;

    private String mBookTitle;
    private String mBookDescription;

    // TODO: 9/26/16 Fix book_item to include Author
    private String mBookAuthor;

    private String mBookPublisher;
    private String mBooKPublishDate;

    public Book(String mBookTitle, String mBookAuthor, String mBooKPublishDate) {
        this.mBookTitle = mBookTitle;
        this.mBookAuthor = mBookAuthor;
        this.mBooKPublishDate = mBooKPublishDate;
    }

    public String getBookImageURL() {
        return mBookImageURL;
    }

    public String getBookTitle() {
        return mBookTitle;
    }

    public String getBookSubTitle() {
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

    public Book(String bookTitle, String bookDescription, String bookPublisher, String booKPublishDate) {
        this.mBookTitle = bookTitle;
        this.mBookDescription = bookDescription;
        this.mBookPublisher = bookPublisher;
        this.mBooKPublishDate = booKPublishDate;
    }
}
