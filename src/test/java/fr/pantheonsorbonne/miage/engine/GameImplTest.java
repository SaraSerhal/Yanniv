package fr.pantheonsorbonne.miage.engine;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.engine.local.GameLocal;

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
