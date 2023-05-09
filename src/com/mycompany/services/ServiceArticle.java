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
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.codename1.util.StringUtil;
import com.mycompany.entites.Categorie;
import com.mycompany.gui.SignInForm;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Yassin Khabthani
 */
public class ServiceArticle {
     
   
    public static ServiceArticle instance = null ;
        public List<Article> us;
        public List <Categorie> cat;

    public static boolean resultOk = true;

    
    private ConnectionRequest req;
    
    
    public static ServiceArticle getInstance() {
        if(instance == null )
            instance = new ServiceArticle();
        return instance ;
    }
    
        public ServiceArticle() {
        req = new ConnectionRequest();
        
    }
        
        
            //ajout 
    public void ajouterArticle(Article article) {
        
        String url =Statics.BASE_URL+"ajoutArticleMobile?titreArticle="+article.getTitreArticle()+"&descriptionArticle="+article.getDescriptionArticle()+"&imageArticle="+article.getImageArticle()+"&categorie="+article.getIdCategorie()+ "&utilisateur="+article.getIdUtilisateur(); 
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println(str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    
    

    
    public List<Article> parseArticles(String json) {
    ArrayList<Article> listArticles = new ArrayList<>();

    try {
        JSONParser j = new JSONParser();
        Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(json.toCharArray()));


        List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

        if (list.size() <= 0) {
            return listArticles;
        }
        for (Map<String, Object> obj : list) {
            Article article = new Article(); 
            float idArticle = Float.parseFloat(obj.get("id").toString());
            article.setIdArticle((int) idArticle);      
            article.setTitreArticle(obj.get("titreArticle").toString());           
            article.setDescriptionArticle(obj.get("descriptionArticle").toString());
            article.setImageArticle(obj.get("imageArticle").toString());
            article.setDateArticle(obj.get("dateArticle").toString());
            float idCategorie = Float.parseFloat(obj.get("idCategorie").toString());
            article.setIdCategorie((int) idCategorie);      
            float idUtilisateur = Float.parseFloat(obj.get("idUtilisateur").toString());
            article.setIdUtilisateur((int) idUtilisateur);      
            listArticles.add(article);
        }
    } catch (Exception ex) {
        System.out.println("com.codename1.sport.services.ServicesPublications.parsePublications()"); 
    }

    return listArticles;
    }
    
       public List<Article> AllArticles() {
        String url = Statics.BASE_URL + "afficherArticleMobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                us = parseArticles(new String(req.getResponseData()));
                
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return us;
    }

    
    
    
    
    //Delete 
    public boolean supprimerArticle(int idArticle ) {
        String url = Statics.BASE_URL +"supprimerArticleMobile?idArticle="+idArticle;
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
    public boolean modifierArticle(Article article) {
        String url = Statics.BASE_URL +"modifierArticleMobile?idArticle="+article.getIdArticle()+"&titreArticle="+article.getTitreArticle()+"&descriptionArticle="+article.getDescriptionArticle()+"&imageArticle="+article.getImageArticle()+"&categorie="+article.getIdCategorie();
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
    
    
        public String badwords(String message){
         ArrayList<String> badwords = new ArrayList<String>();
         badwords.add("test");
         badwords.add("test1");
         badwords.add("test2");
         badwords.add("test3");
         for( int i=0; i<badwords.size();i++){
                 String filter="";
                 for( int j=0; j<badwords.get(i).length();j++) {
                 filter=filter+"*";
                     System.out.println(filter);
             }
                 String a=badwords.get(i);
 
                message= StringUtil.replaceAll(message,a,filter);
                
                System.out.println(message);


         }
        return message;
    }
    
        
        
            public List<Categorie> parseCategories(String json) {
    ArrayList<Categorie> listCategories = new ArrayList<>();

    try {
        JSONParser j = new JSONParser();
        Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(json.toCharArray()));


        List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

        if (list.size() <= 0) {
            return listCategories;
        }
        for (Map<String, Object> obj : list) {
            Categorie categorie = new Categorie(); 
            float idCategorie = Float.parseFloat(obj.get("id").toString());
            categorie.setIdCategorie((int) idCategorie);      
            categorie.setNomCategorie(obj.get("nomCategorie").toString());           

            listCategories.add(categorie);
        }
    } catch (Exception ex) {
        System.out.println("com.codename1.sport.services.ServicesPublications.parsePublications()"); 
    }

    return listCategories;
    }
    
       public List<Categorie> AllCategories() {
        String url = Statics.BASE_URL + "afficherCategorieMobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cat = parseCategories(new String(req.getResponseData()));
                
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return cat;
    }
       String x;
       public String getEmail(TextField id,Resources rs)
       {
            String url = Statics.BASE_URL + "getmail?idArticle="+id.getText().toString();
            System.out.println(url);
        req = new ConnectionRequest(url, false); 
        req.setUrl(url);
        req.addResponseListener((e) ->{
        JSONParser j = new JSONParser();
        String json = new String(req.getResponseData()) + "";

        try {
        
            
             Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
             x =user.get("email").toString();
             

            
        
        }catch(Exception ex) {
                ex.printStackTrace();
        }   
        });
       
        NetworkManager.getInstance().addToQueueAndWait(req);
        return x;
    }
}
