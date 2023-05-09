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
import com.mycompany.entites.Evenement;
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




public class ServiceEvenements {
    public Evenement evenement = null;
    public List<Evenement> us;
    public String result = "";
    public static ServiceEvenements instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
     boolean retour = false;
    
    private ServiceEvenements() {
        req = new ConnectionRequest();
    }

    public static ServiceEvenements getInstance() {
        if (instance == null) {
            instance = new ServiceEvenements();
        }
        return instance;
    }
    
    public List<Evenement> parseEvenements(String json) {
    ArrayList<Evenement> listEvenements = new ArrayList<>();

    try {
        JSONParser j = new JSONParser();
        Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(json.toCharArray()));


        List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

        if (list.size() <= 0) {
            return listEvenements;
        }

        for (Map<String, Object> obj : list) {
            System.out.println(obj);
            Evenement evenement = new Evenement(); 
            float id = Float.parseFloat(obj.get("id").toString());
            System.out.println("###########################################");
            evenement.setIdEvent((int) id);  
            System.out.println("###########################################");
            evenement.setTitreEvent(obj.get("titre").toString());
            System.out.println("###########################################");
            evenement.setLieuEvent(obj.get("lieu").toString());
            System.out.println("###########################################");
            evenement.setDateDebutEvent(obj.get("date_debut").toString());
            System.out.println("###########################################"+obj.get("date_debut").toString());
            evenement.setDateFinEvent(obj.get("date_fin").toString());
            System.out.println("###########################################");
            evenement.setDescEvent(obj.get("description").toString());
            System.out.println("###########################################");
            //float prix = Float.parseFloat(obj.get("prix").toString());
            evenement.setPrixEvent(obj.get("prix").toString());
  System.out.println("###########################################");
            //System.out.println(obj.get("date").toString());
            /*Date date;
            DateFormat formatter;
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            String a=((LinkedHashMap)obj.get("date")).get("date").toString();
            date = (Date) formatter.parse(((LinkedHashMap)obj.get("date")).get("date").toString());
            evenement.setDateEvenement(date);*/
            evenement.setImageEvent(obj.get("image").toString());
            System.out.println(evenement);
            listEvenements.add(evenement);
        }
    } catch (Exception ex) {
         System.out.println(ex); 
        System.out.println("com.codename1.sport.services.ServicesPublications.parsePublications()"); 
    }

    return listEvenements;
    }
    
       public List<Evenement> AllEvenements() {
        String url = Statics.BASE_URL + "allEvenements";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                us = parseEvenements(new String(req.getResponseData()));
                
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return us;
    }

    public boolean add(Evenement t) {
        String url = Statics.BASE_URL + "addEvenement?titre=" + t.getTitreEvent()+ "&lieu=" + t.getLieuEvent()+
               "&date_debut=" +t.getDateDebutEvent()+  "&date_fin=" +t.getDateFinEvent()+"&prix=" +t.getPrixEvent()+
               "&description=" +t.getDescEvent() + "&image=" +t.getImageEvent()  + "&userid=" +t.getIdUser();
                
                //création de l'URL
             req.setUrl(url);// Insertion de l'URL de notre demande de connexion
             req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
               
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        //return resultOK;
        return true;
    }
    
     public boolean edit(Evenement t) {
       
        /*String url = Statics.BASE_URL + "editEvenemnet?titre=" + t.getTitreEvenement()+ "&lieu=" + t.getLieuEvenement()+
                "&type=" +t.getTypeEvenement()+ "&description=" +t.getDescriptionEvenement()
                + "&image=" +t.getImageEvenement() + "&status=" +t.getStatusEvenement() +
                "&prix=" +t.getPrixEvenement() + "&nbplaces=" +t.getNbPlacesEvenement() +"&id=" + t.getIdEvenement();
        
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;*/
                 return true;
    }
     
     public boolean Remove(int id) {
       

        String url = Statics.BASE_URL + "removeEvenement/" + id; //création de l'URL
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
       
        /*String url = Statics.BASE_URL + "reservationEvenementM/?id=" + r.getEv().getIdEvenement() + "&userid=" 
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
        return resultOK;*/
         return true;
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
            msg.setSubject("Your Reservation Has Been Confirmed");
            msg.setSentDate(new Date(System.currentTimeMillis()));
           String txt = "Bienvenue sur notre plateform : PiDevers ";
           msg.setText(txt);  
          SMTPTransport  st = (SMTPTransport)session.getTransport("smtps") ; 
          st.connect("smtp.gmail.com",465,"pidevers3A10@gmail.com","Testtest123");
          st.sendMessage(msg, msg.getAllRecipients());

        }catch(Exception e ) {
            e.printStackTrace();
        }
    }
}
