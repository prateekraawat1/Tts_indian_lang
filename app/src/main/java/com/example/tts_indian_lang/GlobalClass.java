package com.example.tts_indian_lang;

import android.app.Application;

public class GlobalClass extends Application {
    private int langChoice;

    public void setLangChoice(int langChoice) {
        this.langChoice = langChoice;
    }

    public int getLangChoice() {
        return langChoice;
    }
}