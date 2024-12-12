/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import engine.InputHandler;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

/**
 *
 * @author juans
 */
public class Marianito extends GameEntity {
    // Constants
    private static final int JUMP_STRENGTH = 20;
    private static final int GRAVITY = 1;
    private static final int GROUND_LEVEL = 448;
    private static final int VELOCITY = 10;
    
    // Basics
    private int velX, velY;
    
    // Gameplay
    private boolean jumping;
    private final InputHandler inputHandler;
    private int runAnimationStep;
    
    // UI
    private final Image[] sprites;
    private int spriteIdx;
    
    public Marianito() {
        w = 60; h = 80;
        x = 50; y = GROUND_LEVEL - h;
        velX = 0; velY = 0;
        
        inputHandler = InputHandler.getInstance();
        
        sprites = new Image[8];
        spriteIdx = 1;
        initSprites();
    }
    
    private void initSprites() {
        //        + 4 if its to the right
        sprites[0] = Toolkit.getDefaultToolkit().getImage("src/main/java/resources/standing-left.png"); // Standing Left
        sprites[0 + 4] = Toolkit.getDefaultToolkit().getImage("src/main/java/resources/standing-right.png"); // Standing Right
        sprites[1] = Toolkit.getDefaultToolkit().getImage("src/main/java/resources/jumping-left.png"); // Jumping Left
        sprites[1 + 4] = Toolkit.getDefaultToolkit().getImage("src/main/java/resources/jumping-right.png"); // Jumping Right
        sprites[2] = Toolkit.getDefaultToolkit().getImage("src/main/java/resources/running-left-0.png"); // Running Left 0
        sprites[3] = Toolkit.getDefaultToolkit().getImage("src/main/java/resources/running-left-1.png"); // Running Left 1
        sprites[2 + 4] = Toolkit.getDefaultToolkit().getImage("src/main/java/resources/running-right-0.png"); // Running Right 0
        sprites[3 + 4] = Toolkit.getDefaultToolkit().getImage("src/main/java/resources/running-right-1.png"); // Running Right 1
        
        // Extras
        runAnimationStep = 0;
    }
    
    private void jump() {
        if (jumping) return;
        
        jumping = true;
        velY = -JUMP_STRENGTH;
    }
    
    private void handleMovement() {
        if (inputHandler.isPressed(KeyEvent.VK_UP))
            jump();
        
        if (!(inputHandler.isPressed(KeyEvent.VK_LEFT) ^ inputHandler.isPressed(KeyEvent.VK_RIGHT))) // If both or none
            velX = 0;
        else
            velX = VELOCITY * (inputHandler.isPressed(KeyEvent.VK_LEFT) ? -1 : 1);
    }
    
    private void handleJumping() {
        if (!jumping) return;
        
        if (y < GROUND_LEVEL - h)
            velY += GRAVITY;
        else {
            jumping = false;
            velY = 0;
            y = GROUND_LEVEL - h;
        }
    }
    
    public void update() {
        handleJumping();
        handleMovement();
        
        x += velX;
        y += velY;
        
        if (jumping)
            spriteIdx = 1 + (velX < 0 ? 0 : sprites.length / 2);
        else if (velX != 0) {
            spriteIdx = 2 + (velX < 0 ? 0 : sprites.length / 2) + runAnimationStep / 3;
            runAnimationStep++; runAnimationStep %= 6;
        } else
            spriteIdx = 4;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(sprites[spriteIdx], x, y, null);
    }
}
