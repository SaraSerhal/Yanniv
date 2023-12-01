package fr.pantheonsorbonne.miage.card;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public abstract class Pile {
    protected Deque<Card> pile; // stocker les cartes dans une pile
 //remplacer par queue
    public Pile() {
        pile = new ArrayDeque<>();
    }

    public LinkedList<Card> takeCards(int nb) { //Cette méthode retourne un nombre nb cartes à partir de la pile
        Collection<Card> cards =new LinkedList<Card>();
        for(int i=0;i<nb;i++){
            cards.add(pile.pop()); //retire et renvoie l'element au dessus de la pile
        }//renvoyer une exception si la pile est vide
        /*Card card = pile.pollLast(); // Retire et récupère l'élément au-dessus de la pile
        if (card == null) {
            // Gérer le cas où la pile est vide (éventuellement lever une exception)
            // ...
        } else {
            cards.add(card); // Ajoute l'élément récupéré à la liste de cartes
        }*/
        return (LinkedList<Card>) cards;  //pile de cartes de longueur nb.
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

    public Card getFirst(){
        if (pile.isEmpty()) {
            throw new NoSuchElementException("La pile est vide, impossible de récupérer le dernier élément.");
        }
        return pile.peekLast();

    }

    public Card takeFist(){
        return pile.pollLast();
    }

    public void addAll(Deque<Card> cards){
        pile.addAll(cards);
    }

    public void add(Card card){
        pile.add(card);
    }
}
