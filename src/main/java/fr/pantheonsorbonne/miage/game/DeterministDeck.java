package fr.pantheonsorbonne.miage.game;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class DeterministDeck implements Deck {

    private final Queue<Card> cards = new LinkedList<>(); //La file est choisie ici car elle permet de retirer facilement les cartes dans l'ordre.


    public DeterministDeck(Card... cards) { //prend un nombre variable d'arguments, représentant les cartes du deck
        this.cards.addAll(Arrays.asList(cards)); //Les cartes sont ajoutées à la file (cards) dans l'ordre.
    }


    @Override
    public Card[] getCards(int length) { //tirer un nombre spécifié de cartes du deck
        Card[] res = new Card[length]; //crée un tableau de cartes res de la longueur spécifiée
        for (int i = 0; i < length; i++) {
            res[i] = this.cards.poll(); //remplit ce tableau en retirant les cartes de la file (cards)
        }
        return res;
    }
}
