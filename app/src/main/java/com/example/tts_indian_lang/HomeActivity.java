package com.example.tts_indian_lang;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int PERMISSIONS_MULTIPLE_REQUEST = 123;


    MediaRecorder myAudioRecorder;
    String outputfile;
    ImageButton imageButton2;
    Button play, record, stop;
    int languageChoice;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);  //floating button

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }*/;

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        checkPermission();


        play = (Button) findViewById(R.id.play);
        stop = (Button) findViewById(R.id.stop);
        record = (Button) findViewById(R.id.record);
        stop.setEnabled(false);
        play.setEnabled(false);


  


        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    outputfile=outputFile();
                    // make sure the directory we plan to store the recording in exists
                    File directory = new File(outputfile).getParentFile();
                    if (!directory.exists() && !directory.mkdirs()) {
                        throw new IOException("Path to file could not be created.");
                    }
                    myAudioRecorder = new MediaRecorder();
                    myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    myAudioRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    myAudioRecorder.setOutputFile(outputFile());
                    myAudioRecorder.prepare();
                    myAudioRecorder.start();

                } catch (IllegalStateException ise) {
                    // make something ...
                } catch (IOException ioe) {
                    // make something
                }
                record.setEnabled(false);
                stop.setEnabled(true);
                Toast.makeText(getApplicationContext(), "Recording started", Toast.LENGTH_LONG).show();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudioRecorder.stop();
                myAudioRecorder.release();
                myAudioRecorder = null;
                record.setEnabled(true);
                stop.setEnabled(false);
                play.setEnabled(true);
                Toast.makeText(getApplicationContext(), "Audio Recorded successfully", Toast.LENGTH_LONG).show();
            }
        });


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(outputfile);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    Toast.makeText(getApplicationContext(), "Playing Audio", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    // make something
                }
            }
        });

    }

    public String dateTime(){
        DateFormat df = new SimpleDateFormat("yyMMddHHmmss");
        Date dateobj = new Date();
        //System.out.println(df.format(dateobj));
        return df.format(dateobj);
    }

    public String outputFile(){
        return Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording"+dateTime()+".3gp";
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            Intent homeIntent = new Intent(HomeActivity.this, HomeActivity.class);
            startActivity(homeIntent);
        }
        else if (id == R.id.nav_tts) {
            Intent homeIntent = new Intent(HomeActivity.this, TTS.class);
            startActivity(homeIntent);
        }
        else if (id == R.id.nav_earn) {
            Intent homeIntent = new Intent(HomeActivity.this, Earnings.class);
            startActivity(homeIntent);

        } else if (id == R.id.nav_changelang) {
            Intent homeIntent = new Intent(HomeActivity.this, LangSelect.class);
            startActivity(homeIntent);

        } else if (id == R.id.nav_feedback) {
            Intent homeIntent = new Intent(HomeActivity.this, Feedback.class);
            startActivity(homeIntent);

        } else if (id == R.id.nav_About) {
            Intent homeIntent = new Intent(HomeActivity.this, About.class);
            startActivity(homeIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) + ContextCompat
                .checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) + ContextCompat
                .checkSelfPermission(this,
                        Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale
                    (this, Manifest.permission.READ_EXTERNAL_STORAGE) ||
                    ActivityCompat.shouldShowRequestPermissionRationale
                            (this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ||
                    ActivityCompat.shouldShowRequestPermissionRationale
                            (this, Manifest.permission.RECORD_AUDIO)) {

            } else {
                requestPermissions(
                        new String[]{Manifest.permission
                                .READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO},
                        PERMISSIONS_MULTIPLE_REQUEST);
            }
        } else {
            // write your logic code if permission already granted
        }
    }


    String[] hindi_sentence = new String[] {
        "मैरी पियानो बजाती है।",
        "कृपया घर के बाहर प्रतीक्षा करें। |",
        "जल्दी कीजिये!",
        "वह अभी भी जीवित है।",
        "हमारे यहां जून में बहुत बारिश होती है।",
        "उसे पढ़ा नहीं।",
        "उपस्थिति अच्छी नहीं थी",
        "टूटे हुए कांच पर कदम न रखें।",
        "उसने उसे एक लंबा पत्र लिखा, लेकिन उसने उसे पढ़ा नहीं।",
        "रहस्यमयी डायरी आवाज रिकॉर्ड करती है।"
    };


    String[] punjabi_sentence = new String[] {
        "ਮੈਰੀ ਪਿਆਨੋ ਖੇਡਦੀ ਹ",
        "ਕਿਰਪਾ ਕਰਕੇ ਘਰ ਦੇ ਬਾਹਰ ਦੀ ਉਡੀਕ ਕਰੋ",
        "ਜਲਦੀ ਕਰੋ",
        "ਉਹ ਅਜੇ ਵੀ ਜਿੰਦਾ ਹੈ",
        "ਸਾਡੇ ਕੋਲ ਜੂਨ ਵਿੱਚ ਬਹੁਤ ਮੀਂਹ ਪੈਂਦਾ ਹੈ",
        "ਦਿੱਖ ਚੰਗੀ ਨਹੀਂ ਸੀ",
        "ਇੱਕ ਖਰਾਬ ਗਲਾਸ ਤੇ ਨਾ ਜਾਵੋ",
        "ਉਸ ਨੇ ਉਸ ਨੂੰ ਇਕ ਲੰਬੀ ਚਿੱਠੀ ਲਿਖੀ, ਪਰ ਉਸ ਨੇ ਇਸ ਨੂੰ ਨਹੀਂ ਪੜ੍ਹਿਆ",
        "ਦਿੱਖ ਚੰਗੀ ਨਹੀਂ ਸੀ",
        "ਰਹੱਸਮਈ ਡਾਇਰੀ ਆਵਾਜ਼ ਰਿਕਾਰਡ ਕਰਦੀ ਹੈ"

    };

    String[] marathi_sentence = new String[] {
        "मरीया पियानो वाजवते",
        "कृपया घराच्या बाहेर प्रतीक्षा करा",
        "त्वरा करा",
        "तो अजूनही जिवंत आहे",
        "जूनमध्ये आमच्याकडे खूप पाऊस पडला आहे",
        "ते वाचले नाही",
        "देखावा चांगला नव्हता",
        "तुटलेल्या काचेच्या वर जाऊ नका",
        "त्याने त्याला एक लांब पत्र लिहिले, परंतु त्याने ते वाचले नाही",
        "रहस्यमय डायरीने आवाज नोंदविला"
    };

    public void generate_text(View view) {

        final GlobalClass globalvariable = (GlobalClass) getApplicationContext();
        languageChoice = globalvariable.getLangChoice();

        if (languageChoice == 1){
            TextView sentenceTextView=(TextView)findViewById(R.id.textView_sentence);
            int i=(int)(Math.random()*10);
            sentenceTextView.setText(hindi_sentence[i]);
        }

        else if (languageChoice == 2){
            TextView sentenceTextView=(TextView)findViewById(R.id.textView_sentence);
            int i=(int)(Math.random()*10);
            sentenceTextView.setText(punjabi_sentence[i]);
        }

        else if (languageChoice == 3){
            TextView sentenceTextView=(TextView)findViewById(R.id.textView_sentence);
            int i=(int)(Math.random()*10);
            sentenceTextView.setText(marathi_sentence[i]);
        }
    }
}