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

public class chatbotControl extends AsyncTask<String, String, String> {
    String str, uid;
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

    public chatbotControl(String str, String uid) {
        this.uid = uid;
        this.str = str;
    }

    public chatbotControl() {

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        String openApiURL = "http://aiopen.etri.re.kr:8000/Dialog";
        String accessKey = "65e2c0d6-4c3a-41bb-b19d-836fdb3c6846";    // 발급받은 API Key
        String uuid = uid;  // Open Dialog로 부터 생성된 UUID
        String method = "dialog";           // method 호출 방식
        String text = str;          // method 호출 방식
        Gson gson = new Gson();

        Map<String, Object> request = new HashMap<>();
        Map<String, String> argument = new HashMap<>();

        ////////////////////////// OPEN DIALOG //////////////////////////

        argument.put("uuid", uuid);
        argument.put("method", method);
        argument.put("text", text);

        request.put("access_key", accessKey);
        request.put("argument", argument);


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
            //System.out.println(responBody);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responBody;
    }
}