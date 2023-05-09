/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package com.mycompany.gui;



import com.codename1.capture.Capture;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.util.StringUtil;
import com.mycompany.entites.Evenement;
import com.mycompany.services.ServiceEvenements;
import com.mycompany.services.SessionManager;
import com.mycompany.services.UtilisateurService;
import java.io.File;
import java.io.IOException;


public class TAddEvenement extends BaseForm {
 String path;
       String Newpath;
       String x;
       String y;
       String j;
       String k;
       String A;
       String finalpath;
        String finalpathbase;
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));

    private com.codename1.ui.Container gui_Component_Group_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    
    private com.codename1.ui.Label titreLabel = new com.codename1.ui.Label();
    private com.codename1.ui.TextField titreText = new com.codename1.ui.TextField("", "titre");
    private com.codename1.ui.Label lieuLabel = new com.codename1.ui.Label();
    private com.codename1.ui.TextField lieuText = new com.codename1.ui.TextField("", "lieu");
    private com.codename1.ui.Label DataDebutLabel = new com.codename1.ui.Label();
    private com.codename1.ui.TextField DataDebutText = new com.codename1.ui.TextField("", "dateDebut");
	
    private com.codename1.ui.Label DataFinLabel = new com.codename1.ui.Label();
    private com.codename1.ui.TextField DataFinText = new com.codename1.ui.TextField("", "dateFin");
	
	
    private com.codename1.ui.Label descriptionLabel = new com.codename1.ui.Label();
    private com.codename1.ui.TextField descriptionText = new com.codename1.ui.TextField("", "description");
    private com.codename1.ui.Label prixLabel = new com.codename1.ui.Label();
    private com.codename1.ui.TextField prixText = new com.codename1.ui.TextField("", "prix");
    //private com.codename1.ui.Label nbplacesLabel = new com.codename1.ui.Label();
    //private com.codename1.ui.TextField nbplacesText = new com.codename1.ui.TextField("", "nbplaces");
    
    


        
    
    
    private com.codename1.ui.Button btnconfirmer = new com.codename1.ui.Button();
   

    public TAddEvenement() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public TAddEvenement(com.codename1.ui.util.Resources resourceObjectInstance) {
        
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Evenement");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(resourceObjectInstance);

        Image img = resourceObjectInstance.getImage("profile-background.jpg");
        
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        add(LayeredLayout.encloseIn(
                sl
        ));

        initGuiBuilderComponents(resourceObjectInstance);

    }

    private void initGuiBuilderComponents(Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setTitle("Ajouter Evenement");
        setName("Ajout Evenement");
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        //gui_Container_1.addComponent(gui_Label_1);
        gui_Container_1.addComponent(gui_Component_Group_1);

        Container c1 = new Container(new FlowLayout(CENTER, CENTER));
        Container c2 = new Container(new FlowLayout(CENTER, CENTER)); 
        Container c3 = new Container(new FlowLayout(CENTER, CENTER));
        Container c4 = new Container(new FlowLayout(CENTER, CENTER));
        Container c5 = new Container(new FlowLayout(CENTER, CENTER));
        
        
        
        c2.addComponent(titreLabel);
        c2.addComponent(titreText);
        c2.addComponent(lieuLabel);
        c2.addComponent(lieuText);
        c2.addComponent(DataDebutLabel);
        c2.addComponent(DataFinLabel);
        c2.addComponent(DataDebutText);
        c2.addComponent(DataFinText);
        c2.addComponent(descriptionLabel);
        c2.addComponent(descriptionText);
        c2.addComponent(prixLabel);
        c2.addComponent(prixText);
        //c2.addComponent(nbplacesLabel);
        //c2.addComponent(nbplacesText);
        
        
        
        Button btCapture = new Button ("Image");
        Label lbltImage = new Label ();
        c2.add(btCapture);
        c2.add (lbltImage);
        btCapture.addActionListener((e) -> {
            String path1 = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
           if(path1!=null)
                {
                    //File copy from source to destination
                    //DON'T ASK ME HOW !!!!!
                    path=path1;
                    y="C:\\xampp\\htdocs\\PideversImgUploaded\\Event\\";
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
                    finalpathbase=nomimg;
                   
                    File source = new File(finalpath);
                    File Dest= new File(Newpath);
                   try {
                       UtilisateurService.getInstance().copyFileUsingStream(source,Dest);
                       //
                   } catch (IOException ex) {
                       ex.getMessage();
                   }
                    
                    try {
                        Image img1 = Image.createImage(path1);
                        btCapture.setIcon(img1);
                    }
                    catch (IOException ex) {
                        ex.getMessage();
                    }
                }
            
        });
        
      
            

        gui_Component_Group_1.setName("Component_Group_1");
        gui_Component_Group_1.addComponent(c1);
        gui_Component_Group_1.addComponent(c2);
        gui_Component_Group_1.addComponent(c3);
        gui_Component_Group_1.addComponent(c4);
        gui_Component_Group_1.addComponent(c5);

        btnconfirmer.setText("Confirmer");
        btnconfirmer.setName("btnPubli");
        gui_Container_1.addComponent(btnconfirmer);
    

        gui_Component_Group_1.setName("Component_Group_1");

        btnconfirmer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //if (!nomText.getText().isEmpty() && !datePickerdebut.getText().isEmpty() && !datePickerfin.getText().isEmpty() && !primeText.getText().isEmpty()) {
                
                    Evenement evenement = new Evenement();
                    evenement.setTitreEvent(titreText.getText());
                    evenement.setLieuEvent(lieuText.getText());
                    evenement.setDateDebutEvent(DataDebutText.getText());
                    evenement.setDateFinEvent(DataFinText.getText());
					evenement.setPrixEvent(prixText.getText());
                    evenement.setDescEvent(descriptionText.getText());
                    System.out.println(finalpathbase);
                    evenement.setImageEvent(finalpathbase);
                    //evenement.setStatusEvenement(1);
                    int userid = SessionManager.getId();
                    evenement.setIdUser(userid);

                    ServiceEvenements su = ServiceEvenements.getInstance();

                    if (su.add(evenement)) {
                        Dialog.show("Evenement", "Evenement ajouté", new Command("OK"));

                    } else {
                        Dialog.show("Evenement", "Erreur d'ajout", new Command("OK"));
                    }
                    new TEvenements().show();

//                } else {
//                    Dialog.show("Equipe", "Les champs ne doivent pas être vide", new Command("OK"));
//                }
            }
        });

    }
    
    private void addStringValue(String s, Component v) {
        
        add(BorderLayout.west(new Label(s,"PaddedLabel"))
        .add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));
    }

}
