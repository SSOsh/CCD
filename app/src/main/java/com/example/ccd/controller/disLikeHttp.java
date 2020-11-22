package com.example.ccd.controller;

import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class disLikeHttp extends AsyncTask<String, String, String> {
    String strUrl;
    String arr;
    public disLikeHttp(String n) {
        arr = n;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        strUrl = "http://localhost:8080/http.jsp"; //탐색하고 싶은 URL이다.
    }

    @Override
    protected String doInBackground(String... voids) {
        HttpURLConnection conn;
        try {
            String str = "http://";
            String ip = "192.168.43.81:";
            str = str + ip + "8080/http.jsp";
            System.out.println(str);
            URL url = new URL(str);
            //URLConnection con = url.openConnection();
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
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            // 요청 파라미터 출력
            // - 파라미터는 쿼리 문자열의 형식으로 지정 (ex) 이름=값&이름=값 형식&...
            // - 파라미터의 값으로 한국어 등을 송신하는 경우는 URL 인코딩을 해야 함.

            try (OutputStream out = conn.getOutputStream()) {
                System.out.println(arr);
                out.write(arr.getBytes());
//                out.write("&".getBytes());
//                out.write(("name=" + URLEncoder.encode("자바킹","UTF-8")).getBytes());
                out.flush();
            }

            // 응답 내용(BODY) 구하기
            try (InputStream in = conn.getInputStream();
                 ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                byte[] buf = new byte[1024 * 8];
                int length = 0;
                while ((length = in.read(buf)) != -1) {
                    out.write(buf, 0, length);
                }
                System.out.println(new String(out.toByteArray(), "UTF-8"));
            }
            // 접속 해제
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
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
