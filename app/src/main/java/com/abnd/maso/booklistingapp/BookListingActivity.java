package com.abnd.maso.booklistingapp;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class BookListingActivity extends AppCompatActivity
        implements LoaderCallbacks<List<Book>> {

    private static final String LOG_TAG = BookListingActivity.class.getName();
    /**
     * Sample JSON response for a Google Books query
     * https://www.googleapis.com/books/v1/volumes?q=Java&maxResults=3
     */
    private static final String BOOKS_REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    /**
     * Adapter for the list Books
     */
    private BookAdapter mAdapter;
    /**
     * Constant value for the book loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private int BOOK_LOADER_ID = 1;
    private Button mSearch;
    private EditText mQueryBook;
    private String mSearchQuery;


    /**
     * TextView that is display when the list is empty
     */
    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_listing);

        final ListView bookListView = (ListView) findViewById(R.id.activity_book_listing);
        mQueryBook = (EditText) findViewById(R.id.search_text_input);
        mSearch = (Button) findViewById(R.id.search_btn);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);

        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        // Get a refrece to the ConnectivityManager to check state of network connetivity
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        //Get details on the currently active default data network
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        // If there is a network connetoin, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();

        } else {
            loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            mEmptyStateTextView.setText(R.string.no_internet);
        }


        //Set an item click listener to search
        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String query = mQueryBook.getText().toString().replaceAll(" ", "+");
                if (query.isEmpty()) {
                    Toast.makeText(BookListingActivity.this, R.string.pleace_type, Toast.LENGTH_LONG).show();
                    return;
                }
                // TODO: 10/26/16  add maxResult to sharepreferences
                query = BOOKS_REQUEST_URL + query + "&maxResults=15";

                mSearchQuery = query;
                Log.v(LOG_TAG, mSearchQuery.toString());

                View loadingIndicator = findViewById(R.id.loading_indicator);
                loadingIndicator.setVisibility(View.VISIBLE);

                mAdapter = new BookAdapter(BookListingActivity.this, new ArrayList<Book>());
                bookListView.setAdapter(mAdapter);

                // Get a reference to the ConnectivityManager to check state of network connectivity
                ConnectivityManager connectivityManager = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                //Get details on the currently active default data network
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                // If there is a network connection, fetch data
                if (networkInfo != null && networkInfo.isConnected()) {
                    LoaderManager loaderManager = getLoaderManager();
                    loaderManager.restartLoader(BOOK_LOADER_ID, null, BookListingActivity.this);
                } else {
                    loadingIndicator = findViewById(R.id.loading_indicator);
                    loadingIndicator.setVisibility(View.GONE);
                    mEmptyStateTextView.setText(R.string.no_internet);
                }
            }
        });


    }

    @Override
    public Loader<List<Book>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new BookLoader(this, mSearchQuery);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {
        // Hide loading indicator
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        //Set empty state text to display "No Books Found".
        mEmptyStateTextView.setText(R.string.no_books);

        // Clear the adaptor of previous book data
        mAdapter.clear();

        //If there is a valid list will populate the adapter
        if (books != null && !books.isEmpty()) {
            mAdapter.addAll(books);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        // Loader reset, clean adapter data
        mAdapter.clear();

    }
}





