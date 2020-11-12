package com.example.douddishop;

import android.content.Context;
import android.os.Bundle;
import android.se.omapi.Session;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ListMenuFragment extends ListFragment {

    ArrayList<String> merch;
    String[] repasNom;
    List<Merch> listeMerch;
    double prix;
    String nomMerch;
    Button commander;
    ListView menu;

    boolean valide = false;



    public ListMenuFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        merch = new ArrayList<>();
        nomMerch = "";

        View vue = inflater.inflate(R.layout.fragment_list_menu, container, false);

        String dataFichierJson = ouvrirFichierJson(getActivity().getApplicationContext(),"menu.json");
        Gson gson = new Gson();
        Type listeMerchType = new TypeToken<List<Merch>>(){ }.getType();
        listeMerch = new ArrayList<>();
        listeMerch = gson.fromJson(dataFichierJson,listeMerchType);


        for(int i = 0; i < listeMerch.size();i++){
            StringBuilder builder = new StringBuilder();
            builder.append(listeMerch.get(i).getNom()).append("  ")
                    .append(listeMerch.get(i).getPrix()).append("$");
             merch.add(builder.toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, merch);
        setListAdapter(adapter);


        return vue;
    }

   @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {


        super.onListItemClick(l, v, position, id);

        DetailFragment fgm = (DetailFragment)getFragmentManager().findFragmentById(R.id.fragmentDetail);
        prix =listeMerch.get(position).getPrix();
        nomMerch = listeMerch.get(position).getNom();

        fgm.changerElement( listeMerch.get(position).getCategorie(), listeMerch.get(position).getDescription(),String.valueOf(listeMerch.get(position).getPrix())+"$",listeMerch.get(position).getNom());
       getListView().setSelector(R.color.colorRed);
       valide = true;
    }
    public String merchName(){
        return nomMerch;
    }




    static String ouvrirFichierJson(Context contexte, String nomFichier){
        String stringJson;
        try{
            InputStream in = contexte.getAssets().open(nomFichier);

            int taille = in.available();
            byte[] tampon =new byte[taille];
            in.read(tampon);

            stringJson = new String(tampon,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            stringJson=null;
        }
        return stringJson;
    }
}