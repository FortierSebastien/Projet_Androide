package com.example.douddishop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn_voirArticles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ouvrirMainActivity();
    }

    public void ouvrirMainActivity(){
        btn_voirArticles = findViewById(R.id.btn_voirArticle);
        btn_voirArticles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivityListProduit.class);
                startActivity(intent);
            }
        });
    }
}