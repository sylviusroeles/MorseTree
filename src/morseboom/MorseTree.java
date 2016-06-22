/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morseboom;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class MorseTree extends MorseNode {

    public MorseTree() {                       // simpele bouw van de constante boom
        super();        // constructor MorseNode(), deze mag ook weggelaten worden
        add_punt('e').add_punt('i').add_punt('s').add_punt('h');
        add_streep('t').add_streep('m').add_streep('o');

        volg_punt.add_streep('a').add_streep('w').add_streep('j');
        volg_punt.volg_punt.add_streep('u');
        volg_punt.volg_punt.volg_streep.add_punt('f');
        volg_punt.volg_punt.volg_punt.add_streep('v');
        volg_punt.volg_streep.add_punt('r').add_punt('l');
        volg_punt.volg_streep.volg_streep.add_punt('p');

        volg_streep.add_punt('n').add_streep('k').add_streep('y');
        volg_streep.volg_punt.volg_streep.add_punt('c');
        volg_streep.volg_punt.add_punt('d').add_punt('b');
        volg_streep.volg_streep.add_punt('g').add_punt('z');
        volg_streep.volg_streep.volg_punt.add_streep('q');
        volg_streep.volg_punt.volg_punt.add_streep('x');
    }

    @Override
    public void print(String code) {
        super.print(code);
    }

    @Override
    public int count() {
        return super.count();
    }

    @Override
    public char decode(String s,int i) {
        return super.decode(s,i);
    }

    int width = 400;
    int height = 300;
    JPanel panel = new JPanel(true) // true is doublebuffered
    {
        public void paintComponent(Graphics g) // directe (inline) override van deze methode
        {
            super.paintComponent(g);                // moet altijd met Swing componenten
            Graphics2D g2d = (Graphics2D) g;         // nu extra functionaliteit:

            g2d.setStroke(new BasicStroke(5.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            // breedte van de lijnstukken, afgeronde eindpunten
            g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON));
            if (tree != null) {
                Dimension dim = getSize();
                tree.grafPrint(g2d, (int) dim.getWidth() / 2, 50, (int) dim.getHeight() / 4);
            }
        }
    };

    public void grafPrint(Graphics g, int x, int y, int len) {
        g.setColor(Color.BLACK);
        g.drawString("[Node]", x, y);
        if (volg_punt != null) {
            g.setColor(Color.RED);
            g.drawLine(x, y, x - len, y + len - 8);
            volg_punt.grafPrint(g, x - len, y + len, len / 2);
        }
        if (volg_streep != null) {
            g.setColor(Color.BLUE);
            g.drawLine(x, y, x + len, y + len - 8);
            volg_streep.grafPrint(g, x + len, y + len, len / 2);
        }
    }

}
