/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import engine.InputHandler;
import engine.Renderer;
import entities.GameEntity;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author juans
 */
public class GamePanel extends JPanel {
    
// Basics
        // Funcionality
    private static InputHandler inputHandler;
    private static Renderer renderer;
    
    public GamePanel(int w, int h, GameEntity[] entities) {
        inputHandler = InputHandler.getInstance();
        renderer = Renderer.getInstance(entities);
        
        // Settings
        addKeyListener(inputHandler);
        setBounds(0, 0, w, h);
        setFocusable(true);
        requestFocusInWindow();
    }

    @Override
    public void paint(Graphics g) {
        renderer.render(g);
    }
}
