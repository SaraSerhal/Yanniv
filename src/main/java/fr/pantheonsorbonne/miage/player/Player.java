package fr.pantheonsorbonne.miage.player;
import java.util.ArrayList;
import java.util.List;

import fr.pantheonsorbonne.miage.card.Card;
import fr.pantheonsorbonne.miage.card.DeckPile;
import fr.pantheonsorbonne.miage.card.DiscardPile;

public abstract class Player {

    protected String name;
    protected List<Card> hand;
    private int points;

    public Player(String name) {
        this.name = name;
        this.points=0;
        this.hand= new ArrayList<Card>();
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

    public abstract boolean sayYaniv() ;

    public void pickDeckPile(DeckPile deckPile, List<Card> hand){
        hand.add(deckPile.pop());
    }

    public void pickDiscardPile(DiscardPile discardPile,List<Card> hand){
        hand.add(discardPile.pop());
    }

    public abstract void discardCards(DiscardPile discardPile);

    public abstract List<Card> chooseDiscardCards();

    //
}