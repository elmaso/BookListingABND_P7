package com.abnd.maso.booklistingapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mariosoberanis on 9/26/16.
 */

public class BookAdapter extends ArrayAdapter<Book> {

    /**
     * Constructs a new {@link BookAdapter}.
     *
     * @param context of the app
     * @param books   is the list of books, which is the data source of the adapter
     */
    public BookAdapter(Context context, List<Book> books) {
        super(context, 0, books);
    }

    /**
     * Returns a list item view that displays information about a book at the given position
     * in the list of books.
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.book_item, parent, false);
        }

        // Find current book position in the lost of books
        Book currentBook = getItem(position);

        assert currentBook != null;

        TextView titleView = (TextView) listItemView.findViewById(R.id.book_title_text_view);
        titleView.setText(currentBook.getBookTitle());

        TextView Description = (TextView) listItemView.findViewById(R.id.book_description_text_view);
        Description.setText(currentBook.getmBookDescription());


        TextView publisherView = (TextView) listItemView.findViewById(R.id.book_publisher_text_view);
        publisherView.setText(currentBook.getBookAuthor());

        TextView publishDateView = (TextView) listItemView.findViewById(R.id.book_publish_date_text_view);
        publishDateView.setText(currentBook.getBooKPublishDate());

        ImageView bookImage = (ImageView) listItemView.findViewById(R.id.book_image_view);
        bookImage.setImageBitmap(currentBook.getBookImageURL());

        return listItemView;

    }

}
