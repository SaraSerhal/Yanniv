package fr.pantheonsorbonne.miage.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckPile extends Pile {

    public DeckPile() {
        super();
    }

    public void randomDeck() { // mélange les cartes de la pioche, les cartes du deck sont face cachée
        List<Card> cards ;
        if (pile.isEmpty()) { // si la pile cards est vide
            cards = Card.getAllPossibleCards(); // génère toutes les cartes possibles dans la pile
        }else{
            cards = new ArrayList<>(pile); // Convertit la Deque en une liste temporaire
            pile.clear();
        }
        Collections.shuffle(cards); // mélange l'ordre des cartes
        pile.addAll(cards);

    }
}
