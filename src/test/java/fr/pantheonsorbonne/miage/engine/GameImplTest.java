package fr.pantheonsorbonne.miage.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.engine.local.GameLocal;
import fr.pantheonsorbonne.miage.player.Player;
import fr.pantheonsorbonne.miage.player.SmartPlayer;

public class GameImplTest {
    
    GameImpl game= new GameLocal();

   @Test
    public void testInitializePlayers(){
        game.initializePlayers();
        
    }

    @Test
    public void testInstallation(){
        game.installation();

    }

    @Test
    public void testStart(){
        game.start();
    }
    
}
