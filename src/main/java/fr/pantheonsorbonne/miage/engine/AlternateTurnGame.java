package fr.pantheonsorbonne.miage.engine;

import java.util.ArrayList;
import java.util.List;

import fr.pantheonsorbonne.miage.card.Card;
import fr.pantheonsorbonne.miage.player.Player;
import fr.pantheonsorbonne.miage.player.PlayerStatus;

public abstract class AlternateTurnGame extends GameImpl {
    protected Player currentPlayer;

    public AlternateTurnGame() {
        super();
    }

    public Player getFirstPlayer() {
        if (currentPlayer == null) {
            currentPlayer = players.get(random.nextInt(nbPlayers));
        } else {
            for (Player player : players) {
                if (player.getPlayerStatus() == PlayerStatus.YANIV) {
                    currentPlayer = player;
                    break;
                }
            }
        }
        return currentPlayer;
    }

public Player getNextPlayer(){
    return players.get((indPlayer(currentPlayer)+1)%players.size());
}

public int indPlayer(Player p){
    int i=0;
    for( Player player: players){
        if(player.equals(p)){
            return i;
        }else{
            i++;
        }
    }
    return -1;
}

    @Override
    public void goNextRound() {
        if (!hasNextRound) {
            return;
        }
        System.out.println("\n_________\nManche " + numRound + " :\n");
        currentPlayer = getFirstPlayer();
        nextPlayerTurns();
    }

    @Override
    public void nextPlayerTurns() {
        for (;;) {// boucle qui s'arrete quand la manche est finie, chaque iteration est un joueur
                  // qui joue
            if (deckPile.isEmpty()) {
                remakeDeckPile();
            }
            System.out.println("Tour du joueur " + currentPlayer.getNumero() + ":");
            if (!discardPile.isEmpty()) {
                System.out.println("Première carte sur la pile de défausse: " + discardPile.getFirst().toString());
            }
            currentPlayer.play(discardPile, deckPile);
            if (currentPlayer.getPlayerStatus() == PlayerStatus.YANIV) {
                gameStatus = GameStatus.FINISHEDROUND;
                endOfRound();
                numRound++;
                endOfGame();
                break;
            }
            currentPlayer=getNextPlayer();
            System.out.println();
        }
    }

    @Override
    public void endOfRound() {
        System.out.println("La manche " + numRound + " est terminée.");
        System.out.println("Décompte des points:");
        for (Player player : players) {
            if (player != currentPlayer) {
                player.addPoints(player.getHand());
                System.out.println("Joueur " + player.getNumero() + " : " + player.getPoints());
                if (player.hasAssafDeclaration(currentPlayer)) {
                    currentPlayer.addPoints(30);
                }
            }
        }
        System.out.println("Joueur " + currentPlayer.getNumero() + " : " + currentPlayer.getPoints());
        removeLosers();

    }

    @Override
    public void endOfGame() {
        if (this.nbPlayers == 1) {
            System.out.println("Le joueur " + currentPlayer.getNumero() + " remporte la  partie!");
            hasNextRound = false;
        }
    }

    @Override
    public void removeLosers() {
        for (Player player : players) {
            if (player.isLoser()) {
                System.out.println("Le joueur " + player.getNumero() + " a perdu et est éliminé.");
                players.remove(player);
                nbPlayers--;
            }
        }
    }

    public void remakeDeckPile(){
        Card discardCard=discardPile.takeFist();
        deckPile.addAll(discardPile.getPile());
        deckPile.randomDeck();
        discardPile.add(discardCard);
    }
}
