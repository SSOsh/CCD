package com.example.ccd.controller;

///////////////////////// DIALOG //////////////////////////

import android.os.AsyncTask;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class chatbotOpen extends AsyncTask<String, String, String> {

//
//    public static void main(String[] args) {
//
//        openDialog();
//
//
//        while(true) {
//            String str = new String();
//            Dialog(, str)
//        }
//    }


    @Override
    protected String doInBackground(String... strings) {
        String re = "";
        String openApiURL = "http://aiopen.etri.re.kr:8000/Dialog";
        String accessKey = "65e2c0d6-4c3a-41bb-b19d-836fdb3c6846";    // 발급받은 API Key
        String domain = "categoryChatbot";          // 도메인 명
        String access_method = "internal_data";   // 도메인 방식
        String method = "open_dialog";                      // method 호출 방식
        Gson gson = new Gson();

        Map<String, Object> request = new HashMap<>();
        Map<String, String> argument = new HashMap<>();

        ////////////////////////// OPEN DIALOG //////////////////////////

        argument.put("name", domain);
        argument.put("access_method", access_method);
        argument.put("method", method);

        request.put("access_key", accessKey);
        request.put("argument", argument);

        String uid = null;

        URL url;
        Integer responseCode = null;
        String responBody = null;
        try {
            url = new URL(openApiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.write(gson.toJson(request).getBytes("UTF-8"));
            wr.flush();
            wr.close();

            responseCode = con.getResponseCode();
            InputStream is = con.getInputStream();
            byte[] buffer = new byte[is.available()];
            int byteRead = is.read(buffer);
            responBody = new String(buffer);

            System.out.println("[responseCode] " + responseCode);
            System.out.println("[responBody]");
            System.out.println(responBody);

            //안드로이드 형식
            JSONObject responseJSON = new JSONObject(responBody);
            System.out.println(responseJSON.toString());
            JSONObject jarr = (JSONObject) responseJSON.getJSONObject("return_object");
            System.out.println(jarr.toString());
            uid = jarr.getString("uuid");
            System.out.println(uid);
            JSONObject result = (JSONObject) jarr.get("result");
            System.out.println(result);
            re = result.getString("system_text");
            System.out.println(re);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return re + uid;
    }
}