package com.abnd.maso.booklistingapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mariosoberanis on 9/26/16.
 */

public final class QueryUtils {

    /** Sample JSON response for a Google Books query
     * https://www.googleapis.com/books/v1/volumes?q=quijote&maxResults=3 */
    private static final String SAMPLE_JSON_RESPONS = "{\"kind\":\"books#volumes\",\"totalItems\":934,\"items\":[{\"kind\":\"books#volume\",\"id\":\"5s4OAAAAYAAJ\",\"etag\":\"yUaSLZBfhzo\",\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/5s4OAAAAYAAJ\",\"volumeInfo\":{\"title\":\"Don Quijote de la Mancha\",\"authors\":[\"Miguel de Cervantes Saavedra\"],\"publishedDate\":\"1856\",\"industryIdentifiers\":[{\"type\":\"OTHER\",\"identifier\":\"HARVARD:HN4QS1\"}],\"readingModes\":{\"text\":true,\"image\":true},\"printType\":\"BOOK\",\"averageRating\":4.5,\"ratingsCount\":157,\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":false,\"contentVersion\":\"1.1.26.0.full.3\",\"imageLinks\":{\"smallThumbnail\":\"http://books.google.com/books/content?id=5s4OAAAAYAAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\"thumbnail\":\"http://books.google.com/books/content?id=5s4OAAAAYAAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"},\"language\":\"es\",\"previewLink\":\"http://books.google.com.mx/books?id=5s4OAAAAYAAJ&printsec=frontcover&dq=quijote&hl=&cd=1&source=gbs_api\",\"infoLink\":\"http://books.google.com.mx/books?id=5s4OAAAAYAAJ&dq=quijote&hl=&source=gbs_api\",\"canonicalVolumeLink\":\"http://books.google.com.mx/books/about/Don_Quijote_de_la_Mancha.html?hl=&id=5s4OAAAAYAAJ\"},\"saleInfo\":{\"country\":\"MX\",\"saleability\":\"FREE\",\"isEbook\":true,\"buyLink\":\"http://books.google.com.mx/books?id=5s4OAAAAYAAJ&dq=quijote&hl=&buy=&source=gbs_api\"},\"accessInfo\":{\"country\":\"MX\",\"viewability\":\"ALL_PAGES\",\"embeddable\":true,\"publicDomain\":true,\"textToSpeechPermission\":\"ALLOWED\",\"epub\":{\"isAvailable\":true,\"downloadLink\":\"http://books.google.com.mx/books/download/Don_Quijote_de_la_Mancha.epub?id=5s4OAAAAYAAJ&hl=&output=epub&source=gbs_api\"},\"pdf\":{\"isAvailable\":true,\"downloadLink\":\"http://books.google.com.mx/books/download/Don_Quijote_de_la_Mancha.pdf?id=5s4OAAAAYAAJ&hl=&output=pdf&sig=ACfU3U00fG4EsfzvTayHGWOvMGt9mD5QcA&source=gbs_api\"},\"webReaderLink\":\"http://books.google.com.mx/books/reader?id=5s4OAAAAYAAJ&hl=&printsec=frontcover&output=reader&source=gbs_api\",\"accessViewStatus\":\"FULL_PUBLIC_DOMAIN\",\"quoteSharingAllowed\":false}},{\"kind\":\"books#volume\",\"id\":\"CPWlxufKqp4C\",\"etag\":\"abZThoOEQ/o\",\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/CPWlxufKqp4C\",\"volumeInfo\":{\"title\":\"Don Quijote de la Mancha\",\"authors\":[\"Miguel de Cervantes Saavedra\"],\"publisher\":\"EDAF\",\"publishedDate\":\"1999\",\"industryIdentifiers\":[{\"type\":\"ISBN_10\",\"identifier\":\"8441405298\"},{\"type\":\"ISBN_13\",\"identifier\":\"9788441405295\"}],\"readingModes\":{\"text\":false,\"image\":true},\"pageCount\":764,\"printType\":\"BOOK\",\"categories\":[\"Fiction\"],\"averageRating\":5,\"ratingsCount\":2,\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":false,\"contentVersion\":\"1.1.1.0.preview.1\",\"imageLinks\":{\"smallThumbnail\":\"http://books.google.com/books/content?id=CPWlxufKqp4C&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\"thumbnail\":\"http://books.google.com/books/content?id=CPWlxufKqp4C&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"},\"language\":\"es\",\"previewLink\":\"http://books.google.com.mx/books?id=CPWlxufKqp4C&printsec=frontcover&dq=quijote&hl=&cd=2&source=gbs_api\",\"infoLink\":\"http://books.google.com.mx/books?id=CPWlxufKqp4C&dq=quijote&hl=&source=gbs_api\",\"canonicalVolumeLink\":\"http://books.google.com.mx/books/about/Don_Quijote_de_la_Mancha.html?hl=&id=CPWlxufKqp4C\"},\"saleInfo\":{\"country\":\"MX\",\"saleability\":\"NOT_FOR_SALE\",\"isEbook\":false},\"accessInfo\":{\"country\":\"MX\",\"viewability\":\"PARTIAL\",\"embeddable\":true,\"publicDomain\":false,\"textToSpeechPermission\":\"ALLOWED\",\"epub\":{\"isAvailable\":false},\"pdf\":{\"isAvailable\":false},\"webReaderLink\":\"http://books.google.com.mx/books/reader?id=CPWlxufKqp4C&hl=&printsec=frontcover&output=reader&source=gbs_api\",\"accessViewStatus\":\"SAMPLE\",\"quoteSharingAllowed\":false}},{\"kind\":\"books#volume\",\"id\":\"oZlhfesvJ1sC\",\"etag\":\"2d34R8XOOOQ\",\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/oZlhfesvJ1sC\",\"volumeInfo\":{\"title\":\"Leer El Quijote\",\"subtitle\":\"siete tesis sobre ética y literatura\",\"authors\":[\"Iris M. Zavala\"],\"publisher\":\"Anthropos Editorial\",\"publishedDate\":\"2005-01\",\"industryIdentifiers\":[{\"type\":\"ISBN_10\",\"identifier\":\"8476587511\"},{\"type\":\"ISBN_13\",\"identifier\":\"9788476587515\"}],\"readingModes\":{\"text\":false,\"image\":true},\"pageCount\":110,\"printType\":\"BOOK\",\"categories\":[\"Comics & Graphic Novels\"],\"averageRating\":2,\"ratingsCount\":1,\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":false,\"contentVersion\":\"0.0.1.0.preview.1\",\"imageLinks\":{\"smallThumbnail\":\"http://books.google.com/books/content?id=oZlhfesvJ1sC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\"thumbnail\":\"http://books.google.com/books/content?id=oZlhfesvJ1sC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"},\"language\":\"es\",\"previewLink\":\"http://books.google.com.mx/books?id=oZlhfesvJ1sC&printsec=frontcover&dq=quijote&hl=&cd=3&source=gbs_api\",\"infoLink\":\"http://books.google.com.mx/books?id=oZlhfesvJ1sC&dq=quijote&hl=&source=gbs_api\",\"canonicalVolumeLink\":\"http://books.google.com.mx/books/about/Leer_El_Quijote.html?hl=&id=oZlhfesvJ1sC\"},\"saleInfo\":{\"country\":\"MX\",\"saleability\":\"NOT_FOR_SALE\",\"isEbook\":false},\"accessInfo\":{\"country\":\"MX\",\"viewability\":\"PARTIAL\",\"embeddable\":true,\"publicDomain\":false,\"textToSpeechPermission\":\"ALLOWED\",\"epub\":{\"isAvailable\":false},\"pdf\":{\"isAvailable\":false},\"webReaderLink\":\"http://books.google.com.mx/books/reader?id=oZlhfesvJ1sC&hl=&printsec=frontcover&output=reader&source=gbs_api\",\"accessViewStatus\":\"SAMPLE\",\"quoteSharingAllowed\":false}}]}";


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

        }catch (JSONException e){

        }



        // Return a list of books
        return booksArray;
    }


}
