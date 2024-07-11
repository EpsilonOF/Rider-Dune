package Vue;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

import java.io.IOException;
import javax.imageio.ImageIO;

import Model.Conn;
import Model.Fichiers;

public class AchatSkinRider extends JPanel {

    public JPanel panel, panelCenter, panelRVB;
    public JLabel titre, titreCouleur;
    public Image bgImage, coinImage;
    public GradientButton retour, menu;
    public JFrame frame;

    public AchatSkinRider(MenuAccueil accueil){
        try{
            bgImage = ImageIO.read(getClass().getResource("../resources/fondparametre.png"));
            coinImage = ImageIO.read(getClass().getResource("../resources/coin.png"));
        }catch(IOException e){
            System.out.println("erreur chargement image");
        }
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(bgImage.getWidth(null), bgImage.getHeight(null)));
        frame = new JFrame("Achat de skin");
        panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        JPanel panelGen = new JPanel(new BorderLayout());
        panelGen.setOpaque(false);
        panelGen.setBackground(null);
        panelCenter = new JPanel(new GridLayout(2,1));
        panelCenter.setOpaque(false);
        // titre
        titreCouleur = VueParametre.styleJLabel("Modifier les couleurs", 70);
        titreCouleur.setHorizontalAlignment(JLabel.CENTER);
        titreCouleur.setOpaque(false);
        panel.add(titreCouleur, BorderLayout.NORTH);
        // titre des couleurs (1 et 2)
        JPanel panelTitreCouleur = new JPanel(new GridLayout(1,2));
        panelTitreCouleur.setOpaque(false);
        JLabel titreCouleur1 = VueParametre.styleJLabel("Couleur 1", 40);
        JLabel titreCouleur2 = VueParametre.styleJLabel("Couleur 2", 40);
        panelTitreCouleur.add(titreCouleur1);
        panelTitreCouleur.add(titreCouleur2);
        panelGen.add(panelTitreCouleur, BorderLayout.NORTH);
        // sliders pour changer les couleurs
        panelRVB = new JPanel(new GridLayout(3,4));
        panelRVB.setOpaque(false);
        JSlider [] couleur = new JSlider[6];
        JSlider rouge1 = new JSlider(JSlider.HORIZONTAL, 0, 255, VueVehicule.couleurCode[0]); rouge1.setName("Rouge :");
        JSlider rouge2 = new JSlider(JSlider.HORIZONTAL, 0, 255, VueVehicule.couleurCode[1]); rouge2.setName("Rouge :");
        JSlider vert1 = new JSlider(JSlider.HORIZONTAL, 0, 255, VueVehicule.couleurCode[2]); vert1.setName("Vert :");
        JSlider vert2 = new JSlider(JSlider.HORIZONTAL, 0, 255, VueVehicule.couleurCode[3]); vert2.setName("Vert :");
        JSlider bleu1 = new JSlider(JSlider.HORIZONTAL, 0, 255, VueVehicule.couleurCode[4]); bleu1.setName("Bleu :");
        JSlider bleu2 = new JSlider(JSlider.HORIZONTAL, 0, 255, VueVehicule.couleurCode[5]); bleu2.setName("Bleu :");
        couleur[0] = rouge1; couleur[1] = rouge2; couleur[2] = vert1; couleur[3] = vert2; couleur[4] = bleu1; couleur[5] = bleu2;
        for(JSlider j : couleur){
            j.setMajorTickSpacing(50);
            j.setPaintTicks(true);
            j.setPaintLabels(true);
            j.setOpaque(false);
            j.setForeground(Color.WHITE);
            j.setValueIsAdjusting(false);
        }
        for(int i=0;i<6;i++){ changerCouleur(couleur[i], i); }
        panelCenter.add(panelRVB);
        // bouton achat
        GradientButton achat = new GradientButton("        Acheter : 5000");
        achat.setFont(new Font("Upheaval TT (BRK)", Font.BOLD, 50));
        achat.setColors(Color.WHITE, Color.WHITE);
        achat.setOpaque(false);
        achat.setHorizontalAlignment(JLabel.CENTER);
        if(Fichiers.readLigne(13, Conn.fichier).equals("Skin rider : true")) {
            achat.setText("Modifiez les couleurs");
            achat.setFont(new Font("Upheaval TT (BRK)", Font.BOLD, 45));
        }else{
            achat.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    if(Conn.argent()>=5000){
                        Conn.gagne(-5000);
                        achat.setText("Modifiez les couleurs");
                        achat.setFont(new Font("Upheaval TT (BRK)", Font.BOLD, 45));
                        repaint();
                        try{
                            Fichiers.remplacerLigne(13, "Skin rider : true", Conn.fichier);
                        }catch(IOException e1){
                            System.out.println("erreur fichier");
                        }
                        revalidate();
                        repaint();
                    }
                }
            });
        }
        JPanel panelAchat = new JPanel(new GridLayout(1,3));
        panelAchat.add(new JLabel(" "));
        panelAchat.add(achat);
        panelAchat.add(new JLabel(" "));
        panelAchat.setOpaque(false);
        panelCenter.add(panelAchat);
        // bouton retour
        retour = new GradientButton("Retour");
        retour.setColors(Color.WHITE, Color.WHITE);
        retour.setOpaque(false);
        retour.setHorizontalAlignment(JLabel.LEFT);
        retour.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
                int height = (int)dimension.getHeight();
                int width = (int)dimension.getWidth();
                frame = (JFrame) SwingUtilities.getWindowAncestor(AchatSkinRider.this);
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
        menu.setColors(Color.WHITE, Color.WHITE);
        menu.setOpaque(false);
        menu.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame = (JFrame) SwingUtilities.getWindowAncestor(AchatSkinRider.this);
                frame.setSize(728, 410);
                frame.setContentPane(new MenuAccueil());
                frame.setVisible(true);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.remove(panel);
            }
        });
        JPanel south = new JPanel(new GridLayout());
        south.add(retour);
        south.add(menu);
        south.setOpaque(false);
        panel.add(south, BorderLayout.SOUTH);
        panelGen.add(panelCenter, BorderLayout.CENTER);
        panel.add(panelGen, BorderLayout.CENTER);
        JLabel space = VueParametre.styleJLabel("       ", 70);
        space.setOpaque(false);
        panel.add(space, BorderLayout.EAST);
        add(panel);
    }
    public void changerCouleur(JSlider j, int indice){
        JLabel label = VueParametre.styleJLabel(j.getName(), 40);
        panelRVB.add(label);
        j.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent e){
                if(Fichiers.readLigne(13, Conn.fichier).equals("Skin rider : false")){
                    if(((JSlider)e.getSource()).getValue()!=VueVehicule.couleurCode[indice]) ((JSlider)e.getSource()).setValue(VueVehicule.couleurCode[indice]);
                }else{
                    VueVehicule.couleurCode[indice] = j.getValue();
                }
                repaint();
            }
        });
        panelRVB.add(j);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
        Graphics2D gd = (Graphics2D) g;
        drawParam(gd);
        g.drawImage(coinImage, 0, 15, 100, 100, null);
        Font font = new Font("Upheaval TT (BRK)", Font.BOLD, 70);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(Conn.argent()),120, 80);
    }

    public void drawParam(Graphics2D gd){
        int xParam =150;
        int yParam = -1300;
        int facteurParam = 4;
        Color a = new Color(VueVehicule.couleurCode[0], VueVehicule.couleurCode[2], VueVehicule.couleurCode[4]);
        Color b = new Color(VueVehicule.couleurCode[1], VueVehicule.couleurCode[3], VueVehicule.couleurCode[5]);

        Polygon outline = new Polygon();
        outline.addPoint(facteurParam*172+xParam, facteurParam*484+yParam);
        outline.addPoint(facteurParam*190+xParam, facteurParam*474+yParam);
        outline.addPoint(facteurParam*205+xParam, facteurParam*474+yParam);
        outline.addPoint(facteurParam*216+xParam, facteurParam*484+yParam);
        outline.addPoint(facteurParam*226+xParam, facteurParam*486+yParam);
        outline.addPoint(facteurParam*224+xParam, facteurParam*496+yParam);
        outline.addPoint(facteurParam*174+xParam, facteurParam*496+yParam);

        Polygon outline2 = new Polygon();
        outline2.addPoint(facteurParam*171+xParam, facteurParam*483+yParam);
        outline2.addPoint(facteurParam*190+xParam, facteurParam*473+yParam);
        outline2.addPoint(facteurParam*205+xParam, facteurParam*473+yParam);
        outline2.addPoint(facteurParam*217+xParam, facteurParam*483+yParam);
        outline2.addPoint(facteurParam*227+xParam, facteurParam*487+yParam);
        outline2.addPoint(facteurParam*225+xParam, facteurParam*497+yParam);
        outline2.addPoint(facteurParam*173+xParam, facteurParam*497+yParam);
        gd.setColor(Color.WHITE);
        gd.fillPolygon(outline2);
        gd.setColor(Color.BLACK);
        gd.fillPolygon(outline);

        GradientPaint gradient = new GradientPaint(facteurParam*180+xParam,  facteurParam*475+yParam, a, facteurParam*220+xParam, facteurParam*495+yParam, b);
        gd.setPaint(gradient);
        Polygon p = new Polygon();
        p.addPoint(facteurParam*173+xParam, facteurParam*485+yParam);
        p.addPoint(facteurParam*190+xParam, facteurParam*475+yParam);
        p.addPoint(facteurParam*205+xParam, facteurParam*475+yParam);
        p.addPoint(facteurParam*215+xParam, facteurParam*485+yParam);
        p.addPoint(facteurParam*225+xParam, facteurParam*487+yParam);
        p.addPoint(facteurParam*223+xParam, facteurParam*495+yParam);
        p.addPoint(facteurParam*175+xParam, facteurParam*495+yParam);
        gd.fillPolygon(p);

            //roues
            gd.setColor(Color.BLACK);
            gd.fillOval(facteurParam*210+xParam, facteurParam*490+yParam, facteurParam*10, facteurParam*10);
            gd.fillOval (facteurParam*180+xParam, facteurParam*490+yParam, facteurParam*10, facteurParam*10);
            //roues
            gd.setColor(Color.GRAY);
            gd.fillOval(facteurParam*212+xParam, facteurParam*492+yParam, facteurParam*6, facteurParam*6);
            gd.fillOval (facteurParam*182+xParam, facteurParam*492+yParam, facteurParam*6, facteurParam*6);
        
            //vitres
            Polygon olf1 = new Polygon();
            olf1.addPoint(facteurParam*179+xParam,  facteurParam*486+yParam );
            olf1.addPoint(facteurParam*196+xParam,  facteurParam*486+yParam);
            olf1.addPoint(facteurParam*196+xParam,  facteurParam*479+yParam);
            olf1.addPoint(facteurParam*189+xParam,  facteurParam*479+yParam);
            gd.setColor(Color.WHITE);
            gd.fillPolygon(olf1);
            gd.setColor(Color.BLACK);
            
            Polygon fentere1 = new Polygon();
            fentere1.addPoint(facteurParam*180+xParam, facteurParam*485+yParam);
            fentere1.addPoint(facteurParam*195+xParam, facteurParam*485+yParam);
            fentere1.addPoint(facteurParam*195+xParam, facteurParam*480+yParam);
            fentere1.addPoint(facteurParam*190+xParam, facteurParam*480+yParam);
            gd.fillPolygon(fentere1);

            Polygon olf2 = new Polygon();
            olf2.addPoint(facteurParam*212+xParam, facteurParam*486+yParam);
            olf2.addPoint(facteurParam*199+xParam, facteurParam*486+yParam);
            olf2.addPoint(facteurParam*199+xParam, facteurParam*479+yParam);
            olf2.addPoint(facteurParam*204+xParam, facteurParam*479+yParam);
            gd.setColor(Color.WHITE);
            gd.fillPolygon(olf2);
            gd.setColor(Color.BLACK);
        
            Polygon fenetre2 = new Polygon();
            fenetre2.addPoint(facteurParam*210+xParam, facteurParam*485+yParam);
            fenetre2.addPoint(facteurParam*200+xParam, facteurParam*485+yParam);
            fenetre2.addPoint(facteurParam*200+xParam, facteurParam*480+yParam);
            fenetre2.addPoint(facteurParam*205+xParam, facteurParam*480+yParam);
            gd.fillPolygon(fenetre2);
    }
}
