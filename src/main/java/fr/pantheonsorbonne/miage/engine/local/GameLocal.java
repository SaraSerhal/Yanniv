package fr.pantheonsorbonne.miage.engine.local;

import fr.pantheonsorbonne.miage.engine.AlternateTurnGame;

public class GameLocal extends AlternateTurnGame {
    public GameLocal() {
        super();
    }

    public static void main(String... args) {

        GameLocal localWarGame = new GameLocal();
        localWarGame.installation();
        localWarGame.start();
        System.exit(0);
    }

}
