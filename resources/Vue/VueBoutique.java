package Vue;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.imageio.ImageIO;
import java.io.IOException;

public class VueBoutique extends JPanel {
    public Image fond;
    public JPanel panel, panelCenter;
    public JLabel titre, skinRider, labelconnect;
    public GradientButton menu, chFond, chSkinRider, chSkinDune;
    public MenuAccueil maccueil;
    public JFrame frame;

    public VueBoutique(MenuAccueil accueil){
        try {
            fond = ImageIO.read(getClass().getResource("../resources/fondparametre.png"));
        } catch (IOException e) {
            System.out.println("erreur chargement image");
        }
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(fond.getWidth(null), fond.getHeight(null)));
        frame = new JFrame("Boutique");
        maccueil = accueil;
        panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panelCenter = new JPanel(new GridLayout(4,1));
        panelCenter.setOpaque(false);
        titre = VueParametre.styleJLabel("Boutique", 50);
        titre.setOpaque(false);
        chSkinRider = new GradientButton("Changer de couleur de voiture (Rider)");
        chSkinRider.setOpaque(false);
        chSkinRider.setColors(Color.WHITE, Color.WHITE);
        chSkinRider.setHorizontalAlignment(JLabel.CENTER);
        // Aligner le texte au centre
        chSkinRider.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
                int height = (int)dimension.getHeight();
                int width = (int)dimension.getWidth();
                frame = (JFrame) SwingUtilities.getWindowAncestor(VueBoutique.this);
                frame.setSize(width, height);
                frame.setContentPane(new AchatSkinRider(accueil));
                frame.setVisible(true);
                frame.setResizable(false);
                frame.setLocation(0, 0);
                frame.remove(panel);
            }
        });
        chSkinDune = new GradientButton("Changer de skin de balle (Dune)");
        chSkinDune.setOpaque(false);
        chSkinDune.setColors(Color.WHITE, Color.WHITE);
        chSkinDune.setHorizontalAlignment(JLabel.CENTER);
        chSkinDune.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
                int height = (int)dimension.getHeight();
                int width = (int)dimension.getWidth();
                frame = (JFrame) SwingUtilities.getWindowAncestor(VueBoutique.this);
                frame.setSize(width, height);
                frame.setContentPane(new AchatSkinDune(accueil));
                frame.setVisible(true);
                frame.setResizable(false);
                frame.setLocation(0, 0);
                frame.remove(panel);
            }
        });
        chFond = new GradientButton("Changer de fond d'écran");
        chFond.setOpaque(false);
        chFond.setColors(Color.WHITE, Color.WHITE);
        chFond.setHorizontalAlignment(JLabel.CENTER);
        chFond.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
                int height = (int)dimension.getHeight();
                int width = (int)dimension.getWidth();
                frame = (JFrame) SwingUtilities.getWindowAncestor(VueBoutique.this);
                frame.setSize(width, height);
                frame.setContentPane(new AchatFond(accueil));
                frame.setVisible(true);
                frame.setResizable(false);
                frame.setLocation(0, 0);
                frame.remove(panel);
            }
        });
        panelCenter.add(chSkinRider);
        panelCenter.add(chSkinDune);
        panelCenter.add(chFond);
        panel.add(titre, BorderLayout.NORTH);
        panel.add(panelCenter, BorderLayout.CENTER);
        menu = new GradientButton("Menu");
        menu.setFont(new Font("Upheaval TT (BRK)", Font.BOLD, 70));
        menu.setForeground(Color.WHITE);
        menu.setColors(Color.WHITE, Color.WHITE);
        menu.setOpaque(false);
        menu.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                accueil.frame.dispose();
                MenuAccueil.createAndShowGUI();
            }
        });
        panel.add(menu, BorderLayout.SOUTH);        
        add(panel, BorderLayout.CENTER);
    }
    public <T> void setChangWindow(T window){
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int)dimension.getHeight();
        int width = (int)dimension.getWidth();
        frame = (JFrame) SwingUtilities.getWindowAncestor(VueBoutique.this);
        //frame.setContentPane(window);
        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocation(0, 0);
        frame.remove(panel);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fond, 0, 0, getWidth(), getHeight(), this);
    }
    public void createAndShowGUIBoutique() {
        JFrame f = new JFrame("Boutique");
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int)dimension.getHeight();
        int width = (int)dimension.getWidth();
        f.setSize(width, height);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setContentPane(new VueBoutique(maccueil));
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
