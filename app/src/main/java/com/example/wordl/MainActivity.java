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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText editEmail, editSifre;
    private String txtEmail, txtSifre;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private Button btnKayitOl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editEmail =  (EditText)findViewById(R.id.girisKullaniciAdi);
        editSifre = (EditText)findViewById(R.id.girisParola);

        mAuth = FirebaseAuth.getInstance();

        btnKayitOl = findViewById(R.id.btnKayitOl);

        btnKayitOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainKayitOl.class);
                startActivity(intent);
            }
        });
    }
    public void GirisYap(View view){
        txtEmail=editEmail.getText().toString();
        txtSifre=editSifre.getText().toString();
        if(!TextUtils.isEmpty(txtEmail) && !TextUtils.isEmpty(txtSifre)){

            mAuth.signInWithEmailAndPassword(txtEmail,txtSifre)
                    .addOnSuccessListener(this, new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            mUser=mAuth.getCurrentUser();

                            System.out.println("Kullanıdı Adı: " + mUser.getDisplayName());
                            System.out.println("Kullanıdı Email: " + mUser.getEmail());
                            System.out.println("Kullanıdı Uid: " + mUser.getUid());

                            Intent intent = new Intent(MainActivity.this, MainAnasayfa.class);
                            startActivity(intent);

                        }
                    }).addOnFailureListener(this, new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });


        }else
            Toast.makeText(this, "Email ve Şifre Boş Olamaz.", Toast.LENGTH_SHORT).show();

    }


}