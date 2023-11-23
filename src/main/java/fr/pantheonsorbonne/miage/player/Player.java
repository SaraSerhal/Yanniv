package fr.pantheonsorbonne.miage.player;

import java.util.LinkedList;
import java.util.List;

import fr.pantheonsorbonne.miage.card.Card;
import fr.pantheonsorbonne.miage.card.DeckPile;
import fr.pantheonsorbonne.miage.card.DiscardPile;

public abstract class Player {

    protected String name;
    protected List<Card> hand;
    protected int points;
    protected Boolean sayYaniv;
    protected PlayerStatus playerStatus;

    public Player(String name) {
        this.name = name;
        this.points = 0;
        this.hand = new LinkedList<Card>();
        sayYaniv = false;
        playerStatus = PlayerStatus.NORMAL;
    }

    public abstract void play(DiscardPile discardPile, DeckPile deckPile);

    public String getName() {
        return this.name;
    }

    public List<Card> getHand() {
        return this.hand;
    }

    public int getPoints() {
        return points;
    }

    public PlayerStatus getPlayerStatus() {
        return playerStatus;
    }

    public void setPlayerStatus(PlayerStatus pstatus) {
        playerStatus = pstatus;
    }

    public void setHand(List<Card> cards){
        hand= cards;
    }

    public void pickDeckPile(DeckPile deckPile, List<Card> hand) {
        hand.add(deckPile.pop());
    }

    public void pickDiscardPile(DiscardPile discardPile, List<Card> hand) {
        hand.add(discardPile.pop());
    }

    public abstract void discardCards(DiscardPile discardPile);

    public abstract List<Card> chooseDiscardCards();

    public void addPoints(List<Card> hand) {
        points += sumPoints(hand);
    }

    public void addPoints(int nb) {
        points += nb;
    }

    public int sumPoints(List<Card> hand) {
        int sum = 0;
        for (Card card : hand) {
            sum += card.getValue().getRank();
        }
        return sum;
    }

    public void updateStatus(Player playerSayYaniv) {
        if (sumPoints(hand) <= playerSayYaniv.sumPoints(playerSayYaniv.getHand())
                && playerStatus != PlayerStatus.YANIV) {
            playerStatus = PlayerStatus.ASSAF;
            System.out.println(getName() + " déclare 'Assaf'. " + playerSayYaniv.getName()
                    + " est pénalisé et récupère 30 points.");
            playerSayYaniv.addPoints(30);
        }
        if (points >= 100) {
            System.out.println("Le joueur " + getName() + " a perdu et est éliminé.");
            playerStatus = PlayerStatus.LOSER;
        }
    }
}