/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entites;

/**
 *
 * @author user
 */
public class Hotel {
    private Integer idHotel;
    private String nomHotel;
    private String adresseHotel;
    private String emailHotel;
    private Integer etoileHotel;
    private String telephoneHotel;
    private Float prixHotel;
    private String imageHotel;

    public Hotel() {
    }

    public Hotel(String nomHotel, String adresseHotel, String emailHotel, Integer etoileHotel, String telephoneHotel, Float prixHotel, String imageHotel) {
        this.nomHotel = nomHotel;
        this.adresseHotel = adresseHotel;
        this.emailHotel = emailHotel;
        this.etoileHotel = etoileHotel;
        this.telephoneHotel = telephoneHotel;
        this.prixHotel = prixHotel;
        this.imageHotel = imageHotel;
    }

    public Hotel(Integer idHotel, String nomHotel, String adresseHotel, String emailHotel, Integer etoileHotel, String telephoneHotel, Float prixHotel, String imageHotel) {
        this.idHotel = idHotel;
        this.nomHotel = nomHotel;
        this.adresseHotel = adresseHotel;
        this.emailHotel = emailHotel;
        this.etoileHotel = etoileHotel;
        this.telephoneHotel = telephoneHotel;
        this.prixHotel = prixHotel;
        this.imageHotel = imageHotel;
    }

    public Integer getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(Integer idHotel) {
        this.idHotel = idHotel;
    }

    public String getNomHotel() {
        return nomHotel;
    }

    public void setNomHotel(String nomHotel) {
        this.nomHotel = nomHotel;
    }

    public String getAdresseHotel() {
        return adresseHotel;
    }

    public void setAdresseHotel(String adresseHotel) {
        this.adresseHotel = adresseHotel;
    }

    public String getEmailHotel() {
        return emailHotel;
    }

    public void setEmailHotel(String emailHotel) {
        this.emailHotel = emailHotel;
    }

    public Integer getEtoileHotel() {
        return etoileHotel;
    }

    public void setEtoileHotel(Integer etoileHotel) {
        this.etoileHotel = etoileHotel;
    }

    public String getTelephoneHotel() {
        return telephoneHotel;
    }

    public void setTelephoneHotel(String telephoneHotel) {
        this.telephoneHotel = telephoneHotel;
    }

    public Float getPrixHotel() {
        return prixHotel;
    }

    public void setPrixHotel(Float prixHotel) {
        this.prixHotel = prixHotel;
    }

    public String getImageHotel() {
        return imageHotel;
    }

    public void setImageHotel(String imageHotel) {
        this.imageHotel = imageHotel;
    }

    @Override
    public String toString() {
        return "Hotel{" + "idHotel=" + idHotel + ", nomHotel=" + nomHotel + ", adresseHotel=" + adresseHotel + ", emailHotel=" + emailHotel + ", etoileHotel=" + etoileHotel + ", telephoneHotel=" + telephoneHotel + ", prixHotel=" + prixHotel + ", imageHotel=" + imageHotel + '}';
    }
    
    
    
}
