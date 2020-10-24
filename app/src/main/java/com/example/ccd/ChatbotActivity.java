package com.example.ccd;

import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ChatbotActivity extends AppCompatActivity {
    Button home;
    Button bookList;
    Button chatbot;
    Button mypage;
    EditText editText;
    TextView outputpart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);

//        home = findViewById(R.id.home);
//        bookList = findViewById(R.id.bookList);
//        chatbot = findViewById(R.id.chatbot);
//        mypage = findViewById(R.id.mypage);
        editText = findViewById(R.id.editText);
        outputpart = findViewById(R.id.outputpart);

        outputpart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "홈", Toast.LENGTH_SHORT).show();
            }
        });
//        home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "홈", Toast.LENGTH_SHORT).show();
//            }
//        });

//        bookList.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "도서목록", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        chatbot.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "챗봇", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        mypage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "내정보", Toast.LENGTH_SHORT).show();
//            // URL 설정.
//            String url = "http://localhost:8080/";
//
//            // AsyncTask를 통해 HttpURLConnection 수행.
//            NetworkTask networkTask = new NetworkTask(url, null);
//            networkTask.execute();
//            }
//        });
    }

    public class NetworkTask extends AsyncTask<Void, Void, Void> {
//        private String url;
//        private ContentValues values;
        String strUrl, result, strCookie;
        public NetworkTask(String url, ContentValues values) {
//            this.url = url;
//            this.values = values;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            strUrl = "http://m.naver.com"; //탐색하고 싶은 URL이다.
        }

        @Override
        protected Void doInBackground(Void... params) {
            try{
                URL Url = new URL(strUrl); // URL화 한다.
                HttpURLConnection conn = (HttpURLConnection) Url.openConnection(); // URL을 연결한 객체 생성.
                conn.setRequestMethod("GET"); // get방식 통신
                conn.setDoOutput(true); // 쓰기모드 지정
                conn.setDoInput(true); // 읽기모드 지정
                conn.setUseCaches(false); // 캐싱데이터를 받을지 안받을지
                conn.setDefaultUseCaches(false); // 캐싱데이터 디폴트 값 설정

                strCookie = conn.getHeaderField("Set-Cookie"); //쿠키데이터 보관

                InputStream is = conn.getInputStream(); //input스트림 개방

                StringBuilder builder = new StringBuilder(); //문자열을 담기 위한 객체
                BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8")); //문자열 셋 세팅
                String line;

                while ((line = reader.readLine()) != null) {
                    builder.append(line+ "\n");
                }

                result = builder.toString();

            }catch(MalformedURLException | ProtocolException exception) {
                exception.printStackTrace();
            }catch(IOException io){
                io.printStackTrace();
            }
            return null;

//            String result; // 요청 결과를 저장할 변수.
//            HttpClient requestHttpURLConnection = new HttpClient();
//            result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.
//
//            return result;
        }

//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//
//            //doInBackground()로 부터 리턴된 값이 onPostExecute()의 매개변수로 넘어오므로 s를 출력한다.
//            outputpart.setText(s);
//        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
           outputpart.setText(result);
        }
    }
}
