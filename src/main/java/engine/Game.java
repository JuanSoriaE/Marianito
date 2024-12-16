/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package engine;

import entities.Block;
import entities.GameEntity;
import entities.Marianito;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import ui.GamePanel;

/**
 *
 * @author juans
 */
public class Game implements Runnable {
    // Constants
    private static final int FPS = 30;
    private static final long TARGET_TIME = 1000 / FPS;
    private static final int W = 1024, H = 576;
    
    // Entities
    private final Marianito marianito;
    private Block[] blocks;
    
    // Functionality
    private final GamePanel gamePanel;
    
    private boolean running;
    
    // Gameplay
    private static AudioController audioController;
    
    public Game() {
        marianito = new Marianito();
        loadLevel(0);
        
        GameEntity[] entities = new GameEntity[blocks.length + 1];
        entities[entities.length - 1] = marianito;
        System.arraycopy(blocks, 0, entities, 0, blocks.length);
        gamePanel = new GamePanel(W, H, entities);
        
        initAudios();
    }
    
    // Getters and Setters
    public GamePanel getGamePanel() { return gamePanel; }
    
    private void initAudios() {
        audioController = AudioController.getInstance();
        
        audioController.load("background-music", "src/main/java/resources/audios/background-music.wav");
        audioController.load("jump", "src/main/java/resources/audios/jump.wav");
    }
    
    private void loadLevel(int level) {
        try {
            JSONObject config = new JSONObject(
                new String(Files.readAllBytes(Paths.get("src/main/java/resources/levels/level-" + level + ".json"))));
            
            JSONArray blocksConfig = config.getJSONArray("blocks");
            blocks = new Block[blocksConfig.length()];
            for (int i = 0; i < blocksConfig.length(); i++) {
                JSONObject blockConfig = blocksConfig.getJSONObject(i);
                
                blocks[i] = new Block(
                    blockConfig.getInt("type"), blockConfig.getInt("x"), blockConfig.getInt("y"), marianito);
            }
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void start() {
        if (running) return;
        
        running = true;
        audioController.playLoop("background-music");
        Thread thread = new Thread(this);
        thread.start();
    }
    
    public void stop() {
        running = false;
    }
    
    public void update() {
        marianito.update();
    }
    
    public void paint() {
        gamePanel.repaint();
    }

    @Override
    public void run() {
        long last_time = System.currentTimeMillis();
        while (running) {
            long current_time = System.currentTimeMillis(),
                 elapsed_time = current_time - last_time;
            
            if (elapsed_time >= TARGET_TIME) {
                update();
                paint();
                last_time = current_time;
            } else
                try {
                    Thread.sleep(TARGET_TIME - elapsed_time);
                } catch (InterruptedException e) {
                }
        }
    }
    
    
}
