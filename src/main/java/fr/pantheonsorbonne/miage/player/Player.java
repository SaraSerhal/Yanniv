package fr.pantheonsorbonne.miage.player;
import fr.pantheonsorbonne.miage.card.DeckPile;
import fr.pantheonsorbonne.miage.card.DiscardPile;

public abstract class Player {

    private String name;
    //private List<Card> hand;
    private int points;

    public Player(String name) {
        this.name = name;
        this.points=0;
        //this.hand= new LinkedList<Card>();
    }

    public abstract void play(DiscardPile discardPile, DeckPile deckPile);

    public String getName() {
        return this.name;
    }

    public void addPoints(int nb){
        points+=nb;
    }

    public int getPoints(){
        return points;
    }

    public boolean isWinnerRound() {
        //TODO
    }
}