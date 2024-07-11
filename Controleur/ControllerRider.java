package Controleur;

import java.awt.event.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

import java.io.File;
import java.io.IOException;

import Model.Terrain;
import Model.Vehicule;
import Model.VehiculeRider;
import Model.Conn;
import Vue.VueRider;
import Vue.GradientButton;
import Vue.MenuAccueil;

public class ControllerRider extends Controller{
    
    public GradientButton payerVie;

    public ControllerRider(VehiculeRider vehicule, VueRider panel, Terrain sol, MenuAccueil accueil) {
        super(vehicule, panel, sol, accueil);
        payerVie = new GradientButton("1000$ pour une vie ?"); // Bouton pour continuer la partie en cours
        payerVie.setVisible(false);
        payerVie.setOpaque(false);
        payerVie.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (Conn.argent() >= 1000) {
                    Conn.gagne(-1000);
                    vehicule.meche++;
                    panel.repaint();
                }else{
                    payerVie.setText("Pas assez d'argent...");
                }
            }
        });
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == touche) {
                    avancer = true;
                    if (vehicule.meche > 0 && timer.isRunning()) {
                        if (!isPlayingStartupSound) {
                            try {
                                if (isPlayingBrakingSound) {
                                    clip3.stop();
                                    isPlayingBrakingSound = false;
                                }
                                File audioFile = new File("./resources/demarrage.wav");
                                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
                                clip = AudioSystem.getClip();
                                clip.open(audioStream);
                                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                                gainControl.setValue(-15.0f);
                                clip.start();
                
                                // Ajouter un LineListener pour vérifier si le premier clip est terminé
                                clip.addLineListener(new LineListener() {
                                    @Override
                                    public void update(LineEvent event) {
                                        if (event.getType() == LineEvent.Type.STOP) {
                                            try {
                                                File audioFile2 = new File("./resources/roule.wav");
                                                AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
                                                clip2 = AudioSystem.getClip();
                                                clip2.open(audioStream2);
                                                FloatControl gainControl2 = (FloatControl) clip2.getControl(FloatControl.Type.MASTER_GAIN);
                                                gainControl2.setValue(-25.0f);
                                                clip2.loop(Clip.LOOP_CONTINUOUSLY);
                                            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                                                ex.printStackTrace();
                                            }
                                        }
                                    }
                                });
                                isPlayingStartupSound = true;
                            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_P) {
                    if(timerEnd<15){
                        pause = !pause;
                        modePause(pause);
                    }
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == touche) {
                    avancer = false;
                    clip.stop();
                    if (clip2!=null) {
                        clip2.stop();
                    }
                    if (vehicule.meche>0 && timer.isRunning()) {
                    if (!isPlayingBrakingSound) {
                    try {
                        File audioFile = new File("./resources/freinage.wav");
                        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
                        clip3 = AudioSystem.getClip();
                        clip3.open(audioStream);
                        FloatControl gainControl = (FloatControl) clip3.getControl(FloatControl.Type.MASTER_GAIN);
                        gainControl.setValue(-15.0f);
                        clip3.start();
                        isPlayingBrakingSound=true;
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                        ex.printStackTrace();
                    }
                    isPlayingStartupSound=false;
                }
            }
        }
            }
        });
        panel.add(payerVie);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // Lorsqu'on a plus de vie, faire exploser la voiture et afficher le menu de fin
        panel.fumee.update();
        panel.vent.update();
        panel.repaint();
        if(vehicule.meche <0){
            if (clip3!=null) {
                clip3.stop();
            }
            if (clip!=null) {
                clip.stop();
            }
            if (clip2!=null) {
                clip2.stop();
            }
            timerEnd+=0.1;
            panel.vehicule.t += 0.1;
            if(timerEnd <15){
            panel.vueExplosion.explosionJaune.update();
            panel.vueExplosion.explosionRouge.update();
            panel.repaint();
            }
        }else{
            // si on appuie sur Avancer
            panel.vehicule.t+=0.1;
            vehicule.move();
    
            if(avancer && vehicule.saut && vehicule.t >3){
                ((VehiculeRider)this.vehicule).rotation();
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
            if(clip2!=null) clip2.stop();
            modePause(true);
            continuer.setVisible(false);
            payerVie.setVisible(false);
        }
        if(vehicule.x +2200 >=sol.section){
            sol.initialisationRider(vehicule.x*0.0000001);
        }  
    }

    public void modePause(boolean b){
        if(b) timer.stop();
        if (clip3!=null) {
            clip3.stop();
        }
        if (clip!=null) {
            clip.stop();
        }
        if (clip2!=null) {
            clip2.stop();
        }
        continuer.setVisible(b);
        menu.setVisible(b);
        quitter.setVisible(b);
        payerVie.setVisible(b);
        if(!b) timer.start();
    }
}
