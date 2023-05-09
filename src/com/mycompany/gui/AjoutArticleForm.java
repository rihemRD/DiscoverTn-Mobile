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
import com.codename1.util.StringUtil;
import com.mycompany.services.SessionManager;
import com.mycompany.services.UtilisateurService;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;



public class AjoutArticleForm extends BaseForm{
    
    Form current;
    String path;
     
       String Newpath;
       String x;
       String y;
       String j;
       String k;
       String A;
       String finalpath;
        String finalpathbase;
    public AjoutArticleForm (Resources res){
        super ("Newsfeed",BoxLayout.y());
        Toolbar tb= new Toolbar(true);
        current= this;
        getTitleArea().setUIID("Container");
//        setTitle("Article");
        getContentPane().setScrollVisible(false);
        
        
        




        


        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("Mes Articles", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton commentaire = RadioButton.createToggle("commentaires", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton partage = RadioButton.createToggle("Ajouter", barGroup);
        partage.setUIID("SelectBar");
        
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");


        mesListes.addActionListener((e) -> {        
         ListArticlesForm a = new ListArticlesForm(res);
          a.show();
            refreshTheme();
        });
        
                partage.addActionListener((e) -> {        
         AjoutArticleForm aj = new AjoutArticleForm(res);
          aj.show();
            refreshTheme();
        });
                                commentaire.addActionListener((e) -> {        
         ListCommentaireForm aj = new ListCommentaireForm(res);
          aj.show();
            refreshTheme();
        });

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, mesListes, commentaire,partage),
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
        
        TextField titreArticle = new TextField("","Entrer un titre");
        titreArticle.setUIID("TextFieldBlack");
        addStringValue("Titre",titreArticle);
         
        TextField descriptionArticle = new TextField("","Entrer une description");
        descriptionArticle.setUIID("TextFieldBlack");
        addStringValue("Description",descriptionArticle);
        
      
        
        

Button img= new Button("Ajouter une image");
        Label image =new Label();
      
        img.addActionListener((e)->{
             String path1 = Capture.capturePhoto(Display.getInstance().getDisplayWidth(),-1);
           if(path1!=null)
                {
                    //File copy from source to destination
                    //DON'T ASK ME HOW !!!!!
                    path=path1;
                    y="C:\\xampp\\htdocs\\PideversImgUploaded\\Blog\\";
                    x="file://C:/Users/BENMAH~1/AppData/Local/Temp/";
                    A="..";
                    j=".";
                    String Temppath ="C:\\Users\\Ben Mahmoud\\AppData\\Local\\Temp\\";
                    int lenghtfile=path1.length();
                    System.out.println(lenghtfile);
                    String nomimgfalse=path1.substring(44);
                    String nomimg=StringUtil.replaceAll(nomimgfalse, A, j);
                    k=StringUtil.replaceAll(path, A, j);
                    Newpath=StringUtil.replaceAll(k,x,y);
                    finalpath=Temppath+nomimgfalse;
                    finalpathbase=y+nomimg;
                    File source = new File(finalpath);
                    File Dest= new File(Newpath);
                   try {
                       UtilisateurService.getInstance().copyFileUsingStream(source,Dest);
                       //
                   } catch (IOException ex) {
                       ex.getMessage();
                   }
                }
            
        });
        
        
         addStringValue("",img);
              
        
        TextField idCategorie = new TextField("","Entrer une categorie");
        idCategorie.setUIID("TextFieldBlack");
        addStringValue("Categorie",idCategorie);
        
//        TextField idUtilisateur = new TextField("","Entrer une Utilisateur");
//        idUtilisateur.setUIID("TextFieldBlack");
//        addStringValue("Utilisateur",idUtilisateur);
        

        Button btnAjouter = new Button("Ajouter");
        addStringValue("",btnAjouter);
        
        
        

        
        
        
        
        btnAjouter.addActionListener ((e)->{
            try{
                    if((titreArticle.getText().length()==0 ) ||( descriptionArticle.getText().length()==0) || (idCategorie.getText().length() == 0)){
                        Dialog.show("Veuillez vérifier les données","","Annuler","OK");
                    }else{
                        InfiniteProgress ip = new InfiniteProgress();
                        final Dialog iDialog = ip.showInfiniteBlocking();
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        Article ar = new Article(
                         ServiceArticle.getInstance().badwords(String.valueOf(titreArticle.getText()).toString()),
                         ServiceArticle.getInstance().badwords(String.valueOf(descriptionArticle.getText()).toString()),
                        finalpathbase,
                        format.format(new Date()),
                        Integer. parseInt(idCategorie.getText()),
                        //Integer. parseInt(idUtilisateur.getText())
                                SessionManager.getId()
                        );
                        System.out.println("data article =="+ar);
                        ServiceArticle.getInstance().ajouterArticle(ar);
                        
                        iDialog.dispose();
                        refreshTheme();         
                        ListArticlesForm a = new ListArticlesForm(res);
          a.show();
            refreshTheme();

                    }
            }catch(Exception ex){
                          ex.printStackTrace();
                          }
        });
    }

private void addStringValue(String s, Component v) {
        
        add(BorderLayout.west(new Label(s,"PaddedLabel"))
        .add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));
    }

    private void addTab(Tabs swipe, Label spacer , Image image, String string, String text, Resources res) {
        int size;
        size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());

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