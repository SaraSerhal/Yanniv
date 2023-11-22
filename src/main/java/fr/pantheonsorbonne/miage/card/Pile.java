package fr.pantheonsorbonne.miage.card;

import java.util.ArrayList;
import java.util.List;

public abstract class Pile {
    protected List<Card> pile;;  // stocker les cartes dans une pile

    public Pile(){
        pile = new ArrayList<>();
    }

    public Card[] getCard(int nb) { //Cette méthode retourne nb cartes à partir de la pile
        Card[] res = new Card[nb];
        for(int i=0;i<nb;i++){
            res[i]=((ArrayList<Card>) pile).remove(pile.size()); //retire et renvoie le premier element de pile
        }

        return res;  //tableau de cartes de longueur nb.
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

    public Card pop(){ //renvoie la carte au dessus de la pile et la supprime
        return pile.remove(pile.size()-1);
    }
}
