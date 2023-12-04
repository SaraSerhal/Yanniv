package fr.pantheonsorbonne.miage.card;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public abstract class Pile {
    protected Deque<Card> pile; // stocker les cartes dans une pile
    // remplacer par queue

    public Pile() {
        pile = new ArrayDeque<>();
    }

    public boolean isEmpty() {
        return pile.isEmpty();
    }

    public int size() {
        return pile.size();
    }

    public Deque<Card> getPile() {
        return pile;
    }

    public void setPile(Deque<Card> deckCard) {
        pile = deckCard;
    }

    public Card getFirst() {
        if (pile.isEmpty()) {
            throw new NoSuchElementException("La pile est vide, impossible de récupérer le dernier élément.");
        }
        return pile.peekLast();

    }

    public Card takeFist() {
        return pile.pollLast();
    }

    public void addAll(Deque<Card> cards) {
        pile.addAll(cards);
    }

    public void add(Card card) {
        pile.add(card);
    }

    public void clear() {
        pile.clear();
    }
}
