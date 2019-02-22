package com.example.tts_indian_lang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class LangSelect extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lang_select);
    }
    public void OnLangSubmit(View view) {
        Intent homeIntent = new Intent(LangSelect.this, HomeActivity.class);
        startActivity(homeIntent);
        finish();

    }
    public void onRadioButtonClicked(View view) {

        final GlobalClass globalvariable = (GlobalClass) getApplicationContext();
        //Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        //Check which Radio Button was clicked
        switch(view.getId()) {
            case R.id.radioButton1:
                if (checked)
                    globalvariable.setLangChoice(1);
                break;

            case R.id.radioButton2:
                if (checked)
                    globalvariable.setLangChoice(2);
                break;

            case R.id.radioButton3:
                if (checked)
                    globalvariable.setLangChoice(3);
                break;
        }
    }
}
