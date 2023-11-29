package fr.pantheonsorbonne.miage.engine;

import java.util.List;
import java.util.Random;
import fr.pantheonsorbonne.miage.player.Player;
import fr.pantheonsorbonne.miage.player.PlayerStatus;

public abstract class AlternateTurnGame extends GameImpl {

    private final Random random = new Random();
    protected Player currentPlayer;
    private boolean hasNextRound;

    public AlternateTurnGame(int nb, List<Player> players) {
        super(nb, players);
        currentPlayer = players.get(random.nextInt(nbPlayers));
        hasNextRound = true;
    }

    @Override
    public Player getNextPlayer() {
        for (Player player : players) {
            if (player.getPlayerStatus() == PlayerStatus.YANIV) {
                currentPlayer = player;
                break;
            }
        }
        return currentPlayer;
    }

    @Override
    public void goNextRound() {
        for (;;) {// boucle qui s'arrete quand la partie est finie, chaque itération est une
                  // manche
            if (!hasNextRound) {
                break;
            }
            System.out.println("\nManche " + numRound + " :\n");
            currentPlayer = getNextPlayer();
            nextPlayerTurn();
        }
    }

    @Override
    public void nextPlayerTurn() {
        for (;;) {// boucle qui s'arrete quand la manche est finie, chaque iteration est un joueur
                  // qui joue
            System.out.println("Tour de " + currentPlayer.getName() + ":");
            System.out.println("Première carte sur la pile de défausse: " + discardPile.getFirst().toFancyString()
                    + discardPile.getFirst().toString());
            currentPlayer.play(discardPile, deckPile);
            if (currentPlayer.getPlayerStatus() == PlayerStatus.YANIV) {
                gameStatus = GameStatus.FINISHEDROUND;
                endOfRound();
                numRound++;
                endOfGame();
                break;
            }
        }
    }

    @Override
    public void endOfRound() {
        System.out.println("La manche " + numRound + " est terminée.");
        System.out.println("Décompte des points:");
        for (Player player : players) {
            if (player != currentPlayer) {
                player.addPoints(player.getHand());
                System.out.println(player.getName() + " : " + player.getPoints());
                if (player.hasAssafDeclaration(currentPlayer)) {
                    currentPlayer.addPoints(30);
                }
            }
        }
        System.out.println(currentPlayer.getName() + " : " + currentPlayer.getPoints());

        for (Player player : players) {
            if (player.isLoser()) {
                System.out.println("Le joueur " + player.getName() + " a perdu et est éliminé.");
            }
        }

        removeLosers();

    }

    @Override
    public void endOfGame() {
        if (this.nbPlayers == 1) {
            System.out.println(currentPlayer.getName() + " remporte la  partie!");
            hasNextRound = false;
        }
    }

    public void removeLosers() {
        for (Player player : players) {
            if (player.getPlayerStatus() == PlayerStatus.LOSER) {
                System.out.println("Le joueur " + player.getName() + " a perdu et est éliminé.");
                players.remove(player);
                nbPlayers--;
            }
        }
    }
}
