package fr.pantheonsorbonne.miage.engine;

import java.util.LinkedList;
import java.util.List;

import fr.pantheonsorbonne.miage.card.DeckPile;
import fr.pantheonsorbonne.miage.card.DiscardPile;
import fr.pantheonsorbonne.miage.player.Player;

public abstract class GameImpl implements Game {

    protected final List<Player> players;
    protected int maxPlayers;
    protected int nbPlayers;

    public GameImpl(int nb) {
        //pour les test, verifier que le nombre de joueur est minimum 1;
        players= new LinkedList<Player>();
        maxPlayers = nb;
        nbPlayers=nb;
    }

    @Override
    public void start(DiscardPile discardPile, DeckPile deckPile) {
        System.out.println("La partie commence: \n");
        int numRound=1;
        nextRound:
        for (; ; ) {//boucle qui  s'arrete quand la partie est finie, chaque itération est une manche
            System.out.println("Manche "+numRound+" :");
            Player p = getNextPlayer();
            for (; ; ) {//boucle qui s'arrete quand la manche est finie, chaque iteration est un joueur qui joue
                System.out.println("Tour de "+p.getName());
                System.out.println("Première carte sur la pile de défausse: "+ discardPile.getFirst());
                p.play(discardPile, deckPile);
                switch (gameStatus()) {

                    case ONGOING -> {
                        continue;
                    }

                    case FINISHEDROUND -> {
                        System.out.println("La manche "+numRound+" est terminée.");
                        System.out.println("Décompte des points:");
                        for(Player player:players){
                            System.out.println(player.getName()+" : "+player.getPoints());
                            if(player.getPoints()>=100){
                                System.out.println("Le joueur "+player.getName()+" a perdu et est éliminé.");
                                players.remove(player);
                                nbPlayers--;
                            }
                        }
                        numRound++;
                        if(this.nbPlayers==1){
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