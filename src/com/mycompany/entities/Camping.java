/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

import java.util.Date;

/**
 *
 * @author rihem
 */
public class Camping {
     private int periode, nbr_place;
    private int idCamping;
    private String nom, lieux, description;
    Date dateDebut, dateFin;
    private double prix;
    private String image;
    private String imageC;

    public Camping() {
    }

    public Camping(int periode, int nbr_place, String nom, String lieux, String description, Date dateDebut, Date dateFin, double prix, String image, String imageC) {
        this.periode = periode;
        this.nbr_place = nbr_place;
        this.nom = nom;
        this.lieux = lieux;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prix = prix;
        this.image = image;
        this.imageC = imageC;
    }

    public Camping( int idCamping,int periode, int nbr_place, String nom, String lieux, String description, Date dateDebut, Date dateFin, double prix, String image, String imageC) {
        this.periode = periode;
        this.nbr_place = nbr_place;
        this.idCamping = idCamping;
        this.nom = nom;
        this.lieux = lieux;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prix = prix;
        this.image = image;
        this.imageC = imageC;
    }

    public int getPeriode() {
        return periode;
    }

    public void setPeriode(int periode) {
        this.periode = periode;
    }

    public int getNbr_place() {
        return nbr_place;
    }

    public void setNbr_place(int nbr_place) {
        this.nbr_place = nbr_place;
    }

    public int getIdCamping() {
        return idCamping;
    }

    public void setIdCamping(int idCamping) {
        this.idCamping = idCamping;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLieux() {
        return lieux;
    }

    public void setLieux(String lieux) {
        this.lieux = lieux;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageC() {
        return imageC;
    }

    public void setImageC(String imageC) {
        this.imageC = imageC;
    }

    @Override
    public String toString() {
        return "Camping{" + "periode=" + periode + ", nbr_place=" + nbr_place + ", idCamping=" + idCamping + ", nom=" + nom + ", lieux=" + lieux + ", description=" + description + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", prix=" + prix + ", image=" + image + ", imageC=" + imageC + '}';
    }
    
}
