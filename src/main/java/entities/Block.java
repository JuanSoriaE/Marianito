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
    
    public Block(int type, int x, int y) {
        this.x = x; this.y = y;
        this.w = type == 3 ? 120 : 60; this.h = type == 3 ? 141 : 60;
        this.type = type;
        
        texture = Toolkit.getDefaultToolkit().getImage("src/main/java/resources/block-" + type + ".png");
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(texture, x, y, null);
    }
}
