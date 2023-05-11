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

import com.codename1.capture.Capture;
import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.codename1.util.StringUtil;
import com.mycompany.services.UtilisateurService;
import com.mycompany.entites.Utilisateur;
import java.io.File;
import java.io.IOException;



/**
 * Signup UI
 *
 * @author Ben Mahmoud
 */
public class SignUpForm extends BaseForm {
       String path;
       String Newpath;
       String x;
       String y;
       String j;
       String k;
       String A;
       String finalpath;
        String finalpathbase;
        Utilisateur us = new Utilisateur();
    public SignUpForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
        TextField nom = new TextField("", "Nom", 20, TextField.ANY);
        TextField Prenom = new TextField("", "Prenom", 20, TextField.ANY);        
        TextField username = new TextField("", "Username", 20, TextField.ANY);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        TextField email = new TextField("", "E-Mail", 20, TextField.ANY);
        TextField Telephone = new TextField("", "Telephone", 20, TextField.NUMERIC);
        TextField Adresse = new TextField("", "Adresse", 20, TextField.ANY);
        Button img= new Button("Import");
        Label image =new Label();
        
        
      
        img.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              
               String path1 = Capture.capturePhoto(Display.getInstance().getDisplayWidth(),-1);
           
                if(path1!=null)
                {
                    //File copy from source to destination
                    //DON'T ASK ME HOW !!!!!
                    path=path1;
                    y="C:\\xampp\\htdocs\\PideversImgUploaded\\";
                    x="file://C:/Users/BENMAH~1/AppData/Local/Temp/";
                    A="..";
                    j=".";
                    String Temppath ="C:\\Users\\Ben Mahmoud\\AppData\\Local\\Temp\\";
                    int lenghtfile=path1.length();
                    System.out.println(lenghtfile);
                    String nomimgfalse=path1.substring(44);
                    String nomimg=StringUtil.replaceAll(nomimgfalse, A, j);
                    k=StringUtil.replaceAll(path, A, j);
                    Newpath=StringUtil.replaceAll(k,x,y);
                    finalpath=Temppath+nomimgfalse;
                    finalpathbase=y+nomimg;
                    File source = new File(finalpath);
                    File Dest= new File(Newpath);
                   try {
                       UtilisateurService.getInstance().copyFileUsingStream(source,Dest);
                       //
                   } catch (IOException ex) {
                       ex.getMessage();
                   }
                    
                    try {
                        Image img1 = Image.createImage(path1);
                        img.setIcon(img1);
                    }
                    catch (IOException ex) {
                        ex.getMessage();
                    }
                }
            }
        });
        
        username.setSingleLineTextArea(false);
        email.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        nom.setSingleLineTextArea(false);
        Prenom.setSingleLineTextArea(false);
        Telephone.setSingleLineTextArea(false);
        Adresse.setSingleLineTextArea(false);

        Button next = new Button("Next");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");
        
        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                new FloatingHint(nom),
                createLineSeparator(),
                new FloatingHint(Prenom),
                createLineSeparator(),
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(Telephone),
                createLineSeparator(),
                new FloatingHint(Adresse),
                createLineSeparator(),
                img,
                createLineSeparator(),
                createLineSeparator(),
                createLineSeparator(),
                image
        );
       
    
       
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        next.requestFocus();
         next.addActionListener(e -> 
        {
               UtilisateurService.getInstance().signup(nom,Prenom,email,username,password,Telephone,Adresse,finalpathbase,res);

           
        });
    }
    
}
