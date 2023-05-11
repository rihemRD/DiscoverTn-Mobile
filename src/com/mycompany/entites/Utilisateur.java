/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entites;

/**
 *
 * @author Ben Mahmoud
 */
public class Utilisateur {
   private int rankUtilisateur;
    private int idUtilisateur;
    private String nomUtilisateur;
    private String prenomUtilisateur;
    private String emailUtilisateur;
    private String loginUtilisateur;
    private String mot_de_passeUtilisateur;
    private String imgUtilisateur;
    private String numero_telephoneUtilisateur;
    private String adresseUtilisateur;
    
  
     public Utilisateur() {
    }  
    public Utilisateur(int rankUtilisateur, String nomUtilisateur, String prenomUtilisateur, String emailUtilisateur, String loginUtilisateur, String mot_de_passeUtilisateur, String imgUtilisateur, String numero_telephoneUtilisateur, String adresseUtilisateur) {
        this.rankUtilisateur = rankUtilisateur;
        this.nomUtilisateur = nomUtilisateur;
        this.prenomUtilisateur = prenomUtilisateur;
        this.emailUtilisateur = emailUtilisateur;
        this.loginUtilisateur = loginUtilisateur;
        this.mot_de_passeUtilisateur = mot_de_passeUtilisateur;
        this.imgUtilisateur = imgUtilisateur;
        this.numero_telephoneUtilisateur = numero_telephoneUtilisateur;
        this.adresseUtilisateur = adresseUtilisateur;
    }

   

    public Utilisateur(int rankUtilisateur, int idUtilisateur, String nomUtilisateur, String prenomUtilisateur, String emailUtilisateur, String loginUtilisateur, String mot_de_passeUtilisateur, String imgUtilisateur, String numero_telephoneUtilisateur, String adresseUtilisateur) {
        this.rankUtilisateur = rankUtilisateur;
        this.idUtilisateur = idUtilisateur;
        this.nomUtilisateur = nomUtilisateur;
        this.prenomUtilisateur = prenomUtilisateur;
        this.emailUtilisateur = emailUtilisateur;
        this.loginUtilisateur = loginUtilisateur;
        this.mot_de_passeUtilisateur = mot_de_passeUtilisateur;
        this.imgUtilisateur = imgUtilisateur;
        this.numero_telephoneUtilisateur = numero_telephoneUtilisateur;
        this.adresseUtilisateur = adresseUtilisateur;
    }

    
    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getPrenomUtilisateur() {
        return prenomUtilisateur;
    }

    public void setPrenomUtilisateur(String prenomUtilisateur) {
        this.prenomUtilisateur = prenomUtilisateur;
    }

    public String getEmailUtilisateur() {
        return emailUtilisateur;
    }

    public void setEmailUtilisateur(String emailUtilisateur) {
        this.emailUtilisateur = emailUtilisateur;
    }

    public String getLoginUtilisateur() {
        return loginUtilisateur;
    }

    public void setLoginUtilisateur(String loginUtilisateur) {
        this.loginUtilisateur = loginUtilisateur;
    }

    public String getMot_de_passeUtilisateur() {
        return mot_de_passeUtilisateur;
    }

    public void setMot_de_passeUtilisateur(String mot_de_passeUtilisateur) {
        this.mot_de_passeUtilisateur = mot_de_passeUtilisateur;
    }

    public String getImgUtilisateur() {
        return imgUtilisateur;
    }

    public void setImgUtilisateur(String imgUtilisateur) {
        this.imgUtilisateur = imgUtilisateur;
    }

    public int getRankUtilisateur() {
        return rankUtilisateur;
    }

    public void setRankUtilisateur(int rankUtilisateur) {
        this.rankUtilisateur = rankUtilisateur;
    }

    public String getNumero_telephoneUtilisateur() {
        return numero_telephoneUtilisateur;
    }

    public void setNumero_telephoneUtilisateur(String numero_telephoneUtilisateur) {
        this.numero_telephoneUtilisateur = numero_telephoneUtilisateur;
    }

    public String getAdresseUtilisateur() {
        return adresseUtilisateur;
    }

    public void setAdresseUtilisateur(String adresseUtilisateur) {
        this.adresseUtilisateur = adresseUtilisateur;
    }
    
    @Override
    public String toString() {
        return "Utilisateur{" + "rankUtilisateur=" + rankUtilisateur + ", idUtilisateur=" + idUtilisateur + ", nomUtilisateur=" + nomUtilisateur + ", prenomUtilisateur=" + prenomUtilisateur + ", emailUtilisateur=" + emailUtilisateur + ", loginUtilisateur=" + loginUtilisateur + ", mot_de_passeUtilisateur=" + mot_de_passeUtilisateur + ", imgUtilisateur=" + imgUtilisateur + ", numero_telephoneUtilisateur=" + numero_telephoneUtilisateur + ", adresseUtilisateur=" + adresseUtilisateur + '}';
    }

}

