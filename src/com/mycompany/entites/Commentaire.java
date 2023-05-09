/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entites;

/**
 *
 * @author Yassin Khabthani
 */

public class Commentaire {

    private int idCommentaire;
    private String contenuCommentaire;
    private String dateCommentaire;
    private int idArticle;
    private int idUtilisateur;

    public Commentaire() {
    }

    public Commentaire(String contenuCommentaire, String dateCommentaire, int idArticle, int idUtilisateur) {
        this.contenuCommentaire = contenuCommentaire;
        this.dateCommentaire = dateCommentaire;
        this.idArticle = idArticle;
        this.idUtilisateur = idUtilisateur;
    }

    public Commentaire(int idCommentaire, String contenuCommentaire, String dateCommentaire, int idArticle, int idUtilisateur) {
        this.idCommentaire = idCommentaire;
        this.contenuCommentaire = contenuCommentaire;
        this.dateCommentaire = dateCommentaire;
        this.idArticle = idArticle;
        this.idUtilisateur = idUtilisateur;
    }

    public int getIdCommentaire() {
        return idCommentaire;
    }

    public void setIdCommentaire(int idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public String getContenuCommentaire() {
        return contenuCommentaire;
    }

    public void setContenuCommentaire(String contenuCommentaire) {
        this.contenuCommentaire = contenuCommentaire;
    }

    public String getDateCommentaire() {
        return dateCommentaire;
    }

    public void setDateCommentaire(String dateCommentaire) {
        this.dateCommentaire = dateCommentaire;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
    
    
    

}