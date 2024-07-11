package Vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;


import javax.swing.JButton;

public class GradientButton extends JButton {
    Color a = new Color(56, 12, 97);
    Color b = new Color(53, 182, 187);

    public GradientButton(String text) {
        super(text);
        setBorderPainted(true);
        setFont(new Font("Upheaval TT (BRK)", Font.BOLD, 50));
      }
      
      @Override
      protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        
        int w = getWidth();
        int h = getHeight();
        GradientPaint gpText = new GradientPaint(0, 0, a, w, h, b);
        g2d.setColor(Color.BLACK);
        Stroke border = g2d.getStroke();
        g2d.setStroke(new BasicStroke(2));
        g2d.setPaint(gpText);
        g2d.drawChars(getText().toCharArray(), 0, getText().length(), 0, h / 2 + getFont().getSize() / 2 - 2);;
        g2d.drawString(getText(), 0, h / 2 + getFont().getSize() / 2 - 2);
        g2d.setStroke(border);
        g2d.dispose();
      }

      public void setColors(Color a, Color b) {
        this.a = a;
        this.b = b;
      }
}