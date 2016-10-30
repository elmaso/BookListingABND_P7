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

    //Connection variables
    private static final String BOOKS_REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    private int BOOK_LOADER_ID = 1;
    private ConnectivityManager cm;
    private NetworkInfo networkInfo;
    private LoaderManager lm;

    //UI variables elements
    private BookAdapter mAdapter;
    private EditText mQueryBook;
    private String mSearchQuery;
    private TextView mEmptyStateTextView;
    private View mLoadingIndicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_listing);

        ListView bookListView = (ListView) findViewById(R.id.activity_book_listing);

        // TODO: 10/30/16 incorporate ButterKnife library

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        mQueryBook = (EditText) findViewById(R.id.search_text_input);
        mLoadingIndicator = findViewById(R.id.loading_indicator);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        mAdapter = new BookAdapter(this, new ArrayList<Book>());
        bookListView.setAdapter(mAdapter);


        // Get a reference to the ConnectivityManager to check state of network connetivity
        cm = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        //Get details on the currently active default data network
        networkInfo = cm.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            lm = getLoaderManager();
            lm.initLoader(BOOK_LOADER_ID, null, this);
        } else {
            mLoadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet);
        }

    }

    public void SearchBook(View v) {

        // We only start searching if there's a valid string
        String query = mQueryBook.getText().toString().replaceAll(" ", "+");
        if (query.isEmpty()) {
            Toast.makeText(this, R.string.please_type, Toast.LENGTH_LONG).show();
            mEmptyStateTextView.setText(R.string.please_type);
            return;
        }

        // We prepare the new URL   //// TODO: 10/30/16 research on implementing this using SerchView instead
        query = BOOKS_REQUEST_URL + query + "&maxResults=15";
        mSearchQuery = query;
        Log.i(LOG_TAG, mSearchQuery);

        // If there is a network connection, fetch data
        //Get details on the currently active default data network
        networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            lm = getLoaderManager();
            lm.restartLoader(BOOK_LOADER_ID, null, BookListingActivity.this);
            //Starting a new search lest update the UI
            mAdapter.clear();
            mEmptyStateTextView.setText(R.string.searching);
            mLoadingIndicator.setVisibility(View.VISIBLE);
        } else {
            mLoadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet);
            mAdapter.clear();
            return;
        }

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
        } else if (mSearchQuery == null) {
            //Give new user instructions
            mEmptyStateTextView.setText(R.string.welcome_text);
        } else {
            //Set empty state text to display "No Books Found".
            mEmptyStateTextView.setText(R.string.no_books);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        Log.i(LOG_TAG, "TEST: onResetLoader");
        mAdapter.clear();
        if (mSearchQuery == null) {
            //Give new user instructions
            mEmptyStateTextView.setText(R.string.welcome_text);
        } else {
            //Set empty state text to display "No Books Found".
            mEmptyStateTextView.setText(R.string.no_books);
        }

    }

}
