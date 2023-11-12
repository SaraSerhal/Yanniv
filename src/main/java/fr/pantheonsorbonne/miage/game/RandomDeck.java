package fr.pantheonsorbonne.miage.game;

import java.util.*;

/**
 * Represents a Deck of cards
 */
public class RandomDeck implements Deck {

    private final static Random random = new Random();

    private final Queue<Card> deck = new LinkedList<>();  // stocker les cartes du deck dans une file


    public RandomDeck() {  //créer un deck

        //generate all cards
        List<Card> cards = Card.getAllPossibleCards(); //  génère toutes les cartes possibles génère d'abord toutes les cartes possibles
        //shuffle
        Collections.shuffle(cards); //mélange l'ordre des cartes
        //associate with the deck
        for (int i = 0; i < cards.size(); i++) {
            this.deck.offer(cards.get(i)); //ajoute les cartes mélangées à la file deck
        }

    }


    @Override
    public Card[] getCards(int length) { //tirer un nombre spécifié de cartes du deck
        Card[] result = new Card[length]; //crée un tableau de cartes result de la longueur spécifiée
        for (int i = 0; i < length; i++) {
            result[i] = this.deck.poll();//remplit ce tableau en retirant les cartes de la file deck
        }
        return result;
    }

}
