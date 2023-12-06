package fr.pantheonsorbonne.miage.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class DeckPile extends Pile {

    public DeckPile() {
        super();
    }

    public void randomDeck() { // mélange les cartes de la pioche, les cartes du deck sont face cachée
        List<Card> cards;
        if (pile.isEmpty() || pile == null) { // si la pile cards est vide
            cards = Card.getAllPossibleCards(); // génère toutes les cartes possibles dans la pile
        } else {
            cards = new ArrayList<>(pile); // Convertit la Deque en une liste temporaire
            pile.clear();
        }
        Collections.shuffle(cards); // mélange l'ordre des cartes
        pile.addAll(cards);
    }

    public LinkedList<Card> takeCards(int nb) { // Cette méthode retourne un nombre nb cartes à partir de la deck
        LinkedList<Card> cards = new LinkedList<Card>();
        for (int i = 0; i < nb; i++) {
            if (pile == null || pile.isEmpty()) {
                throw new NoSuchElementException("La pile est vide, impossible de prendre des cartes.");
            } else {
                cards.add(pile.pop()); // retire et renvoie l'element au dessus de la pile
            }
        } // renvoyer une exception si la pile est vide
        return cards; // pile de cartes de longueur nb.
    }
}
