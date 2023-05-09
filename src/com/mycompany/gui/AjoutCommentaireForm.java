/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.capture.Capture;
import com.mycompany.entites.Article;
import com.mycompany.services.ServiceArticle;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.components.SpanLabel;

import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Commentaire;
import com.mycompany.services.ServiceCommentaire;
import com.mycompany.services.SessionManager;
import com.sun.mail.smtp.SMTPTransport;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class AjoutCommentaireForm extends BaseForm{
    
    Form current;
    String path;
    public AjoutCommentaireForm (Resources res){
        super ("Newsfeed",BoxLayout.y());
        Toolbar tb= new Toolbar(true);
        current= this;
        getTitleArea().setUIID("Container");
//        setTitle("Article");
        getContentPane().setScrollVisible(false);
        
        
        


        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("Mes Commentaires", barGroup);
        mesListes.setUIID("SelectBar");
                RadioButton articles = RadioButton.createToggle("Articles", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton partage = RadioButton.createToggle("Ajouter", barGroup);
        partage.setUIID("SelectBar");
        
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");


        mesListes.addActionListener((e) -> {        
         ListCommentaireForm a = new ListCommentaireForm(res);
          a.show();
            refreshTheme();
        });
        
                partage.addActionListener((e) -> {        
         AjoutCommentaireForm aj = new AjoutCommentaireForm(res);
          aj.show();
            refreshTheme();
        });
                                articles.addActionListener((e) -> {        
         ListArticlesForm aj = new ListArticlesForm(res);
          aj.show();
            refreshTheme();
        });

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, mesListes, articles,partage),
                FlowLayout.encloseBottom(arrow)
        ));
        
        partage.setSelected(true);
        mesListes.setSelected(false);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(partage, arrow);
        });
        bindButtonSelection(mesListes, arrow);
        bindButtonSelection(partage, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        
        
        //---------------------------------------------------------------//
        
        TextField contenuCommentaire = new TextField("","Entrer contenu");
        contenuCommentaire.setUIID("TextFieldBlack");
        addStringValue("contenu",contenuCommentaire);
         




        
        
              
        
        TextField idArticle = new TextField("","Entrer un article");
        idArticle.setUIID("TextFieldBlack");
        addStringValue("Article",idArticle);
        
//        TextField idUtilisateur = new TextField("","Entrer une Utilisateur");
//        idUtilisateur.setUIID("TextFieldBlack");
//        addStringValue("Utilisateur",idUtilisateur);
        

        Button btnAjouter = new Button("Ajouter");
        addStringValue("",btnAjouter);
        
        
        

        
        
        
        
        btnAjouter.addActionListener ((e)->{
            try{
                    if((contenuCommentaire.getText().length()==0 ) || (idArticle.getText().length() == 0)){
                        Dialog.show("Veuillez vérifier les données","","Annuler","OK");
                    }else{
                        InfiniteProgress ip = new InfiniteProgress();
                        final Dialog iDialog = ip.showInfiniteBlocking();
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        Commentaire com = new Commentaire(
                         ServiceArticle.getInstance().badwords(String.valueOf(contenuCommentaire.getText()).toString()),
                        format.format(new Date()),
                        Integer. parseInt(idArticle.getText()),
//                        Integer. parseInt(idUtilisateur.getText())
                                SessionManager.getId()
                                
                        );
                        System.out.println("data article =="+com);
                        ServiceCommentaire.getInstance().ajouterCommentaire(com);
                        
                        iDialog.dispose();
                        ListCommentaireForm a = new ListCommentaireForm(res);
                        System.out.println(SessionManager.getTestestttt());
                        sendMail( ServiceArticle.getInstance().getEmail(idArticle, res),res,contenuCommentaire);
                     
          a.show();
            refreshTheme();                    }
            }catch(Exception ex){
                          ex.printStackTrace();
                          }
        });
    }
//sendMail
    
     public void sendMail(String email,Resources res,TextField contenuCommentaire) {
        try {
            
            Properties props = new Properties();
                props.put("mail.transport.protocol", "smtp"); 
		props.put("mail.smtps.host", "smtp.gmail.com"); 
		props.put("mail.smtps.auth", "true");        
            Session session = Session.getInstance(props,null); 
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("<pidevers3A10@gmail.com>"));
            msg.setRecipients(Message.RecipientType.TO, email);
            msg.setSubject("Vous aver un commentaire ");
            msg.setSentDate(new Date(System.currentTimeMillis()));
           String txt = contenuCommentaire.getText().toString();
           msg.setText(txt);  
          SMTPTransport  st = (SMTPTransport)session.getTransport("smtps") ; 
          st.connect("smtp.gmail.com",465,"pidevers3A10@gmail.com","Testtest123");
          st.sendMessage(msg, msg.getAllRecipients());

        }catch(Exception e ) {
            e.printStackTrace();
        }
    }
private void addStringValue(String s, Component v) {
        
        add(BorderLayout.west(new Label(s,"PaddedLabel"))
        .add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));
    }

    private void addTab(Tabs swipe, Label spacer , Image image, String string, String text, Resources res) {
        int size;
        size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        
//        if(image.getHeight() < size) {
//            image = image.scaledHeight(size);
//        }
//        
//        
//        
//        if(image.getHeight() > Display.getInstance().getDisplayHeight() / 2 ) {
//            image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
//        }
        
        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        Label overLay = new Label("","ImageOverlay");
        
        
        Container page1 = 
                LayeredLayout.encloseIn(
                imageScale,
                        overLay,
                        BorderLayout.south(
                        BoxLayout.encloseY(
                        new SpanLabel(text, "LargeWhiteText"),
                                        spacer
                        )
                    )
                );
        
        swipe.addTab("",res.getImage("back-logo.jpeg"), page1);
        
        
        
        
    }
    
    
    
    public void bindButtonSelection(Button btn , Label l ) {
        
        btn.addActionListener(e-> {
        if(btn.isSelected()) {
            updateArrowPosition(btn,l);
        }
    });
    }

    private void updateArrowPosition(Button btn, Label l) {
        
        l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth()  / 2  - l.getWidth() / 2 );
        l.getParent().repaint();
    }
    
   

}