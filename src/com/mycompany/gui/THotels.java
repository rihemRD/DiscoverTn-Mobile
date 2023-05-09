/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ScaleImageLabel;
import com.mycompany.entites.Hotel;
import com.mycompany.services.ServiceHotels;
import com.mycompany.utils.Statics;
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
import com.mycompany.services.ServiceEvenements;
import com.mycompany.services.SessionManager;
import java.util.List;

public class THotels extends BaseForm{
    ServiceHotels servicehotels = ServiceHotels.getInstance().getInstance();
    Container containerglobal = new Container(new BoxLayout(BoxLayout.Y_AXIS));

    public THotels() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public THotels(com.codename1.ui.util.Resources resourceObjectInstance) {
        super("Evenement", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Evenement");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(resourceObjectInstance);
        
        //tb.addSearchCommand(e -> {});
        tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ADD_BOX, ev -> {
            new TAddHotel().show();
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
        setTitle("Hotels");
        setName("Hotels");

        addComponent(containerglobal);

        
        List<Hotel> hotels = servicehotels.AllHotels();

        for (Hotel t : hotels) {
            Container containerequipe = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            containerequipe.add(new Label("Id: " + t.getIdHotel()));
            containerequipe.add(new Label("Nom : " + t.getNomHotel()));
            containerequipe.add(new Label("Adresse: " + t.getAdresseHotel()));
            containerequipe.add(new Label("Telephone : " + t.getTelephoneHotel()));
            containerequipe.add(new Label("Prix : " + t.getPrixHotel()));
            containerequipe.add(new Label("Etoile : " + t.getEtoileHotel()));

            Button btnRes = new Button("Reserver");
            Button btnModif = new Button("Modifier");
            Button btnSupp = new Button("Supprimer");
            Container containerbtnmodifsupp = new Container(new BoxLayout(BoxLayout.X_AXIS));

            containerbtnmodifsupp.add(btnRes);

            containerequipe.add(containerbtnmodifsupp);

            containerbtnmodifsupp.add(btnModif);
            containerbtnmodifsupp.add(btnSupp);

            Label gui_separator1 = new Label();
            //separateur
            gui_separator1.setShowEvenIfBlank(true);
            gui_separator1.setUIID("Separator");
            gui_separator1.setInlineStylesTheme(resourceObjectInstance);
            gui_separator1.setName("separator1");

            containerglobal.add(containerequipe);
            containerglobal.addComponent(gui_separator1);

            btnModif.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    TEditHotel.hotel = t;
                    new TEditHotel().show();
                }
            });

            btnSupp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    servicehotels.Remove(t.getIdHotel());
                    new THotels().show();
                }
            });
                     btnRes.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    int userid = SessionManager.getId();
                    Reservation reservation = new Reservation();
                    reservation.setHot(t);
                    reservation.setIdUserR(userid);
                    ServiceHotels su = ServiceHotels.getInstance();
                    su.addReservation(reservation);
                     String email = SessionManager.getEmail();
                            servicehotels.getInstance().sendMail(email, resourceObjectInstance);
                    if (su.addReservation(reservation)) {
                        Dialog.show("Hotel", "Hotel reservé", new Command("OK"));

                    } else {
                        Dialog.show("Hotel", "Erreur reservé", new Command("OK"));
                    }
                }
            });
        }
    }


}
