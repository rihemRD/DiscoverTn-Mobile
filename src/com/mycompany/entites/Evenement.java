/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entites;

import java.util.Date;



/**
 *
 * @author user
 */
public class Evenement {
    private int IdEvent;
    private String titreEvent;
    private String dateDebutEvent;
    private String dateFinEvent;
    private String prixEvent;
    private String lieuEvent;
    private String descEvent;
    private String imageEvent;
    private int idUser;
    
 

    public Evenement() {
    }

    public Evenement(int idEvent,String titreEvent, String dateDebutEvent, String dateFinEvent, String prixEvent, String lieuEvent, String descEvent, String imageEvent, int idUser) {
        this.titreEvent = titreEvent;
        this.dateDebutEvent = dateDebutEvent;
        this.dateFinEvent = dateFinEvent;
        this.prixEvent = prixEvent;
        this.lieuEvent = lieuEvent;
        this.descEvent = descEvent;
        this.imageEvent = imageEvent;
        this.idUser = idUser;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    

    public int getIdEvent() {
        return IdEvent;
    }

    public void setIdEvent(int IdEvent) {
        this.IdEvent = IdEvent;
    }

    public String getTitreEvent() {
        return titreEvent;
    }

    public void setTitreEvent(String titreEvent) {
        this.titreEvent = titreEvent;
    }
    
    public String getDateDebutEvent() {
        return dateDebutEvent;
    }

    public void setDateDebutEvent(String dateDebutEvent) {
        this.dateDebutEvent = dateDebutEvent;
    }
    
    public String getDateFinEvent() {
        return dateFinEvent;
    }

    public void setDateFinEvent(String dateFinEvent) {
        this.dateFinEvent = dateFinEvent;
    }

    public String getPrixEvent() {
        return prixEvent;
    }

    public void setPrixEvent(String prixEvent) {
        this.prixEvent = prixEvent;
    }

    
    public String getLieuEvent() {
        return lieuEvent;
    }

    public void setLieuEvent(String lieuEvent) {
        this.lieuEvent = lieuEvent;
    }

    
    public String getDescEvent() {
        return descEvent;
    }

    public void setDescEvent(String descEvent) {
        this.descEvent = descEvent;
    }

    
    public String getImageEvent() {
        return imageEvent;
    }

    public void setImageEvent(String imageEvent) {
        this.imageEvent = imageEvent;
    }

    
    
    @Override
    public String toString() {
        return "Evenement{" + "titreEvent=" + titreEvent + ", dateFinEvent=" + dateFinEvent + ", prixEvent=" + prixEvent + ", lieuEvent=" + lieuEvent + ", descEvent=" + descEvent + ", imageEvent=" + imageEvent +"}";
    }

   
}
