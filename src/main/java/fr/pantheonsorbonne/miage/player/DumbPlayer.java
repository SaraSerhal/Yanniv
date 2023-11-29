package fr.pantheonsorbonne.miage.player;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;

import fr.pantheonsorbonne.miage.card.Card;
import fr.pantheonsorbonne.miage.card.DeckPile;
import fr.pantheonsorbonne.miage.card.DiscardPile;

public class DumbPlayer extends Player {

    Random random = new Random();

    public DumbPlayer(String name) {
        super(name);
    }

    @Override
    public void play(DiscardPile discardPile, DeckPile deckPile) {
        if(points<=7){
            playerStatus=PlayerStatus.YANIV;
            System.out.println(name+" déclare 'Yaniv'. ");
            return;
        }
        boolean wantDeck = this.random.nextBoolean(); // renvoie true si il veut piocher et false si il veut pendre une carte défaussée
        if (wantDeck) {
            System.out.println(name + " décide de piocher");
            if(deckPile.isEmpty()){
                Deque<Card> deckCard= new ArrayDeque<Card>();
                deckCard.add(discardPile.getFirst());
                discardPile.getPile().removeAll(deckCard);
                deckPile.setPile(discardPile.getPile());
                discardPile.setPile(deckCard);
            }
            pickDeckPile(deckPile, hand);
        } else {
            System.out.println(name + " décide de prendre la carte de la pile  de défausse");
            pickDiscardPile(discardPile, hand);
        }
        discardPile.add(chooseDiscardCards());
        discardCards(discardPile);
    }

    @Override
    public void discardCards(DiscardPile discardPile) {
        discardPile.add(chooseDiscardCards());
    }

    @Override
    public List<Card> chooseDiscardCards() {
        List<Card> discardedCards= new ArrayList<>();
        Card card = discardedCards.get(random.nextInt(hand.size())); //choisir une carte au hasard a défausser
        discardedCards.add(card);
        System.out.print("Carte(s) défaussée(s): " +card.toString());
        for(Card cardHand: hand){ //verifier si ya un double ou un triple
            if(cardHand.equals(card)){
                discardedCards.add(cardHand);
                System.out.print(card.toString());
            }
        }
        hand.removeAll(discardedCards);
        return discardedCards;
    }

}