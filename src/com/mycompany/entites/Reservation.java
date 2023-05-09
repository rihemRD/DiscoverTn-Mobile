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
public class Reservation {
    private int idReservation;
    private Evenement ev = new Evenement();
    private Hotel hot = new Hotel();
    private Camping camp = new Camping();
    //private Utilisateur utilisateur = new Utilisateur();
    private int idUserR;

    public Reservation() {
    }
    
   public Reservation(int idReservation, Evenement e, int idUserR) {
        this.idReservation = idReservation;
        this.ev = ev;
        this.idUserR = idUserR;
    }

    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public Evenement getEv() {
        return ev;
    }

    public void setEv(Evenement ev) {
        this.ev = ev;
    }
        public Hotel getHot() {
        return hot;
    }

    public void setHot(Hotel hot) {
        this.hot = hot;
    }

    public int getIdUserR() {
        return idUserR;
    }

    public void setIdUserR(int idUserR) {
        this.idUserR = idUserR;
    }

    public Camping getCamp() {
        return camp;
    }

    public void setCamp(Camping camp) {
        this.camp = camp;
    }

    @Override
    public String toString() {
        return "Reservation{" + "idReservation=" + idReservation + ", ev=" + ev + ", hot=" + hot + ", camp=" + camp + ", idUserR=" + idUserR + '}';
    }
    
}
