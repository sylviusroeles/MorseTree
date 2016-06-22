/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morseboom;

import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * 
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        final MorseTree boom = new MorseTree();
        boom.print("");
        System.out.println("count char in morse tree: " + boom.count());
        FrameOut fr = new FrameOut(boom);
        JFrame frame = new JFrame("BinTree3");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new JScrollPane(fr));
        frame.setSize(new java.awt.Dimension(600, 800));
        frame.setVisible(true);
        while (true) {
            byte buf[] = new byte[100];
            try {
                System.out.print("input morse string: ");
                System.in.read(buf);
            } catch (Exception e) {       // eof
            }
            String s = new String(buf);
            s = s.trim();
            System.out.println("'" + boom.decode(s, 1) + "'");
        }
    }

}
