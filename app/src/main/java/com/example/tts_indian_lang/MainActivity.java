package com.example.tts_indian_lang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void generate_text(View view) {
        TextView sentenceTextView = (TextView) findViewById(R.id.textView_sentence);
        sentenceTextView.setText("Hello World!");

    }
}
