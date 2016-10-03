package com.abnd.maso.booklistingapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mariosoberanis on 9/26/16.
 */

public final class QueryUtils {

    /**
     * Sample JSON response for a Google Books query
     * https://www.googleapis.com/books/v1/volumes?q=quijote&maxResults=3
     */
    private static final String SAMPLE_JSON_RESPONS = "{\"kind\":\"books#volumes\",\"totalItems\":575,\"items\":[{\"kind\":\"books#volume\",\"id\":\"ka2VUBqHiWkC\",\"etag\":\"mK5KtFGhTDQ\",\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/ka2VUBqHiWkC\",\"volumeInfo\":{\"title\":\"Effective Java\",\"authors\":[\"Joshua Bloch\"],\"publisher\":\"Addison-Wesley Professional\",\"publishedDate\":\"2008-05-08\",\"description\":\"Are you looking for a deeper understanding of the Java™ programming language so that you can write code that is clearer, more correct, more robust, and more reusable? Look no further! Effective Java™, Second Edition, brings together seventy-eight indispensable programmer’s rules of thumb: working, best-practice solutions for the programming challenges you encounter every day. This highly anticipated new edition of the classic, Jolt Award-winning work has been thoroughly updated to cover Java SE 5 and Java SE 6 features introduced since the first edition. Bloch explores new design patterns and language idioms, showing you how to make the most of features ranging from generics to enums, annotations to autoboxing. Each chapter in the book consists of several “items” presented in the form of a short, standalone essay that provides specific advice, insight into Java platform subtleties, and outstanding code examples. The comprehensive descriptions and explanations for each item illuminate what to do, what not to do, and why. Highlights include: New coverage of generics, enums, annotations, autoboxing, the for-each loop, varargs, concurrency utilities, and much more Updated techniques and best practices on classic topics, including objects, classes, libraries, methods, and serialization How to avoid the traps and pitfalls of commonly misunderstood subtleties of the language Focus on the language and its most fundamental libraries: java.lang, java.util, and, to a lesser extent, java.util.concurrent and java.io Simply put, Effective Java™, Second Edition, presents the most practical, authoritative guidelines available for writing efficient, well-designed programs.\",\"industryIdentifiers\":[{\"type\":\"ISBN_10\",\"identifier\":\"0132778041\"},{\"type\":\"ISBN_13\",\"identifier\":\"9780132778046\"}],\"readingModes\":{\"text\":true,\"image\":true},\"pageCount\":368,\"printType\":\"BOOK\",\"categories\":[\"Computers\"],\"averageRating\":4.5,\"ratingsCount\":31,\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":true,\"contentVersion\":\"0.3.5.0.preview.3\",\"imageLinks\":{\"smallThumbnail\":\"http://books.google.com/books/content?id=ka2VUBqHiWkC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\"thumbnail\":\"http://books.google.com/books/content?id=ka2VUBqHiWkC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"},\"language\":\"en\",\"previewLink\":\"http://books.google.com.mx/books?id=ka2VUBqHiWkC&printsec=frontcover&dq=java&hl=&cd=1&source=gbs_api\",\"infoLink\":\"https://play.google.com/store/books/details?id=ka2VUBqHiWkC&source=gbs_api\",\"canonicalVolumeLink\":\"https://market.android.com/details?id=book-ka2VUBqHiWkC\"},\"saleInfo\":{\"country\":\"MX\",\"saleability\":\"FOR_SALE\",\"isEbook\":true,\"listPrice\":{\"amount\":518.23,\"currencyCode\":\"MXN\"},\"retailPrice\":{\"amount\":518.23,\"currencyCode\":\"MXN\"},\"buyLink\":\"https://play.google.com/store/books/details?id=ka2VUBqHiWkC&rdid=book-ka2VUBqHiWkC&rdot=1&source=gbs_api\",\"offers\":[{\"finskyOfferType\":1,\"listPrice\":{\"amountInMicros\":518230000,\"currencyCode\":\"MXN\"},\"retailPrice\":{\"amountInMicros\":518230000,\"currencyCode\":\"MXN\"},\"giftable\":true}]},\"accessInfo\":{\"country\":\"MX\",\"viewability\":\"PARTIAL\",\"embeddable\":true,\"publicDomain\":false,\"textToSpeechPermission\":\"ALLOWED_FOR_ACCESSIBILITY\",\"epub\":{\"isAvailable\":false},\"pdf\":{\"isAvailable\":false},\"webReaderLink\":\"http://books.google.com.mx/books/reader?id=ka2VUBqHiWkC&hl=&printsec=frontcover&output=reader&source=gbs_api\",\"accessViewStatus\":\"SAMPLE\",\"quoteSharingAllowed\":false},\"searchInfo\":{\"textSnippet\":\"Each chapter in the book consists of several “items” presented in the form of a short, standalone essay that provides specific advice, insight into Java platform subtleties, and outstanding code examples.\"}},{\"kind\":\"books#volume\",\"id\":\"USaAQ0hHQWIC\",\"etag\":\"mHQ82eAnmQQ\",\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/USaAQ0hHQWIC\",\"volumeInfo\":{\"title\":\"Java a Tope: J2me (java 2 Micro Edition).\",\"publisher\":\"Sergio Gálvez Rojas\",\"publishedDate\":\"2003\",\"industryIdentifiers\":[{\"type\":\"ISBN_13\",\"identifier\":\"9788468847047\"},{\"type\":\"ISBN_10\",\"identifier\":\"8468847046\"}],\"readingModes\":{\"text\":false,\"image\":true},\"pageCount\":188,\"printType\":\"BOOK\",\"averageRating\":4,\"ratingsCount\":9,\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":false,\"contentVersion\":\"1.1.0.0.preview.1\",\"imageLinks\":{\"smallThumbnail\":\"http://books.google.com/books/content?id=USaAQ0hHQWIC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\"thumbnail\":\"http://books.google.com/books/content?id=USaAQ0hHQWIC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"},\"language\":\"en\",\"previewLink\":\"http://books.google.com.mx/books?id=USaAQ0hHQWIC&printsec=frontcover&dq=java&hl=&cd=2&source=gbs_api\",\"infoLink\":\"http://books.google.com.mx/books?id=USaAQ0hHQWIC&dq=java&hl=&source=gbs_api\",\"canonicalVolumeLink\":\"http://books.google.com.mx/books/about/Java_a_Tope_J2me_java_2_Micro_Edition.html?hl=&id=USaAQ0hHQWIC\"},\"saleInfo\":{\"country\":\"MX\",\"saleability\":\"NOT_FOR_SALE\",\"isEbook\":false},\"accessInfo\":{\"country\":\"MX\",\"viewability\":\"ALL_PAGES\",\"embeddable\":true,\"publicDomain\":false,\"textToSpeechPermission\":\"ALLOWED\",\"epub\":{\"isAvailable\":false},\"pdf\":{\"isAvailable\":false},\"webReaderLink\":\"http://books.google.com.mx/books/reader?id=USaAQ0hHQWIC&hl=&printsec=frontcover&output=reader&source=gbs_api\",\"accessViewStatus\":\"SAMPLE\",\"quoteSharingAllowed\":false}},{\"kind\":\"books#volume\",\"id\":\"is2J44U4DpsC\",\"etag\":\"Aql7Oo5DbUI\",\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/is2J44U4DpsC\",\"volumeInfo\":{\"title\":\"Cómo programar en Java\",\"authors\":[\"Paul J. Deitel\"],\"publisher\":\"Pearson Educación\",\"publishedDate\":\"2004\",\"industryIdentifiers\":[{\"type\":\"ISBN_10\",\"identifier\":\"9702605180\"},{\"type\":\"ISBN_13\",\"identifier\":\"9789702605188\"}],\"readingModes\":{\"text\":false,\"image\":true},\"pageCount\":1325,\"printType\":\"BOOK\",\"categories\":[\"Java (Computer program language)\"],\"averageRating\":5,\"ratingsCount\":1,\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":false,\"contentVersion\":\"preview-1.0.0\",\"imageLinks\":{\"smallThumbnail\":\"http://books.google.com/books/content?id=is2J44U4DpsC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\"thumbnail\":\"http://books.google.com/books/content?id=is2J44U4DpsC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"},\"language\":\"es\",\"previewLink\":\"http://books.google.com.mx/books?id=is2J44U4DpsC&printsec=frontcover&dq=java&hl=&cd=3&source=gbs_api\",\"infoLink\":\"http://books.google.com.mx/books?id=is2J44U4DpsC&dq=java&hl=&source=gbs_api\",\"canonicalVolumeLink\":\"http://books.google.com.mx/books/about/C%C3%B3mo_programar_en_Java.html?hl=&id=is2J44U4DpsC\"},\"saleInfo\":{\"country\":\"MX\",\"saleability\":\"NOT_FOR_SALE\",\"isEbook\":false},\"accessInfo\":{\"country\":\"MX\",\"viewability\":\"PARTIAL\",\"embeddable\":true,\"publicDomain\":false,\"textToSpeechPermission\":\"ALLOWED\",\"epub\":{\"isAvailable\":false},\"pdf\":{\"isAvailable\":false},\"webReaderLink\":\"http://books.google.com.mx/books/reader?id=is2J44U4DpsC&hl=&printsec=frontcover&output=reader&source=gbs_api\",\"accessViewStatus\":\"SAMPLE\",\"quoteSharingAllowed\":false}},{\"kind\":\"books#volume\",\"id\":\"M6reV4TGyIQC\",\"etag\":\"KPthXGxusvM\",\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/M6reV4TGyIQC\",\"volumeInfo\":{\"title\":\"Java a tope: Java2D. Cómo tratar con Java figuras, imágenes y texto en dos dimensiones\",\"publisher\":\"Sergio Gálvez Rojas\",\"industryIdentifiers\":[{\"type\":\"ISBN_13\",\"identifier\":\"9788469056776\"},{\"type\":\"ISBN_10\",\"identifier\":\"8469056778\"}],\"readingModes\":{\"text\":false,\"image\":true},\"printType\":\"BOOK\",\"averageRating\":4,\"ratingsCount\":7,\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":false,\"contentVersion\":\"1.1.0.0.preview.1\",\"imageLinks\":{\"smallThumbnail\":\"http://books.google.com/books/content?id=M6reV4TGyIQC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\"thumbnail\":\"http://books.google.com/books/content?id=M6reV4TGyIQC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"},\"language\":\"es\",\"previewLink\":\"http://books.google.com.mx/books?id=M6reV4TGyIQC&printsec=frontcover&dq=java&hl=&cd=4&source=gbs_api\",\"infoLink\":\"http://books.google.com.mx/books?id=M6reV4TGyIQC&dq=java&hl=&source=gbs_api\",\"canonicalVolumeLink\":\"http://books.google.com.mx/books/about/Java_a_tope_Java2D_C%C3%B3mo_tratar_con_Java.html?hl=&id=M6reV4TGyIQC\"},\"saleInfo\":{\"country\":\"MX\",\"saleability\":\"NOT_FOR_SALE\",\"isEbook\":false},\"accessInfo\":{\"country\":\"MX\",\"viewability\":\"ALL_PAGES\",\"embeddable\":true,\"publicDomain\":false,\"textToSpeechPermission\":\"ALLOWED\",\"epub\":{\"isAvailable\":false},\"pdf\":{\"isAvailable\":false},\"webReaderLink\":\"http://books.google.com.mx/books/reader?id=M6reV4TGyIQC&hl=&printsec=frontcover&output=reader&source=gbs_api\",\"accessViewStatus\":\"SAMPLE\",\"quoteSharingAllowed\":false}}]}";


    public QueryUtils() {
    }

    /**
     * Return a list of {@link Book} build from parsing JSON
     */

    public static ArrayList<Book> extractBooks() {

        //Create an empty ArrayList that we can add books to it
        ArrayList<Book> booksArray = new ArrayList<>();

        try {
            // Create a JSONObject from the SAMPLE_JSON_RESPONSE string
            JSONObject baseJsonResponse = new JSONObject(SAMPLE_JSON_RESPONS);

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
                if (volumeInfo.has("imageLinks")) {
                    String image_link = volumeInfo.getJSONObject("imageLinks").getString("thumbnail");
                }

                //We create a new Book and add JSON values
                Book book = new Book(title, description, author, publishedDate);
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
