package fr.pantheonsorbonne.miage.engine;

import java.util.List;
import java.util.Random;

import fr.pantheonsorbonne.miage.player.Player;
import fr.pantheonsorbonne.miage.player.PlayerStatus;

public abstract class AlternateTurnGame extends GameImpl {

    private final Random random = new Random();
    protected Player currentPlayer;

    public AlternateTurnGame(int nb, List<Player> players) {
        super(nb, players);
        currentPlayer = players.get(random.nextInt(nbPlayers));
    }

    @Override
    public Player getNextPlayer() {
        for (Player player : players) {
            if (player.getPlayerStatus() == PlayerStatus.YANIV) {
                currentPlayer = player;
            }
        }
        return currentPlayer;
    }
}
