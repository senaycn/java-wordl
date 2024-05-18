package com.example.wordl;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;
import java.util.Random;

public class MainIntermediate extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private TextView textWord1;
    private TextView textMeaning1;
    private Button btnNext1, btnBack1;
    private TextToSpeech textToSpeech;

    // İngilizce ve Türkçe kelime listesi
    private String[] englishWords = {"arrive", "begin", "calculate", "decide", "encourage", "forget", "happen", "improve", "join", "keep",
            "laugh", "manage", "notice", "offer", "prepare", "question", "receive", "study", "travel", "understand",
            "visit", "wait", "x-ray", "yell", "zip", "accept", "believe", "choose", "develop", "excite",
            "find", "give", "hope", "invite", "know", "learn", "meet", "need", "open", "plan",
            "read", "see", "talk", "use", "visit", "want", "excite", "yell", "zip"};
    private String[] turkishMeanings = {"varmak", "başlamak", "hesaplamak", "karar vermek", "teşvik etmek", "unutmak", "olmak", "geliştirmek", "katılmak", "saklamak",
            "gülmek", "yönetmek", "fark etmek", "teklif etmek", "hazırlamak", "soru sormak", "almak", "çalışmak", "seyahat etmek", "anlamak",
            "ziyaret etmek", "beklemek", "röntgen çektirmek", "bağırmak", "fırlamak", "kabul etmek", "inanmak", "seçmek", "geliştirmek", "heyecanlandırmak",
            "bulmak", "vermek", "umut etmek", "davet etmek", "bilmek", "öğrenmek", "tanışmak", "ihtiyaç duymak", "açmak", "plan yapmak",
            "okumak", "görmek", "konuşmak", "kullanmak", "ziyaret etmek", "istemek", "heyecanlandırmak", "bağırmak", "fermuar çekmek"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_intermediate);

        textWord1 = findViewById(R.id.textWord1);
        textMeaning1 = findViewById(R.id.textMeaning1);
        btnNext1 = findViewById(R.id.btnNext1);
        btnBack1 = findViewById(R.id.btnBack1);

        textToSpeech = new TextToSpeech(this, this);

        btnNext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int randomIndex = new Random().nextInt(englishWords.length);
                String selectedWord = englishWords[randomIndex];
                String meaning = turkishMeanings[randomIndex];

                textWord1.setText(selectedWord);
                textMeaning1.setText(meaning);

                speak(selectedWord);
            }
        });

        btnBack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainIntermediate.this, MainAnasayfa.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

            int result = textToSpeech.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {

            }
        } else {
        }
    }

    private void speak(String text) {
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
}