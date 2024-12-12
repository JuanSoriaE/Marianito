/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package engine;

import entities.GameEntity;
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
        
        background = Toolkit.getDefaultToolkit().getImage("src/main/java/resources/mundo.png");
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
        g.drawImage(background, 0, 0, null);
    }
    
    private void drawUI(Graphics g) {
    }
}
