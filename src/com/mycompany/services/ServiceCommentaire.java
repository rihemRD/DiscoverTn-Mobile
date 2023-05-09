/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.entites.Article;
import com.mycompany.utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.codename1.util.StringUtil;
import com.mycompany.entites.Categorie;
import com.mycompany.entites.Commentaire;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Yassin Khabthani
 */
public class ServiceCommentaire {
     
    //singleton 
    public static ServiceCommentaire instance = null ;
        public List<Commentaire> com;

    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceCommentaire getInstance() {
        if(instance == null )
            instance = new ServiceCommentaire();
        return instance ;
    }
    
        public ServiceCommentaire() {
        req = new ConnectionRequest();
        
    }
        
        
            //ajout 
    public void ajouterCommentaire(Commentaire commentaire) {
        
        String url =Statics.BASE_URL+"ajoutCommentaireMobile?contenuCommentaire="+commentaire.getContenuCommentaire()+"&article="+commentaire.getIdArticle()+ "&utilisateur="+commentaire.getIdUtilisateur(); 
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println(str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    
   
    
    
    public List<Commentaire> parseCommentaires(String json) {
    ArrayList<Commentaire> listCommentaires = new ArrayList<>();

    try {
        JSONParser j = new JSONParser();
        Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(json.toCharArray()));


        List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

        if (list.size() <= 0) {
            return listCommentaires;
        }
        for (Map<String, Object> obj : list) {
            Commentaire commentaire = new Commentaire(); 
            float idCommentaire = Float.parseFloat(obj.get("id").toString());
            commentaire.setIdCommentaire((int) idCommentaire);      
            commentaire.setContenuCommentaire(obj.get("contenuCommentaire").toString());
            commentaire.setDateCommentaire(obj.get("dateCommentaire").toString());
            float idArticle = Float.parseFloat(obj.get("idArticle").toString());
            commentaire.setIdArticle((int) idArticle);      
            float idUtilisateur = Float.parseFloat(obj.get("idUtilisateur").toString());
            commentaire.setIdUtilisateur((int) idUtilisateur);
            listCommentaires.add(commentaire);
        }
    } catch (Exception ex) {
        System.out.println("com.codename1.sport.services.ServicesPublications.parsePublications()"); 
    }

    return listCommentaires;
    }
    
       public List<Commentaire> AllCommentaires() {
        String url = Statics.BASE_URL + "afficherCommentaireMobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                com = parseCommentaires(new String(req.getResponseData()));
                
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return com;
    }

    
    
    
    
    //Delete 
    public boolean supprimerCommentaire(int idCommentaire ) {
        String url = Statics.BASE_URL +"supprimerCommentaireMobile?idCommentaire="+idCommentaire;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
    
    
    
    //Update 
    public boolean modifierCommentaire(Commentaire commentaire) {
        String url = Statics.BASE_URL +"modifierCommentaireMobile?idCommentaire="+commentaire.getIdCommentaire()+"&contenuCommentaire="+commentaire.getContenuCommentaire();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
        
    }
    
    
        
}
