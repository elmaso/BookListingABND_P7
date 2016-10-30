package com.abnd.maso.booklistingapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mariosoberanis on 9/26/16.
 */

public final class QueryUtils {

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = QueryUtils.class.getSimpleName();


    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Query the USGS dataset and return a list of {@link Book} objects.
     */
    public static List<Book> fetchBooksData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link Earthquake}s
        List<Book> books = extractFeatureFromJson(jsonResponse);

        // Return the list of {@link Earthquake}s
        return books;
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();


            // If the request was succesful (response code 200),
            //then read the infput stream and parse the response
            if (urlConnection.getResponseCode() == 200) {
                Log.i(LOG_TAG, "Got good ResponseCode");
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the books JSON result.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }


    /**
     * Return a list of {@link Book} build from parsing JSON
     */

    public static List<Book> extractFeatureFromJson(String bookJASON) {

        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(bookJASON)) {
            return null;
        }

        //Create an empty ArrayList that we can add books to it
        List<Book> booksArray = new ArrayList<>();

        try {
            // Create a JSONObject from the SAMPLE_JSON_RESPONSE string
            JSONObject baseJsonResponse = new JSONObject(bookJASON);

            // Extract the JSONArray associated with the key called "items",
            // which represents a list of books .
            JSONArray itemsArray = baseJsonResponse.getJSONArray("items");

            //for each book will extract the info we need

            for (int i = 0; i < itemsArray.length(); i++) {

                //Get a single Book
                JSONObject currentBook = itemsArray.getJSONObject(i);
                JSONObject volumeInfo = currentBook.getJSONObject("volumeInfo");

                //Extract the Title of the book
                String title = "No title";
                if (null != volumeInfo.getString("title")) {
                    title = volumeInfo.getString("title");
                }

                //Extract the Author Not all of the books have author so we check first
                String author = "No Author";
                JSONArray authorsArray;
                if (volumeInfo.has("authors")) {
                    authorsArray = volumeInfo.getJSONArray("authors");
                    //Extract the Author of the book
                    author = authorsArray.getString(0);
                }

                // We try to get the description
                String description = "No description available";
                if (volumeInfo.has("description")) {
                    description = volumeInfo.getString("description");
                }

                //Extract the publish date of the book
                String publishedDate = volumeInfo.getString("publishedDate");

                //Extract the thumbnail URL for future version
                Bitmap images_thumb = null;
                if (volumeInfo.has("imageLinks")) {
                    String image_link = volumeInfo.getJSONObject("imageLinks").getString("thumbnail");
                    try {
                        InputStream images = new URL(image_link).openStream();
                        images_thumb = BitmapFactory.decodeStream(images);
                    } catch (Exception e) {
                        Log.e(LOG_TAG, "Error Images");
                    }
                }

                //We create a new Book and add JSON values
                Book book = new Book(title, description, author, publishedDate, images_thumb);
                booksArray.add(book);

            }
        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the BookListing JSON results", e);
        }

        // Return a list of books
        return booksArray;
    }


}
