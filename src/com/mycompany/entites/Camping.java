/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entites;

/**
 *
 * @author ProtocolBlood
 */
public class Camping {
    private int idCamping;
    private String nomCamping;
    private String adresseCamping;
    private String imageCamping;
    private String telephoneCamping;
    private int ratingCamping;
    private String descirptionCamping;
    private int idUser;

    public Camping() {
    }

    public Camping(int idCamping, String nomCamping, String adresseCamping, String imageCamping, String telephoneCamping, int ratingCamping, String descirptionCamping, int idUser) {
        this.idCamping = idCamping;
        this.nomCamping = nomCamping;
        this.adresseCamping = adresseCamping;
        this.imageCamping = imageCamping;
        this.telephoneCamping = telephoneCamping;
        this.ratingCamping = ratingCamping;
        this.descirptionCamping = descirptionCamping;
        this.idUser = idUser;
    }

    public int getIdCamping() {
        return idCamping;
    }

    public void setIdCamping(int idCamping) {
        this.idCamping = idCamping;
    }

    public String getNomCamping() {
        return nomCamping;
    }

    public void setNomCamping(String nomCamping) {
        this.nomCamping = nomCamping;
    }

    public String getAdresseCamping() {
        return adresseCamping;
    }

    public void setAdresseCamping(String adresseCamping) {
        this.adresseCamping = adresseCamping;
    }

    public String getImageCamping() {
        return imageCamping;
    }

    public void setImageCamping(String imageCamping) {
        this.imageCamping = imageCamping;
    }

    public String getTelephoneCamping() {
        return telephoneCamping;
    }

    public void setTelephoneCamping(String telephoneCamping) {
        this.telephoneCamping = telephoneCamping;
    }

    public int getRatingCamping() {
        return ratingCamping;
    }

    public void setRatingCamping(int ratingCamping) {
        this.ratingCamping = ratingCamping;
    }

    public String getDescirptionCamping() {
        return descirptionCamping;
    }

    public void setDescirptionCamping(String descirptionCamping) {
        this.descirptionCamping = descirptionCamping;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Camping{" + "idCamping=" + idCamping + ", nomCamping=" + nomCamping + ", adresseCamping=" + adresseCamping + ", imageCamping=" + imageCamping + ", telephoneCamping=" + telephoneCamping + ", ratingCamping=" + ratingCamping + ", descirptionCamping=" + descirptionCamping + ", idUser=" + idUser + '}';
    }
    
    
}
