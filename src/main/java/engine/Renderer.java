/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package engine;

import entities.GameEntity;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author juans
 */
public final class Renderer {
    // Basics
    private static Renderer instance;
    
    // Entities
    private final GameEntity[] entities;
    
    // UI
    private final Image background;
    
    public Renderer(GameEntity[] entities) {
        this.entities = entities;
        
        background = Toolkit.getDefaultToolkit().getImage("src/main/java/resources/images/background.png");
    }
    
    public static Renderer getInstance(GameEntity[] entities) {
        if (instance == null)
            instance = new Renderer(entities);
        return instance;
    }
    
    public void render(Graphics g) {
        drawBackground(g);
        
        for (GameEntity entity : entities)
            entity.render(g);
        
        drawUI(g);
    }
    
    private void drawBackground(Graphics g) {
        g.drawImage(background, 1024 - entities[entities.length - 1].getX() / 2, 0, null);
        g.drawImage(background, - entities[entities.length - 1].getX() / 2, 0, null);
        g.drawImage(background, -1024 - entities[entities.length - 1].getX() / 2, 0, null);
    }
    
    private void drawUI(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Monospaced", Font.BOLD, 18));
        g.drawString("MARIANITO", 10, 20);
    }
}
