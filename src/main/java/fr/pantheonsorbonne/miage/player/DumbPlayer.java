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

    public DumbPlayer(int numero) {
        super(numero);
    }

    @Override
    public void play(DiscardPile discardPile, DeckPile deckPile) {
        if(pointsHand()<=7){
            playerStatus=PlayerStatus.YANIV;
            System.out.println("Joueur "+numero+" déclare 'Yaniv'. ");
            return;
        }
        boolean wantDeck = this.random.nextBoolean(); // renvoie true si il veut piocher et false si il veut pendre une carte défaussée
        Card cardDiscardPile=null;
        if (wantDeck||discardPile.isEmpty()) {
            System.out.println("Joueur "+numero+ " décide de piocher");
            pickDeckPile(deckPile, hand);
        } else {
            System.out.println("Joueur "+numero+ " décide de prendre la carte de la pile  de défausse");
            cardDiscardPile= pickDiscardPile(discardPile);
        }
        discardCards(discardPile);
        if(!(wantDeck||discardPile.isEmpty())){
            hand.add(cardDiscardPile);
        }
    }

    @Override
    public void discardCards(DiscardPile discardPile) { //defausse
        discardPile.add(chooseDiscardCards());
    }

    @Override
    public List<Card> chooseDiscardCards() { //choisi cartes a defausser
        List<Card> discardedCards= new ArrayList<>();
        Card card = hand.get(random.nextInt(hand.size())); //choisir une carte au hasard a défausser
        discardedCards.add(card);
        System.out.print("Carte(s) défaussée(s): " +card.toString()+" ");
        for(Card cardHand: hand){ //verifier si ya un double ou un triple
            if(cardHand.getCardValue().getRank()==card.getCardValue().getRank()&&!(cardHand.equals(card))){
                discardedCards.add(cardHand);
                System.out.print(cardHand.toString()+" ");
            }
        }
        System.out.println();
        hand.removeAll(discardedCards);
        return discardedCards;
    }

}