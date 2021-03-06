package com.example.lavanya.celebrityquiz;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;
import java.util.Random;


import static com.example.lavanya.celebrityquiz.MainActivity.levels;
import static com.example.lavanya.celebrityquiz.MainActivity.quizObjectArrayList;

public class Level7 extends AppCompatActivity {
    private static final String TAG = Level7.class.getSimpleName();
    TextView textView1;
    Button button0,button1,button2,button3;
    com.example.lavanya.celebrityquiz.SingleShotImageView imageview;
    int locationofanswer;
    int locationofwronganswer;
    String[] answers=new String[4];
    LinearLayout linearLayout;
    QuizObject qobject;
    boolean succeed=true;
    List<QuizObject> quizObjectList;
    Button playagain;
    int questions=0;
    int answered=0;
    TextView timertext;
    String buttonchosen;
    String correctanswer;
    int score;
    int listsize;
    int currentlevel=7;
    MediaPlayer mediaPlayer;
    int m=181;
    int n=0;
    Random random = new Random();
    DBHandler db = new DBHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level7);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imageview = (SingleShotImageView) findViewById(R.id.imageView);
        textView1 = (TextView) findViewById(R.id.questionsans);
        button0 = (Button) findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.button2);
        button2 = (Button) findViewById(R.id.button3);
        button3 = (Button) findViewById(R.id.button4);
        button0.setOnClickListener(celebchosenlistener);
        button1.setOnClickListener(celebchosenlistener);
        button2.setOnClickListener(celebchosenlistener);
        button3.setOnClickListener(celebchosenlistener);
        playagain= (Button) findViewById(R.id.playagain);
        quizObjectList=db.getcelebrity(m);
        listsize = quizObjectList.size();
        Log.d(TAG,"the list size" + quizObjectList.size());
        while(!(listsize==30)){
            quizObjectList=db.getcelebrity(m);
            listsize=quizObjectList.size();
        }
        CreateNewQuestion();
    }
    private void CreateNewQuestion() {
        button0.setBackgroundColor(getResources().getColor(R.color.colorose));
        button1.setBackgroundColor(getResources().getColor(R.color.colorose));
        button2.setBackgroundColor(getResources().getColor(R.color.colorose));
        button3.setBackgroundColor(getResources().getColor(R.color.colorose));
        //qobject= db.getcelebrity(m);
        qobject=quizObjectList.get(n);
        correctanswer=qobject.getCelebname();
        byte[] outImage=qobject.getImageInbyte();

            Glide.with(getApplicationContext())
                    //.load(theImage)
                    .load(outImage)
                    //  .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    // .thumbnail(0.1f)
                    .override(200, 200)
                    .into(imageview);


        questions++;

        if (timertext != null) {
            timertext.setText(questions + "/"+listsize);
        }
        locationofanswer = random.nextInt(4);
        for (int l = 0; l < 4; l++) {
            if (l == locationofanswer) {
               // answers[l] = qobject.getCelebname();
                answers[l]=correctanswer;
            } else {

                locationofwronganswer = random.nextInt((quizObjectArrayList.size()));

                //while(quizObjectArrayList.get(locationofwronganswer).equals(qobject.getCelebname())) {
                    while(quizObjectArrayList.get(locationofwronganswer).equals(correctanswer)) {
                    locationofwronganswer = random.nextInt((quizObjectArrayList.size()));
                }

                answers[l] = quizObjectArrayList.get(locationofwronganswer);


            }
        }
        button0.setText(answers[0]);
        button1.setText(answers[1]);
        button2.setText(answers[2]);
        button3.setText(answers[3]);

    }
    View.OnClickListener celebchosenlistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            buttonchosen = view.getTag().toString();
            button0.setEnabled(false);
            button1.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
            if (view.getTag().toString().equals(Integer.toString(locationofanswer))) {
                score = score + 1;
                view.setBackgroundColor(getResources().getColor(R.color.colorgreen));
                playmusic();
                answered++;
            } else {
                view.setBackgroundColor(getResources().getColor(R.color.colorred));
                playerrormusic();
                if (locationofanswer == 0) {
                    button0.setBackgroundColor(getResources().getColor(R.color.colorgreen));
                } else if (locationofanswer == 1) {
                    button1.setBackgroundColor(getResources().getColor(R.color.colorgreen));
                } else if (locationofanswer == 2) {
                    button2.setBackgroundColor(getResources().getColor(R.color.colorgreen));
                } else if (locationofanswer == 3) {
                    button3.setBackgroundColor(getResources().getColor(R.color.colorgreen));
                }
            }

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    button0.setEnabled(true);
                    button1.setEnabled(true);
                    button2.setEnabled(true);
                    button3.setEnabled(true);

                    if(questions == listsize && answered >=20){

                        TextView textView = (TextView) findViewById(R.id.winnermsg);
                        textView.setText("\t\tCongratulations!! \n  \t You Completed \n\t \t\t\t\t\t\t" +
                                " Level " + currentlevel );
                        textView1.setText("Your Score: " + score);
                        linearLayout = (LinearLayout) findViewById(R.id.playagainlayout);
                        if(currentlevel == levels){
                            playagain.setText("Start Over");
                        }
                        else {
                            playagain.setText("Play Next Level");


                        }
                        linearLayout.setVisibility(View.VISIBLE);
                        playcheermusic();
                        button3.setEnabled(false);
                        button2.setEnabled(false);
                        button1.setEnabled(false);
                        button0.setEnabled(false);

                    }
                    else if(questions ==listsize && answered <20){
                        TextView textView = (TextView) findViewById(R.id.winnermsg);
                        textView.setText(" You Failed This \n \t \tLevel " + currentlevel);
                        textView1.setText("Your Score: " + score);
                        linearLayout = (LinearLayout) findViewById(R.id.playagainlayout);
                        playagain.setText("Play Again");
                        linearLayout.setVisibility(View.VISIBLE);
                        succeed=false;
                        button3.setEnabled(false);
                        button2.setEnabled(false);
                        button1.setEnabled(false);
                        button0.setEnabled(false);


                    }
                    else {

                       // m++;
                        n++;
                        imageview.setImageResource(0);
                        CreateNewQuestion();

                    }

                }

            },800);

        }
    };
    private void playmusic() {
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.error);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.stop();
                mediaPlayer.release();

            };
        });

    }

    private void playerrormusic() {
        MediaPlayer mediaPlayer1 = MediaPlayer.create(getApplicationContext(), R.raw.beep);
        mediaPlayer1.start();
        mediaPlayer1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaPlayer1) {
                mediaPlayer1.stop();
                mediaPlayer1.release();

            };
        });
    }
    private void playcheermusic() {
        MediaPlayer mp=  MediaPlayer.create(getApplicationContext(), R.raw.cheer);
        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.release();

            };
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem timerItem = menu.findItem(R.id.break_timer);
        timertext = (TextView) MenuItemCompat.getActionView(timerItem);
        timertext.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
        timertext.setPadding(10, 0, 10, 0); //Or something like that...
        timertext.setText(questions+"/"+listsize);
        return true;
    }

    public void playagain(View view) {
        // timeup = false;
        button3.setEnabled(true);
        button2.setEnabled(true);
        button1.setEnabled(true);
        button0.setEnabled(true);
        linearLayout.setVisibility(View.INVISIBLE);
        score = 0;
        answered = 0;
        questions=0;
        if(succeed) {
            quizObjectList.clear();
            Intent intent = new Intent(this, Level8.class);
            startActivity(intent);
            finish();
        }
        else{
            if (Build.VERSION.SDK_INT >= 11) {
                recreate();
            } else {
                Intent intent = getIntent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
                overridePendingTransition(0, 0);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
