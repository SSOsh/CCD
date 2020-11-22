package com.example.ccd.controller;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class chatbotHttp extends AsyncTask<String, String, String> {
    String strUrl;


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... voids) {
        String openApiURL = "http://aiopen.etri.re.kr:8000/Dialog";
        String accessKey = "65e2c0d6-4c3a-41bb-b19d-836fdb3c6846";    // 발급받은 API Key
//        String accessKey = "Hx5a5Wq96PTFxPzpQwk6jHJW8ulXubbPFg/EPRfz+1k=";    // 발급받은 API Key
        String domain = "Weather";          // 도메인 명
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


        URL url;
        Integer responseCode = null;
        String responBody = null;
        try {
            url = new URL(openApiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
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

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String aVoid) {
        super.onPostExecute(aVoid);
    }
}
