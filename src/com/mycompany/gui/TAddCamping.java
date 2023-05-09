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
import com.mycompany.entites.Camping;
import com.mycompany.entites.Evenement;
import com.mycompany.services.ServiceEvenements;
import com.mycompany.services.ServicesCampings;
import com.mycompany.services.SessionManager;
import com.mycompany.services.UtilisateurService;
import java.io.File;
import java.io.IOException;


public class TAddCamping extends BaseForm {
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
    
    private com.codename1.ui.Label nomLabel = new com.codename1.ui.Label();
    private com.codename1.ui.TextField nomText = new com.codename1.ui.TextField("", "nom");
    private com.codename1.ui.Label adresseLabel = new com.codename1.ui.Label();
    private com.codename1.ui.TextField adresseText = new com.codename1.ui.TextField("", "adresse");
    private com.codename1.ui.Label telephoneLabel = new com.codename1.ui.Label();
    private com.codename1.ui.TextField telephoneText = new com.codename1.ui.TextField("", "telephone");
    private com.codename1.ui.Label ratingLabel = new com.codename1.ui.Label();
    private com.codename1.ui.TextField ratingText = new com.codename1.ui.TextField("", "prix");
    private com.codename1.ui.Label descriptionLabel = new com.codename1.ui.Label();
    private com.codename1.ui.TextField descriptionText = new com.codename1.ui.TextField("", "description");
    
    


        
    private String myPath = "";
    
    private com.codename1.ui.Button btnconfirmer = new com.codename1.ui.Button();
   

    public TAddCamping() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public TAddCamping(com.codename1.ui.util.Resources resourceObjectInstance) {
        
        super("Camping", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Camping");
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
        setTitle("Ajouter Camping");
        setName("Ajout Camping");
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
        
        
        
        c2.addComponent(nomLabel);
        c2.addComponent(nomText);
        c2.addComponent(adresseLabel);
        c2.addComponent(adresseText);
        c2.addComponent(telephoneLabel);
        c2.addComponent(telephoneText);
        c2.addComponent(ratingLabel);
        c2.addComponent(ratingText);
        c2.addComponent(descriptionLabel);
        c2.addComponent(descriptionText);
        
        
        
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
                    finalpathbase=y+nomimg;
                   
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
                
                    Camping camping = new Camping();
                   
                    camping.setNomCamping(nomText.getText());
                    camping.setAdresseCamping(adresseText.getText());            
                    camping.setTelephoneCamping(telephoneText.getText());
                    camping.setRatingCamping(Integer.valueOf(ratingText.getText()));
                    camping.setDescirptionCamping(descriptionText.getText());
                    camping.setImageCamping(finalpathbase);
                    int userid = SessionManager.getId();
                    camping.setIdUser(userid);

                    ServicesCampings su = ServicesCampings.getInstance();

                    if (su.add(camping)) {
                        Dialog.show("Camping", "Camping ajouté", new Command("OK"));

                    } else {
                        Dialog.show("Camping", "Erreur d'ajout", new Command("OK"));
                    }
                    new TCampings().show();

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
