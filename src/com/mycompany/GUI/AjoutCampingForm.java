/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.GUI;

import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.util.StringUtil;
import com.mycompany.entities.Camping;
import com.mycompany.services.ServiceCamping;
//import static java.awt.SystemColor.text;
//import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author rihem
 */
public class AjoutCampingForm extends BaseForm{
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
        
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));

      private com.codename1.ui.Container gui_Component_Group_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
  
    private  Label nomLabel = new  Label();
    private TextField nom = new  TextField("", "nom");
    private  Label lieuxLabel = new  Label();
    private TextField lieux = new  TextField("", "lieux");
    private Label PeriodeLabel = new  Label();
    private TextField Periode = new  TextField("", "Periode");
    private Label PrixLabel = new  Label();
    private TextField Prix = new  TextField("", "Prix");
    private Label PlaceLabel = new  Label();
    private TextField Place = new   TextField("", "Place");
    private Label descriptionLabel = new  Label();
    private TextField description = new  TextField("", "description");
 
    
        
        
      private String myPath = "";
    
    private  Button btnconfirmer = new com.codename1.ui.Button();        
        
    
     public AjoutCampingForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
        //onclick button event 
    public AjoutCampingForm(Resources resourceObjectInstance) {
        
        super("Camping", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Camping");
        getContentPane().setScrollVisible(false);
        
//        super.addSideMenu(resourceObjectInstance);

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
        c2.addComponent(nom);
        c2.addComponent(PrixLabel);
        c2.addComponent(Prix);
        c2.addComponent(PeriodeLabel);
        c2.addComponent(Periode);
        c2.addComponent(descriptionLabel);
        c2.addComponent(description);
        c2.addComponent(PlaceLabel);
        c2.addComponent(Place);
        c2.addComponent(lieuxLabel);
        c2.addComponent(lieux);
        
        
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
                    //x="file://C:/Users/BENMAH~1/AppData/Local/Temp/";
                    A="..";
                    j=".";
                    String Temppath ="C:\\Users\\Ben Mahmoud\\AppData\\Local\\Temp\\";
//                    int lenghtfile=path1.length();
//                    System.out.println(lenghtfile);
//                    String nomimgfalse=path1.substring(44);
//                    String nomimg=StringUtil.replaceAll(nomimgfalse, A, j);
//                    k=StringUtil.replaceAll(path, A, j);
//                    Newpath=StringUtil.replaceAll(k,x,y);
//                    finalpath=Temppath+nomimgfalse;
//                    finalpathbase=y+nomimg;
//                   
                  //  File source = new File(finalpath);
                    //File Dest= new File(Newpath);
                  
                    
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
                
                   Camping r = new Camping(
                           Integer.parseInt(Periode.getText()),
                           Integer.parseInt(Place.getText()),
                           String.valueOf( nom.getText()),
                           String.valueOf(lieux.getText()) ,
                            String.valueOf(description.getText()),
                           null,null,
                            Integer.parseInt(Prix.getText()),
                             String.valueOf(finalpathbase),
                             String.valueOf(finalpathbase));
                   

                    ServiceCamping su = ServiceCamping.getInstance();

                    if (su.ajoutCamping(r)) {
                        Dialog.show("Camping", "Camping ajouté", new Command("OK"));

                    } else {
                        Dialog.show("Camping", "Erreur d'ajout", new Command("OK"));
                    }
                    //new ListCampingForm(r).show();

//                } else {
//                    Dialog.show("Equipe", "Les champs ne doivent pas être vide", new Command("OK"));
//                }
            }
        });

    }
//        btnAjouter.addActionListener((e) -> {
// 
//            public AjoutCampingForm( Resources res) {
//                
//            }
//            
//            try {
//                
//                if(objet.getText().equals("") || description.getText().equals("")) {
//                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
//                }
//                
//                else {
//                    InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
//                
//                    final Dialog iDialog = ip.showInfiniteBlocking();
//                    
//                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//                    
//                    //njibo iduser men session (current user)
//                    Camping r = new Camping(
//                           Integer.parseInt(Periode.getText()),
//                           Integer.parseInt(Place.getText()),
//                           String.valueOf( nom.getText()),
//                           String.valueOf(lieux.getText()) ,
//                            String.valueOf(description.getText()),
//                           null,null,
//                            Integer.parseInt(Prix.getText()),
//                             String.valueOf(ImageC.getText()),
//                             String.valueOf(Image.getText()));
//                    
//                    System.out.println("data  reclamation == "+r);
//                    
//                        
//                    //appelle methode ajouterReclamation mt3 service Reclamation bch nzido données ta3na fi base 
//                    ServiceCamping.getInstance().ajoutCamping(r);
//                    
//                    iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
//                    
//                    //ba3d ajout net3adaw lel ListREclamationForm
//                    new ListCampingForm(res).show();
//                    
//                    
//                    refreshTheme();//Actualisation
//                            
//                }
//                
//            }catch(Exception ex ) {
//                ex.printStackTrace();
//            }
//            
//            
//            
//            
//            
//        });
//        
//        
//    }
//
//    private void addStringValue(String s, Component v) {
//        
//        add(BorderLayout.west(new Label(s,"PaddedLabel"))
//        .add(BorderLayout.CENTER,v));
//        add(createLineSeparator(0xeeeeee));
//    }

     private void addTab(Tabs swipe, Label spacer , Image image, String string, String text, Resources res) {
       int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        
  /*      if(image.getHeight() < size) {
            image = image.scaledHeight(size);
        }
   */     
        
        
/*        if(image.getHeight() > Display.getInstance().getDisplayHeight() / 2 ) {
            image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }*/
       
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
