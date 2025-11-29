package com.bookshop.util;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class APIUtil {

    // Build a JSON response
    public JSONObject buildResponse(String message){
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("response", message);
            return jsonObject;
        }catch (JSONException jsonException){
            System.out.println("Error: " + jsonException);
            return new JSONObject();
        }
    }
}
