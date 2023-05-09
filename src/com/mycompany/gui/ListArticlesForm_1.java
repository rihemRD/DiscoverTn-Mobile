/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
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
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Article;
import com.mycompany.services.ServiceArticle;
import java.util.ArrayList;
import java.util.List;


public class ListArticlesForm_1 extends BaseForm{
    
    Form current;
    public ListArticlesForm_1(Resources res ) {
          super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
         List<Article> articles;
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
//        setTitle("Article");
        getContentPane().setScrollVisible(false);
                

        Button goback = new Button("Retour");
 goback.addActionListener(e->{
            new NewsfeedForm(res).show();
        });
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("Mes Articles", barGroup);
        mesListes.setUIID("SelectBar");
        
        RadioButton commentaire = RadioButton.createToggle("commentaires", barGroup);
        mesListes.setUIID("SelectBar");

        RadioButton partage = RadioButton.createToggle("Ajouter", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");


        mesListes.addActionListener((e) -> {        
         ListArticlesForm_1 a = new ListArticlesForm_1(res);
          a.show();
            refreshTheme();
        });
        
                partage.addActionListener((e) -> {        
         AjoutArticleForm aj = new AjoutArticleForm(res);
          aj.show();
            refreshTheme();
        });
                                commentaire.addActionListener((e) -> {        
         ListCommentaireForm aj = new ListCommentaireForm(res);
          aj.show();
            refreshTheme();
        });

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(4, mesListes,commentaire, partage,goback),
                FlowLayout.encloseBottom(arrow)
        ));
        
        partage.setSelected(false);
        mesListes.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(mesListes, arrow);
        });
        bindButtonSelection(mesListes, arrow);

        bindButtonSelection(partage, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        
      
        //Appel affichage methode
        articles = ServiceArticle.getInstance().AllArticles();
           
        for (Article ar: articles){
    String urlImage ="back-logo.jpeg";//image statique pour le moment ba3d taw fi  videos jayin nwarikom image 
            
             Image placeHolder = Image.createImage(120, 90);
             EncodedImage enc =  EncodedImage.createFromImage(placeHolder,false);
             URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage, URLImage.RESIZE_SCALE);
             
                addButton(urlim,ar,res);
        
                ScaleImageLabel image = new ScaleImageLabel(urlim);
                
                Container containerImg = new Container();
                
                image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        }
}

    
    
     private void addTab(Tabs swipe, Label spacer , Image image, String string, String text, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
//        
//        if(image.getHeight() < size) {
//            image = image.scaledHeight(size);
//        }
//        
//        
//        
//        if(image.getHeight() > Display.getInstance().getDisplayHeight() / 2 ) {
//            image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
//        }
        
        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        Label overLay = new Label("","ImageOverlay");
        
        
        Container page1 = 
                LayeredLayout.encloseIn(
                imageScale,
                        overLay,
                        BorderLayout.south(
                        BoxLayout.encloseY(
                        new SpanLabel(text, "LargeWhiteText"),
                                        spacer
                        )
                    )
                );
        
        swipe.addTab("",res.getImage("back-logo.jpeg"), page1);
        
        
        
        
    }
    
    
    
    public void bindButtonSelection(Button btn , Label l ) {
        
        btn.addActionListener(e-> {
        if(btn.isSelected()) {
            updateArrowPosition(btn,l);
        }
    });
    }

    private void updateArrowPosition(Button btn, Label l) {
        
        l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth()  / 2  - l.getWidth() / 2 );
        l.getParent().repaint();
    }
    
  private void addButton(Image img,Article rec , Resources res) {
        
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        
        
        Label dateTxt = new Label("Date: "+rec.getDateArticle(),"NewsTopLine2");
        Label catTxt= new Label("categorie: "+rec.getIdCategorie(),"NewsTopLine2");
        SpanLabel titreTxt = new SpanLabel("Titre: "+rec.getTitreArticle(),"NewsTopLine2");
        SpanLabel descriptionTxt = new SpanLabel("Description: "+rec.getDescriptionArticle(),"NewsTopLine2" );
        descriptionTxt.getUnselectedStyle().setMargin(BOTTOM,80);
        createLineSeparator();
        

        //supprimer
        Label lSupprimer = new Label(" ");
        lSupprimer.setUIID("NewsTopLine");
        Style supprmierStyle = new Style(lSupprimer.getUnselectedStyle());
        supprmierStyle.setFgColor(0xf21f1f);
        
        FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
        lSupprimer.setIcon(suprrimerImage);
        lSupprimer.setTextPosition(RIGHT);
        

        lSupprimer.addPointerPressedListener(l -> {
            
            Dialog dig = new Dialog("Suppression");
            
            if(dig.show("Suppression","Vous voulez supprimer ce reclamation ?","Annuler","Oui")) {
                dig.dispose();
            }
            else {
                dig.dispose();
                 }
                if(ServiceArticle.getInstance().supprimerArticle(rec.getIdArticle())) {
                    new ListArticlesForm_1(res).show();
                }
           
        });
        
        //Update  
        Label lModifier = new Label(" ");
        lModifier.setUIID("NewsTopLine");
        Style modifierStyle = new Style(lModifier.getUnselectedStyle());
        modifierStyle.setFgColor(0xf7ad02);
        
        FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
        lModifier.setIcon(mFontImage);
        lModifier.setTextPosition(LEFT);
        
        
        lModifier.addPointerPressedListener(l -> {
        new ModifierArticleForm(res,rec).show();
        });
        
        
        cnt.add(BorderLayout.WEST,BoxLayout.encloseY(
                
                BoxLayout.encloseX(dateTxt,lModifier,lSupprimer),
                BoxLayout.encloseX(catTxt),
                BoxLayout.encloseX(titreTxt),
                BoxLayout.encloseX(descriptionTxt)
        ));
        
        
        
        
        add(cnt);
    }
    
  }
  
