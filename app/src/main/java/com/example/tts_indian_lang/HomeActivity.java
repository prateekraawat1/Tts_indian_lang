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
    TextView textView_output;
    SpeechRecognizer mSpeechRecognizer;
    Intent mSpeechRecognizerIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        checkPermission();


        textView_output = findViewById(R.id.textView_output);

        mSpeechRecognizer  = SpeechRecognizer.createSpeechRecognizer(this);
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

                if (matches!=null)
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

    private void checkPermission()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if(!(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED))
            {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + getPackageName()));
                startActivity(intent);
                finish();
            }
        }
    }

    public void generate_text(View view) {
        TextView sentenceTextView = (TextView) findViewById(R.id.textView_sentence);
        String[] sentence=new String[10];
        int i;
        //for(i=0;i<10;i++){
        //    sentence[i]="Hello "+i;
        //}
        sentence[0]="भारत ने पाकिस्तान से छीना 'मोस्ट फेवर्ड नेशन' का दर्जा, जानिए क्या होगा इसका असर";
        sentence[1]="पुलवामा हमले पर सोनिया गांधी को आया गुस्सा, बोलीं-उम्मीद है, दोषी दंडित किए जाएंगे |";
        sentence[2]="अमेरिका ने पुलवामा में आतंकवादी हमले की निंदा की, दिया ये बड़ा बयान";
        sentence[3]="वंदे भारत एक्सप्रेस 180 किमी की रफ्तार से दौड़ी, टिकटों की बुकिंग शुरू";
        sentence[4]="Gully Boy Box Office: रणवीर सिंह ने 'गली बॉय' से किया धमाल, पहले दिन कमाए इतने करोड़";
        sentence[5]="इसलिए मारिया शारापोवा बीएनबी परिबास टूर्नामेंट से हुईं बाहर";
        sentence[6]="नोएडा: महिला ने पति के खिलाफ दर्ज कराया अप्राकृतिक यौनाचार का मामला";
        sentence[7]="लड़की के साथ छेड़छाड़ का किया विरोध तो गोली मारकर कार से हो गए फरार, पुलिस ने एक घंटे में ऐसे दबोचा";
        sentence[8]="Valentine Week 2019: 8 फरवरी को प्रपोज़ डे, तो 9 को है चॉकलेट डे, पढ़ें Valentine's Day से जुड़े फेक्ट...";
        sentence[9]="नई जनरेशन 2019 होंडा सिविक की जानकारी आई सामने, आगामी हफ्तों में लॉन्च संभव";
        i=(int)(Math.random()*10);
        sentenceTextView.setText(sentence[i]);

    }
}