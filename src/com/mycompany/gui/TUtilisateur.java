package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.Log;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Utilisateur;
import com.mycompany.services.UtilisateurService;

import java.util.List;

public class TUtilisateur extends BaseForm {

    UtilisateurService serviceevenements = UtilisateurService.getInstance();
    Container containerglobal = new Container(new BoxLayout(BoxLayout.Y_AXIS));

    public TUtilisateur() {
        this(Resources.getGlobalResources());
    }

    public TUtilisateur(Resources resourceObjectInstance) {

        super("Liste-Utilisateurs", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);

        setTitle("Utilisateurs");


        super.addSideMenu(resourceObjectInstance);

        /* // tb.addSearchCommand(e -> {});
        tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ADD_BOX, ev -> {
            new TAddEvenement().show();
        });*/
        Image img = resourceObjectInstance.getImage("profile-background.jpg");

        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        add(LayeredLayout.encloseIn(
                sl));

        initGuiBuilderComponents(resourceObjectInstance);

    }

    private void initGuiBuilderComponents(Resources resourceObjectInstance) {
        String a;
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        setTitle("yahya ");


        Image img = resourceObjectInstance.getImage("profile-background.jpg");

        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        addComponent(containerglobal);
        List<Utilisateur> pubs = serviceevenements.getAllUtilisateurs();

        for (Utilisateur t : pubs) {
            System.out.println(t);
            Container containerUtilisateur = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            containerUtilisateur.add(new Label("name : " + t.getNomUtilisateur()));
            containerUtilisateur.add(new Label("login : " + t.getLoginUtilisateur()));

            // Add the image
            String filePath = t.getImgUtilisateur();
            String formattedPath = "/" + filePath.substring(filePath.lastIndexOf("/") + 1);
            if (t.getImgUtilisateur() != null && !t.getImgUtilisateur().isEmpty()) {
                try {
                    ImageViewer imgv;
                    imgv = new ImageViewer(Image.createImage(formattedPath));
                    containerUtilisateur.add(imgv);
                } catch (Exception ex) {
                    Log.e(ex);
                }
            }

            Button btnModif = new Button("Modifier");
            Button btnReport = new Button("report");
            Button btnSupp = new Button("Supprimer");
            Button btnRes = new Button("ajouter");
            Container containerbtnmodifsupp = new Container(new BoxLayout(BoxLayout.X_AXIS));

            containerUtilisateur.add(containerbtnmodifsupp);

            containerbtnmodifsupp.add(btnModif);
            containerbtnmodifsupp.add(btnReport);

            containerbtnmodifsupp.add(btnSupp);
            containerglobal.add(btnRes);
            Button chooseFileButton = new Button("Choose File");
            containerbtnmodifsupp.add(chooseFileButton);



            chooseFileButton.addActionListener(e -> {
                Display.getInstance().openGallery(e2 -> {
                    if (e2 != null && e2.getSource() != null) {
                        String filePath1 = (String) e2.getSource();

                    }
                }, Display.GALLERY_IMAGE);
            });

            Label gui_separator1 = new Label();
            // separateur
            gui_separator1.setShowEvenIfBlank(true);
            gui_separator1.setUIID("Separator");


            containerglobal.add(containerUtilisateur);
            containerglobal.addComponent(gui_separator1);

            btnModif.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    TeditUtilisateurs.Utilisateur = t;
                    new Teditpublication().show();


                }
            });

            btnSupp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    UtilisateurService.instance.Remove(t.getIdUtilisateur());
                    new Tpublication().show();
                }
            });

            btnRes.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    new TAddpublication().show();
                }
            });
            btnReport.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent evt) {
                                                String s="yahya.fhima@esprit.tn";
                                                // try {
                                                // ServicePublication.instance.sendMail(s,s,s);
                                                // } catch (MessagingException e) {
                                                //     throw new RuntimeException(e);
                                            }
                                        }
            );



        }
    }
}