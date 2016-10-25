package com.abnd.maso.booklistingapp;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class BookListingActivity extends AppCompatActivity
        implements LoaderCallbacks<List<Book>> {

    private static final String LOG_TAG = BookListingActivity.class.getName();

    /**
     * Sample JSON response for a Google Books query
     * https://www.googleapis.com/books/v1/volumes?q=Java&maxResults=3
     */
    private static final String BOOKS_REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?maxResult=20&q=quijote";

    /**
     * Constant value for the book loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */

    private static final int BOOK_LOADER_ID = 1;

    /**
     * Adapter for the list Books
     */
    private BookAdapter mAdapter;
    private EditText mSearchBook;

    /**
     * TextView that is display when the list is empty
     */
    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_listing);

        // Find a reference to the {@link ListView} in the layout
        ListView booklistListView = (ListView) findViewById(R.id.activity_book_listing);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        booklistListView.setEmptyView(mEmptyStateTextView);

        // Create a new adapter that takes an empty list of books as input
        mAdapter = new BookAdapter(this, new ArrayList<Book>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        booklistListView.setAdapter(mAdapter);

        mSearchBook = (EditText) findViewById(R.id.search_text_input);
        mSearchBook.setText("mario");


        //Set an item click listener on the ListView
      /*  booklistListView.setOnClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Book currenBook = mAdapter.getItem(i);
                // String bookX = currenBook.getBookTitle();

               //Toast toast = Toast.makeText(this,bookX,duration).show();
                Uri bookUri = Uri.parse(currenBook.getBookImageURL());


               Intent websiteIntent = new Intent(Intent.ACTION_VIEW, bookUri);
                startActivity(websiteIntent);
            }
        });*/

        // Get a refrece to the ConnectivityManager to check state of network connetivity
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        //Get details on the currently active default data network
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        // If there is a network connetoin, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();


            loaderManager.initLoader(BOOK_LOADER_ID, null, this);
        } else {
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            mEmptyStateTextView.setText(R.string.no_internet);
        }

    }

    @Override
    public Loader<List<Book>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new BookLoader(this, BOOKS_REQUEST_URL);
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





