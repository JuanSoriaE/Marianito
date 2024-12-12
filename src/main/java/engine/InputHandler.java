/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

/**
 *
 * @author juans
 */
public final class InputHandler implements KeyListener {
    // Basics
    private static InputHandler instance;
    
    private final HashSet<Integer> pressedKeys;
    
    public InputHandler() {
        pressedKeys = new HashSet<>();
    }
    
    public static InputHandler getInstance() {
        if (instance == null)
            instance = new InputHandler();
        return instance;
    }
    
    public boolean isPressed(int keyCode) { return pressedKeys.contains(keyCode); }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        pressedKeys.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.remove(e.getKeyCode());
    }
}
