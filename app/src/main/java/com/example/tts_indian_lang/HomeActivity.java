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
    public static String language = "hindi";
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + getPackageName()));
                startActivity(intent);
                finish();
            }
        }
    }

    public void generate_text_hindi(HomeActivity view) {
        TextView sentenceTextView = (TextView) findViewById(R.id.textView_sentence);
        String[] sentence = new String[10];
        int i;
        //for(i=0;i<10;i++){
        //    sentence[i]="Hello "+i;
        //}
        sentence[0] = "मैरी पियानो बजाती है।";
        sentence[1] = "कृपया घर के बाहर प्रतीक्षा करें। |";
        sentence[2] = "जल्दी कीजिये!";
        sentence[3] = "वह अभी भी जीवित है।";
        sentence[4] = "हमारे यहां जून में बहुत बारिश होती है।";
        sentence[5] = "उसे पढ़ा नहीं।";
        sentence[6] = "उपस्थिति अच्छी नहीं थी";
        sentence[7] = "टूटे हुए कांच पर कदम न रखें।";
        sentence[8] = "उसने उसे एक लंबा पत्र लिखा, लेकिन उसने उसे पढ़ा नहीं।";
        sentence[9] = "रहस्यमयी डायरी आवाज रिकॉर्ड करती है।";
        i = (int) (Math.random() * 10);
        sentenceTextView.setText(sentence[i]);

    }

    public void generate_text_punjabi(HomeActivity view) {
        TextView sentenceTextView = (TextView) findViewById(R.id.textView_sentence);
        String[] sentence = new String[10];

        {
            int i;

            //for(i=0;i<10;i++){
            //    sentence[i]="Hello "+i;
            //}
            sentence[0] = "ਮੈਰੀ ਪਿਆਨੋ ਖੇਡਦੀ ਹ";
            sentence[1] = "ਕਿਰਪਾ ਕਰਕੇ ਘਰ ਦੇ ਬਾਹਰ ਦੀ ਉਡੀਕ ਕਰੋ ";
            sentence[2] = "ਜਲਦੀ ਕਰੋ";
            sentence[3] = "ਉਹ ਅਜੇ ਵੀ ਜਿੰਦਾ ਹੈ";
            sentence[4] = "ਸਾਡੇ ਕੋਲ ਜੂਨ ਵਿੱਚ ਬਹੁਤ ਮੀਂਹ ਪੈਂਦਾ ਹੈ";
            sentence[5] = "ਦਿੱਖ ਚੰਗੀ ਨਹੀਂ ਸੀ";
            sentence[6] = "ਇੱਕ ਖਰਾਬ ਗਲਾਸ ਤੇ ਨਾ ਜਾਵੋ";
            sentence[7] = "ਉਸ ਨੇ ਉਸ ਨੂੰ ਇਕ ਲੰਬੀ ਚਿੱਠੀ ਲਿਖੀ, ਪਰ ਉਸ ਨੇ ਇਸ ਨੂੰ ਨਹੀਂ ਪੜ੍ਹਿਆ";
            sentence[8] = "ਰਹੱਸਮਈ ਡਾਇਰੀ ਆਵਾਜ਼ ਰਿਕਾਰਡ ਕਰਦੀ ਹੈ";
            sentence[9] = "ਦਿੱਖ ਚੰਗੀ ਨਹੀਂ ਸੀ";
            i = (int) (Math.random() * 10);
            sentenceTextView.setText(sentence[i]);
        }
    }

    public void generate_text_marathi(HomeActivity view) {
        TextView sentenceTextView = (TextView) findViewById(R.id.textView_sentence);
        String[] sentence = new String[10];

        {
            int i;

            //for(i=0;i<10;i++){
            //    sentence[i]="Hello "+i;
            //}
            sentence[0] = "मरीया पियानो वाजवते";
            sentence[1] = " कृपया घराच्या बाहेर प्रतीक्षा करा";
            sentence[2] = "त्वरा करा";
            sentence[3] = "तो अजूनही जिवंत आहे";
            sentence[4] = "जूनमध्ये आमच्याकडे खूप पाऊस पडला आहे";
            sentence[5] = "ते वाचले नाही";
            sentence[6] = "देखावा चांगला नव्हता";
            sentence[7] = "तुटलेल्या काचेच्या वर जाऊ नका";
            sentence[8] = "त्याने त्याला एक लांब पत्र लिहिले, परंतु त्याने ते वाचले नाही";
            sentence[9] = "रहस्यमय डायरीने आवाज नोंदविला";
            i = (int) (Math.random() * 10);
            sentenceTextView.setText(sentence[i]);
        }
    }

    public void generate_text() {
        if (language.equals("hindi")) {
            generate_text_hindi(this);
        }
        else if (language.equals("punjabi"))
        {            generate_text_punjabi(this);}

        else
        {       generate_text_marathi(this);
    }
    }
}