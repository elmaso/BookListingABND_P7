package com.abnd.maso.booklistingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class BookListingActivity extends AppCompatActivity {

    private static final String LOG_TAG = BookListingActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_listing);

        // Create a fake list of earthquake locations.
        ArrayList<Book> books = new ArrayList<>();
        books.add  (new Book("Mysql 5.0","This is a good description of the book","Paquigua","2016"));
        books.add  (new Book("PlanBB 5.0","This is a good description of the PlanBB his is a good description of the PlanBB his is a good description of the PlanBB","Telnor","2016"));
        books.add  (new Book("Firebase","This is a good description of the Firebase","Ots","2015"));
        books.add  (new Book("Polymer","This is a good description of the Polymer","Gdg","2016"));



        // Find a reference to the {@link ListView} in the layout
        ListView booklistListView = (ListView) findViewById(R.id.activity_book_listing);

        // Create a new {@link BookAdapter} of earthquakes
        BookAdapter adapter = new BookAdapter(this,books );

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        booklistListView.setAdapter(adapter);
    }
}
