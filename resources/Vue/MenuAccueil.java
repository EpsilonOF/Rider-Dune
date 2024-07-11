package Vue;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import Controleur.ControllerDune;
import Controleur.ControllerRider;
import Model.Conn;
import Model.Terrain;
import Model.VehiculeDune;
import Model.VehiculeRider;

public class MenuAccueil extends JPanel {
    private Image bgImage;
    public JFrame frame; // nécessaire pour le retour au menu dans Controller
    private Clip clip;

    public MenuAccueil() {
        try {
            bgImage = ImageIO.read(getClass().getResource("../resources/rider.jpg"));
            File audioFile = new File("./resources/sonaccueil.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);       
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-25.0f);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        setLayout(new GridLayout(1, 1));
        setPreferredSize(new Dimension(bgImage.getWidth(null), bgImage.getHeight(null)));
        GradientButton playDune = new GradientButton("Dune");
        playDune.setOpaque(false);
        playDune.setBorderPainted(false);
        GradientButton playRider = new GradientButton("Rider");
        playRider.setOpaque(false);
        playRider.setBorderPainted(false);
        MenuAccueil accueil = this; // nécessaire pour le retour au menu dans Controller
        JPanel content = new JPanel(new GridLayout(3, 3));
        content.setOpaque(false);
        content.add(playRider);
        content.add(new JLabel(""));
        playRider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (clip.isRunning()) {
                    clip.stop();
                }
                //initailisation du jeu rider
                Terrain sol = new Terrain();
                sol.initialisationRider(0.0);
                VehiculeRider rider = new VehiculeRider(sol);
                VueRider panel = new VueRider(rider, sol);
                ControllerRider controller = new ControllerRider(rider, panel, sol, accueil);
                Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
                int height = (int)dimension.getHeight();
                int width = (int)dimension.getWidth();
                frame = (JFrame) SwingUtilities.getWindowAncestor(MenuAccueil.this);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setTitle("Rider");
                frame.setSize(width, height);
                frame.setLocation(0, 0);
                frame.add(panel);
                frame.setVisible(true);
                frame.remove(content);
            }
        });
                            //Paramètre
        GradientButton parametre = new GradientButton("Paramètres");
        parametre.setFont(new Font("Upheaval TT (BRK)", Font.BOLD, 39));
        parametre.setOpaque(false);
        parametre.setBorderPainted(true);
        parametre.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (clip.isRunning()) {
                    clip.stop();
                }
                Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
                int height = (int)dimension.getHeight();
                int width = (int)dimension.getWidth();
                frame = (JFrame) SwingUtilities.getWindowAncestor(MenuAccueil.this);
                frame.setSize(width, height);
                frame.setContentPane(new VueParametre(MenuAccueil.this));
                frame.setVisible(true);
                frame.setResizable(false);
                frame.setLocation(0, 0);
                frame.remove(content);
            }
        });
        content.add(parametre);
        content.add(new JLabel(""));
        if(Conn.estConnecte){
            GradientButton deconnexion = new GradientButton("Déconnexion");
            deconnexion.setFont(new Font("Upheaval TT (BRK)", Font.BOLD, 35));
            deconnexion.setOpaque(false);
            deconnexion.setBorderPainted(true);
            deconnexion.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    if (clip.isRunning()) {
                        clip.stop();
                    }
                    Conn.deconnexion();
                    frame = (JFrame) SwingUtilities.getWindowAncestor(MenuAccueil.this);
                    frame.setSize(728, 410);
                    frame.setContentPane(new MenuAccueil());
                    frame.setVisible(true);
                    frame.setResizable(true);
                    frame.setLocationRelativeTo(null);
                    frame.remove(content);
                }
            });
            content.add(deconnexion);
        }else{
                         //Connexion
            GradientButton connexion = new GradientButton("Connexion");
            connexion.setFont(new Font("Upheaval TT (BRK)", Font.BOLD, 39));
            connexion.setOpaque(false);
            connexion.setBorderPainted(true);
            connexion.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    if (clip.isRunning()) {
                        clip.stop();
                    }
                    frame = (JFrame) SwingUtilities.getWindowAncestor(MenuAccueil.this);
                    frame.setSize(728, 410);
                    frame.setContentPane(new Connexion(MenuAccueil.this));
                    frame.setVisible(true);
                    frame.setResizable(false);
                    frame.setLocationRelativeTo(null);

                    frame.remove(content);
                }
            });
            content.add(connexion);
        }
        content.add(new JLabel(""));
                           //Boutique
        GradientButton boutique = new GradientButton("Boutique");
        boutique.setFont(new Font("Upheaval TT (BRK)", Font.BOLD, 39));
        boutique.setOpaque(false);
        boutique.setBorderPainted(true);
        boutique.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (clip.isRunning()) {
                    clip.stop();
                }
                if(Conn.fichier.equals("profils/Invite")) {
                    frame = (JFrame) SwingUtilities.getWindowAncestor(MenuAccueil.this);
                    frame.setContentPane(new Connexion(MenuAccueil.this));
                    frame.setLocationRelativeTo(null);
                }else{
                    Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
                    int height = (int)dimension.getHeight();
                    int width = (int)dimension.getWidth();
                    frame = (JFrame) SwingUtilities.getWindowAncestor(MenuAccueil.this);
                    frame.setSize(width, height);
                    frame.setContentPane(new VueBoutique(MenuAccueil.this));
                    frame.setLocation(0, 0);
                }
                frame.setVisible(true);
                frame.setResizable(false);
                frame.remove(content);
            }
        });
        content.add(boutique);
        content.add(new JLabel(""));
        playDune.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (clip.isRunning()) {
                    clip.stop();
                }
                //Permet d'initialiser le jeu Dune
                Terrain sol = new Terrain();
                sol.initialisationDune();
                VehiculeDune dune = new VehiculeDune(sol);
                VueDune panel = new VueDune(dune, sol);
                ControllerDune controller = new ControllerDune(dune, panel, sol, accueil);
                Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
                int height = (int)dimension.getHeight();
                int width = (int)dimension.getWidth();
                frame = (JFrame) SwingUtilities.getWindowAncestor(MenuAccueil.this);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setTitle("Dune");
                frame.setSize(width, height);
                frame.setLocation(0, 0);
                frame.add(panel);
                frame.setVisible(true);
                frame.remove(content);
            }
        });
        JPanel aligneDune = new JPanel(new GridLayout(1,2));
        aligneDune.setOpaque(false);
        aligneDune.add(new JLabel(""));
        aligneDune.add(playDune);
        content.add(aligneDune);
        add(content);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
    }
 
    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Menu d'accueil");
        frame.setSize(728, 410);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new MenuAccueil());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
          } catch (Exception e) {
            e.printStackTrace();
          }
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
