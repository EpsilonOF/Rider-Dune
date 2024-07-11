package Vue;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;

import javax.imageio.ImageIO;
import java.io.IOException;

import Controleur.Controller;

public class VueParametre extends JPanel {
    public JPanel panel, panelCenter, panelTouche, panelGrav, panelCouleur, panelCouleur1, panelCouleur2, panelRVB;
    public JLabel titre, titreTouche, titreGrav, titreCouleur, erreurTouche, valeurGrav;
    public JTextField toucheText;
    public JSlider graviteSlider, rouge1, rouge2, vert1, vert2, bleu1, bleu2;
    public GradientButton valider, menu;
    public Image bgImage;

    public VueParametre(MenuAccueil accueil){
        try {
            bgImage = ImageIO.read(getClass().getResource("../resources/fondparametres.png"));
        } catch (IOException e) {
            System.out.println("erreur chargement image");
            e.printStackTrace();
        }
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(bgImage.getWidth(null), bgImage.getHeight(null)));
        panel = new JPanel(new BorderLayout());

        panel.setOpaque(false);

        // Ajouter le titre
        titre = styleJLabel("Paramètres", 100);
        panel.add(titre, BorderLayout.NORTH);
        panelCenter = new JPanel(new GridLayout(3,1));
        panelCenter.setOpaque(false);

        // Partie "Changer de touche"
        // titre de la partie
        panelTouche = new JPanel(new GridLayout(1,4));
        panelTouche.setOpaque(false);
        titreTouche = styleJLabel("Modifier la touche :", 40);
        titreTouche.setHorizontalAlignment(JLabel.LEFT);
        panelTouche.add(titreTouche);
        // zone de texte pour remplacer la touche
        toucheText = new JTextField(1);
        toucheText.setHorizontalAlignment(JLabel.CENTER);
        toucheText.setOpaque(false);
        toucheText.setForeground(Color.WHITE);
        toucheText.setFont(new Font("Upheaval TT (BRK)", Font.BOLD, 40));
        toucheText.setOpaque(false);
        toucheText.setBorder(BorderFactory.createEmptyBorder());
        panelTouche.add(toucheText);
        // Bouton Valider pour activer la touche et vérifier la validité
        valider = new GradientButton("Valider");
        valider.setFont(new Font("Upheaval TT (BRK)", Font.BOLD, 40));
        valider.setColors(Color.WHITE, Color.WHITE);
        valider.setOpaque(false);
        valider.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(toucheText.getText().length()>1){
                    erreurTouche.setText("N'entrez qu'une seule touche");
                    erreurTouche.setVisible(true);
                }else if(toucheText.getText().equals("p")){
                    erreurTouche.setText("Touche déjà utilisée");
                    erreurTouche.setVisible(true);
                }else if(toucheText.getText().length()==1){
                    if((toucheText.getText().charAt(0)<65 || toucheText.getText().charAt(0)>90) && (toucheText.getText().charAt(0)<97 || toucheText.getText().charAt(0)>122)){
                        erreurTouche.setText("Entrez une lettre");
                        erreurTouche.setVisible(true);
                    }else{
                        Controller.touche = toucheText.getText().toUpperCase().charAt(0);
                        toucheText.setText("");
                        erreurTouche.setVisible(false);
                    }
                }
                if(toucheText.getText().length()==0) erreurTouche.setVisible(false);
                Model.Vehicule.g = graviteSlider.getValue();
            }
        });
        panelTouche.add(valider);
        // Message d'erreur si l'entrée n'est pas valide
        erreurTouche = styleJLabel("", 25);
        erreurTouche.setVisible(false);
        erreurTouche.setHorizontalAlignment(JLabel.LEFT);
        panelTouche.add(erreurTouche);

        panelCenter.add(panelTouche);

        // Partie "Changer la gravité"
        // titre de la partie
        panelGrav = new JPanel(new GridLayout(1,3));
        panelGrav.setOpaque(false);
        titreGrav = styleJLabel("Modifier la gravité :", 40);
        titreGrav.setHorizontalAlignment(JLabel.LEFT);
        panelGrav.add(titreGrav);
        // Slider pour changer la gravité
        graviteSlider = new JSlider(JSlider.HORIZONTAL, 0, 20, (int)Model.Vehicule.g);
        graviteSlider.setMajorTickSpacing(5);
        graviteSlider.setPaintTicks(true);
        graviteSlider.setPaintLabels(true);
        graviteSlider.setOpaque(false);
        graviteSlider.setForeground(Color.WHITE);
        // Afficher la valeur actuelle de la gravité (actualisée en live)
        valeurGrav = styleJLabel("La gravité est à " + (int)Model.Vehicule.g, 30);
        // Changer la valeur de la gravité en fonction du slider (dans le jeu et sur le texte)
        graviteSlider.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent e){
                if(((JSlider)e.getSource()).getValue()==0) ((JSlider)e.getSource()).setValue(1);
                Model.Vehicule.g = graviteSlider.getValue();
                valeurGrav.setText("La gravité est à " + (int)Model.Vehicule.g);
            }
        });
        panelGrav.add(graviteSlider);
        panelGrav.add(valeurGrav);

        panelCenter.add(panelGrav);
        // Bouton pour retourner au menu
        menu = new GradientButton("Menu");
        menu.setFont(new Font("Upheaval TT (BRK)", Font.BOLD, 70));
        menu.setForeground(Color.WHITE);
        menu.setColors(Color.WHITE, Color.WHITE);
        menu.setOpaque(false);
        menu.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                accueil.frame.dispose();
                MenuAccueil.createAndShowGUI();
            }
        });
        panel.add(menu, BorderLayout.SOUTH);

        panel.add(panelCenter, BorderLayout.CENTER);
        add(panel);
    }
    // Fonction pour styliser les JLabel
    public static JLabel styleJLabel(String name, int taille){
        JLabel label = new JLabel(name);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Upheaval TT (BRK)", Font.BOLD, taille));
        label.setForeground(Color.WHITE);
        label.setOpaque(false);
        return label;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
    }
    
}
