package com.handson.basic.util;

import okhttp3.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


// copied from documentation https://www.sms4free.co.il/outcome-sms-api.html
@Service
public class SmsService {
    protected final Log logger = LogFactory.getLog(getClass());
    OkHttpClient client = new OkHttpClient.Builder().build();

    @Value("${sms4free.key}")
    private String ACCOUNT_KEY;
    @Value("${sms4free.user}")
    private String ACCOUNT_USER;
    @Value("${sms4free.password}")
    private String ACCOUNT_PASS;


    //Overloaded constructor to send a message to both a phone number and an email address.
    public boolean send(String text, String phoneNumber) {
        if (phoneNumber == null) return false;

        String url = "https://api.sms4free.co.il/ApiSMS/v2/SendSMS";
        String key = ACCOUNT_KEY;
        String user = ACCOUNT_USER;
        String pass = ACCOUNT_PASS;
        try {

            // Construct JSON body
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("key", key);
            jsonObject.put("user", user);
            jsonObject.put("pass", pass);
            jsonObject.put("sender", "MICROSOFT:)");
            jsonObject.put("recipient", phoneNumber);
            jsonObject.put("msg", text);

            // Convert JSON object to string
            String jsonBody = jsonObject.toString();

            // Set JSON media type
            MediaType mediaType = MediaType.parse("application/json");

            // Create the request body with JSON content
            RequestBody body = RequestBody.create(mediaType, jsonBody);

            // Build the request
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept-Language", "en-US,en;q=0.5")
                    .build();

            // Execute the request
            var result = client.newCall(request).execute();
            String data = result.body().string();

            // Parse JSON to get the status value
            JSONObject jsonResponse = new JSONObject(data);
            int status = jsonResponse.getInt("status");

            // Check if status indicates success
            return status > 0;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }
}