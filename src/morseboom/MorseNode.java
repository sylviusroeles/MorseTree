/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morseboom;

import java.awt.Color;
import java.awt.Graphics;

/**
 * De topnode (class MorseTree) is een speciale uitvoering van een gewone node
 * (class MorseNode). In de implementatie (de source hieronder) zie je ook dat
 * de MorseTree class de MorseNode class extends en een aantal methodes van
 * MorseNode overrides (is opnieuw implementeren).
 *
 *
 */
public class MorseNode {

    private char c;
    MorseNode volg_punt;         // links
    MorseNode volg_streep;       // rechts
    MorseNode tree = null;

    MorseNode() {
        this.c = ' ';   // deze wordt gebruikt bij de top node
    }                       // zie class m_boom

    MorseNode(char c) // maak een nieuwe node
    {
        this.c = c;
    }

    void print(String code) // print de node plus onderliggen nodes
    {
        System.out.println(c + " = " + code);
        if (volg_punt != null) {
            volg_punt.print(code + ".");
        }
        if (volg_streep != null) {
            volg_streep.print(code + "-");
        }
    }                        // code is de opsparende parameter

    public String decode(char letter, String code) {
        return code;
    }

    public char decode(String s, int i) // decode string -> char
    // mag ook:   char decode(String s, int i)    // overload via index in String
    {
        if (i-1 < s.length()) {
            if (volg_punt != null && s.charAt(i - 1) == '.') {
                return volg_punt.decode(s, i + 1);
            }
            if (volg_streep != null && s.charAt(i - 1) == '-') {
                return volg_streep.decode(s, i + 1);
            }
        }
        return c;
    }

    public int count()// tel de node + onder liggen
    {
        int i = 1;
        if (volg_punt != null) {
            i += volg_punt.count();
        }
        if (volg_streep != null) {
            i += volg_streep.count();
        }
        return i;
    }

    public MorseNode add_punt(char c) // zie constructor class  MorseTree
    {
        volg_punt = new MorseNode(c);
        return volg_punt;
    }

    public MorseNode add_streep(char c) {
        volg_streep = new MorseNode(c);
        return volg_streep;
    }

    public void build(String s, char c) {

    }

    public void grafPrint(Graphics g, int x, int y, int len) {
        g.setColor(Color.BLACK);
        g.drawString("[" + c + "]", x, y);
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
