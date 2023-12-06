package fr.pantheonsorbonne.miage.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.engine.local.GameLocal;

class GameStatusTest {

  @Test
    public void testGetGameStatus(){
        GameLocal game = new GameLocal();
        assertEquals("ONGOING",GameStatus.ONGOING);

    }


    
}