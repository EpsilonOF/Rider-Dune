package Vue;

import javax.swing.*;
import Model.Fichiers;

import java.awt.*;
import java.awt.event.*;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;

public class CreerProfil extends JPanel {
    private GradientButton menu, submit;
    private JLabel labelp, labelmdp, erreur, creerProfilLabel;
    private JTextField pseudo;
    private JPasswordField mdp;
    private JPanel panel, connPanel, boutonPanel;
    private Image bgImage;
    

    public CreerProfil(MenuAccueil accueil) {
        try {
            bgImage = ImageIO.read(getClass().getResource("../resources/fondparametres.png"));
        } catch (IOException e) {
            System.out.println("erreur chargement image");
            e.printStackTrace();
        }
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(bgImage.getWidth(null), bgImage.getHeight(null)));
        panel = new JPanel(new BorderLayout());
        connPanel = new JPanel(new GridLayout(2,2));
        JPanel connPanelerr = new JPanel(new GridLayout(2, 1));
        boutonPanel = new JPanel(new GridLayout(1,2));

        panel.setOpaque(false);

        creerProfilLabel = VueParametre.styleJLabel("Créer un nouveau profil", 50);
        pseudo = new JTextField();
        pseudo.setForeground(Color.WHITE);
        mdp = new JPasswordField();
        labelp= VueParametre.styleJLabel("Pseudo :", 40);
        labelmdp = VueParametre.styleJLabel("Mot de passe :", 40);
        erreur  = VueParametre.styleJLabel("", 40);
        menu = new GradientButton("Menu");
        menu.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                accueil.frame.dispose();
                MenuAccueil.createAndShowGUI();
            }
        });
        submit = new GradientButton("Entrer");
        submit.addActionListener(event ->{
        if (!(pseudo.getText() != null && !pseudo.getText().isEmpty())) {
            erreur.setText("il n'y a pas de pseudo");
        } else if(!(mdp.getText() != null && !mdp.getText().isEmpty())) {
            erreur.setText("il n'y a pas de mdp");
        } else if(mdp.getText().length() <6) {
            erreur.setText("Le mot de passe est trop court");
        } else {
            File file = new File("profils/" + pseudo.getText());
            if(file.exists()) {
                erreur.setText("Il y a déjà un profil "+pseudo.getText());
            } else {
                Fichiers.ajouterFichier(file.getPath());
                Fichiers.ajouterTxt(mdp.getText(), file.getPath());
                Fichiers.ajouterTxt("Monnaie : 0\n", file.getPath());
                Fichiers.ajouterTxt("Balle :\nSkin 1 : true\nSkin 2 : false\nSkin 3 : false\nSkin 4 : false\nSkin 5 : false\nSkin 6 : false\nSkin 7 : false\nSkin 8 : false\nSkin 9 : false\n", file.getPath());
                Fichiers.ajouterTxt("Skin rider : false\nFond :\nSkin 1 : true\nSkin 2 : false\nSkin 3 : false\nSkin 4 : false\nSkin 5 : true\nSkin 6 : false\nSkin 7 : false\nSkin 8 : false\n", file.getPath());
                mdp.setText("");
                erreur.setText("Votre profil a bien été créé");
                pseudo.setText("");
            }
        }
    });

        mdp.setOpaque(false);
        mdp.setBorder(BorderFactory.createEmptyBorder());
        mdp.setHorizontalAlignment(JLabel.CENTER);
        mdp.setOpaque(false);
        pseudo.setOpaque(false);
        pseudo.setBorder(BorderFactory.createEmptyBorder());
        pseudo.setHorizontalAlignment(JLabel.CENTER);
        pseudo.setFont(new Font("Upheaval TT (BRK)", Font.BOLD, 40));
        labelp.setHorizontalAlignment(JLabel.CENTER);
        labelmdp.setHorizontalAlignment(JLabel.CENTER);
        menu.setOpaque(false);
        submit.setOpaque(false);
        labelmdp.setOpaque(false);
        labelp.setOpaque(false);
        erreur.setOpaque(false);
        
        connPanel.add(labelp);
        connPanel.add(pseudo);
        connPanel.add(labelmdp);
        connPanel.add(mdp);
        connPanelerr.add(connPanel);
        connPanelerr.add(erreur);
        boutonPanel.add(menu);
        boutonPanel.add(submit);
        boutonPanel.setOpaque(false);
        connPanel.setOpaque(false);
        connPanelerr.setOpaque(false);
        panel.add(boutonPanel, BorderLayout.SOUTH);
        panel.add(connPanelerr, BorderLayout.CENTER);
        panel.add(creerProfilLabel, BorderLayout.NORTH);
        add(panel);
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), null);
    }

}
