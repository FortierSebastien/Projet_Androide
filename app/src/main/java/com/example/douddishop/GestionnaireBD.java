package com.example.douddishop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class GestionnaireBD extends SQLiteOpenHelper {

    private static final int VERSION_BD = 1;
    private static final String NOM_BD = "GestionClientsCommandes";
    private static final String TABLE_CLIENT = "Clients";
    private static final String CLE_NO = "id";
    private static final String CLE_NOM = "nom";
    private static final String CLE_PRENOM = "prenom";
    private static final String CLE_ADRESSE = "adresse";
    private static final String CLE_TEL = "telephone";

    private static final String TABLE_COMMANDE = "Commandes";
    private static final String CLE_NO_COMMANDE = "id";
    private static final String CLE_ARTICLE_NOM = "nom";
    private static final String CLE_DATE = "date";


    // constructeur
    public GestionnaireBD(Context contexte) {
        super(contexte, NOM_BD, null, VERSION_BD);
    }

    // création des tables
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String creation_table_client = "CREATE TABLE " + TABLE_CLIENT + "(" +
                CLE_NO + " INTEGER PRIMARY KEY," +
                CLE_NOM + " TEXT," +
                CLE_PRENOM + " TEXT," +
                CLE_ADRESSE + " TEXT," +
                CLE_TEL + " TEXT" + ")";
        sqLiteDatabase.execSQL(creation_table_client);


       /* String creation_table_commande = "CREATE TABLE " + TABLE_COMMANDE + "(" +
                CLE_NO_COMMANDE + " INTEGER PRIMARY KEY," +
                CLE_ARTICLE_NOM + " TEXT," +
                CLE_DATE + " TEXT" + ")";

        sqLiteDatabase.execSQL(creation_table_commande);*/
    }

    // mise à jour de la BD
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // effacer les tables si elles existent déjà
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENT);
       // sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMANDE);

        // création de la table
        onCreate(sqLiteDatabase);

    }

    // ajouter un etudiant
    public void ajouterClient(Client client) {
        // mode écriture
        SQLiteDatabase bd = this.getWritableDatabase();
        //création d'un conteneur de valeurs
        ContentValues valeurs = new ContentValues();
        valeurs.put(CLE_NOM,  client.getNom());
        valeurs.put(CLE_NOM,  client.getPrenom());
        valeurs.put(CLE_NOM,  client.getAdresse());
        valeurs.put(CLE_TEL, client.getTelephone());

        // insertion dans la BD - retourne le clé primaire de la nouvelle ligne
        long ID_ligne = bd.insert(TABLE_CLIENT, null, valeurs);
        // fermeture la BD
        bd.close();
    }

    // obtenir un etudiant
 /*   public Etudiant getEtudiant(int no) {
        SQLiteDatabase bd = this.getReadableDatabase();
        Cursor cur = bd.query(TABLE_ETUDIANT, new String[] {CLE_NO, CLE_NOM, CLE_TEL},
                CLE_NO + "=?", new String[] {String.valueOf(no)},
                null, null, null, null);

        if(cur != null) {
            cur.moveToFirst();
        }

        Etudiant etudiant = new Etudiant(Integer.parseInt(cur.getString(0)),
                cur.getString(1), cur.getString(2));

        return etudiant;
    }
*/

    // obtenir une liste des étudiants au complet
    public List<Client> getListeEtudiant() {
        List<Client> listeEtudiant = new ArrayList<Client>();

        // requete sélection
        String req = "SELECT * FROM " + TABLE_CLIENT;
        SQLiteDatabase bd = this.getReadableDatabase();
        Cursor cur = bd.rawQuery(req, null);

        // lecture des données et ajout dans la liste
        if(cur.moveToFirst()) {
            do {
                Client client = new Client(1,"","","","");
                client.setNoClient(Integer.parseInt(cur.getString(0)));
                client.setNom(cur.getString(1));
                client.setPrenom(cur.getString(2));
                client.setAdresse(cur.getString(3));
                client.setTelephone(cur.getString(4));

                listeEtudiant.add(client);
            } while (cur.moveToNext());

        }
        return listeEtudiant;
    }


    // mettre à jour un etudiant
   /* public int majEtudiant(Etudiant etu) {
        SQLiteDatabase bd = this.getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put(CLE_NOM, etu.getNom());
        val.put(CLE_TEL, etu.getNoTelephone());

        // MAJ
        return bd.update(TABLE_ETUDIANT, val, CLE_NO + " =?", new String[] {String.valueOf(etu.getNo())});
    }


    // effacer un etudiant
    public void effacerEtudiant(Etudiant etu) {
        SQLiteDatabase bd = this.getWritableDatabase();
        bd.delete(TABLE_ETUDIANT, CLE_NO + " =?", new String[] {String.valueOf(etu.getNo())});

    }

*/
    // retourner le nombre d'étudiants dans la BD
    public int getNbClient() {
        String req = "SELECT * FROM " + TABLE_CLIENT;
        SQLiteDatabase bd = this.getReadableDatabase();
        Cursor cur = bd.rawQuery(req, null);
        cur.close();

        return cur.getCount();
    }
}
