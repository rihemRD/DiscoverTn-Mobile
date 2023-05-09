/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.mycompany.entites.Hotel;


import com.mycompany.services.ServiceHotels;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;


public class TEditHotel extends BaseForm{
     static  Hotel hotel=new Hotel();
     private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));

    private com.codename1.ui.Container gui_Component_Group_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
  
    
    private com.codename1.ui.Label idLabel = new com.codename1.ui.Label();
    private com.codename1.ui.TextField idText = new com.codename1.ui.TextField("", "id");
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
   
    private com.codename1.ui.Button btnconfirmer = new com.codename1.ui.Button();
    
    public TEditHotel() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public TEditHotel(com.codename1.ui.util.Resources resourceObjectInstance) {
       initGuiBuilderComponents(resourceObjectInstance);      
       
       // installSidemenu(resourceObjectInstance);
        remplirEvenement();
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_PUBLIC, e -> {});
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev -> {
            new TEvenements().show();
        });
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
        c2.addComponent(nomLabel);
        c2.addComponent(nomText);
        c2.addComponent(adresseLabel);
        c2.addComponent(adresseText);
        c2.addComponent(telephoneLabel);
        c2.addComponent(telephoneText);
        c2.addComponent(emailLabel);
        c2.addComponent(emailText);
        c2.addComponent(prixLabel);
        c2.addComponent(prixText);
        c2.addComponent(etoileLabel);
        c2.addComponent(etoileText);
        

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

                Hotel hotel = new Hotel();
                
                hotel.setIdHotel(Integer.valueOf(idText.getText()));
                hotel.setNomHotel(nomText.getText());
                hotel.setAdresseHotel(adresseText.getText());
                hotel.setEmailHotel(emailText.getText());
                hotel.setTelephoneHotel(telephoneText.getText());
                hotel.setPrixHotel(Float.valueOf(prixText.getText()));
                hotel.setEtoileHotel(Integer.valueOf(etoileText.getText()));

                ServiceHotels su = ServiceHotels.getInstance();

                    if (su.edit(hotel)) {
                        Dialog.show("Evenement", "Evenement modifié", new Command("OK"));
                    } else {
                        Dialog.show("Evenement", "Erreur de modification", new Command("OK"));
                    }
                    new THotels().show();
            }
        });

        
       
    }
    
    public void remplirEvenement() {
        idText.setText(String.valueOf(hotel.getIdHotel()));
        nomText.setText(hotel.getNomHotel());
        adresseText.setText(hotel.getAdresseHotel());
        emailText.setText(hotel.getEmailHotel());
        prixText.setText(String.valueOf(hotel.getPrixHotel()));
        etoileText.setText(String.valueOf(hotel.getEtoileHotel()));
    }
    
//     @Override
//    protected boolean isCurrentEvenement() {
//        return true;
//    }
}
