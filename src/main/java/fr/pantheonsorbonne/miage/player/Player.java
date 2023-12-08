package fr.pantheonsorbonne.miage.player;

import java.util.LinkedList;
import java.util.List;

import fr.pantheonsorbonne.miage.card.Card;
import fr.pantheonsorbonne.miage.card.DeckPile;
import fr.pantheonsorbonne.miage.card.DiscardPile;
import fr.pantheonsorbonne.miage.card.enums.DiscardedCards;
import fr.pantheonsorbonne.miage.card.enums.PowerCardStatus;

public abstract class Player {
    protected int numero;
    protected List<Card> hand;
    protected DiscardedCards discardedCards;
    protected int points;
    protected Boolean sayYaniv;
    protected PlayerStatus playerStatus;
    protected PowerCardStatus powerCardStatus;

    public Player(int numero) {
        this.numero = numero;
        this.points = 0;
        this.hand = new LinkedList<Card>();
        discardedCards = DiscardedCards.EMPTY;
        sayYaniv = false;
        playerStatus = PlayerStatus.NORMAL;
        powerCardStatus = PowerCardStatus.NOTHING;
    }

    public abstract void play(DiscardPile discardPile, DeckPile deckPile, boolean canChooseOnlyDeck);

    public abstract void pickPlayerHand(Player player);

    public abstract void winPower();

    public abstract void discardCards(DiscardPile discardPile);

    public abstract List<Card> chooseDiscardCards();

    public int getNumero() {
        return this.numero;
    }

    public List<Card> getHand() {
        return this.hand;
    }

    public List<Card> getDiscardedCards() {
        return this.discardedCards.getList();
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

    public void setHand(List<Card> cards) {
        hand = cards;
    }

    public void resetDiscardedCards() {
        discardedCards.reset();
        this.discardedCards = DiscardedCards.EMPTY;
    }

    public void setDiscardedCards(List<Card> discardedCards) {
        this.discardedCards.setList(discardedCards);
    }

    public DiscardedCards getdiscardedCards() {
        return discardedCards;
    }

    public void pickDeckPile(DeckPile deckPile) { // pioche 1 carte
        hand.add(deckPile.takeFist());
    }

    public void pickDiscardPile(DiscardPile discardPile) {
        hand.add(discardPile.takeFist());
    }

    public void addPoints(List<Card> hand) {
        points += sumPointsHand();
    }

    public void addPoints(int nb) {
        points += nb;
    }

    public int sumPointsHand() {
        int sum = 0;
        if (hand != null || !hand.isEmpty()) {
            for (Card card : hand) {
                sum += card.getCardValue().getValue();
            }
        }else{
            //renvoyer exception
        }
        return sum;
    }

    public boolean isLoser() {
        if (points >= 100) {
            playerStatus = PlayerStatus.LOSER;
            return true;
        }
        return false;
    }

    public boolean hasAssafDeclaration(Player playerSayYaniv) {
        if (sumPointsHand() <= playerSayYaniv.sumPointsHand()
                && playerStatus != PlayerStatus.YANIV) {
            return true;
        }
        return false;
    }

    public PowerCardStatus getPowerCardStatus() {
        return powerCardStatus;
    }

    public void setPowerCardStatus(PowerCardStatus value) {
        powerCardStatus = value;
    }

}