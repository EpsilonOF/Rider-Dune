package Controleur;
import java.awt.event.*;

import Model.Terrain;
import Model.Vehicule;
import Model.VehiculeDune;
import Vue.VueRider;
import Vue.MenuAccueil;
import Vue.VueDune;

public class ControllerDune extends Controller{

    VueDune panelDune;

    public ControllerDune(VehiculeDune vehicule, VueRider panel, Terrain sol, MenuAccueil accueil) {
        super( vehicule,  panel,  sol,  accueil);
        panelDune = (VueDune)panel;
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == touche) {
                    avancer = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_P) {
                    pause = !pause;
                    if(pause) modePause(true);
                    else modePause(false);
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                avancer = false;
                if(vehicule.saut){
                    return;
                }
                if (e.getKeyCode() == touche) {
                    vehicule.angle = Math.atan(vehicule.pente(vehicule.x));
                    vehicule.saut = true;
                    vehicule.t = 0;
                    isPlayingStartupSound=false;
                }
            }
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // Lorsqu'on a plus de vie, faire exploser la voiture et afficher le menu de fin
        if(vehicule.meche < 0){
            timerEnd+=0.1;
            Vehicule.v = 0;
            panel.vueExplosion.explosionJaune.update();
            panel.vueExplosion.explosionRouge.update();
            panel.repaint();
        }else{
            // si on appuie sur Avancer
            panel.vehicule.t+=0.1;
            vehicule.move();
            panel.vent.update();
            panel.repaint();
            panelDune.trainee.update();
            if (vehicule.saut){
                panel.fumee.update();
            }
            if(avancer && vehicule.saut && !vehicule.tombe){
                Vehicule.g = 50;
                vehicule.angle = Math.atan(vehicule.penteAir())+1;
                vehicule.setPosition(vehicule.x, vehicule.y);
                vehicule.t = 0;
                vehicule.tombe = true;
            }
            if(!vehicule.saut){
                if(avancer){
                    Vehicule.v -= vehicule.pente(vehicule.x);
                    if(Vehicule.v <100) Vehicule.v ++;
                }else{
                    Vehicule.v -= vehicule.pente(vehicule.x) + Vehicule.v*0.01 ;
                }
            }
        } 
        if(timerEnd>=15.0){
            modePause(true);
            continuer.setVisible(false);
        }
        if(vehicule.x +2200 >=sol.section){
            sol.initialisationDune();
        }
    }
}

