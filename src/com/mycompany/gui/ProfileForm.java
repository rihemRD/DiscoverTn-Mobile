/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.services.SessionManager;
import com.mycompany.services.UtilisateurService;

/**
 * The user profile form
 *
 * @author Ben Mahmoud
 */
public class ProfileForm extends BaseForm {

    public ProfileForm(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {});
        
        
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                    GridLayout.encloseIn(1, 
                      
                            FlowLayout.encloseCenter(
                                new Label(res.getImage("utilisateur.png"), "PictureWhiteBackgrond"))    
                    )
                )
        ));
        
        String email1 = SessionManager.getEmail();
        String password1 = SessionManager.getPassowrd();
        String username1 = SessionManager.getUserName();
        String nom1 = SessionManager.getNom();
        String prenom1 = SessionManager.getPrenom();
        String tel1 = SessionManager.getTelephone();
        String adr1 = SessionManager.getAdresse();
        
        TextField username = new TextField(username1);
        username.setUIID("TextFieldBlack");
        addStringValue("Username", username);
        TextField email = new TextField(email1, "E-Mail", 20, TextField.EMAILADDR);
        email.setUIID("TextFieldBlack");
        addStringValue("E-Mail", email);
        TextField password = new TextField(password1, "Password", 20, TextField.PASSWORD);
        password.setUIID("TextFieldBlack");
        addStringValue("Password", password);
       
          
           TextField nom = new TextField(nom1);
        nom.setUIID("TextFieldBlack");
        addStringValue("Nom", nom);
        
        
          TextField prenom = new TextField(prenom1);
        prenom.setUIID("TextFieldBlack");
        addStringValue("prenom", prenom);
        
        
          TextField tel = new TextField(tel1);
        tel.setUIID("TextFieldBlack");
        addStringValue("Telehpone", tel);
        
        
          TextField adr = new TextField(adr1);
        adr.setUIID("TextFieldBlack");
        addStringValue("Adresse", adr);
          
          
            email.setEnabled(false);
        username.setEnabled(false);
           Button update = new Button("Update");
          addStringValue("", update);
        update.addActionListener(e -> 
        {
               UtilisateurService.getInstance().update(username,nom,prenom,tel,adr,password, res);
        });
     
    }
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}
