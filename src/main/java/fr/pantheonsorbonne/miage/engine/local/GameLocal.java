package fr.pantheonsorbonne.miage.engine.local;

import java.util.LinkedList;
import java.util.List;
import fr.pantheonsorbonne.miage.engine.AlternateTurnGame;
import fr.pantheonsorbonne.miage.player.DumbPlayer;
import fr.pantheonsorbonne.miage.player.Player;

public class GameLocal extends AlternateTurnGame {
    public GameLocal(int nb, List<Player> players) {
        super(nb, players);
    }

    public static void main(String... args) {

        DumbPlayer mayane = new DumbPlayer("Mayane");
        DumbPlayer sara = new DumbPlayer("Sara");
        List<Player> players = new LinkedList<>();
        players.add(mayane);
        players.add(sara);

        GameLocal localWarGame = new GameLocal(2, players);
        localWarGame.installation();
        localWarGame.start();
        System.exit(0);
    }

}
