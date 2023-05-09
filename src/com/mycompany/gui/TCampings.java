/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.gui;

import com.codename1.components.ScaleImageLabel;
import com.mycompany.entites.Evenement;
import com.mycompany.services.ServicesCampings;
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
import com.mycompany.entites.Camping;
import com.mycompany.entites.Reservation;
import com.mycompany.gui.BaseForm;
import com.mycompany.services.SessionManager;

import java.util.List;

public class TCampings extends BaseForm {

    ServicesCampings servicescampings = ServicesCampings.getInstance();
    Container containerglobal = new Container(new BoxLayout(BoxLayout.Y_AXIS));

    public TCampings() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public TCampings(com.codename1.ui.util.Resources resourceObjectInstance) {
        
        super("Camping", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Camping");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(resourceObjectInstance);
        
        //tb.addSearchCommand(e -> {});
        tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ADD_BOX, ev -> {
            new TAddCamping().show();
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
        setTitle("Campings");
        setName("Campings");
        
        Image img = resourceObjectInstance.getImage("profile-background.jpg");

        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        addComponent(containerglobal);
        List<Camping> campings = servicescampings.AllCampings();

          for (Camping t : campings) {
            Container containerCamping = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            containerCamping.add(new Label("Id: " + t.getIdCamping()));
            containerCamping.add(new Label("Nom : " + t.getNomCamping()));
            containerCamping.add(new Label("Adresse : " + t.getAdresseCamping()));
            containerCamping.add(new Label("Telephone : " + t.getTelephoneCamping()));
            containerCamping.add(new Label("Rating : " + t.getRatingCamping()));
            containerCamping.add(new Label("Description : " + t.getDescirptionCamping()));
            Button btnModif = new Button("Modifier");
            Button btnSupp = new Button("Supprimer");
            Button btnRes = new Button("Reserver");
            Container containerbtnmodifsupp = new Container(new BoxLayout(BoxLayout.X_AXIS));

            containerCamping.add(containerbtnmodifsupp);
                  
            containerbtnmodifsupp.add(btnModif);
            containerbtnmodifsupp.add(btnSupp);
            containerbtnmodifsupp.add(btnRes);

            Label gui_separator1 = new Label();
            //separateur
            gui_separator1.setShowEvenIfBlank(true);
            gui_separator1.setUIID("Separator");
            gui_separator1.setInlineStylesTheme(resourceObjectInstance);
            gui_separator1.setName("separator1");

            containerglobal.add(containerCamping);
            containerglobal.addComponent(gui_separator1);

            btnModif.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    TEditCamping.camping = t;
                    System.out.println(t);
                    new TEditCamping().show();
                }
            });

            btnSupp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    servicescampings.Remove(t.getIdCamping());
                    new TCampings().show();
                }
            });
            
           
            btnRes.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    int userid = SessionManager.getId();
                    Reservation reservation = new Reservation();
                    reservation.setCamp(t);
                    reservation.setIdUserR(userid);
                    ServicesCampings su = ServicesCampings.getInstance();
                    su.addReservation(reservation);
 
                    if (su.addReservation(reservation)) {
                        Dialog.show("Reservation", "Camping reservé", new Command("OK"));

                    } else {
                        Dialog.show("Reservation", "Erreur de reservation", new Command("OK"));
                    }
                }
            });
        }
    }
}