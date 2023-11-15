package fr.pantheonsorbonne.miage.card;

import java.util.LinkedList;
import java.util.List;

public abstract class Pile {
    protected List<Card> pile;;  // stocker les cartes dans une pile

    public Pile(){
        pile = new LinkedList<>();
    }

    public Card[] getCard(int nb) { //Cette méthode retourne nb cartes à partir de la pile
        Card[] res = new Card[nb];
        for(int i=0;i<nb;i++){
            res[i]=((LinkedList<Card>) pile).poll(); //retire et renvoie le premier element de pile
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
}
