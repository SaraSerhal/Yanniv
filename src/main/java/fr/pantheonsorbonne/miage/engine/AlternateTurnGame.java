package fr.pantheonsorbonne.miage.engine;

import fr.pantheonsorbonne.miage.player.Player;
import fr.pantheonsorbonne.miage.player.PlayerStatus;

public abstract class AlternateTurnGame extends GameImpl {
    protected Player currentPlayer;

    public AlternateTurnGame() {
        super();
    }

    @Override
    public Player getNextPlayer() {
        if(currentPlayer == null){
            currentPlayer = players.get(random.nextInt(nbPlayers));
        }else {
            for (Player player : players) {
                if (player.getPlayerStatus() == PlayerStatus.YANIV) { 
                    currentPlayer = player;
                    break;
                }
            }
        }
        return currentPlayer;
    }

    @Override
    public void goNextRound() {
            if (!hasNextRound) {
                return;
            }
            System.out.println("\nManche " + numRound + " :\n");
            currentPlayer = getNextPlayer();
            nextPlayerTurns();
    }

    @Override
    public void nextPlayerTurns() {
        for (;;) {// boucle qui s'arrete quand la manche est finie, chaque iteration est un joueur
                  // qui joue
            System.out.println("Tour du joueur " + currentPlayer.getNumero() + ":");
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
                System.out.println("Joueur "+player.getNumero() + " : " + player.getPoints());
                if (player.hasAssafDeclaration(currentPlayer)) {
                    currentPlayer.addPoints(30);
                }
            }
        }
        System.out.println("Joueur "+currentPlayer.getNumero() + " : " + currentPlayer.getPoints());

        for (Player player : players) {
            if (player.isLoser()) {
                System.out.println("Le joueur " + player.getNumero() + " a perdu et est éliminé.");
            }
        }
        removeLosers();

    }

    @Override
    public void endOfGame() {
        if (this.nbPlayers == 1) {
            System.out.println("Le joueur "+currentPlayer.getNumero()+ " remporte la  partie!");
            hasNextRound = false;
        }
    }

    @Override
    public void removeLosers() {
        for (Player player : players) {
            if (player.getPlayerStatus() == PlayerStatus.LOSER) {
                System.out.println("Le joueur " + player.getNumero() + " a perdu et est éliminé.");
                players.remove(player);
                nbPlayers--;
            }
        }
    }
}
