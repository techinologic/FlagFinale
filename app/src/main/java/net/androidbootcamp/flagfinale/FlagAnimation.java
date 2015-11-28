package net.androidbootcamp.flagfinale;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class FlagAnimation extends AppCompatActivity {

    private AnimationDrawable animate_usa;

    private MediaPlayer usaAnthem;
    private MediaPlayer philippineAnthem;
    private MediaPlayer nepalAnthem;
    private MediaPlayer saudiAnthem;
    private MediaPlayer ethiopiaAnthem;

    private final String[] Country = {"United States", "Philippines",
            "Nepal", "Saudi Arabia", "Ethiopia"};
    private final String[] Anthem = {"\"Star Spangled Banner\"", "\"Chosen Land\"", "\"We are Hundreds of Flowers\"",
            "\"The National Anthem\"", "\"March Forward, Dear Mother Ethiopia\""};

    private TextView countryName;
    private TextView anthemName;

    private ImageView imgFrame;

    private CountDownTimer philippines;
    private CountDownTimer nepal;
    private CountDownTimer saudi;
    private CountDownTimer ethiopia;
    private CountDownTimer usa;

    int timeInMilliSec = 10000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag_animation);
        final Button btnStop = (Button) findViewById(R.id.btnStop);

        countryName = (TextView) findViewById(R.id.txtCountryName);
        anthemName = (TextView) findViewById(R.id.txtAnthemTitle);

        usaAnthem = MediaPlayer.create(this, R.raw.usa_anthem);
        usaAnthem.start();

        //assign the audio files to variables
        philippineAnthem = MediaPlayer.create(this, R.raw.philippines_anthem);
        nepalAnthem = MediaPlayer.create(this, R.raw.nepal_anthem);
        saudiAnthem = MediaPlayer.create(this, R.raw.saudi_anthem);
        ethiopiaAnthem = MediaPlayer.create(this, R.raw.ethiopia_anthem);

        countryName.setText(Country[0]);
        anthemName.setText(Anthem[0]);

        imgFrame = (ImageView) findViewById(R.id.imgFlag);

        //starts usa flag animation
        imgFrame.setBackgroundResource(R.drawable.animation_usa);
        animate_usa = (AnimationDrawable) imgFrame.getBackground();
        animate_usa.start();

        //implements a timer that will change the flag being animated after timeInMilliSec;
        usa = new CountDownTimer(timeInMilliSec, 1000) {

            public void onTick(long millisUntilFinished) {
                //do something while ticking
            }
            //run below when timeInmilliSec expires
            public void onFinish() {
                animate_usa.stop();
                imgFrame.setBackgroundResource(R.drawable.animation_philippines);
                animate_usa = (AnimationDrawable) imgFrame.getBackground();
                usaAnthem.stop();
                philippineAnthem.start();
                animate_usa.start();
                philippines.start();
                countryName.setText(Country[1]);
                anthemName.setText(Anthem[1]);
            }

        }.start();

        //another timer that will time the philippines flag animation
        philippines = new CountDownTimer(timeInMilliSec, 1000) {

            public void onTick(long millisUntilFinished) {
                //do something while ticking
            }
            public void onFinish() {
                animate_usa.stop();
                imgFrame.setBackgroundResource(R.drawable.animation_nepal);
                animate_usa = (AnimationDrawable) imgFrame.getBackground();
                philippineAnthem.stop();
                nepalAnthem.start();
                animate_usa.start();
                nepal.start();
                countryName.setText(Country[2]);
                anthemName.setText(Anthem[2]);
            }

        };

        nepal = new CountDownTimer(timeInMilliSec, 1000) {

            public void onTick(long millisUntilFinished) {
                //do something while ticking
            }
            public void onFinish() {
                animate_usa.stop();
                imgFrame.setBackgroundResource(R.drawable.animation_saudi);
                animate_usa = (AnimationDrawable) imgFrame.getBackground();
                nepalAnthem.stop();
                saudiAnthem.start();
                animate_usa.start();
                saudi.start();
                countryName.setText(Country[3]);
                anthemName.setText(Anthem[3]);
            }

        };

        saudi = new CountDownTimer(timeInMilliSec, 1000) {

            public void onTick(long millisUntilFinished) {
                //do something while ticking
            }
            public void onFinish() {
                animate_usa.stop();
                imgFrame.setBackgroundResource(R.drawable.animation_ethiopia);
                animate_usa = (AnimationDrawable) imgFrame.getBackground();
                saudiAnthem.stop();
                ethiopiaAnthem.start();
                animate_usa.start();
                ethiopia.start();
                countryName.setText(Country[4]);
                anthemName.setText(Anthem[4]);
            }
        };

        ethiopia = new CountDownTimer(timeInMilliSec, 1000) {

            public void onTick(long millisUntilFinished) {
                //do something while ticking
            }
            public void onFinish() {
                animate_usa.stop();
                ethiopiaAnthem.stop();
                imgFrame.startAnimation(AnimationUtils.loadAnimation(FlagAnimation.this,
                        R.anim.fading));
            }
        };

        //code for the stop flags button, stops any flag animating and starts a 10sec fade animation
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animate_usa.stop();
                usaAnthem.stop();
                nepalAnthem.stop();
                ethiopiaAnthem.stop();
                saudiAnthem.stop();
                philippineAnthem.stop();

                usa.cancel();
                philippines.cancel();
                nepal.cancel();
                saudi.cancel();
                ethiopia.cancel();
                imgFrame.startAnimation(AnimationUtils.loadAnimation(FlagAnimation.this,
                        R.anim.fading));

                //a timer to finish the activity flaganimatiin and return to main screen.
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        finish();
                        startActivity(new Intent(FlagAnimation.this, Credits.class));
                    }
                };
                Timer opening = new Timer();
                opening.schedule(task, 11000);
            }
        });

    }


}
