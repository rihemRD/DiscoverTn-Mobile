/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.Preferences;

/**
 *
 * @author Ben Mahmoud
 */
public class SessionManager {
    
    public static Preferences pref ; 
    
    
    
  
    private static int id ; 
    private static String userName ; 
    private static String email; 
    private static String passowrd ;
    private static String nom ;
    private static String prenom ;
    private static String telephone ;
    private static String adresse ;
    private static String photo;
    private static String Testestttt;

    public static Preferences getPref() {
        return pref;
    }

    public static void setPref(Preferences pref) {
        SessionManager.pref = pref;
    }

    public static int getId() {
        return pref.get("id",id);
    }

    public static void setId(int id) {
        pref.set("id",id);
    }

    public static String getUserName() {
        return pref.get("username",userName);
    }

    public static void setUserName(String userName) {
         pref.set("username",userName);
    }

    public static String getEmail() {
        return pref.get("email",email);
    }

    public static void setEmail(String email) {
         pref.set("email",email);
    }

    public static String getPassowrd() {
        return pref.get("passowrd",passowrd);
    }

    public static void setPassowrd(String passowrd) {
         pref.set("passowrd",passowrd);
    }

    public static String getTestestttt() {
        return pref.get("Testestttt",Testestttt);
        
    }

    public static void setTestestttt(String Testestttt) {
        pref.set("Testestttt",Testestttt);
       
    }

    public static String getPhoto() {
        return pref.get("photo",photo);
    }

    public static void setPhoto(String photo) {
         pref.set("photo",photo);
    }

    public static String getNom() {
        return nom;
    }

    public static void setNom(String nom) {
        SessionManager.nom = nom;
    }

    public static String getPrenom() {
        return prenom;
    }

    public static void setPrenom(String prenom) {
        SessionManager.prenom = prenom;
    }

    public static String getTelephone() {
        return telephone;
    }

    public static void setTelephone(String telephone) {
        SessionManager.telephone = telephone;
    }

    public static String getAdresse() {
        return adresse;
    }

    public static void setAdresse(String adresse) {
        SessionManager.adresse = adresse;
    }
    
    
    
    
    
    
}
