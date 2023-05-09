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
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Camping;
import com.mycompany.entites.Evenement;
import com.mycompany.services.ServiceEvenements;
import com.mycompany.services.ServicesCampings;
import com.mycompany.services.SessionManager;
import java.io.IOException;





public class TEditCamping extends BaseForm {
    
    private String myPath = "";
    
     static  Camping camping=new Camping();
     private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));

    private com.codename1.ui.Container gui_Component_Group_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
  
    
    private com.codename1.ui.Label idLabel = new com.codename1.ui.Label();
    private com.codename1.ui.TextField idText = new com.codename1.ui.TextField("", "id");
   
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
    
  
     
    private com.codename1.ui.Button btnconfirmer = new com.codename1.ui.Button();
    
    public TEditCamping() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public TEditCamping(com.codename1.ui.util.Resources resourceObjectInstance) {
      super("EditCampig", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("EditCamping");
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
        
        remplirCamping();
    }

    private void initGuiBuilderComponents(Resources resourceObjectInstance) {
       
        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setTitle("Edit Camping");
        setName("Edit");
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
            String path = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
            if(path != null){
                try {
                    Image img = Image.createImage(path);
                    lbltImage.setIcon(img);
                    myPath = path;
                } catch (IOException ex) {
                    ex.printStackTrace();
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

                Camping camping = new Camping();
                camping.setNomCamping(nomText.getText());
                camping.setAdresseCamping(adresseText.getText());            
                camping.setTelephoneCamping(telephoneText.getText());
                camping.setRatingCamping(Integer.valueOf(ratingText.getText()));
                camping.setDescirptionCamping(descriptionText.getText());
                camping.setImageCamping(myPath);
                int userid = SessionManager.getId();
                camping.setIdUser(userid);
                    ServicesCampings su = ServicesCampings.getInstance();

                        if (su.edit(camping)) {
                            Dialog.show("Camping", "Camping modifié", new Command("OK"));
                        } else {
                            Dialog.show("Camping", "Erreur de modification", new Command("OK"));
                        }
                        new TCampings().show();
            }
        });

        
       
    }
    
    public void remplirCamping() {
    idText.setText(String.valueOf(camping.getIdCamping()));
    nomText.setText(camping.getNomCamping());
    adresseText.setText(camping.getAdresseCamping());
    telephoneText.setText(camping.getTelephoneCamping());
    descriptionText.setText(camping.getDescirptionCamping());
    ratingText.setText(String.valueOf(camping.getRatingCamping()));
    }
    
}
