/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package engine;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author juans
 */
public final class AudioController {
    // Basics
    private static AudioController instance;
    
    private final LinkedList<String> ids;
    private final LinkedList<Clip> clips;
    
    public AudioController() {
        ids = new LinkedList<>();
        clips = new LinkedList<>();
    }
    
    public static AudioController getInstance() {
        if (instance == null)
            instance = new AudioController();
        return instance;
    }
    
    public void load(String id, String path) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(path));
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            
            ids.add(id);
            clips.add(clip);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            Logger.getLogger(AudioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private int getIdx(String id) {
        for (int i = 0; i < ids.size(); i++)
            if (ids.get(i).equals(id))
                return i;
        return -1;
    }
    
    public void play(String id) {
        int clipIdx = getIdx(id);
        if (clipIdx == -1) return;
        
        clips.get(clipIdx).stop();
        clips.get(clipIdx).setFramePosition(0);
        clips.get(clipIdx).start();
    }
    
    public void playLoop(String id) {
        int clipIdx = getIdx(id);
        if (clipIdx == -1) return;
        
        clips.get(clipIdx).stop();
        clips.get(clipIdx).setFramePosition(0);
        clips.get(clipIdx).loop(Clip.LOOP_CONTINUOUSLY);
        clips.get(clipIdx).start();
    }
}
