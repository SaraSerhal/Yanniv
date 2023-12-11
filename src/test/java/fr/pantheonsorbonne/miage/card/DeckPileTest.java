package fr.pantheonsorbonne.miage.card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.card.enums.CardColor;
import fr.pantheonsorbonne.miage.card.enums.CardValue;

public class DeckPileTest{

    @Test
    public void testRandomDeck(){
        DeckPile deck = new DeckPile();
        deck.randomDeck();
        Deque<Card> pile = deck.getPile();
        assertEquals(Card.getAllPossibleCards().size(), pile.size());      
    }

    @Test 
    public void testTakeCards(){
        DeckPile deck = new DeckPile();
        deck.addAll(new ArrayDeque<Card>(List.of(
            new Card(CardColor.HEART,CardValue.ACE),
            new Card(CardColor.DIAMOND, CardValue.EIGHT),
            new Card(CardColor.CLUB, CardValue.SIX))          
        ));

        LinkedList<Card> cards = deck.takeCards(1);
        assertEquals(1,cards.size());
        assertEquals(2, deck.size());
        assertThrows(NoSuchElementException.class, () -> {
            deck.takeCards(5);
        });

    }


}
