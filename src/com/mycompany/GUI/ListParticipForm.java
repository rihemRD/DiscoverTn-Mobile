/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.GUI;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Camping;
import com.mycompany.entities.Participation;
import com.mycompany.services.ServiceCamping;
import com.mycompany.services.sms;
import java.util.List;

/**
 *
 * @author rihem
 */
public class ListParticipForm extends BaseForm{
    ServiceCamping servicescampings = ServiceCamping.getInstance();
    Container containerglobal = new Container(new BoxLayout(BoxLayout.Y_AXIS));

    public ListParticipForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public ListParticipForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        
        super("Participation", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Participation");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(resourceObjectInstance);
        
        //tb.addSearchCommand(e -> {});
        tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ADD_BOX, ev -> {
            new AjoutCampingForm().show();
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
        setTitle("Participations");
        setName("Participations");
        
        Image img = resourceObjectInstance.getImage("profile-background.jpg");

        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        addComponent(containerglobal);
        List<Participation> participations = servicescampings.AllParticipations();
          for (Participation t : participations) {
            Container containerCamping = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            containerCamping.add(new Label("Ref: " + t.getRefp()));
            containerCamping.add(new Label("Nom : " + t.getNom()));
            containerCamping.add(new Label("Etat : " + t.getEtat()));
            containerCamping.add(new Label("Date : " + t.getDateParti()));
            containerCamping.add(new Label("Prix : " + t.getMontant()));
            
            Button btnSupp = new Button("Supprimer");
           Button btnconf = new Button("Confirmer");
           Button btnshow = new Button("show");
           
            Container containerbtnmodifsupp = new Container(new BoxLayout(BoxLayout.X_AXIS));

            containerCamping.add(containerbtnmodifsupp);
                  containerbtnmodifsupp.add(btnshow);
            
            String etat = t.getEtat();
            if (etat.equals("En Attend")) {
              containerbtnmodifsupp.add(btnconf);
              containerbtnmodifsupp.add(btnSupp);
                }
            
            Label gui_separator1 = new Label();
            //separateur
            gui_separator1.setShowEvenIfBlank(true);
            gui_separator1.setUIID("Separator");
            gui_separator1.setInlineStylesTheme(resourceObjectInstance);
            gui_separator1.setName("separator1");

            containerglobal.add(containerCamping);
            containerglobal.addComponent(gui_separator1);


            btnSupp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    servicescampings.deleteParticipation(t.getIdParti());
                    new ListParticipForm().show();
                }
            });
            btnconf.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    servicescampings.ConfParticipation(t.getIdParti());
                    servicescampings.SendMail(t.getIdParti());
                    new ListParticipForm().show();
                }
            });
            btnshow.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    servicescampings.ConfParticipation(t.getIdParti());
                    servicescampings.SendMail(t.getIdParti());
                    new ListParticipForm().show();
                }
            });
          
        }
     }
}
