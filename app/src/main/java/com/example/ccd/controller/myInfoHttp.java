package com.example.ccd.controller;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class myInfoHttp extends AsyncTask<String, String, String> {
    String[] arr;
    JSONArray jarr;
    String result;
    public myInfoHttp(String n) {
        arr = n.split("/");
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... voids) {
        HttpURLConnection conn;
        result = "";
        try {
            String str = "http://";
            String ip = Value.ip;
            str = str + ip + ":8080/myInfo.jsp";
            System.out.println(str);
            URL url = new URL(str);
            //URLConnection con = url.openConnection();
//            // HTTP 접속 구하기
            conn = (HttpURLConnection) url.openConnection();
            conn.setAllowUserInteraction(true);
            System.out.println("오냐");
            // HTTP 접속 구하기


            // 리퀘스트 메소드를 POST로 설정
            conn.setRequestMethod("POST");

            // 서버로부터 메시지 받음
            conn.setDoInput(true);

            // 연결된 connection 에서 출력도 하도록 설정
            conn.setDoOutput(true);
            System.out.println("오냐");
            // 타임 아웃
            conn.setConnectTimeout(5000);
            //헤더정의
            conn.setRequestProperty("Cache-Control", "no-cache");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            System.out.println("오냐");
            // 요청 파라미터 출력
            // - 파라미터는 쿼리 문자열의 형식으로 지정 (ex) 이름=값&이름=값 형식&...
            // - 파라미터의 값으로 한국어 등을 송신하는 경우는 URL 인코딩을 해야 함.

            try (OutputStream out = conn.getOutputStream()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("oldname", arr[0]);
                jsonObject.put("oldid", arr[1]);
                jsonObject.put("oldpw", arr[2]);
                jsonObject.put("oldnickname", arr[3]);
                jsonObject.put("oldaddress", arr[4]);
                jsonObject.put("name", arr[5]);
                jsonObject.put("id", arr[6]);
                jsonObject.put("pw", arr[7]);
                jsonObject.put("nickname", arr[8]);
                jsonObject.put("address", arr[9]);
                System.out.println(jsonObject.toString());
                out.write(jsonObject.toString().getBytes());
                out.flush();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }


            // 응답 내용(BODY) 구하기
            int responseCode = conn.getResponseCode();

            ByteArrayOutputStream baos = null;
            InputStream is = null;
            String responseStr = null;


            if (responseCode == HttpURLConnection.HTTP_OK) {
                is = conn.getInputStream();
                baos = new ByteArrayOutputStream();
                byte[] byteBuffer = new byte[1024];
                byte[] byteData = null;
                int nLength = 0;
                while ((nLength = is.read(byteBuffer, 0, byteBuffer.length)) != -1) {
                    baos.write(byteBuffer, 0, nLength);
                }
                byteData = baos.toByteArray();

                responseStr = new String(byteData);

                JSONObject responseJSON = new JSONObject(responseStr);

                //json데이터가 Map같은 형식일 때
                jarr = responseJSON.getJSONArray("myInfo");
                for(int i=0;i<jarr.length();i++) {
                    JSONObject obj = jarr.getJSONObject(i);
                    result = obj.getString("result");
                }
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
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String aVoid) {
        super.onPostExecute(aVoid);
    }
}
