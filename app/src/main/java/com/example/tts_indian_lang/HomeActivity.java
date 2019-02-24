package com.example.tts_indian_lang;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 1;
    final GlobalClass globalvariable = (GlobalClass) getApplicationContext();
    TextView textView_output;
    SpeechRecognizer mSpeechRecognizer;
    Intent mSpeechRecognizerIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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
            public void onReadyForSpeech(Bundle params) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int error) {

            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                if (matches != null)
                    textView_output.setText(matches.get(0));

            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

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
        if (globalvariable.getLangChoice() == 1){
            TextView sentenceTextView=(TextView)findViewById(R.id.textView_sentence);
            int i=(int)(Math.random()*10);
            sentenceTextView.setText(hindi_sentence[i]);
        }

        else if (globalvariable.getLangChoice() == 2){
            TextView sentenceTextView=(TextView)findViewById(R.id.textView_sentence);
            int i=(int)(Math.random()*10);
            sentenceTextView.setText(punjabi_sentence[i]);
        }

        else if (globalvariable.getLangChoice() == 3){
            TextView sentenceTextView=(TextView)findViewById(R.id.textView_sentence);
            int i=(int)(Math.random()*10);
            sentenceTextView.setText(marathi_sentence[i]);
        }

    }
}