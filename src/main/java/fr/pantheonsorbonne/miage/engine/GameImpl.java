package fr.pantheonsorbonne.miage.engine;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import fr.pantheonsorbonne.miage.card.Card;
import fr.pantheonsorbonne.miage.card.DeckPile;
import fr.pantheonsorbonne.miage.card.DiscardPile;
import fr.pantheonsorbonne.miage.player.Player;
import fr.pantheonsorbonne.miage.player.PlayerStatus;

public abstract class GameImpl implements Game {

    protected List<Player> players=new LinkedList<Player>();;
    protected int maxPlayers;
    protected int nbPlayers;
    private GameStatus gameStatus;

    public GameImpl(int nb,List<Player> players) {
        // pour les test, verifier que le nombre de joueur est minimum 1;
        this.players = players;
        maxPlayers = nb;
        nbPlayers = nb;
        gameStatus = GameStatus.ONGOING;
    }

    public void giveCardsToPlayer(List<Player> players,DeckPile deckPile) {
        for(Player player: players){
            List<Card> cards=deckPile.getCard(7);
            player.setHand(cards);
        }
    }

    @Override
    public void start(DiscardPile discardPile, DeckPile deckPile) {
        System.out.println("La partie commence: ");
        System.out.println("Joueurs: ");
        for(Player player: players){
            System.out.print(player.getName()+"-");
        }
        int numRound = 1;
        nextRound: for (;;) {// boucle qui s'arrete quand la partie est finie, chaque itération est une
                             // manche
            System.out.println("\nManche " + numRound + " :\n");
            Player p = getNextPlayer();
            for (;;) {// boucle qui s'arrete quand la manche est finie, chaque iteration est un joueur
                      // qui joue
                System.out.println("Tour de " + p.getName()+":");
                System.out.println("Première carte sur la pile de défausse: " + discardPile.getFirst());
                p.play(discardPile, deckPile);
                if (p.getPlayerStatus() == PlayerStatus.YANIV) {
                    gameStatus = GameStatus.FINISHEDROUND;
                }
                switch (gameStatus) {

                    case ONGOING -> {
                        continue;
                    }

                    case FINISHEDROUND -> {
                        System.out.println("La manche " + numRound + " est terminée.");
                        System.out.println("Décompte des points:");
                        for (Player player : players) {
                            player.addPoints(player.getHand());
                            System.out.println(player.getName() + " : " + player.getPoints());
                            player.updateStatus(p);
                            if(player.getPlayerStatus()==PlayerStatus.LOSER){
                                System.out.println("Le joueur " + player.getName() + " a perdu et est éliminé.");
                                players.remove(player);
                                nbPlayers--;
                            }
                        }
                        numRound++;
                        if (this.nbPlayers == 1) {
                            System.out.println(p.getName() + " remporte la  partie!");
                            break nextRound;
                        }
                        continue nextRound;
                    }
                }
            }
        }
    }
}