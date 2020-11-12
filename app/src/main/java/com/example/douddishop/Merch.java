package com.example.douddishop;

public class Merch {

    private  int noMerch;
    private String nom;
    private String description;
    private String categorie;
    private double prix;

    public int getNoMerch() {
        return noMerch;
    }

    public void setNoMerch(int noMerch) {
        this.noMerch = noMerch;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
    public Merch (int pNoMerch,String pNom, String pDescription, String pCategorie, double pPrix){
        this.noMerch = pNoMerch;
        this.nom = pNom;
        this.description = pDescription;
        this.categorie = pCategorie;
        this.prix = pPrix;
    }


}
