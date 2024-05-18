package com.example.wordl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainKayitOl extends AppCompatActivity {

    private EditText editEmail, editSifre;
    private String txtEmail, txtSifre;
    private FirebaseAuth mAuth;
    private Button btnGiriseDon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_kayit_ol);
        editEmail =  (EditText)findViewById(R.id.kayit_ol_editEmail);
        editSifre = (EditText)findViewById(R.id.kayit_ol_editSifre);

        mAuth = FirebaseAuth.getInstance();

        btnGiriseDon = findViewById(R.id.btnGiriseDon);

        btnGiriseDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainKayitOl.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public void kayitOl(View v){
        txtEmail = editEmail.getText().toString();
        txtSifre = editSifre.getText().toString();

        if (!TextUtils.isEmpty(txtEmail) && !TextUtils.isEmpty(txtSifre)){
            mAuth.createUserWithEmailAndPassword(txtEmail, txtSifre)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                        Toast.makeText(MainKayitOl.this, "Kayıt İşlemi Başarılı", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(MainKayitOl.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }else
            Toast.makeText(this,"Email ve Şifre Boş Olamaz.", Toast.LENGTH_SHORT).show();


    }
}