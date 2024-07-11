package Controleur;
import java.awt.event.*;

import javax.swing.Timer;
import javax.sound.sampled.Clip;

import Model.Terrain;
import Model.Vehicule;
import Vue.VueRider;
import Vue.GradientButton;
import Vue.MenuAccueil;

public class Controller implements ActionListener{
    public Vehicule vehicule;
    public VueRider panel;
    public Terrain sol;
    public Timer timer;
    public double timerEnd = 0;
    public boolean avancer, pause = false;
    public GradientButton continuer, menu, quitter;
    public static int touche = 32;
    public Clip clip;
    public Clip clip2;
    public Clip clip3;
    public boolean isPlayingStartupSound = false;
    public boolean isPlayingBrakingSound = false;

    public Controller(Vehicule vehicule, VueRider panel, Terrain sol, MenuAccueil accueil) {
        this.vehicule = vehicule;
        this.sol = sol;
        this.panel = panel;
        menu = new GradientButton("Menu"); // Bouton pour revenir au menu d'accueil
        menu.setVisible(false);
        menu.setOpaque(false);
        menu.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                accueil.frame.dispose();
                MenuAccueil.createAndShowGUI();
            }
        });
        quitter = new GradientButton("Quitter"); // Bouton pour quitter le jeu
        quitter.setVisible(false);
        quitter.setOpaque(false);
        quitter.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
        continuer = new GradientButton("Continuer"); // Bouton pour continuer la partie en cours
        continuer.setVisible(false);
        continuer.setOpaque(false);
        continuer.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                pause = false;
                modePause(false);
            }
        });
        panel.add(menu);
        panel.add(quitter);
        panel.add(continuer);
        panel.setFocusable(true);
        
        // Création de la représentation du temps
        timer = new Timer(10, this);
        timer.start();
    }


    @Override
    public void actionPerformed(ActionEvent e) {}
    
    // Fonction permettant d'afficher ou de cacher les différents boutons du mode Pause (également arrête ou relance le timer)
    public void modePause(boolean b){
        if(b) timer.stop();
        continuer.setVisible(b);
        menu.setVisible(b);
        quitter.setVisible(b);
        if(!b) timer.start();
    }
}