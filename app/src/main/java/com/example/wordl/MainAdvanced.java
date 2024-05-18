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

public class MainAdvanced extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private TextView textWord2;
    private TextView textMeaning2;
    private Button btnNext2, btnBack2;
    private TextToSpeech textToSpeech;

    // İngilizce ve Türkçe kelime listesi
    private String[] englishWords = {"aberration", "benevolent", "cacophony", "debilitate", "ephemeral", "fortuitous", "gregarious", "hedonist", "idiosyncratic", "juxtaposition",
            "luminous", "mellifluous", "nefarious", "obfuscate", "paragon", "querulous", "resilient", "sagacious", "trepidation", "ubiquitous",
            "vociferous", "wanton", "zealous", "alacrity", "bucolic", "clemency", "diatribe", "ebullient", "facetious", "garrulous",
            "halcyon", "impetuous", "jubilant", "kaleidoscope", "labyrinth", "magnanimous", "nemesis", "obsequious", "panacea", "quixotic",
            "recalcitrant", "sycophant", "temerity", "ubiquity", "vitriolic", "wheedle", "xenophobia", "yokel", "zeitgeist", "xenial"};
    private String[] turkishMeanings = {"yanılgı", "iyiliksever", "karmaşa", "güçsüz düşürmek", "geçici", "tesadüfi", "sosyal", "haz düşkünü", "tuhaf", "yan yana koyma",
            "parlak", "akıcı", "kötü", "anlaşılmaz kılmak", "model", "şikayetçi", "esnek", "bilge", "endişe", "heryerde bulunan",
            "gürültülü", "arzulu", "hevesli", "çeviklik", "kırsal", "hafifletme", "söylev", "coşkulu", "şakacı", "konuşkan",
            "huzurlu", "düşüncesiz", "sevinçli", "kaleydoskop", "labirent", "büyük yürekli", "kader", "yaltaklanan", "çare", "hayalperest",
            "uzlaşmaz", "yaltaklanan", "cesaret", "her yerde bulunan", "aşındırıcı", "düzeltmek için yalvarmak", "yabancı korkusu", "köylü", "zaman ruhu", "misafirperver"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_advanced);

        textWord2 = findViewById(R.id.textWord2);
        textMeaning2 = findViewById(R.id.textMeaning2);
        btnNext2 = findViewById(R.id.btnNext2);
        btnBack2 = findViewById(R.id.btnBack2);

        textToSpeech = new TextToSpeech(this, this);

        btnNext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int randomIndex = new Random().nextInt(englishWords.length);
                String selectedWord = englishWords[randomIndex];
                String meaning = turkishMeanings[randomIndex];

                textWord2.setText(selectedWord);
                textMeaning2.setText(meaning);

                speak(selectedWord);
            }
        });

        btnBack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainAdvanced.this, MainAnasayfa.class);
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