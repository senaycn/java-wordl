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

public class MainBeginner extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private TextView textWord;
    private TextView textMeaning;
    private Button btnNext, btnBack;
    private TextToSpeech textToSpeech;

    // İngilizce ve Türkçe kelime listesi
    private String[] englishWords = {"apple", "ball", "cat", "dog", "elephant", "fish", "grape", "horse", "ice cream", "juice",
            "key", "lion", "monkey", "nose", "orange", "pencil", "queen", "rose", "sun", "tree",
            "umbrella", "van", "water", "xylophone", "yogurt", "zebra", "ant", "book", "chair", "desk",
            "egg", "flower", "guitar", "hat", "ink", "jacket", "kite", "lemon", "moon", "nest", "owl",
            "pizza", "quail", "rabbit", "snake", "table", "umbrella", "violin", "watch", "xylophone", "yacht",
            "zeppelin"};
    private String[] turkishMeanings = {"elma", "top", "kedi", "köpek", "fil", "balık", "üzüm", "at", "dondurma", "meyve suyu",
            "anahtar", "aslan", "maymun", "burun", "portakal", "kalem", "kraliçe", "gül", "güneş", "ağaç",
            "şemsiye", "minibüs", "su", "xylofon", "yoğurt", "zebra", "karınca", "kitap", "sandalye", "masa",
            "yumurta", "çiçek", "gitar", "şapka", "mürekkep", "ceket", "uçurtma", "limon", "ay", "yuva",
            "baykuş", "pizza", "bıldırcın", "tavşan", "yılan", "masa", "keman", "saat", "xylofon", "yelkenli",
            "zeplin"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_beginner);

        textWord = findViewById(R.id.textWord);
        textMeaning = findViewById(R.id.textMeaning);
        btnNext = findViewById(R.id.btnNext);
        btnBack = findViewById(R.id.btnBack);

        textToSpeech = new TextToSpeech(this, this);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int randomIndex = new Random().nextInt(englishWords.length);
                String selectedWord = englishWords[randomIndex];
                String meaning = turkishMeanings[randomIndex];

                textWord.setText(selectedWord);
                textMeaning.setText(meaning);

                speak(selectedWord);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainBeginner.this, MainAnasayfa.class);
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