package com.example.lavanya.celebrityquiz;

import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import static com.example.lavanya.celebrityquiz.R.id.imageView;
import static com.example.lavanya.celebrityquiz.R.id.info;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    //String result;
   static ArrayList<String> quizObjectArrayList=new ArrayList<>();
   // HttpURLConnection urlConnection;
    //Bitmap bitmap;
    Button button0,button1,button3;
    static int levels=10;
    DBHandler db = new DBHandler(this);

  //  byte imageInByte[];
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
        Databaseopener databaseopener=new Databaseopener(MainActivity.this,null,null,null,1);
        try {
            databaseopener.copyDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

     String[] namelist={ "Donald Trump", "Adele", "Beyonce","Kanye West","Selena Gomez","Jimmy Kimmel"
        ,"Harrison Ford"
        ,"Kim Kardashian"
        ,"Justin Bieber"
        ,"Katy Perry"
        ,"Kate Upton"
        ,"Britney Spears"
        ,"Scarlett Johansson"
        ,"Melania Trump"
        ,"Lady Gaga"
        ,"Stephen Colbert"
        ,"Emily Ratajkowski"
        ,"Christine Teigen"
        ,"Madonna"
        ,"Alanis Morissette"
        ,"John Legend"
        ,"Ashton Kutcher"
        ,"Rihanna"
        ,"Ben Affleck"
        ,"Nick Cannon"
        ,"Hillary Clinton"
        ,"Hilary Duff"
        ,"Matt Damon"
        ,"Michelle Obama"
        ,"Tom Brady"
        ,"Arnold Schwarzenegger"
        ,"Katherine Heigl"
        ,"Tiffany Trump"
        ,"Barack Obama"
        ,"Susan Sarandon"
        ,"Gwen Stefani"
        ,"Kendall Jenner"
        ,"Ryan Reynolds"
        ,"Seth Meyers"
        ,"Taylor Swift"
        ,"Keanu Reeves"
        ,"Jimmy Fallon"
        ,"Khloe Kardashian"
        ,"Lindsay Lohan"
        ,"Robert Downey Jr"
        ,"Mariah Carey"
        ,"Ashley Graham"
        ,"Jamie Lynn Spears"
        ,"Howard Stern"
        ,"Ivanka Trump"
        ,"Kesha"
        ,"George Clooney"
        ,"Emma Stone"
        ,"Tom Cruise"
        ,"Michael Kors"
        ,"Kate Middleton"
        ,"Lena Dunham"
        ,"Angelina Jolie"
        ,"Prince Harry"
        ,"Tom Hardy"
        ,"David Bowie"
        ,"Justin Timberlake"
        ,"Mandy Moore"
        ,"Alicia Keys"
        ,"Prince William"
        ,"Nicole Kidman"
        ,"Oprah Winfrey"
        ,"Serena Williams"
        ,"Kendrick Lamar"
        ,"Tracy Morgan"
        ,"Jennifer Lawrence"
        ,"Reese Witherspoon"
        ,"Boy George"
        ,"Piers Morgan"
        ,"Denzel Washington"
        ,"Gigi Hadid"
        ,"Keira Knightley"
        ,"Milo Ventimiglia"
        ,"Christie Brinkley"
        ,"Melissa McCarthy"
        ,"George Michael"
        ,"Hugh Grant"
        ,"Liam Hemsworth"
        ,"Sofia Vergara"
        ,"Bruno Mars"
        ,"Matt Lauer"
        ,"Lisa Rinna"
        ,"Alec Baldwin"
        ,"Vince Vaughn"
        ,"Jessica Lange"
        ,"Amber Rose"
        ,"Miley Cyrus"
        ,"Solange Knowles"
        ,"Colin Firth"
        ,"Charlie Hunnam"
        ,"Bill Nighy"
        ,"Meghan Markle"
        ,"Liam Neeson"
        ,"Rosario Dawson"
        ,"Robert Pattinson"
        ,"Kris Jenner"
        ,"Drew Barrymore"
        ,"Ryan Seacrest"
        ,"Will Smith"
        ,"Kid Rock"
        ,"Maksim Chmerkovskiy"
        ,"Edward James Olmos"
        ,"Ariana Grande"
        ,"Michael Fassbender"
        ,"Amanda Seyfried"
        ,"John Mayer"
        ,"Venus Williams"
        ,"Ice Cube"
        ,"Emma Watson"
        ,"Casey Affleck"
        ,"Amal Clooney"
        ,"Queen Latifah"
        ,"Bill Murray"
        ,"Busta Rhymes"
        ,"Whoopi Goldberg"
        ,"Princess Diana"
        ,"Derek Jeter"
        ,"Ryan Gosling"
        ,"Viola Davis"
        ,"Kerry Washington"
        ,"James Cameron"
        ,"Thomas Lennon"
        ,"Benedict Cumberbatch"
        ,"Carey Hart"
        ,"Anna Wintour"
        ,"Natalie Portman"
        ,"Nina Dobrev"
        ,"Sienna Miller"
        ,"Vin Diesel"
        ,"Chris Evans"
        ,"Rowan Atkinson"
        ,"Pamela Anderson"
        ,"Scott Baio"
        ,"Channing Tatum"
        ,"Mel Gibson"
        ,"Dwayne Johnson"
        ,"Charlie Day"
        ,"JoJo"
        ," John Oliver"
        ,"Omarosa"
        ,"Sarah Jessica Parker"
        ,"Kevin Smith"
        ,"Robbie Williams"
        ,"Ariel Winter"
        ,"Jamie Dornan"
        ,"Bradley Cooper"
        ,"Prince Charles"
        ,"George Takei"
        ,"Sandra Bullock"
        ,"Jim Parsons"
        ,"Dev Patel"
        ,"Blac Chyna"
        ,"Octavia Spencer"
        ,"Chris Hemsworth"
        ,"Krysten Ritter"
        ,"Nick Jonas"
        ,"Sam Worthington"
        ,"Meryl Streep"
        ,"Kourtney Kardashian"
        ,"Jack Nicholson"
        ,"LeBron James"
        ,"Olivia Wilde"
        ,"Andrew Garfield"
        ,"Christine Baranski"
        ,"Hugh Hefner"
        ,"Emma Thompson"
        ,"Halle Berry"
        ,"Carrie Fisher"
        ,"Alan Rickman"
        ,"Herbie Hancock"
        ,"Wilmer Valderrama"
        ,"Lea Michele"
        ,"Shia LaBeouf"
        ,"Marc Jacobs"
        ,"Niall Horan"
        ,"Brad Pitt"
        ,"Bethenny Frankel"
        ,"Elliott Gould"
        ,"Zoe Kravitz"
        ,"Kristen Stewart"
        ,"Julia Roberts"
        ,"America Ferrera"
        ,"Cate Blanchett"
        ,"Pauley Perrette"
        ,"Kristen Bell"
        ,"Jennifer Lopez"
        ,"Kaley Cuoco"
        ,"Jennifer Aniston"
        ,"Jeff Bridges"
        ,"Michael Strahan"
        ,"Margot Robbie"
        ,"Janelle Monae"
        ,"Pete Wentz"
        ,"Sheree Whitfield"
        ,"Michael Jordan"
        ,"Bobby Brown"
        ,"Don Cheadle"
        ,"Lisa Vanderpump"
        ,"Sarah Silverman"
        ,"Maggie Siff"
        ,"Sting"
        ,"Smokey Robinson"
        ,"Bindi Irwin"
        ,"Chris Pratt"
        ,"Lauren Conrad"
        ,"Johnny Cash"
        ,"Debbie Reynolds"
        ,"Connie Britton"
        ,"Bill Clinton"
        ,"Haylie Duff"
        ,"Demetri Martin"
        ,"Emily Blunt"
        ,"Kim Zolciak"
        ,"Sidney Poitier"
        ,"Giuliana Rancic"
        ,"Michael Keaton"
        ,"Justin Baldoni"
        ,"Caitlyn Jenner"
        ,"Mark Harmon"
        ,"Tony Romo"
        ,"Kenan Thompson"
        ,"Amy Poehler"
        ,"Joe Jonas"
        ,"Chris Brown"
        ,"Whitney Houston"
        ,"Billy Bob Thornton"
        ,"Diego Luna"
        ,"Clint Eastwood"
        ,"Celine Dion"
        ,"Michael Jackson"
        ,"NeNe Leakes"
        ,"John Cena"
        ,"Jason Biggs"
        ,"Tom Hiddleston"
        ,"Ian Somerhalder"
        ,"Johnny Galecki"
        ,"Daisy Ridley"
        ,"Michelle Yeoh"
        ,"Bryan Cranston"
        ,"Christina Hendricks"
        ,"Simon Helberg"
        ,"Dax Shepard"
        ,"Nicki Minaj"
        ,"Michael B. Jordan"
        ,"Rob Kardashian"
        ,"Macklemore"
        ,"Leonardo DiCaprio"
        ,"Robin Thicke"
        ,"Gisele Bundchen"
        ,"Rachel McAdams"
        ,"Jessica Alba"
        ,"Ethan Hawke"
        ,"Chiwetel Ejiofor"
        ,"Kate Hudson"
        ,"Sean Penn"
        ,"Gwyneth Paltrow"
        ,"Simon Cowell"
        ,"Whitney Port"
        ,"Jeff Daniels"
        ,"will.i.am"
        ,"Alison Krauss"
        ,"Benicio Del Toro"
        ,"Stephen Nichols"
        ,"Bob Dylan"
        ,"Idina Menzel"
        ,"Mr. T"
        ,"Justine Bateman"
        ,"Victoria Justice"
        ,"Katie Holmes"
        ,"Jenna Dewan Tatum"
        ,"Dierks Bentley"
        ,"Lil Wayne"
        ,"Kate Mara"
        ,"Shania Twain"
        ,"Kate Moss"
        ,"Chevy Chase"
        ,"Chelsea Clinton"
        ,"Rosie Huntington-Whiteley"
        ,"Tony Hale"
        ,"Adam Scott"
        ,"Samantha Bee"
        ,"Jenna Dewan"
        ,"Emmy Rossum"
        ,"Victoria Beckham"
        ,"Garrett Hedlund"
        ,"Diane Kruger"
        ,"Giovanni Ribisi"
        ,"Fergie"
        ,"David Blaine"
        ,"Adam Brody"
        ,"RuPaul"
        ,"Tim Burton"
        ,"Goldie Hawn"
        ,"Kurt Russell"
        ,"Damian Lewis"
        ,"Nick Lachey"
        ,"Elton John"
        ,"Jon Stewart"
        ,"Leighton Meester"
        ," Zach Braff"
        ,"Chace Crawford"
        ,"Dakota Johnson"
        ,"Keith Urban"
        ,"Robert Davi"
        ,"Jane Seymour"
        ,"Michael Bolton"
        ,"Simon Pegg"
        ,"Jena Malone"
        ,"Timothy Olyphant"
        ,"Rose Byrne"
        ,"Amal Alamuddin"
        ,"Naya Rivera"
        ,"Sophia Bush"
        ,"Andy Cohen"
        ,"Lorde"
        ,"Britt Robertson"
        ,"Mike Tyson"
        ,"Anthony Rapp"
        ,"Felicity Jones"
        ,"Kirk Douglas"
        ,"Rosamund Pike"
        ,"Jason Statham"
        ,"Jason Derulo"
        ,"Josh Kelley"
        ,"Demi Lovato"
        ,"Paris Hilton"
        ,"Jennifer Garner"
        ,"Adam Driver"
        ,"Jon Favreau"
        ,"Viggo Mortensen"
        ,"Jamie Foxx"
        ,"Gavin Rossdale"
        ,"Joey Fatone"
        ,"Lena Headey"
        ,"Steven Spielberg"
        ,"Laura Dern"
        ,"Hayley Atwell"
        ,"Taron Egerton"
        ,"Taylor Kitsch"
        ,"Julianna Margulies"
        ,"Anika Noni Rose"
        ,"Kristin Scott Thomas"
        ,"Thandie Newton"
        ,"Molly Ringwald"
        ,"R. Kelly"
        ,"Penelope Cruz"
        ,"Cindy Crawford"
        ,"Hailee Steinfeld"
        ,"Johnny Depp"
        ,"Faith Hill"
        ,"Judd Apatow"
        ,"Natalie Morales"
        ,"LeAnn Rimes"
        ,"Kylie Minogue"
        ,"Fat Joe"
        ,"John Goodman"
        ,"Christian Siriano"
        ,"Rosemarie DeWitt"};
        quizObjectArrayList.addAll(Arrays.asList(namelist));
        Log.i(TAG,"the size"+ quizObjectArrayList.size());
        if (isNetworkAvailable()) {
          /*  DownloadTask downloadTask = new DownloadTask();
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
            }*/
        }
        else{
                Toast.makeText(this, "Check Network Connection", Toast.LENGTH_SHORT).show();
        }
        }


   /* public class DownloadTask extends AsyncTask<String,Void,String>{
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
                    SQLiteDatabase sqLiteDatabase=db.getWritableDatabase();
                    db.onUpgrade(sqLiteDatabase,1,1);
                        for (int k = 0; k < 400; k++) {

                            JSONObject jsonObject1 = jsonArray1.getJSONObject(k);
                            int id = jsonObject1.getInt("celebId");
                            String name = jsonObject1.getString("name");
                           String urls = "https://celebritybucks.com/images/celebs/full/" + id + ".jpg";
                            try {

                                URL url=new URL(urls);
                                urlConnection= (HttpURLConnection) url.openConnection();
                                 //urlConnection.connect();
                            //    urlConnection.setRequestMethod("HEAD");
                             //   int responseCode = urlConnection.getResponseCode();

                                // always check HTTP response code first
                              //  if (responseCode == HttpURLConnection.HTTP_OK) {
                                    InputStream inputStream = urlConnection.getInputStream();

                                    bitmap = BitmapFactory.decodeStream(inputStream);
                                    if(bitmap !=null){
                                    ByteArrayOutputStream stream = new ByteArrayOutputStream();

                                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

                                        imageInByte = stream.toByteArray();
                                        if(imageInByte.length>0) {
                                            QuizObject quizObject = new QuizObject(id, name, imageInByte);
                                            quizObjectArrayList.add(name);
                                            db.addcelebrity(quizObject);
                                        }
                                    }
                              //  }

                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            //  QuizObject quizObject = new QuizObject(id, name, url);

                            //quizObjectArrayList.add(quizObject);


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


    }*/

        private boolean isNetworkAvailable() {
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }


    }