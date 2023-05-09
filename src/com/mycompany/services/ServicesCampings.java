package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;

import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Camping;
import com.mycompany.entites.Reservation;
import com.mycompany.utils.Statics;
import com.sun.mail.smtp.SMTPTransport;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;




public class ServicesCampings {
    public Camping camping = null;
    public List<Camping> us;
    public String result = "";
    public static ServicesCampings instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
     boolean retour = false;
    
    private ServicesCampings() {
        req = new ConnectionRequest();
    }

    public static ServicesCampings getInstance() {
        if (instance == null) {
            instance = new ServicesCampings();
        }
        return instance;
    }
    
    public List<Camping> parseCampings(String json) {
    ArrayList<Camping> listCampings = new ArrayList<>();

    try {
        JSONParser j = new JSONParser();
        Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(json.toCharArray()));


        List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

        if (list.size() <= 0) {
            return listCampings;
        }

        for (Map<String, Object> obj : list) {

            Camping camping = new Camping(); 
            float id = Float.parseFloat(obj.get("id").toString());
            camping.setIdCamping((int) id);      
            camping.setNomCamping(obj.get("nom").toString());
            camping.setAdresseCamping(obj.get("adresse").toString());
            camping.setImageCamping(obj.get("image").toString());
            camping.setTelephoneCamping(obj.get("telephone").toString());
            float rating = Float.parseFloat(obj.get("rating").toString());
            camping.setRatingCamping((int)rating);
            camping.setDescirptionCamping(obj.get("description").toString());
            listCampings.add(camping);
        }
    } catch (Exception ex) {
        System.out.println("com.codename1.sport.services.ServicesPublications.parsePublications()"); 
    }

    return listCampings;
    }
    
       public List<Camping> AllCampings() {
        String url = Statics.BASE_URL + "allCampings";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                us = parseCampings(new String(req.getResponseData()));
                
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return us;
    }

    public boolean add(Camping t) {
        String url = Statics.BASE_URL + "addCamping?nom=" + t.getNomCamping()+ "&adresse=" + t.getAdresseCamping()+
                "&image=" +t.getImageCamping()+ "&telephone=" +t.getTelephoneCamping()
                + "&rating=" +t.getRatingCamping()+ "&description=" +t.getDescirptionCamping() +
                "&userid=" +t.getIdUser();
                
                //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
     public boolean edit(Camping t) {
       
        String url = Statics.BASE_URL + "editCamping?nom=" + t.getNomCamping()+ "&adresse=" + t.getAdresseCamping()+
                "&image=" +t.getImageCamping()+ "&telephone=" +t.getTelephoneCamping()
                + "&rating=" +t.getRatingCamping()+ "&description=" +t.getDescirptionCamping() +
                "&userid=" +t.getIdUser();
  
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
       

        String url = Statics.BASE_URL + "removeCamping/" + id; //création de l'URL
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
       
        String url = Statics.BASE_URL + "reservationCampingM/?id=" + r.getCamp().getIdCamping()+ "&userid=" 
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
     
}
