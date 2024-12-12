/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ingsw.marianito;

import engine.Game;
import ui.GameFrame;

/**
 *
 * @author juans
 */
public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        new GameFrame(game.getGamePanel());
        
        game.start();
    }
}
