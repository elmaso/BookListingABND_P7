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
        ArrayList<String> list_book = new ArrayList<>();
        list_book.add("San Francisco");
        list_book.add("London");
        list_book.add("Tokyo");
        list_book.add("Mexico City");
        list_book.add("Moscow");
        list_book.add("Rio de Janeiro");
        list_book.add("Paris");

        // Find a reference to the {@link ListView} in the layout
        ListView booklistListView = (ListView) findViewById(R.id.activity_book_listing);

        // Create a new {@link ArrayAdapter} of earthquakes
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, list_book);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        booklistListView.setAdapter(adapter);
    }
}
