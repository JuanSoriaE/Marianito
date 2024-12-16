/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author juans
 */
public abstract class GameEntity {
    protected int x, y, w, h;
    protected Image texture;
    
    // Getters and Setters
    public int getX() { return x; }
    public int getY() { return y; }
    public int getW() { return w; }
    public int getH() { return h; }
    
    public abstract void update();
    public abstract void render(Graphics g);
}
