package Vue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import Model.Fichiers;
import Model.Conn;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Connexion extends JPanel {
    private GradientButton menu, submit, creerProfil;
    private JLabel labelp, labelmdp, erreur,connexionLabel;
    private JTextField pseudo;
    private JPasswordField mdp;
    private JPanel panel, connPanel, connPanel1, connPanel2, boutonPanel;
    private Image bgImage;
    


    public Connexion(MenuAccueil accueil) {
        try {
            bgImage = ImageIO.read(getClass().getResource("../resources/fondparametres.png"));
        } catch (IOException e) {
            System.out.println("erreur chargement image");
            e.printStackTrace();
        }
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(bgImage.getWidth(null), bgImage.getHeight(null)));
        panel = new JPanel(new BorderLayout());
        connPanel = new JPanel(new GridLayout(2, 1));
        connPanel1 = new JPanel(new GridLayout(2, 2));
        connPanel2 = new JPanel(new GridLayout(2, 1));
        boutonPanel = new JPanel(new GridLayout(1,2));

        panel.setOpaque(false);

        connexionLabel = VueParametre.styleJLabel("Connexion", 50);
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
        creerProfil = new GradientButton("Créer un nouveau profil");
        creerProfil.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JFrame frame = new JFrame();
                frame = (JFrame) SwingUtilities.getWindowAncestor(Connexion.this);
                frame.setSize(728, 410);
                frame.setContentPane(new CreerProfil(accueil));
                frame.setVisible(true);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setTitle("Créer un profil");                
            }
        });
        submit = new GradientButton("Valider");
        submit.addActionListener(event ->{
            if (!(pseudo.getText() != null && !pseudo.getText().isEmpty())) {
                erreur.setText("il n'y a pas de pseudo");

            }else if(!(mdp.getText() != null && !mdp.getText().isEmpty())) {
                erreur.setText("il n'y a pas de mdp");
            }else{
                if(!(Fichiers.readLigne(1,"profils/"+pseudo.getText()).equals(mdp.getText()))){
                    erreur.setFont(new Font("Upheaval TT (BRK)", Font.BOLD, 27));
                    erreur.setText("Le mot de passe\n ou le profil \nest incorrect");
                }else{
                    mdp.setText("");;
                    erreur.setText("Bienvenue "+ pseudo.getText()+'!');
                    Conn.connexion(pseudo.getText());
                    pseudo.setText("");
                }
            }
        });

        mdp.setOpaque(false);
        mdp.setBorder(BorderFactory.createEmptyBorder());
        mdp.setHorizontalAlignment(JLabel.CENTER);
        mdp.setOpaque(false);
        creerProfil.setOpaque(false);
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

        connPanel1.add(labelp);
        connPanel1.add(pseudo);
        connPanel1.add(labelmdp);
        connPanel1.add(mdp);
        connPanel2.add(erreur);
        connPanel2.add(creerProfil);
        connPanel1.setOpaque(false);
        connPanel2.setOpaque(false);
        connPanel.add(connPanel1);
        connPanel.add(connPanel2);
        boutonPanel.add(menu);
        boutonPanel.add(submit);
        boutonPanel.setOpaque(false);
        connPanel.setOpaque(false);
        panel.add(boutonPanel, BorderLayout.SOUTH);
        panel.add(connPanel, BorderLayout.CENTER);
        JPanel panelNorth = new JPanel(new GridLayout(1,2));
        panelNorth.add(connexionLabel);
        panelNorth.setOpaque(false);
        panel.add(panelNorth, BorderLayout.NORTH);
        add(panel);
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), null);
    }

}
