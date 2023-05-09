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
import com.mycompany.entites.Evenement;
import com.mycompany.services.ServiceEvenements;
import com.mycompany.services.SessionManager;
import java.io.IOException;





public class TEditEvenement extends BaseForm {
    
    private String myPath = "";
    
     static  Evenement evenement=new Evenement();
     private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));

    private com.codename1.ui.Container gui_Component_Group_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
  
    
    private com.codename1.ui.Label idLabel = new com.codename1.ui.Label();
    private com.codename1.ui.TextField idText = new com.codename1.ui.TextField("", "id");
	private com.codename1.ui.Label DataDebutLabel = new com.codename1.ui.Label();
    private com.codename1.ui.TextField DataDebutText = new com.codename1.ui.TextField("", "dateDebut");
	
    private com.codename1.ui.Label DataFinLabel = new com.codename1.ui.Label();
    private com.codename1.ui.TextField DataFinText = new com.codename1.ui.TextField("", "dateFin");
    private com.codename1.ui.Label titreLabel = new com.codename1.ui.Label();
    private com.codename1.ui.TextField titreText = new com.codename1.ui.TextField("", "titre");
    private com.codename1.ui.Label lieuLabel = new com.codename1.ui.Label();
    private com.codename1.ui.TextField lieuText = new com.codename1.ui.TextField("", "lieu");
    private com.codename1.ui.Label typeLabel = new com.codename1.ui.Label();
    private com.codename1.ui.TextField typeText = new com.codename1.ui.TextField("", "type");
    private com.codename1.ui.Label descriptionLabel = new com.codename1.ui.Label();
    private com.codename1.ui.TextField descriptionText = new com.codename1.ui.TextField("", "description");
    private com.codename1.ui.Label prixLabel = new com.codename1.ui.Label();
    private com.codename1.ui.TextField prixText = new com.codename1.ui.TextField("", "prix");
    
  
     
    private com.codename1.ui.Button btnconfirmer = new com.codename1.ui.Button();
    
    public TEditEvenement() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public TEditEvenement(com.codename1.ui.util.Resources resourceObjectInstance) {
      super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
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
        
        remplirEvenement();
    }

    private void initGuiBuilderComponents(Resources resourceObjectInstance) {
       
        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setTitle("Edit Evenement");
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
        
      
        c2.addComponent(idLabel);
        c2.addComponent(idText);
        c2.addComponent(titreLabel);
        c2.addComponent(titreText);
        c2.addComponent(lieuLabel);
        c2.addComponent(lieuText);
        c2.addComponent(typeLabel);
        c2.addComponent(typeText);
        c2.addComponent(descriptionLabel);
        c2.addComponent(descriptionText);
        c2.addComponent(DataDebutLabel);
        c2.addComponent(DataFinLabel);
        c2.addComponent(prixLabel);
        c2.addComponent(prixText);
        
        
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

                Evenement evenement = new Evenement();

                evenement.setIdEvent(Integer.valueOf(idText.getText()));
                 evenement.setTitreEvent(titreText.getText());
                    evenement.setLieuEvent(lieuText.getText());
                    evenement.setDateDebutEvent(DataDebutText.getText());
                    evenement.setDateFinEvent(DataFinText.getText());
                    evenement.setPrixEvent(prixText.getText());
                    evenement.setDescEvent(descriptionText.getText());
                   
                   /// evenement.setStatusEvenement(1);
                    int userid = SessionManager.getId();
                    evenement.setIdUser(userid);

                ServiceEvenements su = ServiceEvenements.getInstance();

                        if (su.edit(evenement)) {
                            Dialog.show("Evenement", "Evenement modifié", new Command("OK"));
                        } else {
                            Dialog.show("Evenement", "Erreur de modification", new Command("OK"));
                        }
                        new TEvenements().show();
            }
        });

        
       
    }
    
    public void remplirEvenement() {
        System.out.println(evenement);
        idText.setText(String.valueOf(evenement.getIdEvent()));
        titreText.setText(evenement.getTitreEvent());
        DataDebutText.setText(evenement.getDateDebutEvent());
        DataFinText.setText(evenement.getDateFinEvent());
        lieuText.setText(evenement.getLieuEvent());
        typeText.setText(evenement.getDescEvent());
        descriptionText.setText(evenement.getDescEvent());
        prixText.setText(evenement.getPrixEvent());
    }
    
}
