/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entites;

/**
 *
 * @author Yassin Khabthani
 */

public class Article {

    private int idArticle;
    private String titreArticle;
    private String descriptionArticle;
    private String imageArticle;
    private String dateArticle;
    private int idCategorie;
    private int idUtilisateur;
    
    
    
        public Article(){
    }

    /* Constructor with ID*/
    public Article(int idArticle, String titreArticle, String descriptionArticle, String imageArticle, String dateArticle,int idCategorie, int idUtilisateur) {
        this.idArticle = idArticle;
        this.titreArticle = titreArticle;
        this.descriptionArticle = descriptionArticle;
        this.imageArticle = imageArticle;
        this.dateArticle = dateArticle;
        this.idCategorie=idCategorie;
        this.idUtilisateur=idUtilisateur;

    }

    /* Constructor with NO ID*/
    public Article( String titreArticle, String descriptionArticle, String imageArticle, String dateArticle,int idCategorie,int idUtilisateur) {
        this.titreArticle = titreArticle;
        this.descriptionArticle = descriptionArticle;
        this.imageArticle = imageArticle;
        this.dateArticle = dateArticle;
        this.idCategorie=idCategorie;
        this.idUtilisateur=idUtilisateur;

    }

    public int getIdArticle() {
        return idArticle;
    }

    public String getTitreArticle() {
        return titreArticle;
    }

    public String getDescriptionArticle() {
        return descriptionArticle;
    }

    public String getImageArticle() {
        return imageArticle;
    }

    public String getDateArticle() {
        return dateArticle;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public void setTitreArticle(String titreArticle) {
        this.titreArticle = titreArticle;
    }

    public void setDescriptionArticle(String descriptionArticle) {
        this.descriptionArticle = descriptionArticle;
    }

    public void setImageArticle(String imageArticle) {
        this.imageArticle = imageArticle;
    }

    public void setDateArticle(String dateArticle) {
        this.dateArticle = dateArticle;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
    
    
    
}
