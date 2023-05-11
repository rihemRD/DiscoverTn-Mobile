/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.GUI;

import com.codename1.capture.Capture;
import com.codename1.components.FloatingHint;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Camping;
import com.mycompany.services.ServiceCamping;
import java.io.IOException;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
/**
 *
 * @author rihem
 */
public class ModifierCampingForm extends BaseForm {

    Form current;
     private String myPath = "";
    
     static  Camping camping=new Camping();
     private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));

    private com.codename1.ui.Container gui_Component_Group_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
  
    
    private com.codename1.ui.Label idLabel = new  Label();
    private com.codename1.ui.TextField idText = new  TextField("", "id");
   
    private  Label nomLabel = new  Label();
    private TextField nom = new  TextField("", "nom");
    private  Label lieuxLabel = new  Label();
    private TextField lieux = new  TextField("", "lieux");
    private Label PeriodeLabel = new  Label();
    private TextField Periode = new  TextField("", "Periode");
    private Label PrixLabel = new  Label();
    private TextField Prix = new  TextField("", "Prix");
    private Label PlaceLabel = new  Label();
    private TextField Place = new   TextField("", "Place");
    private Label descriptionLabel = new  Label();
    private TextField description = new  TextField("", "description");
    private Label DateDebutLabel = new  Label("Date Debut");
    private Picker dateDebutPicker = new Picker();
    private Label DateFinLabel = new Label("Date Fin");
    private Picker dateFinPicker = new Picker();
 
    private com.codename1.ui.Button btnconfirmer = new com.codename1.ui.Button();
    
    public ModifierCampingForm() {
        this(Resources.getGlobalResources());
    }

    public ModifierCampingForm(Resources resourceObjectInstance) {
      super("EditCampig", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("EditCamping");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(resourceObjectInstance);
        
        //tb.addSearchCommand(e -> {});
        tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ADD_BOX, ev -> {
            new ModifierCampingForm().show();
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
        c2.addComponent(nom);
        c2.addComponent(PrixLabel);
        c2.addComponent(Prix);
        c2.addComponent(PeriodeLabel);
        c2.addComponent(Periode);
        c2.addComponent(descriptionLabel);
        c2.addComponent(description);
        c2.addComponent(PlaceLabel);
        c2.addComponent(Place);
        c2.addComponent(lieuxLabel);
        c2.addComponent(lieux);
        c2.addComponent(DateDebutLabel);
        c2.addComponent(dateDebutPicker);
        c2.addComponent(DateFinLabel);
        c2.addComponent(dateFinPicker);
        
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
        String dateDString = dateDebutPicker.getText();
        String dateFString = dateFinPicker.getText();
        DateTimeFormatter formatter = DateTimeFormat.forPattern("MM/dd/yyyy");
        LocalDate dateD = LocalDate.parse(dateDString, formatter);
        LocalDate dateF = LocalDate.parse(dateFString, formatter);
        
        btnconfirmer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                 Camping r = new Camping(
                         Integer.parseInt(idText.getText()),
                           Integer.parseInt(Periode.getText()),
                           Integer.parseInt(Place.getText()),
                           String.valueOf( nom.getText()),
                           String.valueOf(lieux.getText()) ,
                            String.valueOf(description.getText()),
                           dateD,dateF,
                            Float.parseFloat(Prix.getText()),
                             String.valueOf(myPath),
                             String.valueOf(myPath));
                   

                    ServiceCamping su = ServiceCamping.getInstance();


                        if (su.modifierCamping(r)) {
                            Dialog.show("Camping", "Camping modifié", new Command("OK"));
                        } else {
                            Dialog.show("Camping", "Erreur de modification", new Command("OK"));
                        }
                        new ListCampingForm().show();
            }
        });

        
       
    }
    
    public void remplirCamping() {
    idText.setText(String.valueOf(camping.getIdCamping()));
    nom.setText(camping.getNom());
    lieux.setText(camping.getLieux());
    description.setText(camping.getDescription());
    Periode.setText(String.valueOf(camping.getPeriode()));
    Place.setText(String.valueOf(camping.getNbr_place()));
    Prix.setText(String.valueOf(camping.getPrix()));
    
//        LocalDate localDate = camping.getDateDebut();
//        Date date = Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
//        dateDebutPicker.setDate(date);
  //  dateDebutPicker.setValue(camping.getDateDebut());
    
    }
    
}
