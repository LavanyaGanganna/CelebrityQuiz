package com.example.lavanya.celebrityquiz;

import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import static com.example.lavanya.celebrityquiz.R.id.imageView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    String result;
   static ArrayList<String> quizObjectArrayList=new ArrayList<>();

    Button button0,button1,button3;
    static int levels=10;
    DBHandler db = new DBHandler(this);
    static int currentlevel=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button3 = (Button) findViewById(R.id.button3);
        String url = "https://celebritybucks.com/developers/export/JSON";
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Level1.class);
                startActivity(intent);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("market://details?id=" + getApplicationContext().getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                // To count with Play market backstack, After pressing back button,
                // to taken back to our application, we need to add following flags to intent.
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName())));
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if (isNetworkAvailable()) {
            DownloadTask downloadTask = new DownloadTask();
            DownloadJson downloadJson = new DownloadJson();

            try {
                result = downloadTask.execute(url).get();
                downloadJson.execute(result);
                result=null;
                //Log.i("reslt is",result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        else{
                Toast.makeText(this, "Check Network Connection", Toast.LENGTH_SHORT).show();
        }
        }


    public class DownloadTask extends AsyncTask<String,Void,String>{
        OkHttpClient okHttpClient=new OkHttpClient();
        @Override
        protected String doInBackground(String... strings) {
            Request.Builder builder=new Request.Builder();
            builder.url(strings[0]);
            Request request=builder.build();
            Response response = null;
            try {
                response = okHttpClient.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    public class DownloadJson extends AsyncTask<String,Void,Void> {

        @Override
        protected Void doInBackground(String... strings) {
            try {
                if (strings[0] != null) {
                    JSONObject jsonObject = new JSONObject(strings[0]);

                        JSONArray jsonArray1 = jsonObject.getJSONArray("CelebrityValues");
                        for (int k = 0; k < 400; k++) {
                            JSONObject jsonObject1 = jsonArray1.getJSONObject(k);
                            int id = jsonObject1.getInt("celebId");
                            String name = jsonObject1.getString("name");
                            String url = "https://celebritybucks.com/images/celebs/full/" + id + ".jpg";
                            QuizObject quizObject = new QuizObject(id, name, url);
                            //quizObjectArrayList.add(quizObject);
                           quizObjectArrayList.add(name);
                            db.addcelebrity(quizObject);

                        }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //return quizObjectArrayList;
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //CreateNewQuestion();
        }


    }


        private boolean isNetworkAvailable() {
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }


    }