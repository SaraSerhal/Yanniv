package fr.pantheonsorbonne.miage.card;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.card.enums.CardColor;
import fr.pantheonsorbonne.miage.card.enums.CardValue;

public class PileTest {
    
    @Test
    public void testIsEmpty(){
        Pile pile = new DeckPile();
        assertEquals(true, pile.isEmpty());
    }

    @Test //vérifier que la card est bien ajoutée à la Pile
    public void testAddToPile(){
        Pile pile = new DeckPile();
        Card card = new Card(CardColor.HEART, CardValue.EIGHT);
        pile.add(card);
        assertEquals(1,pile.size());
    }

    @Test
    public void testSetPile(){
        Pile pile = new DeckPile();
        Card card = new Card(CardColor.HEART,CardValue.KING);
        Card card2 = new Card(CardColor.SPADE,CardValue.FIVE);
        Deque<Card> deckCard = new ArrayDeque<Card>();
        deckCard.add(card);
        deckCard.add(card2);
        pile.setPile(deckCard);
        assertEquals(true,!pile.isEmpty());   
    }

    @Test
    public void testPileSize(){ //vérifier la taille de la pile
        Pile pile = new DeckPile();
        Card card = new Card(CardColor.DIAMOND,CardValue.ACE);
        Card card2 = new Card(CardColor.CLUB,CardValue.FOUR);
        Deque<Card> deckCard = new ArrayDeque<Card>();
        deckCard.add(card);
        deckCard.add(card2);
        pile.setPile(deckCard);
        assertEquals(2,pile.size());
    }

    @Test
    public void testAddAllToPile(){
        Pile pile = new DeckPile();
        Deque<Card> deque = new ArrayDeque<Card>();
        Card card = new Card(CardColor.DIAMOND,CardValue.ACE);
        Card card2 = new Card(CardColor.CLUB,CardValue.FOUR);
        deque.add(card);
        deque.add(card2);
        pile.addAll(deque);
        assertEquals(2,pile.size());
    }

    @Test
    public void testClearPile(){
        Pile pile = new DeckPile();
        Card card = new Card(CardColor.HEART,CardValue.KING);
        Card card2 = new Card(CardColor.SPADE,CardValue.FIVE);
        Deque<Card> deckCard = new ArrayDeque<Card>();
        deckCard.add(card);
        deckCard.add(card2);
        pile.setPile(deckCard);
        pile.clear();
        assertEquals(0,pile.size());
        assertEquals(true,pile.isEmpty());

    }

    @Test
    public void testGetFirst(){
        Pile pile = new DeckPile();
        Card card = new Card(CardColor.CLUB, CardValue.JACK);
        Card card2 = new Card(CardColor.DIAMOND, CardValue.FIVE);
        pile.add(card);
        pile.add(card2);
        assertEquals(card2,pile.getFirst());
        
    }
    


}
