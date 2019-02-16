package com.example.tts_indian_lang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.InputDevice;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textview_sentence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview_sentence = findViewById(R.id.button_submit);
        findViewById(R.id.button_submit).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction())
                {
                    case event.ACTION_UP:
                        textview_sentence.setHint("You will see the text here");
                        break;

                    case event.ACTION_DOWN:
                        textview_sentence.setHint("Listening");
                        break;

                }
                return false;
            }
        });
    }
}
