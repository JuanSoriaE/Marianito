/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.awt.Graphics;
import java.awt.Toolkit;

/**
 *
 * @author juans
 */
public class Block extends GameEntity {
    // Basics
    private final int type;
    
    // Gameplay
    private final Marianito marianito;
    
    public Block(int type, int x, int y, Marianito marianito) {
        this.x = x; this.y = y;
        this.w = type == 3 ? 120 : 60; this.h = type == 3 ? 141 : 60;
        this.type = type;
        this.marianito = marianito;
        
        texture = Toolkit.getDefaultToolkit().getImage("src/main/java/resources/images/block-" + type + ".png");
    }
    
    // Getters and Setter
    public int getType() { return type; }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(texture, x - marianito.getX() / 2, y, null);
    }
}
