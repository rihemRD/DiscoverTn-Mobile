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
import com.mycompany.services.ServiceArticle;

/**
 *
 * @author Lenovo
 */
public class ModifierArticleForm extends BaseForm {
    
    Form current;
    public ModifierArticleForm(Resources res , Article r) {
         super("Newsfeed",BoxLayout.y()); 
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Article");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        
        TextField titre = new TextField(r.getTitreArticle() , "Titre" , 20 , TextField.ANY);
        TextField description = new TextField(r.getDescriptionArticle() , "Description" , 20 , TextField.ANY);
               TextField image = new TextField(String.valueOf(r.getImageArticle()) , "Image" , 20 , TextField.ANY);
                              TextField categorie = new TextField(String.valueOf(r.getIdCategorie()) , "Categorie" , 20 , TextField.ANY);

        //etat bch na3mlo comobbox bon lazm admin ya3mlleha approuver mais just chnwarikom ComboBox

        
        titre.setUIID("NewsTopLine");
        description.setUIID("NewsTopLine");
        image.setUIID("NewsTopLine");
        categorie.setUIID("NewsTopLine");
        
        titre.setSingleLineTextArea(true);
        description.setSingleLineTextArea(true);
        image.setSingleLineTextArea(true);
                categorie.setSingleLineTextArea(true);

        
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           r.setTitreArticle(titre.getText());
           r.setDescriptionArticle(description.getText());
                      r.setImageArticle(image.getText());
                      r.setIdCategorie(Integer.valueOf(categorie.getText()));

      
       
       
       if(ServiceArticle.getInstance().modifierArticle(r)) { // if true
           new ListArticlesForm(res).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListArticlesForm(res).show();
       });
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
        Container content = BoxLayout.encloseY(
                l1, l2, 
                new FloatingHint(titre),
                createLineSeparator(),
                new FloatingHint(description),
                createLineSeparator(),
                                new FloatingHint(image),
                createLineSeparator(),//ligne de séparation
                
                                new FloatingHint(categorie),
                createLineSeparator(),//ligne de séparation
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
    }
}