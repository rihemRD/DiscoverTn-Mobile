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
public class Participation {
     private int idParti, idRand,idClient ;
    private int nombre;
    private Double montant;
    private Date dateParti;
    private String etat,refp,nom;
    Camping idCamp;

    public Participation() {
    }

    public Participation(int idRand, int idClient, int nombre, Double montant, Date dateParti, String etat, String refp, String nom, Camping idCamp) {
        this.idRand = idRand;
        this.idClient = idClient;
        this.nombre = nombre;
        this.montant = montant;
        this.dateParti = dateParti;
        this.etat = etat;
        this.refp = refp;
        this.nom = nom;
        this.idCamp = idCamp;
    }

    public Participation(int idParti, int idRand, int idClient, int nombre, Double montant, Date dateParti, String etat, String refp, String nom, Camping idCamp) {
        this.idParti = idParti;
        this.idRand = idRand;
        this.idClient = idClient;
        this.nombre = nombre;
        this.montant = montant;
        this.dateParti = dateParti;
        this.etat = etat;
        this.refp = refp;
        this.nom = nom;
        this.idCamp = idCamp;
    }

    public int getIdParti() {
        return idParti;
    }

    public void setIdParti(int idParti) {
        this.idParti = idParti;
    }

    public int getIdRand() {
        return idRand;
    }

    public void setIdRand(int idRand) {
        this.idRand = idRand;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Date getDateParti() {
        return dateParti;
    }

    public void setDateParti(Date dateParti) {
        this.dateParti = dateParti;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getRefp() {
        return refp;
    }

    public void setRefp(String refp) {
        this.refp = refp;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Camping getIdCamp() {
        return idCamp;
    }

    public void setIdCamp(Camping idCamp) {
        this.idCamp = idCamp;
    }

    @Override
    public String toString() {
        return "Participation{" + "idParti=" + idParti + ", idRand=" + idRand + ", idClient=" + idClient + ", nombre=" + nombre + ", montant=" + montant + ", dateParti=" + dateParti + ", etat=" + etat + ", refp=" + refp + ", nom=" + nom + ", idCamp=" + idCamp + '}';
    }
    
}
