package Vue;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;

import Model.Terrain;
import Model.Vehicule;
import Model.Vent;
import Model.Conn;
import Model.Explosion;
import Model.Fumee;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.awt.*;  

public class VueRider extends JPanel{
    public Vehicule vehicule;
    public VueVehicule vueVehicule;
    public VueSol vueSol;
    public Terrain sol;
    public Fumee fumee;
    public Vent vent;
    public VueExplosion vueExplosion;
    public File audioFile = new File("../resources/explosion.mp3");
    public boolean b=false;
    public boolean revive = false;
    public Image fond;
    public Image argent;
    public int score;


    public VueRider(Vehicule vehicule, Terrain sol) {
        this.vehicule = vehicule;
        this.sol = sol;
        this.fumee = new Fumee(vehicule);
        this.vent = new Vent(vehicule);
        this.vueVehicule = new VueVehicule(vehicule);
        this.vueSol = new VueSol(sol, vehicule, new Color(250, 250, 13), new Color( 33, 44,105) );
        this.vueExplosion = new VueExplosion(null, null, vehicule);
        setBackground(Color.WHITE);
        try {
            fond = ImageIO.read(getClass().getResource(Conn.imageRider));
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
        g.drawImage(fond, 0, 0, getWidth(), getHeight(), this);
        Graphics2D gd = (Graphics2D) g;
        Font font = new Font("Upheaval TT (BRK)", Font.BOLD, 50);
        
        gd.setColor(Color.WHITE);
        
        if(vehicule.meche > 0){
            for(int i = 0 ; i< fumee.particules.size(); i++){
                int r = (int)(i * 255.0 / (fumee.particules.size()-1));
                g.setColor(new Color( 255 -r,255 -r, 255 -r));
                g.fillRect( fumee.particules.get(fumee.particules.size() - i-1).x - vehicule.x0+200  - (int)vehicule.vitesseX ,465 - fumee.particules.get( fumee.particules.size() - i-1).y, 4,4 );
            }

            gd.setColor(Color.WHITE);
            g.setFont(font);
            String s = String.valueOf(vehicule.score);
            g.drawString(s, WIDTH/2, 100);
            g.drawString(String.valueOf(Conn.argent()), getWidth()/2, 100);
            g.drawImage(argent, getWidth()/2-60 , 60, 50, 50, this);

            vueSol.drawSol(gd);
            vueVehicule.draw(gd);
            
            gd.setColor(Color.BLACK);
            for(int i = 0; i<vent.particules.size(); i++){
                int r = (int)(i * 255.0 / (vent.particules.size()-1));
                g.setColor(new Color( r,  r, r));
                int random = (int)(Math.random()*20);
                g.drawLine( vent.particules.get(i).p1.x- vehicule.x0+200  - (int)vehicule.vitesseX - random, 470 - vent.particules.get(i).p1.y, vent.particules.get(i).p2.x - vehicule.x0+200  - (int)vehicule.vitesseX -random, 500 - vent.particules.get(i).p2.y);
            }

        }
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
        if(vehicule.meche < 0 || (revive && vehicule.meche > 1000)){
            vueExplosion.draw(gd);
            vueSol.drawSol(gd);
        }
    }
    
}