/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.gui;

import com.codename1.components.ScaleImageLabel;
import com.mycompany.entites.Evenement;
import com.mycompany.services.ServiceEvenements;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Reservation;
import com.mycompany.gui.BaseForm;
import com.mycompany.services.SessionManager;

import java.util.List;

public class TEvenements extends BaseForm {

    ServiceEvenements serviceevenements = ServiceEvenements.getInstance();
    Container containerglobal = new Container(new BoxLayout(BoxLayout.Y_AXIS));

    public TEvenements() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public TEvenements(com.codename1.ui.util.Resources resourceObjectInstance) {
        
        super("Evenement", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Evenement");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(resourceObjectInstance);
        
        //tb.addSearchCommand(e -> {});
        tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ADD_BOX, ev -> {
            new TAddEvenement().show();
        });
        
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

        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setInlineStylesTheme(resourceObjectInstance);
        setInlineStylesTheme(resourceObjectInstance);
        setTitle("Evenements");
        setName("Evenements");
        
        Image img = resourceObjectInstance.getImage("profile-background.jpg");

        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        addComponent(containerglobal);
        List<Evenement> evenements = serviceevenements.AllEvenements();

          for (Evenement t : evenements) {
              System.out.println(t);
            Container containerEvenement = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            containerEvenement.add(new Label("Id: " + t.getIdEvent()));
            containerEvenement.add(new Label("Titre : " + t.getTitreEvent()));
            containerEvenement.add(new Label("Date début : " + t.getDateDebutEvent()));
            containerEvenement.add(new Label("Date fin : " + t.getDateFinEvent()));
            containerEvenement.add(new Label("Description : " + t.getDescEvent()));
            containerEvenement.add(new Label("prix : " + t.getPrixEvent()));
            containerEvenement.add(new Label("Lieu : " + t.getLieuEvent()));
            containerEvenement.add(new Label("Image : " + t.getImageEvent()));
            Button btnModif = new Button("Modifier");
            Button btnSupp = new Button("Supprimer");
            Button btnRes = new Button("Reserver");
            Container containerbtnmodifsupp = new Container(new BoxLayout(BoxLayout.X_AXIS));

            containerEvenement.add(containerbtnmodifsupp);
                  
            containerbtnmodifsupp.add(btnModif);
            containerbtnmodifsupp.add(btnSupp);
            containerbtnmodifsupp.add(btnRes);

            Label gui_separator1 = new Label();
            //separateur
            gui_separator1.setShowEvenIfBlank(true);
            gui_separator1.setUIID("Separator");
            gui_separator1.setInlineStylesTheme(resourceObjectInstance);
            gui_separator1.setName("separator1");

            containerglobal.add(containerEvenement);
            containerglobal.addComponent(gui_separator1);

            btnModif.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    TEditEvenement.evenement = t;
                    new TEditEvenement().show();
                }
            });

            btnSupp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    serviceevenements.Remove(t.getIdEvent());
                    new TEvenements().show();
                }
            });
            
           
            btnRes.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    int userid = SessionManager.getId();
                    Reservation reservation = new Reservation();
                    reservation.setEv(t);
                    reservation.setIdUserR(userid);
                    ServiceEvenements su = ServiceEvenements.getInstance();
                    su.addReservation(reservation);
                    String email = SessionManager.getEmail();
                            ServiceEvenements.getInstance().sendMail(email, resourceObjectInstance);
                    if (su.addReservation(reservation)) {
                        Dialog.show("Reservation", "Evenement reservé", new Command("OK"));

                    } else {
                        Dialog.show("Reservation", "Erreur de reservation", new Command("OK"));
                    }
                }
            });
        }
    }
}