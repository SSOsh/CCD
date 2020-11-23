package com.example.ccd.controller;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Member;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;


public class commentEnrollHttp extends AsyncTask<String, String, String> {
    String strUrl;
    String[] arr;

    int reint;
    String result;
    String result1;
    Context context;
    JSONObject jsonObj;
    JSONArray jarr;
    String LoadData;

    public commentEnrollHttp(String n, Context context) {
        arr = n.split("/");
        this.context = context;
    }

    public commentEnrollHttp(String n, View view) {
        arr = n.split("/");
        this.context = context;
    }

    public commentEnrollHttp(String n) {
        arr = n.split("/");
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... voids) {
        //jarr 생성자는 삭제요망
        jarr = new JSONArray();
        HttpURLConnection conn;
        try {
            String str = "http://";
            String ip = "172.30.1.2:8080/";
            str = str + ip + "login.jsp";
            System.out.println(str);
            URL url = new URL(str);
//            // HTTP 접속 구하기
            conn = (HttpURLConnection) url.openConnection();
            conn.setAllowUserInteraction(true);

            // HTTP 접속 구하기
            conn = (HttpURLConnection) url.openConnection();


            // 리퀘스트 메소드를 POST로 설정
            conn.setRequestMethod("POST");

            // 서버로부터 메시지 받음
            conn.setDoInput(true);

            // 연결된 connection 에서 출력도 하도록 설정
            conn.setDoOutput(true);

            // 타임 아웃
            conn.setConnectTimeout(5000);
            //헤더정의
            conn.setRequestProperty("Cache-Control", "no-cache");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //여기까진 똑같음

            // 요청 파라미터 출력
            // - 파라미터는 쿼리 문자열의 형식으로 지정 (ex) 이름=값&이름=값 형식&...
            // - 파라미터의 값으로 한국어 등을 송신하는 경우는 URL 인코딩을 해야 함.

            try (OutputStream out = conn.getOutputStream()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("memberID", arr[0]);
                jsonObject.put("contents", arr[1]);

                out.write(jsonObject.toString().getBytes());
                out.flush();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            // 응답 내용(BODY) 구하기
//            int responseCode = conn.getResponseCode();
//
//            ByteArrayOutputStream baos = null;
//            InputStream is = null;
//            String responseStr = null;
//
//
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//                is = conn.getInputStream();
//                baos = new ByteArrayOutputStream();
//                byte[] byteBuffer = new byte[1024];
//                byte[] byteData = null;
//                int nLength = 0;
//                while ((nLength = is.read(byteBuffer, 0, byteBuffer.length)) != -1) {
//                    baos.write(byteBuffer, 0, nLength);
//                }
//                byteData = baos.toByteArray();
//
//                responseStr = new String(byteData);
//
//                JSONObject responseJSON = new JSONObject(responseStr);
//                //json데이터가 Map같은 형식일 때
//                jarr =  responseJSON.getJSONArray("");
//
//                //파싱
//                String title, author, starRating, bookcoverUrl;
//                String result="";
//                for(int i=0;i<jarr.length();i++) {
//                    JSONObject obj = jarr.getJSONObject(i);
//                    title = obj.getString("title");
//                    author = obj.getString("author");
//                    starRating = obj.getString("starRating");
//                    bookcoverUrl = obj.getString("bookcoverUrl");
//
//                    result += title + "/" + author + "/" + starRating + "/" + bookcoverUrl;
//                }
//            }
            // 접속 해제
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();}
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        return null;
    }

    @Override
    protected void onPostExecute(String aVoid) {
        super.onPostExecute(aVoid);

        try {
            Toast.makeText(context, jarr.getJSONObject(0).toString(), Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
