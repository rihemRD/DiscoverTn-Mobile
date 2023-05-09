/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.NewsfeedForm;
import com.mycompany.gui.ProfileForm;
import com.mycompany.gui.SignInForm;
import com.mycompany.utils.Statics;
import com.sun.mail.smtp.SMTPTransport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import java.util.Map;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



/**
 *
 * @author Ben Mahmoud
 */
public class UtilisateurService {
    public static UtilisateurService instance = null ;
    public static boolean resultOk = true;
    String json;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static UtilisateurService getInstance() {
        if(instance == null )
            instance = new UtilisateurService();
        return instance ;
    }
    
    
    
    public UtilisateurService() {
        req = new ConnectionRequest();
        
    }
     public void signin(TextField username,TextField password, Resources rs ) {
        String url = Statics.BASE_URL+"signin?username="+username.getText().toString()+"&password="+password.getText().toString();
        req = new ConnectionRequest(url, false); 
        req.setUrl(url);
        System.out.println ( username.getText().toString() +": " + password );
        req.addResponseListener((e) ->{
        JSONParser j = new JSONParser();
        String json = new String(req.getResponseData()) + "";
          
        try {
           
        if(json.contains("error")) 
        {
            
            Dialog.show("Echec d'authentification","Username ou mot de passe éronné","OK",null);
        }else 
        {
                 Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                //Session 
                float id = Float.parseFloat(user.get("id").toString());
                System.out.println(user);
                SessionManager.setId((int)id);
                SessionManager.setPassowrd(user.get("pwd").toString());
                SessionManager.setUserName(user.get("login").toString());
                SessionManager.setEmail(user.get("email").toString());
                SessionManager.setAdresse(user.get("adresse").toString());
                SessionManager.setNom(user.get("name").toString());
                SessionManager.setPrenom(user.get("prenom").toString());
                SessionManager.setTelephone(user.get("telephone").toString());
               
                //photo 
                if(user.get("photo") != null)
                    SessionManager.setPhoto(user.get("imgPath").toString());
               if(user.size() >0 ) 
                  
   new NewsfeedForm(rs).show();
            }
            }catch(Exception ex) {
                ex.printStackTrace();
            }   
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

   public static void copyFileUsingStream(File source, File dest) throws IOException {
    InputStream is = null;
    OutputStream os = null;
    System.out.println("source"+source.getAbsolutePath());
    System.out.println("dest"+dest.getAbsolutePath());
    try {
        is = new FileInputStream(source);
        os = new FileOutputStream(dest);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
    } finally {
        is.close();
        os.close();
    }
}


    public void signup(TextField nom, TextField Prenom, TextField email, TextField username, TextField password, TextField Telephone, TextField Adresse, String path, Resources res) {
   SessionManager.setPref(null);
        String url = Statics.BASE_URL+"signup?nom="+nom.getText().toString()+"&prenom="+Prenom.getText().toString()
                                 +"&email="+email.getText().toString()+"&username="+username.getText().toString()
                                 +"&password="+password.getText().toString()+"&telephone="+Telephone.getText().toString()
                                 +"&adresse="+Adresse.getText().toString()+"&pathimg="+path;
        
        
        req = new ConnectionRequest(url, false); 
        req.setUrl(url);
        req.addResponseListener((e) ->{
        JSONParser j = new JSONParser();
        String json = new String(req.getResponseData()) + "";
                System.out.println(json);

        try {
        if(json.contains("Error mail")) 
        {
            Dialog.show("Echec d'inscription","Email incorrect","Not ok",null);
        }else if(json.contains("Error login"))
        {
            
            Dialog.show("Echec d'inscription","Login deja existe","Not ok",null);
        }
        else
        {
            Dialog.show("Done","Merci pour votre inscription, vous allez recevoir un mail de confirmation","Not ok",null);
            sendMail(email.getText().toString(), res);
            new SignInForm(res).show();
        }
        }catch(Exception ex) {
                ex.printStackTrace();
        }   
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

  

   

    public void update(TextField username, TextField nom, TextField prenom, TextField tel, TextField adr, TextField password, Resources res) {
        String url = Statics.BASE_URL+"update?username="+username.getText().toString()
                +"&prenom="+prenom.getText().toString()
                +"&nom="+nom.getText().toString()
                +"&telephone="+tel.getText().toString()
                +"&password="+password.getText().toString()
                +"&adresse="+adr.getText().toString();
        req = new ConnectionRequest(url, false); 
        req.setUrl(url);
        req.addResponseListener((e) ->{
        JSONParser j = new JSONParser();
        String json = new String(req.getResponseData()) + "";
                System.out.println(json);

        try {
        if(json.contains("Updated")) 
        {
            Dialog.show("Updated","updated successfully"," ok",null);
                ReloadSession(username.getText().toString(),password.getText().toString());
              new ProfileForm(res).show();
        }
             
        
        }catch(Exception ex) {
                ex.printStackTrace();
        }   
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    private void ReloadSession(String username,String password) {
              String url = Statics.BASE_URL+"ReloadSession?username="+username+"&password="+password;
    
     req = new ConnectionRequest(url, false); 
        req.setUrl(url);
        req.addResponseListener((e) ->{
        JSONParser j = new JSONParser();
        String json = new String(req.getResponseData()) + "";
        try {
        if(json.contains("error")) 
        {
            Dialog.show("Echec d'authentification","Username ou mot de passe éronné","OK",null);
        }else 
        {
                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                //Session 
                float id = Float.parseFloat(user.get("id").toString());
                SessionManager.setId((int)id);
                SessionManager.setPassowrd(user.get("pwd").toString());
                SessionManager.setUserName(user.get("login").toString());
                SessionManager.setEmail(user.get("email").toString());
                SessionManager.setAdresse(user.get("adresse").toString());
                SessionManager.setNom(user.get("name").toString());
                SessionManager.setPrenom(user.get("prenom").toString());
                SessionManager.setTelephone(user.get("telephone").toString());
                if(user.get("photo") != null)
                    SessionManager.setPhoto(user.get("imgPath").toString());
            
            }
            }catch(Exception ex) {
                ex.printStackTrace();
            }   
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

   
    public void sendreclam(String EmailForm, String nom, String prenom, String email, TextArea body, Resources res) {
String url = Statics.BASE_URL+"Addreclamation?nom="+nom
                +"&prenom="+prenom
                +"&email="+email
                +"&body="+body.getText().toString()
               ;
         req = new ConnectionRequest(url, false); 
        req.setUrl(url);
        req.addResponseListener((e) ->{
        JSONParser j = new JSONParser();
        String json = new String(req.getResponseData()) + "";
        try {
        if(json.contains("error")) 
        {
        Dialog.show("Echec reclam","Error try again","OK",null);
        }else 
        {
        Dialog.show("Done","Votre Reclamation a été ajouté","OK",null);
            System.out.println(email);
            
        new NewsfeedForm(res).show();
        }
        }catch(Exception ex) {
        ex.printStackTrace();
        }   
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    
    
    }
     
    //sendMail
    
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
            msg.setSubject("Mail de bienvenu ");
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
