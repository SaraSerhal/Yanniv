package fr.pantheonsorbonne.miage.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Pile {
    protected List<Card> pile; // stocker les cartes dans une pile

    public Pile() {
        pile = new ArrayList<>();
    }

    public List<Card> getCard(int nb) { //Cette méthode retourne un nombre nb cartes à partir de la pile
      return Collections.EMPTY_LIST;
    }

    /**
     * Vérifie si la pile est vide.
     */
    public boolean isEmpty() {
        return pile.isEmpty();
    }

    /**
     * Obtient le nombre de cartes dans la pile .
     */
    public int size() {
        return pile.size();
    }

    public Card pop() { // renvoie la carte au dessus de la pile et la supprime
        return pile.remove(pile.size() - 1);
    }

    public List<Card> getPile() {
        return pile;
    }

    public void setPile(List<Card> cards) {
        pile = cards;
    }
}
