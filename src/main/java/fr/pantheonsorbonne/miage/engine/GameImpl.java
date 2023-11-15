package fr.pantheonsorbonne.miage.engine;

import fr.pantheonsorbonne.miage.card.DeckPile;
import fr.pantheonsorbonne.miage.card.DiscardPile;
import fr.pantheonsorbonne.miage.player.Player;

public abstract class GameImpl implements Game {

    protected final Player[] players;

    public GameImpl(int nb) {
        players= new Player[nb];
    }

    @Override
    public void start(DiscardPile discardPile, DeckPile deckPile) {
        System.out.println("La partie commence: \n");
        int numRound=1;
        for (; ; ) {//boucle qui  s'arrete quand la partie est finie
            System.out.println("Manche "+numRound+" :");
            for (; ; ) {//boucle qui s'arrete quand la manche est finie
                Player p = getNextPlayer();
                System.out.println("Tour de "+p.getName());
                p.play(discardPile, deckPile);
                //System.out.println("Première carte sur la pile de défausse: "+ discardPile.getFirst());
                switch (gameStatus()) {

                    case ONGOING -> {
                        continue;
                    }

                    case FINISHEDROUND -> {
                        System.out.println("La manche "+numRound+" est terminée.");
                        if(p.isWinnerRound()){
                            System.out.println(p.getName()+" remporte la manche.");
                        }
                        System.out.println("Décompte des points:");
                        for(int i=0;i<players.length;i++){
                            System.out.println(players[i].getName()+" : "+players[i].getPoints());
                        }
                        return;
                    }
                }
            }
            numRound++;

            switch (gameStatus()) {

                case FINISHEDGAME -> {
                    System.out.println(p.getName() + " remporte la  partie!");
                    return;
                }
                default -> {
                    continue;
                }
            }
        }
        System.out.println();
    }

}