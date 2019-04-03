package com.unitbean.UnitBean.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class JsonUtils {

    private static final String TITLE = "title";
    private static final String CONTEXT = "body";
    private static final String EMAIL = "email";
    private static final String NAME = "name";
    private static final String ID = "id";

    public static String [] getJsonTitleArray(String jsonString){

        return getJsonArrayValue(jsonString, TITLE);

    }

    public static String [] getJsonEmailArray(String jsonString){

        return getJsonArrayValue(jsonString, EMAIL);

    }

    public static String [] getJsonNameArray(String jsonString){

        return getJsonArrayValue(jsonString, NAME);

    }

    public static String [] getJsonContentArray(String jsonString){

        return getJsonArrayValue(jsonString, CONTEXT);

    }

    public static String [] getJsonPostIdArray(String jsonString){

        return getJsonArrayValue(jsonString, ID);

    }

    private static String [] getJsonArrayValue(String jsonString, String key){
        ArrayList<String> arrayList = new ArrayList<String>();
        try {
            JSONArray jsonResponse = new JSONArray(jsonString);
            for (int i=0; i < jsonResponse.length()-1; i++){
                JSONObject jsonElementObj = jsonResponse.getJSONObject(i);
                String title = jsonElementObj.getString(key);
                arrayList.add(title);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String[] returnStr = arrayList.toArray(new String[0]);

        return returnStr;

    }

}
