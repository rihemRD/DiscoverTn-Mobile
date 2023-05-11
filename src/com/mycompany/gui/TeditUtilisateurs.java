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

import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.util.StringUtil;
import com.mycompany.entites.Utilisateur;
import com.mycompany.services.UtilisateurService;
//import com.mycompany.services.SessionManager;
import java.io.File;
import java.io.IOException;
import com.codename1.ui.Dialog;


public class TeditUtilisateurs extends BaseForm {
    String path;
    String Newpath;
    String x;
    String y,yy;
    String j;
    String k;
    String A;
    String finalpath;
    String finalpathbase;
    String finalpathbase1;
    private String myPath = "";

    static  Utilisateur Utilisateur=new Utilisateur();
    private Container gui_Container_1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

    private Container gui_Component_Group_1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));





    private Label nom = new Label();
    private com.codename1.ui.TextField NomText = new com.codename1.ui.TextField("", "name");

    private Label image = new Label();
    private com.codename1.ui.TextField imgPath = new com.codename1.ui.TextField("", "urlimage");


    private Button btnconfirmer = new Button();

    public TeditUtilisateurs() {
        this(Resources.getGlobalResources());
    }

    public TeditUtilisateurs(Resources resourceObjectInstance) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);

        setTitle("Profile");


        super.addSideMenu(resourceObjectInstance);

        //tb.addSearchCommand(e -> {});
      /*  tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ADD_BOX, ev -> {
            new TAddEvenement().show();
        });*/

        Image img = resourceObjectInstance.getImage("profile-background.jpg");


        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);


        add(LayeredLayout.encloseIn(
                sl
        ));



        initGuiBuilderComponents(resourceObjectInstance);

        remplirUtilisateur();
    }

    private void initGuiBuilderComponents(Resources resourceObjectInstance) {

        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setTitle("Edit Publication");

        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
        gui_Container_1.setScrollableY(true);

        //gui_Container_1.addComponent(gui_Label_1);
        gui_Container_1.addComponent(gui_Component_Group_1);

        Container c1 = new Container();
        Container c2 = new Container();
        Container c3 = new Container();
        Container c4 = new Container();
        Container c5 = new Container();


        c2.addComponent(NomText);
        c2.addComponent(imgPath);



        Button btCapture = new Button ("Image");
        Label lbltImage = new Label ();
        c2.add(btCapture);
        c2.add (lbltImage);
        btCapture.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String path1 = Capture.capturePhoto(Display.getInstance().getDisplayWidth(),-1);

                if(path1!=null)
                {
                    //File copy from source to destination
                    //DON'T ASK ME HOW !!!!!
                    path=path1;
                    y="C:\\Users\\21622\\Documents\\NetBeansProjects\\pidev\\src";
                    yy="C:\\xampp\\htdocs\\devcomimgupload\\";
                    x="file://C:/Users/21622~1/AppData/Local/Temp/";
                    A="..";
                    j=".";
                    String Temppath ="C:\\Users\\21622\\AppData\\Local\\Temp\\";
                    int lenghtfile=path1.length();
                    System.out.println(lenghtfile);
                    String nomimgfalse=path1.substring(40);
                    String nomimg=StringUtil.replaceAll(nomimgfalse, A, j);
                    k=StringUtil.replaceAll(path, A, j);
                    Newpath=StringUtil.replaceAll(k,x,y);
                    finalpath=Temppath+nomimgfalse;
                    finalpathbase=y+nomimg;
                    finalpathbase1=yy+nomimg;
                    File source = new File(finalpath);
                    File Dest= new File(finalpathbase);
                    File Dest1= new File(finalpathbase1);
                    try {
                        UtilisateurService.getInstance().copyFileUsingStream(source,Dest);
                        UtilisateurService.getInstance().copyFileUsingStream(source,Dest1);
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
            }
        });


        gui_Component_Group_1.addComponent(c1);
        gui_Component_Group_1.addComponent(c2);
        gui_Component_Group_1.addComponent(c3);
        gui_Component_Group_1.addComponent(c4);
        gui_Component_Group_1.addComponent(c5);

        btnconfirmer.setText("Confirmer");

        gui_Container_1.addComponent(btnconfirmer);



        btnconfirmer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                Utilisateur U = new Utilisateur();


                U.setNomUtilisateur(NomText.getText());

                U.setImgUtilisateur(finalpathbase1);


                UtilisateurService su = UtilisateurService.getInstance();


                new Tpublication().show();
            }
        });



    }

    public void remplirUtilisateur() {
        System.out.println(Utilisateur);


        NomText.setText(Utilisateur.getNomUtilisateur());
        imgPath.setText(Utilisateur.getPrenomUtilisateur());


    }

}
