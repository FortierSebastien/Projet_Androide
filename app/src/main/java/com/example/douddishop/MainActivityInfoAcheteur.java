package com.example.douddishop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivityInfoAcheteur extends AppCompatActivity {
    String nom="";
    TextView tv_titreMerch;
    EditText et_date,et_nom,et_prenom,et_adresse,et_telephone;
    CheckBox chk_terms,chk_notif;
    Button btn_acheterMerch;
    String toastItemAcheter ;
    String toastCaseVide ;
    String toastCocherTermes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_info_acheteur);

        toastItemAcheter = getResources().getString(R.string.toast_acheterMerch);
        toastCaseVide = getResources().getString(R.string.toast_case_vide);
        toastCocherTermes = getResources().getString(R.string.toast_cocherTerms);

        if((String) getIntent().getSerializableExtra("nom")!=null) {
            nom  = (String) getIntent().getSerializableExtra("nom");
        }
        tv_titreMerch = findViewById(R.id.tv_titreMerch);
        et_nom = findViewById(R.id.et_nom);
        et_prenom = findViewById(R.id.et_prenom);
        et_adresse= findViewById(R.id.et_adresse);
        et_telephone = findViewById(R.id.et_telephone);
        et_date = findViewById(R.id.et_date);
        chk_notif = findViewById(R.id.chk_notif);
        chk_terms = findViewById(R.id.chk_terms);
        btn_acheterMerch = findViewById(R.id.btn_acheterMerch);

        et_date.setText(Calendar.getInstance().getTime().toString());
        tv_titreMerch.setText(nom);
        acheterMerch();
    }

    public void acheterMerch(){
        btn_acheterMerch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(verifierSiInfoRemplit()){
                    Toast.makeText(getApplicationContext(),toastItemAcheter,Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(MainActivityInfoAcheteur.this, MainActivityListProduit.class);
                    startActivity(intent);
                }else{
                    if(et_nom.getText().toString().matches("")||et_prenom.getText().toString().matches("")
                            ||et_adresse.getText().toString().matches("")
                            ||et_telephone.getText().toString().matches("")){

                        Toast.makeText(getApplicationContext(),toastCaseVide,Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(),toastCocherTermes,Toast.LENGTH_LONG).show();
                    }

                }
            }
        });


    }
    public boolean verifierSiInfoRemplit(){

        if(et_nom.getText().toString().matches("")||et_prenom.getText().toString().matches("")
                ||et_adresse.getText().toString().matches("")
                ||et_telephone.getText().toString().matches("")
                ||!chk_terms.isChecked()){

                return false;
        }else{
            return true;
        }

    }
    public void envoyerSms(){
        if(chk_notif.isChecked()){

        }
    }

}