package com.unitbean.UnitBean.Utils;

import android.net.Uri;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NetworkUtils {

    private static final String API_URL = "http://jsonplaceholder.typicode.com";
    private static final String RESOURCE_POSTS = "/posts";
    private static final String RESOURCE_COMMENTS = "/comments";
    private static final String USER_ID = "userId";
    private static final String POST_ID = "postId";

    public static URL generateURLposts(){
        URL url = null;
        url = generateURL(RESOURCE_POSTS, null, null);
        return url;
    }

    public static URL generateURLcomments(String commentID){
        URL url = null;
        url = generateURL(RESOURCE_COMMENTS, POST_ID, commentID);
        return url;
    }

    private static URL generateURL(String resource, String getParamName, String getParamValue){
        Uri builtUri = Uri.parse(API_URL + resource)
                .buildUpon()
                .appendQueryParameter(getParamName, getParamValue)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e){
            e.printStackTrace();
        }

        return url;
    }



    public static String getResponseFromURL (URL url) throws IOException{
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();

            if(hasInput){
                return scanner.next();
            } else {
                return null;
            }
        } catch (UnknownHostException e){
            return null;
        } finally {
            urlConnection.disconnect();
        }
    }

}
