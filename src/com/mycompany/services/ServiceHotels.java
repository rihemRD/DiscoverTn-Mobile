/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.mycompany.entites.Hotel;
import com.mycompany.utils.Statics;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Reservation;
import com.sun.mail.smtp.SMTPTransport;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class ServiceHotels {
    
    public Hotel hotel = null;
    public List<Hotel> us;
    public String result = "";
    public static ServiceHotels instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    boolean retour = false;
    
    private ServiceHotels() {
        req = new ConnectionRequest();
    }

    public static ServiceHotels getInstance() {
        if (instance == null) {
            instance = new ServiceHotels();
        }
        return instance;
    }
    
    
        public List<Hotel> parseHotels(String json) {
        ArrayList<Hotel> listHotels = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(json.toCharArray()));
           

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            if (list.size() <= 0) {
                return listHotels;
            }
            
            for (Map<String, Object> obj : list) {
                
            Hotel hotel = new Hotel();
           
            float id = Float.parseFloat(obj.get("id").toString());
            hotel.setIdHotel((int) id);
            
            hotel.setNomHotel(obj.get("nom").toString());
            hotel.setAdresseHotel(obj.get("adresse").toString());
            hotel.setTelephoneHotel(obj.get("telephone").toString());
            hotel.setEmailHotel(obj.get("email").toString());
           
            float prix = Float.parseFloat(obj.get("prix").toString());
            hotel.setPrixHotel(prix);
            
            float etoile = Float.parseFloat(obj.get("etoile").toString());
            hotel.setEtoileHotel((int)etoile);
            
            listHotels.add(hotel);
     
            }
        } catch (Exception ex) {
            System.out.println("com.codename1.sport.services.ServicesPublications.parsePublications()"); 
        }
        
        return listHotels;
    }
    
       public List<Hotel> AllHotels() {
        String url = Statics.BASE_URL + "allHotels";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                us = parseHotels(new String(req.getResponseData()));
                
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return us;
    }

    public boolean add(Hotel t) {
        String url = Statics.BASE_URL + "addHotel?nom=" + t.getNomHotel()+ "&adresse=" + t.getAdresseHotel()+
                "&email=" +t.getEmailHotel()+ "&telephone=" +t.getTelephoneHotel()
                + "&prix=" +t.getPrixHotel()+ "&etoile=" +t.getEtoileHotel()+"&image="+t.getImageHotel() ;
                
    //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Ser vice task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
     public boolean edit(Hotel t) {
       
        String url = Statics.BASE_URL + "editHotel?nom=" + t.getNomHotel()+ "&adresse=" + t.getAdresseHotel()+
                "&email=" +t.getEmailHotel()+ "&telephone=" +t.getTelephoneHotel()+
                "&prix=" +t.getPrixHotel()+ "&etoile=" +t.getEtoileHotel()+"&id=" + t.getIdHotel();
        
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     
     public boolean Remove(int id) {
        String url = Statics.BASE_URL + "removeHotel/" + id; //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
           public boolean addReservation(Reservation r) {
        String url = Statics.BASE_URL + "reservationHotelM/?id=" + 
                r.getHot().getIdHotel() + "&userid=" 
                + r.getIdUserR(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
           
        public void sendMail(String email,Resources res) {
            try {
            Properties props = new Properties();
                props.put("mail.transport.protocol", "smtp"); 
		props.put("mail.smtps.host", "smtp.gmail.com"); 
		props.put("mail.smtps.auth", "true");        
            Session session = Session.getInstance(props,null); 
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("<pidevers3A10@gmail.com>"));
            msg.setRecipients(Message.RecipientType.TO, email);
            msg.setSubject("Hotel ");
            msg.setSentDate(new Date(System.currentTimeMillis()));
           String txt = "U Have Succcessfully Reserved An Hotel From Our Platform";
           msg.setText(txt);  
          SMTPTransport  st = (SMTPTransport)session.getTransport("smtps") ; 
          st.connect("smtp.gmail.com",465,"pidevers3A10@gmail.com","Testtest123");
          st.sendMessage(msg, msg.getAllRecipients());
        }catch(Exception e ) {
            e.printStackTrace();
        }
    }
    
}
