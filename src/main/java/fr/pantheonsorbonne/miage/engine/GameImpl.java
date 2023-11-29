package fr.pantheonsorbonne.miage.engine;

import java.util.LinkedList;
import java.util.List;

import fr.pantheonsorbonne.miage.card.Card;
import fr.pantheonsorbonne.miage.card.DeckPile;
import fr.pantheonsorbonne.miage.card.DiscardPile;
import fr.pantheonsorbonne.miage.player.Player;

public abstract class GameImpl implements Game {

    protected List<Player> players=new LinkedList<Player>();;
    protected int maxPlayers;
    protected int nbPlayers;
    protected GameStatus gameStatus;
    protected int numRound;
    protected DeckPile deckPile;
    protected DiscardPile discardPile;

    public GameImpl(int nb,List<Player> players) {
        // pour les test, verifier que le nombre de joueur est minimum 1;
        this.players = players;
        maxPlayers = nb;
        nbPlayers = nb;
        gameStatus = GameStatus.ONGOING;
        numRound = 1;
        deckPile= new DeckPile();
        discardPile = new DiscardPile();

    }

    public void giveCardsToPlayer() {
        for(Player player: players){
            List<Card> cards=deckPile.takeCards(7);
            player.setHand(cards);
        }
    }

    @Override
    public void start() {
        System.out.println("La partie commence: ");
        System.out.println("Joueurs: ");
        for(Player player: players){
            System.out.print(player.getName()+"-");
        }
        goNextRound();
    }

    public void installation(){
        deckPile.RandomDeck() ;
        giveCardsToPlayer();
    }
}