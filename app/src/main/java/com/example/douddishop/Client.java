package com.example.douddishop;

public class Client {
    int noClient;
    String nom;
    String prenom;
    String adresse;
    String telephone;

    public int getNoClient() {
        return noClient;
    }

    public void setNoClient(int noClient) {
        this.noClient = noClient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
 public Client(int pNoClient,String pNom, String pPrenom,String pAdresse, String pTelephone){

        this.noClient = pNoClient;
        this.nom = pNom;
        this.prenom =pPrenom;
        this.adresse = pAdresse;
        this.telephone=pTelephone;



 }
}
