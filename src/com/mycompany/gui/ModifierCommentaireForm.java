/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Article;
import com.mycompany.entites.Commentaire;
import com.mycompany.services.ServiceArticle;
import com.mycompany.services.ServiceCommentaire;

/**
 *
 * @author Lenovo
 */
public class ModifierCommentaireForm extends BaseForm {
    
    Form current;
    public ModifierCommentaireForm(Resources res , Commentaire r) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
//        setTitle("Article");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        
        TextField titre = new TextField(r.getContenuCommentaire(), "Contenu" , 20 , TextField.ANY);
      

        
        titre.setUIID("NewsTopLine");

        
        titre.setSingleLineTextArea(true);


        
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           r.setContenuCommentaire(titre.getText());
       
      
       
       //appel fonction modfier reclamation men service
       
       if(ServiceCommentaire.getInstance().modifierCommentaire(r)) { // if true
           new ListCommentaireForm(res).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListCommentaireForm(res).show();
       });
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
        Container content = BoxLayout.encloseY(
                l1, l2, 
                new FloatingHint(titre),
                createLineSeparator(),//ligne de séparation
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
    }
}