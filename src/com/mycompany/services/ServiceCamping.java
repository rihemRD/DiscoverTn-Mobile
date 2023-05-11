/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;
import java.util.List;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Camping;
import com.mycompany.entities.Participation;
import com.mycompany.utils.Statics;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author rihem
 */
public class ServiceCamping {
        public static ServiceCamping instance = null ;
        
         public static boolean resultOk = true;
         boolean resultOK;
         public List<Camping> us;
         public List<Participation> usp;
         
        
            private ConnectionRequest req;

        public static ServiceCamping getInstance() {
        if(instance == null )
            instance = new ServiceCamping();
        return instance ;
    }
        
        public ServiceCamping() {
        req = new ConnectionRequest();
        
    }
        
         public boolean ajoutCamping(Camping camp) {
                String url = Statics.BASE_URL + "/ajoutCampingMobile?nom=" + camp.getNom()+ "&lieux=" + camp.getLieux()+
                "&imagec=" +camp.getImageC()+ "&description=" +camp.getDescription()+ "&dateDebut=" +camp.getDateDebut() +
                "&dateFin=" +camp.getDateFin()+ "&prix=" +camp.getPrix()+ "&image=" +camp.getImage()+
                "&periode="+camp.getPeriode()+"&nbr_place="+camp.getNbr_place();        
                req.setUrl(url);
                req.setPost(false);
                System.out.println(url); 
                
                req.addResponseListener(new ActionListener<NetworkEvent>() {
                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                        req.removeResponseListener(this);
                    }
                });
                NetworkManager.getInstance().addToQueueAndWait(req);
                return resultOK;
        
    }
         
          public List<Camping> parseCampings(String json) {
    ArrayList<Camping> listCampings = new ArrayList<>();
                JSONParser jsonp ;
                jsonp = new JSONParser();
    try {
        JSONParser j = new JSONParser();
        //Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(json.toCharArray()));


 Map<String,Object>mapCampings = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
   List<Map<String, Object>> list = (List<Map<String, Object>>) mapCampings.get("root");
            
                    
        if (list.size() <= 0) {  
        System.out.println("fergha");    
        return listCampings;
            
        }       
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Map<String, Object> obj : list) {

            Camping camping = new Camping(); 
            float id = Float.parseFloat(obj.get("id_Camping").toString());
            camping.setIdCamping((int) id);      
            camping.setNom(obj.get("Nom").toString());
            camping.setLieux(obj.get("lieux").toString());
            camping.setImageC(obj.get("imagec").toString());
//            String dateDebutString = obj.get("dateDebut").toString();
//            Date DateDebut = dateFormat.parse(dateDebutString);
//            camping.setDateDebut(DateDebut);
//            String dateFinString = obj.get("dateFin").toString();
//            Date dateFin = dateFormat.parse(dateFinString);
//            camping.setDateFin(dateFin);
            float Periode = Float.parseFloat(obj.get("periode").toString());
            camping.setPeriode((int)Periode);
            float Prix = Float.parseFloat(obj.get("prix").toString());
            camping.setPrix((float)Prix);
            float Place = Float.parseFloat(obj.get("nbr_place").toString());
            camping.setNbr_place((int)Place);
            camping.setDescription(obj.get("description").toString());
             System.out.println(obj);
            listCampings.add(camping);
            System.out.println("jawek behy");
                        }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
System.out.println(listCampings);

    return listCampings;
    }
         
          public List<Camping> AllCampings() {
        String url = Statics.BASE_URL + "/afficherCampingMobile";
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
         
         
         
       public ArrayList<Camping>affichageCampings() {
        ArrayList<Camping> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/afficherCampingMobile";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapCampings = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                        List<Map<String, Object>> list = (List<Map<String, Object>>) mapCampings.get("root");
                    
                        for (Map<String, Object> obj : list) {
                        Camping camp = new Camping();
                        
                        float id = Float.parseFloat(obj.get("id").toString());
                        String Nom = obj.get("nom").toString();
                        String description = obj.get("description").toString();
                        float Prix = Float.parseFloat(obj.get("prix").toString());
                        camp.setIdCamping((int)id);
                        camp.setNom(Nom);
                        camp.setDescription(description);
                        camp.setPrix((int)Prix);
                        String DateConverter =  obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp") + 10 , obj.get("date").toString().lastIndexOf("}"));
                        Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String dateString = formatter.format(currentTime);
                       // camp.setDateDebut( currentTime);
                        
                        //insert data into ArrayList result
                        result.add(camp);
                        }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
        
    }
        
        public boolean deleteCamping(int id ) {
        
         boolean confirmed = Dialog.show("Confirmation", "Are you sure you want to delete this camping?", "Yes", "Cancel");
    if (confirmed) {
        String url = Statics.BASE_URL + "/supprimerCampingMobile/" + id;
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        if (resultOK) {
            Dialog.show("Success", "Camping deleted successfully", "OK", null);
        } else {
            Dialog.show("Error", "Failed to delete camping", "OK", null);
        }
    }
    return resultOK;
    }
    
//        public boolean edit(Camping t) {
//       
//        String url = Statics.BASE_URL + "editCamping?nom=" + t.getNomCamping()+ "&adresse=" + t.getAdresseCamping()+
//                "&image=" +t.getImageCamping()+ "&telephone=" +t.getTelephoneCamping()
//                + "&rating=" +t.getRatingCamping()+ "&description=" +t.getDescirptionCamping() +
//                "&userid=" +t.getIdUser();
//  
//        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
//                req.removeResponseListener(this); //Supprimer cet actionListener
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return resultOK;
//    }
          public boolean modifierCamping(Camping camp) {
        String url = Statics.BASE_URL + "/modifierCampingMobile/"+camp.getIdCamping()+"/?nom=" + camp.getNom()+ "&lieux=" + camp.getLieux()+
                "&imagec=" +camp.getImageC()+ "&description=" +camp.getDescription()+ "&dateDebut=" +camp.getDateDebut() +
                "&dateFin=" +camp.getDateFin()+ "&prix=" +camp.getPrix()+ "&image=" +camp.getImage()+
                "&periode="+camp.getPeriode()+"&nbr_place="+camp.getNbr_place();
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
         System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                 System.out.println("fergha");
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
        
    }
          public boolean addParticipation(Camping camp) {
       
        String url = Statics.BASE_URL + "/ajoutParticipationMobile/" + camp.getIdCamping();  //création de l'URL
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
          
          
           public List<Participation> parseParticipations(String json) {
    ArrayList<Participation> listParticipations = new ArrayList<>();
                JSONParser jsonp ;
                jsonp = new JSONParser();
    try {
        JSONParser j = new JSONParser();
        //Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(json.toCharArray()));


 Map<String,Object>mapParticipations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
   List<Map<String, Object>> list = (List<Map<String, Object>>) mapParticipations.get("root");
            
                    
        if (list.size() <= 0) {  
        System.out.println("fergha");    
        return listParticipations;
            
        }       
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Map<String, Object> obj : list) {

            Participation participation = new Participation(); 
            float id = Float.parseFloat(obj.get("id").toString());
            participation.setIdParti((int) id);      
            participation.setNom(obj.get("nom").toString());
            participation.setEtat(obj.get("etat").toString());
            participation.setRefp(obj.get("refp").toString());
            String dateDebutString = obj.get("dateParti").toString();
            //participation.setDateParti(dateFormat.parse(dateDebutString));
//            String dateFinString = obj.get("dateFin").toString();
//            Date dateFin = dateFormat.parse(dateFinString);
//            camping.setDateFin(dateFin);
            float Nombre = Float.parseFloat(obj.get("nombre").toString());
            participation.setNombre((int)Nombre);
            float Montant = Float.parseFloat(obj.get("montant").toString());
            participation.setMontant((double)Montant);
            
             System.out.println(obj);
            listParticipations.add(participation);
            System.out.println("jawek behy");
                        }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
System.out.println(listParticipations);

    return listParticipations;
    }
          
          
       public List<Participation> AllParticipations() {
        String url = Statics.BASE_URL + "/afficherParticipationMobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                usp = parseParticipations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return usp;
    }
         
         
         
       public ArrayList<Participation>affichageParticipations() {
        ArrayList<Participation> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/afficherParticipationMobile";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapParticipations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                        List<Map<String, Object>> list = (List<Map<String, Object>>) mapParticipations.get("root");
                    
                        for (Map<String, Object> obj : list) {
                        Participation part = new Participation();
                        
                        float id = Float.parseFloat(obj.get("id").toString());
                        String Nom = obj.get("nom").toString();
                        String Ref = obj.get("refp").toString();
                        String etat = obj.get("etat").toString();
                        float Nombre = Float.parseFloat(obj.get("nombre").toString());
                        float Montant = Float.parseFloat(obj.get("montant").toString());
                        part.setIdParti((int)id);
                        part.setNom(Nom);
                        part.setRefp(Ref);
                        part.setEtat(etat);
                        part.setNombre((int)Nombre);
                        part.setMontant((double)Montant);
                        String DateConverter =  obj.get("dateParti").toString().substring(obj.get("date").toString().indexOf("timestamp") + 10 , obj.get("date").toString().lastIndexOf("}"));
                        Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String dateString = formatter.format(currentTime);
                        part.setDateParti( currentTime);
                        
                        //insert data into ArrayList result
                        result.add(part);
                        }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
        
    } 
        public boolean deleteParticipation(int id ) {
        
         boolean confirmed = Dialog.show("Confirmation", "Are you sure you want to delete this Participation?", "Yes", "Cancel");
    if (confirmed) {
        String url = Statics.BASE_URL + "/supprimerParticipationMobile/" + id;
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        if (resultOK) {
            Dialog.show("Success", "Participation deleted successfully", "OK", null);
        } else {
            Dialog.show("Error", "Failed to delete participation", "OK", null);
        }
    }
    return resultOK;
    }
        
        
 public boolean ConfParticipation(int id ) {
        
         boolean confirmed = Dialog.show("Confirmation", "Are you sure you want to Confirm this Participation?", "Yes", "Cancel");
    if (confirmed) {
        String url = Statics.BASE_URL + "/confirmerParticipationMobile/" + id;
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        if (resultOK) {
            Dialog.show("Success", "Participation Confirmer successfully", "OK", null);
        } else {
            Dialog.show("Error", "Failed to Confirm participation", "OK", null);
        }
    }
    return resultOK;
    }
 
 public boolean SendMail(int id ) {
        
//         boolean confirmed = Dialog.show("Confirmation", "Are you sure you want to Confirm this Participation?", "Yes", "Cancel");
//    if (confirmed) {
        String url = Statics.BASE_URL + "/mailling/" + id;
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        if (resultOK) {
            Dialog.show("Success", "Participation Confirmer successfully", "OK", null);
        } else {
            Dialog.show("Error", "Failed to Confirm participation", "OK", null);
        }
//    }
    return resultOK;
    }
}
