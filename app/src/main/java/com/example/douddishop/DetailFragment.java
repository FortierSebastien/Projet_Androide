package com.example.douddishop;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class DetailFragment extends Fragment {
    Button commander;
    TextView tv_categorie, tv_description, tv_Prix ;
    String noSelection ;
    Boolean valide = false;
    TextView tv_invisible;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        noSelection= getResources().getString(R.string.toast_commander);
        View vue = inflater.inflate(R.layout.fragment_detail, container, false);

        tv_invisible = vue.findViewById(R.id.tv_invisible);
        commander = vue.findViewById(R.id.btn_commanderMerch);
        commander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(valide){

                    Intent intent = new Intent(getActivity(),MainActivityInfoAcheteur.class);
                    intent.putExtra("nom",tv_invisible.getText().toString());
                    startActivity(intent);
                }else {
                    Toast.makeText(getActivity().getApplicationContext(), noSelection, Toast.LENGTH_LONG).show();
                }
            }
        });
        tv_categorie = vue.findViewById(R.id.tv_categorie);
        tv_description = vue.findViewById(R.id.tv_description);
        tv_Prix = vue.findViewById(R.id.tv_prix);

        // Inflate the layout for this fragment
        return vue;
    }

    public void changerElement(String pCategorie, String pDescription, String pPrix, String pNom) {
        tv_categorie.setText(pCategorie);
        tv_description.setText(pDescription);
        tv_Prix.setText(pPrix);
        tv_invisible.setText(pNom);
        tv_invisible.setVisibility(View.GONE);
        valide= true;


    }
}