package Vue;

import java.util.LinkedList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.IOException;
import javax.imageio.ImageIO;

import Model.Conn;
import Model.Fichiers;

public class AchatFond extends JPanel {
    public JPanel panel, panelGen, panelCenter, panelCenter1, panelCenter2;
    public JLabel titre, titreSkin;
    public Image bgImage, coinImage;
    public GradientButton retour, menu;
    public JFrame frame;
    public LinkedList<Image> listeSkin;
    public GradientButton [] buttonTab;

    public AchatFond(MenuAccueil accueil){
        try{
            bgImage = ImageIO.read(getClass().getResource("../resources/fondparametre.png"));
            coinImage = ImageIO.read(getClass().getResource("../resources/coin.png"));
        }catch(IOException e){
            System.out.println("erreur chargement image");
        }
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(bgImage.getWidth(null), bgImage.getHeight(null)));
        buttonTab = new GradientButton[9];
        for(int i=0; i<8; i++){
            buttonTab[i] = new GradientButton("");
        }
        listeSkin = new LinkedList<Image>();
        frame = new JFrame("Achat fond");
        panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panelGen = new JPanel(new BorderLayout());
        panelGen.setOpaque(false);
        panelCenter = new JPanel(new GridLayout(1,2));
        panelCenter.setOpaque(false);
        panelCenter1 = new JPanel(new GridLayout(2,2));
        panelCenter1.setOpaque(false);
        panelCenter2 = new JPanel(new GridLayout(2,2));
        panelCenter2.setOpaque(false);
        // titre
        titreSkin = VueParametre.styleJLabel("Modifier le fond d'écran", 70);
        titreSkin.setHorizontalAlignment(JLabel.CENTER);
        titreSkin.setOpaque(false);
        panel.add(titreSkin, BorderLayout.NORTH);
        // panel titre skin
        JLabel titreRider = new JLabel("Rider");
        titreRider.setHorizontalAlignment(JLabel.CENTER);
        titreRider.setFont(new Font("Upheaval TT (BRK)", Font.BOLD, 30));
        titreRider.setForeground(Color.WHITE);
        JLabel titreDune = new JLabel("Dune");
        titreDune.setHorizontalAlignment(JLabel.CENTER);
        titreDune.setFont(new Font("Upheaval TT (BRK)", Font.BOLD, 30));
        titreDune.setForeground(Color.WHITE);
        JPanel panelTitre = new JPanel(new GridLayout(1,2));
        panelTitre.setOpaque(false);
        panelTitre.add(titreRider);
        panelTitre.add(titreDune);
        panelGen.add(panelTitre, BorderLayout.NORTH);
        // panel skin
        ajouterSkinPanel("../resources/rider1.png", 500, 0);
        ajouterSkinPanel("../resources/rider2.png", 500, 1);
        ajouterSkinPanel("../resources/dune1.png", 500, 4);
        ajouterSkinPanel("../resources/dune2.png", 500, 5);
        ajouterSkinPanel("../resources/rider3.png", 500, 2);
        ajouterSkinPanel("../resources/rider4.png", 500, 3);
        ajouterSkinPanel("../resources/dune3.png", 500, 6);
        ajouterSkinPanel("../resources/dune4.png", 500, 7);

        // bouton retour
        retour = new GradientButton("Retour");
        retour.setOpaque(false);
        retour.setColors(Color.WHITE, Color.WHITE);
        retour.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
                int height = (int)dimension.getHeight();
                int width = (int)dimension.getWidth();
                frame = (JFrame) SwingUtilities.getWindowAncestor(AchatFond.this);
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
                frame = (JFrame) SwingUtilities.getWindowAncestor(AchatFond.this);
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
        panelCenter.add(panelCenter1);
        panelCenter.add(panelCenter2);
        panelGen.add(panelCenter, BorderLayout.CENTER);
        panel.add(panelGen, BorderLayout.CENTER);
        JLabel argent = VueParametre.styleJLabel("    ", 70);//"     "+ Conn.argent()
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
        int y = 250;
        for(int i=0; i<listeSkin.size(); i++){
            if(i>0 && i%4==0) {
                y += getHeight()/3;
            }
            g.drawImage(listeSkin.get(i), -350+(getWidth()*(i%4+1)-350)/4, y, 400, 200, null);
        }
        Font font = new Font("Upheaval TT (BRK)", Font.BOLD, 70);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(Conn.argent()),120, 80);
    }
    public void ajouterSkinPanel(String nom, int prix, int buttonNum){
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);
        try{
            listeSkin.add(ImageIO.read(getClass().getResource(nom)));
        }catch(IOException e){
            System.out.println("erreur chargement image");
        }
        if(Fichiers.readLigne(15+buttonNum, Conn.fichier).equals("Skin "+(buttonNum+1)+" : false")){
            buttonTab[buttonNum] = new GradientButton("Acheter : "+prix);
            buttonTab[buttonNum].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    if(Conn.argent()>=prix){
                        Conn.gagne(-prix);
                        revalidate();
                        repaint();
                        try{
                            Fichiers.remplacerLigne(15+buttonNum, "Skin "+(buttonNum+1)+" : true", Conn.fichier);
                        }catch(IOException e1){
                            System.out.println("erreur chargement fichier");
                        }
                        buttonTab[buttonNum].setText("Equiper");
                        buttonTab[buttonNum].removeActionListener(this);
                        buttonTab[buttonNum].addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e){
                                if(buttonNum<4) equiper(nom, buttonNum, true);
                                else equiper(nom, buttonNum, false);
                                revalidate();
                                repaint();
                            }
                        });
                    }
                }
            });
        }else{
            if(Conn.imageRider.equals(nom) || Conn.imageDune.equals(nom)) {
                buttonTab[buttonNum] = new GradientButton("Equipé");
                revalidate();
                repaint();
            }else{
                buttonTab[buttonNum] = new GradientButton("Equiper");
                revalidate();
                repaint();
                buttonTab[buttonNum].addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        if(buttonNum<4) equiper(nom, buttonNum, true);
                        else equiper(nom, buttonNum, false);
                    }
                });
            }
        }
        buttonTab[buttonNum].setOpaque(false);
        buttonTab[buttonNum].setColors(Color.WHITE, Color.WHITE);
        p.add(buttonTab[buttonNum], BorderLayout.SOUTH);
        if(buttonNum<4) panelCenter1.add(p);
        else panelCenter2.add(p);
    }
    public void equiper(String nom, int buttonNum, boolean rider){
        if(rider) Conn.imageRider = nom;
        else Conn.imageDune = nom;
        buttonTab[buttonNum].setText("Equipé");
        if(rider){
            for(int i=0;i<4;i++){
                if(buttonTab[i].getText().equals("Equipé") && i!=buttonNum) buttonTab[i].setText("Equiper");
            }
        }else{
            for(int i=4;i<8;i++){
                if(buttonTab[i].getText().equals("Equipé") && i!=buttonNum) buttonTab[i].setText("Equiper");
            }
        }
        revalidate();
        repaint();
    }
}
