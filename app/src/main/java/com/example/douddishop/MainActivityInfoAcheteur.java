package com.example.douddishop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
    File monFichier;
    SharedPreferences fichierPref;
    public static  final String CONST_NOM_FIC ="commandeInfo.txt";
    public final String DIRECTORY_DOWNLOADS="Commande/";
    boolean verificationStockage;
    GestionnaireBD bd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_info_acheteur);
         bd = new GestionnaireBD(this);

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


        fichierPref = getSharedPreferences(CONST_NOM_FIC, Context.MODE_PRIVATE);
        verificationStockage=true;

        if (!stockageExterneDisponibles() || stockageExterneLectureSeul()){
            verificationStockage=false;
        }else{
            monFichier = new File(getExternalFilesDir(DIRECTORY_DOWNLOADS),CONST_NOM_FIC);


        }
        acheterMerch();
    }

    public void acheterMerch(){
        btn_acheterMerch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                // insertion des étudiants
                bd.ajouterClient(new Client(bd.getNbClient()+1, et_nom.getText().toString(),et_prenom.getText().toString(),et_adresse.getText().toString()
                        ,et_telephone.getText().toString()));

                if(verifierSiInfoRemplit()){
                    Toast.makeText(getApplicationContext(),toastItemAcheter,Toast.LENGTH_LONG).show();
                    try {
                        FileOutputStream file = new FileOutputStream(monFichier);
                        file.flush();
                        file.write(et_nom.getText().toString().getBytes());
                        file.close();



                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }//}




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
    private static boolean stockageExterneLectureSeul(){
        boolean lectureSeule = false;
        String etat = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED_READ_ONLY.equals(etat)){
            lectureSeule = true;
        }
        return lectureSeule;
    }
    private static boolean stockageExterneDisponibles(){
        boolean disponible = false;

        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            disponible = true;
        }
        return disponible;
    }

}