package fr.pantheonsorbonne.miage.card;

import java.util.Collections;
import java.util.List;

public class DeckPile extends Pile{

    public DeckPile(){
        super();
    }

    public void RandomDeck(List<Card> cards) {  //créer un deck à partir d'une pile de cartes cards, les cartes du deck sont face cachée
        if(cards.isEmpty()){ //si la pile cards est vide
            cards = Card.getAllPossibleCards(); //génère toutes les cartes possibles dans la pile
        }
        //puis, melange les cartes dans la pile cards
        Collections.shuffle(cards); //mélange l'ordre des cartes
        for (int i = 0; i < cards.size(); i++) {
            pile.add(cards.get(i)); //ajoute les cartes mélangées à la pile deck
        }
    }
}
