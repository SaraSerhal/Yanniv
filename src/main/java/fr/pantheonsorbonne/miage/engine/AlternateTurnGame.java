package fr.pantheonsorbonne.miage.engine;

import java.util.Random;

import fr.pantheonsorbonne.miage.player.Player;

public abstract class AlternateTurnGame extends GameImpl {

    private final Random random = new Random();
    protected int numCurrentPlayer;

    public AlternateTurnGame(int nb) {
        super(nb);
        numCurrentPlayer=-1;
    }

    @Override
    public Player getNextPlayer() {
        if (numCurrentPlayer == -1) {
            numCurrentPlayer=random.nextInt();
        } else {
            numCurrentPlayer= (numCurrentPlayer++)%players.length;
        }
        return players[numCurrentPlayer];
    }
}
