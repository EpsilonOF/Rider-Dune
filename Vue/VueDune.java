package Vue;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import Model.Terrain;
import Model.Vehicule;
import Model.Explosion;
import Model.ParticuleDune;
import Model.Conn;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.awt.*;  


public class VueDune extends VueRider{
    public VueBoule vueBoule;
    public ParticuleDune trainee;
    public Image skin;


    public VueDune(Vehicule vehicule, Terrain sol) {
        super(vehicule, sol);
        this.vueBoule = new VueBoule(vehicule);
        this.vueSol = new VueSol(sol, vehicule, new Color(231, 207, 162), new Color(231, 207, 162) );
        this.trainee= new ParticuleDune(vehicule);



        try {
            fond = ImageIO.read(getClass().getResource(Conn.imageDune));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            skin = ImageIO.read(getClass().getResource(Conn.skinActuelBalle));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            argent = ImageIO.read(getClass().getResource("../resources/coin.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gd = (Graphics2D)(g);
        Font font = new Font("Upheaval TT (BRK)", Font.BOLD, 50);
        if(vehicule.meche > 0){
        g.drawImage(fond, 0, 0, getWidth(), getHeight(), this);
        g.setFont(font);
        
        g.setColor(Color.black);
        String s = String.valueOf(vehicule.x/500);
        g.drawString(s, 75, 100);
        vueSol.drawSol(gd);
        g.setColor(Color.WHITE);
        if(Vehicule.v >130 && vehicule.saut){
            g.setColor(Color.RED);
        }
        // Dessine une trainée de particule qui suit la balle
        for(int i = 1 ; i< trainee.particules.size()-1; i++){
            if(Vehicule.v >130 && vehicule.saut){
                g.setColor(new Color(255,255 -10*i ,30));
            }
            g.fillOval( trainee.particules.get(i).x -vehicule.x+185 , 472 - trainee.particules.get(i).y, i+5, i+5);
            
        }
        if(trainee.particules.size() >=1){
            gd.drawImage(skin, trainee.particules.get(trainee.particules.size()-1).x -vehicule.x+185 , 472 - trainee.particules.get(trainee.particules.size()-1).y, 30,30, null);
        }
        }
        // Déclenche l'explosion quand le véhicule n'a plus de vie
        if(vehicule.meche ==0){
            vueExplosion.explosionJaune = new Explosion(vehicule);
            vueExplosion.explosionRouge = new Explosion(vehicule);
            vueSol.drawSol(gd);
            vehicule.meche--;
            try {
                File audioFile = new File("./resources/explosion.wav");
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);       
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-15.0f);
                clip.start();
                b=true;
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                ex.printStackTrace();
            }
        }
    }
}
