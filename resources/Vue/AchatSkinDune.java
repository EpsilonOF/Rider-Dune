package Vue;

import java.util.LinkedList;

import javax.swing.*;

import Model.Conn;
import Model.Fichiers;

import java.awt.*;
import java.awt.event.*;

import java.io.IOException;
import javax.imageio.ImageIO;

public class AchatSkinDune extends JPanel {
    public JPanel panel, panelCenter, panelSkin;
    public JLabel titre, titreSkin;
    public Image bgImage, coinImage;
    public GradientButton retour, menu;
    public JFrame frame;
    public LinkedList<Image> listeSkin;
    public GradientButton [] buttonTab;
    public boolean louka = false;

    public AchatSkinDune(MenuAccueil accueil){
        try{
            bgImage = ImageIO.read(getClass().getResource("../resources/fondparametre.png"));
            coinImage = ImageIO.read(getClass().getResource("../resources/coin.png"));
        }catch(IOException e){
            System.out.println("erreur chargement image");
        }
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(bgImage.getWidth(null), bgImage.getHeight(null)));
        buttonTab = new GradientButton[9];
        for(int i=0; i<9; i++){
            buttonTab[i] = new GradientButton("");
        }
        listeSkin = new LinkedList<Image>();
        frame = new JFrame("Achat skin dune");
        panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panelCenter = new JPanel(new GridLayout(3,1));
        panelCenter.setOpaque(false);
        // titre
        titreSkin = VueParametre.styleJLabel("Modifier le skin de balle", 70);
        titreSkin.setHorizontalAlignment(JLabel.CENTER);
        titreSkin.setOpaque(false);
        panel.add(titreSkin, BorderLayout.NORTH);
        // panel skin
        ajouterSkinPanel("../resources/blanc.png", 100, 0);
        ajouterSkinPanel("../resources/foot.png", 100, 1);
        ajouterSkinPanel("../resources/interdit.png", 50, 2);
        ajouterSkinPanel("../resources/pokeball.png", 300, 3);
        ajouterSkinPanel("../resources/pearl.png", 500, 4);
        ajouterSkinPanel("../resources/plague.png", 500, 5);
        ajouterSkinPanel("../resources/up.png", 500, 6);
        ajouterSkinPanel("../resources/coin.png", 500, 7);
        if(loukaOn()) ajouterSkinPanel("../resources/louka.png", 500, 8);
        // bouton retour
        retour = new GradientButton("Retour");
        retour.setOpaque(false);
        retour.setColors(Color.WHITE, Color.WHITE);
        retour.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
                int height = (int)dimension.getHeight();
                int width = (int)dimension.getWidth();
                frame = (JFrame) SwingUtilities.getWindowAncestor(AchatSkinDune.this);
                frame.setSize(width, height);
                frame.setContentPane(new VueBoutique(accueil));
                frame.setVisible(true);
                frame.setResizable(false);
                frame.setLocation(0, 0);
                frame.remove(panel);
            }
        });
        // bouton menu
        menu = new GradientButton("Menu");
        menu.setOpaque(false);
        menu.setColors(Color.WHITE, Color.WHITE);
        menu.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame = (JFrame) SwingUtilities.getWindowAncestor(AchatSkinDune.this);
                frame.setSize(728, 410);
                frame.setContentPane(new MenuAccueil());
                frame.setVisible(true);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.remove(panel);
            }
        });
        JPanel south = new JPanel(new GridLayout(1,2));
        south.setOpaque(false);
        south.add(retour);
        south.add(menu);
        panel.add(south, BorderLayout.SOUTH);
        panel.add(panelCenter, BorderLayout.CENTER);
        JLabel argent = VueParametre.styleJLabel("     "+ Conn.argent(), 70);
        argent.setForeground(Color.WHITE);
        argent.setOpaque(false);
        argent.setVerticalAlignment(JLabel.TOP);
        panel.add(argent, BorderLayout.WEST);
        add(panel);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), null);
        g.drawImage(coinImage, 0, 15, 100, 100, null);
        int y = 50;
        for(int i=0; i<listeSkin.size(); i++){
            if(i>0 && i%3==0) {
                y += getHeight()/3;
            }
            g.drawImage(listeSkin.get(i), -300+(getWidth()*(i%3+1)-150)/3, y, 150, 150, null);
        }
    }
    public void ajouterSkinPanel(String nom, int prix, int buttonNum){
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);
        try{
            listeSkin.add(ImageIO.read(getClass().getResource(nom)));
        }catch(IOException e){
            System.out.println("erreur chargement image");
        }
        if(Fichiers.readLigne(4+buttonNum, Conn.fichier).equals("Skin "+(buttonNum+1)+" : false")){
            buttonTab[buttonNum] = new GradientButton("Acheter : "+prix);
            buttonTab[buttonNum].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    if(Conn.argent()>=prix){
                        Conn.gagne(-prix);
                        revalidate();
                        repaint(); 
                        try{
                            Fichiers.remplacerLigne(4+buttonNum, "Skin "+(buttonNum+1)+" : true", Conn.fichier);
                        }catch(IOException e1){
                            System.out.println("erreur chargement fichier");
                        }
                        buttonTab[buttonNum].setText("Equiper");
                        if(!louka && loukaOn()){
                            louka = true;
                            ajouterSkinPanel("../resources/louka.png", 10000, 4);
                            try{
                                Fichiers.remplacerLigne(12, "Skin 12 : true", Conn.fichier);
                            }catch(IOException e1){
                                System.out.println("erreur chargement fichier");
                            }
                            buttonTab[8].addActionListener(new ActionListener(){
                                public void actionPerformed(ActionEvent e){
                                    equiper(nom, 8);
                                    revalidate();
                                    repaint();
                                }
                            });
                            try{
                                Fichiers.remplacerLigne(12, "Skin 9 : true", Conn.fichier);
                            }catch(IOException e1){
                                System.out.println("erreur chargement fichier");
                            }
                        }
                        buttonTab[buttonNum].removeActionListener(this);
                        buttonTab[buttonNum].addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e){
                                equiper(nom, buttonNum);
                                revalidate();
                                repaint();
                            }
                        });
                        revalidate();
                        repaint();
                    }
                }
            });
            buttonTab[buttonNum].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    equiper(nom, buttonNum);
                    revalidate();
                    repaint();
                }
            });
        }else{
            if(Conn.skinActuelBalle.equals(nom)) {
                buttonTab[buttonNum] = new GradientButton("Equipé");
                revalidate();
                repaint();
            }
            else{
                buttonTab[buttonNum] = new GradientButton("Equiper");
                revalidate();
                repaint();
            }
        }
        buttonTab[buttonNum].setOpaque(false);
        buttonTab[buttonNum].setColors(Color.WHITE, Color.WHITE);
        if(buttonTab[buttonNum].getText().equals("Equiper") || buttonTab[buttonNum].getText().equals("Equipé")) {
            buttonTab[buttonNum].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    equiper(nom, buttonNum);
                    revalidate();
                    repaint();
                }
            });
        }
        p.add(buttonTab[buttonNum], BorderLayout.SOUTH);
        if(Conn.skinActuelBalle.equals(nom)) {
            buttonTab[buttonNum].setText("Equipé");
            revalidate();
            repaint();
        }
        panelCenter.add(p);
    }
    public void equiper(String nom, int buttonNum){
        Conn.skinActuelBalle = nom;
        buttonTab[buttonNum].setText("Equipé");
        for(int i=0;i<buttonTab.length;i++){
            if(buttonTab[i].getText().equals("Equipé") && i!=buttonNum) buttonTab[i].setText("Equiper");
        }
        revalidate();
        repaint();
    }
    public boolean loukaOn(){
        for(int i=0;i<buttonTab.length-1;i++){
            if(Fichiers.readLigne(4+i, Conn.fichier).equals("Skin "+(i+1)+" : false")) {
                return false;
            }
        }
        return true;
    }
}
