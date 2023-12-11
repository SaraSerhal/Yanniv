package fr.pantheonsorbonne.miage.engine.local;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class GameLocalTest {

    @Test
    public void testGameLocalInitialization() {
        GameLocal game = new GameLocal();
        assertNotNull(game);
    }
}
