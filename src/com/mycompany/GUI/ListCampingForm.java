/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.GUI;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Camping;
import com.mycompany.entities.Participation;
import com.mycompany.services.ServiceCamping;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author rihem
 */
public class ListCampingForm extends BaseForm {
    
 ServiceCamping servicescampings = ServiceCamping.getInstance();
    Container containerglobal = new Container(new BoxLayout(BoxLayout.Y_AXIS));

    public ListCampingForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public ListCampingForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        
        super("Camping", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Camping");
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
            containerCamping.add(new Label("Nom : " + t.getNom()));
            containerCamping.add(new Label("Lieux : " + t.getLieux()));
            //containerCamping.add(new Label("Date : " + t.getDateDebut()));
            containerCamping.add(new Label("Prix : " + t.getPrix()));
            containerCamping.add(new Label("Description : " + t.getDescription()));
            Button btnModif = new Button("Modifier");
            Button btnSupp = new Button("Supprimer");
            Button btnRes = new Button("Participation");
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
                    ModifierCampingForm.camping = t;
                    System.out.println(t);
                    new ModifierCampingForm().show();
                }
            });

            btnSupp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    servicescampings.deleteCamping(t.getIdCamping());
                    new ListCampingForm().show();
                }
            });
            
           
//            btnRes.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent evt) {
//                    int userid = SessionManager.getId();
//                    Participation reservation = new Participation();
//                    reservation.setIdCamp(t);
//                    reservation.setIdClient(userid);
//                    ServiceCamping su = ServiceCamping.getInstance();
//                    su.addParticipation(reservation);
// 
//                    if (su.addParticipation(reservation)) {
//                        Dialog.show("Reservation", "Camping reservé", new Command("OK"));
//
//                    } else {
//                        Dialog.show("Reservation", "Erreur de reservation", new Command("OK"));
//                    }
//                }
//            });
        }
     }
   
}