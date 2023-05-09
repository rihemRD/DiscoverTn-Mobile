package com.mycompany.gui;

import com.codename1.capture.Capture;
import com.mycompany.entites.Hotel;
import com.codename1.ui.Button;
import com.mycompany.services.ServiceHotels;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.util.StringUtil;
import com.mycompany.services.UtilisateurService;
import java.io.File;
import java.io.IOException;


public class TAddHotel extends BaseForm{
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
    private com.codename1.ui.Label emailLabel = new com.codename1.ui.Label();
    private com.codename1.ui.TextField emailText = new com.codename1.ui.TextField("", "email");
    private com.codename1.ui.Label telephoneLabel = new com.codename1.ui.Label();
    private com.codename1.ui.TextField telephoneText = new com.codename1.ui.TextField("", "telephone");
    private com.codename1.ui.Label prixLabel = new com.codename1.ui.Label();
    private com.codename1.ui.TextField prixText = new com.codename1.ui.TextField("", "prix");
    private com.codename1.ui.Label etoileLabel = new com.codename1.ui.Label();
    private com.codename1.ui.TextField etoileText = new com.codename1.ui.TextField("", "etoile");
  
    private String myPath = "";
    
    Picker datePickerdebut = new Picker();
    Picker datePickerfin = new Picker();


    private com.codename1.ui.Button btnconfirmer = new com.codename1.ui.Button();
   

    public TAddHotel() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public TAddHotel(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);

        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_PUBLIC, e -> {
        });
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev -> {
            new TEvenements().show();
        });
    }

    private void initGuiBuilderComponents(Resources resourceObjectInstance) {
        datePickerdebut.setType(Display.PICKER_TYPE_CALENDAR);
        datePickerfin.setType(Display.PICKER_TYPE_CALENDAR);

        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setTitle("Ajouter Hotel");
        setName("AjoutHotel");
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
        c2.addComponent(emailLabel);
        c2.addComponent(emailText);
        c2.addComponent(telephoneLabel);
        c2.addComponent(telephoneText);
        c2.addComponent(prixLabel);
        c2.addComponent(prixText);
        c2.addComponent(etoileLabel);
        c2.addComponent(etoileText);
    
        
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
                    y="C:\\xampp\\htdocs\\PideversImgUploaded\\hotel\\";
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
            public void actionPerformed(ActionEvent evt){
                //if (!nomText.getText().isEmpty() && !datePickerdebut.getText().isEmpty() && !datePickerfin.getText().isEmpty() && !primeText.getText().isEmpty()) {
                Hotel hotel = new Hotel();
                
                hotel.setNomHotel(nomText.getText());
                hotel.setAdresseHotel(adresseText.getText());
                hotel.setEmailHotel(emailText.getText());
                hotel.setTelephoneHotel(telephoneText.getText());
                hotel.setPrixHotel(Float.valueOf(prixText.getText()));
                hotel.setEtoileHotel(Integer.valueOf(etoileText.getText()));
                hotel.setImageHotel(finalpathbase);
                
                ServiceHotels sh = ServiceHotels.getInstance();

                    if (sh.add(hotel)) {
                        Dialog.show("Hotel", "Hotel ajouté", new Command("OK"));

                    } else {
                        Dialog.show("Hotel", "Erreur d'ajout", new Command("OK"));
                    }
                    new THotels().show();

//                } else {
//                    Dialog.show("Equipe", "Les champs ne doivent pas être vide", new Command("OK"));
//                }
            }
        });

    }
    
}
