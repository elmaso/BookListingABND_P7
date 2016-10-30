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

/**
 * Created by mariosoberanis on 10/29/16.
 */

public class BookListingActivity extends AppCompatActivity
        implements LoaderCallbacks<List<Book>> {

    private static final String LOG_TAG = BookListingActivity.class.getName();
    private static final String BOOKS_REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    private int BOOK_LOADER_ID = 1;
    private BookAdapter mAdapter;
    private Button mSearch;
    private EditText mQueryBook;
    private String mSearchQuery;

    private TextView mEmptyStateTextView;
    private View mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_listing);

        ListView bookListView = (ListView) findViewById(R.id.activity_book_listing);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        mSearch = (Button) findViewById(R.id.search_btn);
        mQueryBook = (EditText) findViewById(R.id.search_text_input);
        mLoadingIndicator = findViewById(R.id.loading_indicator);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        mAdapter = new BookAdapter(this, new ArrayList<Book>());
        bookListView.setAdapter(mAdapter);

        // Get a reference to the ConnectivityManager to check state of network connetivity
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        //Get details on the currently active default data network
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(BOOK_LOADER_ID, null, this);
            mLoadingIndicator.setVisibility(View.VISIBLE);

        } else {
            mLoadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet);
        }


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

                // Get a refrece to the ConnectivityManager to check state of network connetivity
                ConnectivityManager connectivityManager = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);

                //Get details on the currently active default data network
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

                // If there is a network connection, fetch data
                if (networkInfo != null && networkInfo.isConnected()) {
                    LoaderManager loaderManager = getLoaderManager();
                    loaderManager.restartLoader(BOOK_LOADER_ID, null, BookListingActivity.this);
                    mEmptyStateTextView.setText("");
                    //Starting a new search lest update the UI
                    mAdapter.clear();
                    mLoadingIndicator.setVisibility(View.VISIBLE);
                } else {
                    mLoadingIndicator.setVisibility(View.GONE);
                    mEmptyStateTextView.setText(R.string.no_internet);
                }

            }

        });

    }

    @Override
    public Loader<List<Book>> onCreateLoader(int i, Bundle bundle) {
        Log.i(LOG_TAG, "TEST: onCreateLoader");
        return new BookLoader(this, mSearchQuery);

    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {

        mLoadingIndicator.setVisibility(View.GONE);
        mQueryBook.clearFocus();

        // Clear the adapter of previous earthquake data
        mAdapter.clear();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (books != null && !books.isEmpty()) {
            mAdapter.addAll(books);
        } else {
            //Set empty state text to display "No Books Found".
            mEmptyStateTextView.setText(R.string.no_books);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        Log.i(LOG_TAG, "TEST: onResetLoader");
        mAdapter.clear();
    }

}
