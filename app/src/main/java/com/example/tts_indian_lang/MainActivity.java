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
