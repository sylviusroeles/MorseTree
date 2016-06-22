/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morseboom;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * 
 */
public class FrameOut extends JPanel {

    MorseTree tree = null;
    BufferedImage grid;                         // declare een image voor het grid
    int width = 400;
    int height = 300;
    Color bgColor = Color.gray;

    public FrameOut(MorseTree tree) {
        super(true);                        // dubbele buffering van de JPanel zelf
        this.tree = tree;
    }

    public void setBGColor(Color bgColor) {
        this.bgColor = bgColor;
    }

    public void paintComponent(Graphics g) // directe (inline) override van deze methode
    {
        super.paintComponent(g);                // moet altijd met Swing componenten
        Graphics2D g2d = (Graphics2D) g;         // nu extra functionaliteit:

        int w = this.getWidth();                // een grid met met altijd 100 vlakken (10 * 10)
        int h = this.getHeight();
        grid = (BufferedImage) (this.createImage(w, h)); // elke keer opnieuw!
        Graphics2D gc = grid.createGraphics();
        gc.setColor(bgColor);
        for (int x = 0; x < w; x += w / 10) {
            gc.drawLine(x, 0, x, h);
        }
        for (int y = 0; y < h; y += h / 10) {
            gc.drawLine(0, y, w, y);
        }

        g2d.setStroke(new BasicStroke(2.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        // breedte van de lijnstukken, afgeronde eindpunten
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON));
        // achtergrond eerst 
        g2d.drawImage(grid, null, 0, 0);
        if (tree != null) {
            tree.grafPrint(g2d, w / 2, h / 8, (w < h) ? w / 4 : h / 4);
        }
    }
}
