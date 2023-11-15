package fr.pantheonsorbonne.miage.card;

import java.util.Arrays;

public class DiscardPile extends Pile{

    public DiscardPile(){
        super();
    }

    public Card getFirst(){
        return ((DiscardPile) pile).getFirst();
    }

    public void add(Card... cards) { //prend un nombre variable d'arguments, représentant les cartes de la pile à défausse
        pile.addAll(Arrays.asList(cards)); //Les cartes sont ajoutées à la discardpile dans l'ordre.
    }


}