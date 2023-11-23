package fr.pantheonsorbonne.miage.player;

import java.util.ArrayList;
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
        boolean randomBoolean = this.random.nextBoolean(); // renvoie true si il veut piocher et false si il veut pendre une carte défaussée
        if (randomBoolean) {
            System.out.println(name + " décide de piocher");
            if(deckPile.isEmpty()){
                List<Card> deckCard= new ArrayList<Card>();
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
        //pose une carte random qui est supprimé de hand
        discardCards(discardPile);
    }

    @Override
    public void discardCards(DiscardPile discardPile) {
        discardPile.add(chooseDiscardCards());
    }

    @Override
    public List<Card> chooseDiscardCards() {
        System.out.println("Carte(s) défaussée(s): ");
        List<Card> discardedCards= new ArrayList<>();
        int indexRandom = random.nextInt(hand.size());
        Card card = discardedCards.get(indexRandom);
        discardedCards.add(card);
        System.out.print(card.toString());
        for(Card cardHand: hand){
            if(cardHand.equals(card)){
                discardedCards.add(cardHand);
                System.out.print(card.toString());
            }
        }
        return discardedCards;
    }

}