package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.IOException;
import javax.imageio.ImageIO;

public class AchatTrainee extends JPanel {
    public JPanel panel, panelCenter, panelSkin;
    public JLabel titre, titreSkin;
    public Image bgImage;
    public GradientButton retour, menu;
    public JFrame frame;

    public AchatTrainee(MenuAccueil accueil){
        try{
            bgImage = ImageIO.read(getClass().getResource("../resources/fondparametres.png"));
        }catch(IOException e){
            System.out.println("erreur chargement image");
        }
        frame = new JFrame("Achat skin dune");
        panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panelCenter = new JPanel(new GridLayout(2,1));
        panelCenter.setOpaque(false);
        // titre
        titreSkin = VueParametre.styleJLabel("Modifier le skin de balle :", 40);
        titreSkin.setHorizontalAlignment(JLabel.CENTER);
        titreSkin.setOpaque(false);
        panel.add(titreSkin, BorderLayout.NORTH);
        // panel skin
        panelSkin = new JPanel(new GridLayout());
        panelSkin.setOpaque(false);
        panel.add(panelSkin, BorderLayout.CENTER);
        // bouton retour
        retour = new GradientButton("Retour");
        retour.setOpaque(false);
        retour.setHorizontalAlignment(JLabel.LEFT);
        retour.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
                int height = (int)dimension.getHeight();
                int width = (int)dimension.getWidth();
                frame = (JFrame) SwingUtilities.getWindowAncestor(AchatTrainee.this);
                frame.setSize(width, height);
                frame.setContentPane(new VueBoutique(accueil));
                frame.setVisible(true);
                frame.setResizable(false);
                frame.setLocation(0, 0);
                frame.remove(panel);
            }
        });
        panel.add(retour, BorderLayout.WEST);
        // bouton menu
        menu = new GradientButton("Menu");
        menu.setOpaque(false);
        menu.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame = (JFrame) SwingUtilities.getWindowAncestor(AchatTrainee.this);
                frame.setSize(728, 410);
                frame.setContentPane(new MenuAccueil());
                frame.setVisible(true);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.remove(panel);
            }
        });
        panel.add(menu, BorderLayout.SOUTH);
        add(panel);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), null);
    }
}
