package com.example.tts_indian_lang;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 1;
    TextView textView_output;
    SpeechRecognizer mSpeechRecognizer;
    Intent mSpeechRecognizerIntent;
    int languageChoice;

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

        textView_output = findViewById(R.id.textView_output);

        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        final Intent mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault());

        mSpeechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int i) {

            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                if (matches != null)
                    textView_output.setText(matches.get(0));

            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });

        findViewById(R.id.imageButton2).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        textView_output.setHint("You will see the text here");
                        mSpeechRecognizer.stopListening();
                        break;

                    case MotionEvent.ACTION_DOWN:
                        textView_output.setText("");
                        textView_output.setHint("Listening...");
                        mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
                        break;

                }
                return false;
            }
        });

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
        } else if (id == R.id.nav_earn) {
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

    private void checkPermission() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.RECORD_AUDIO)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECORD_AUDIO},
                        MY_PERMISSIONS_REQUEST_RECORD_AUDIO);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
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