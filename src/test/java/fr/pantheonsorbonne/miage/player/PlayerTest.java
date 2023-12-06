package fr.pantheonsorbonne.miage.player;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.card.Card;
import fr.pantheonsorbonne.miage.card.DeckPile;
import fr.pantheonsorbonne.miage.card.enums.CardColor;
import fr.pantheonsorbonne.miage.card.enums.CardValue;

public class PlayerTest{

    PlayerTest engine;
    Player player;
    Card card;

    @Test
    public void testgetNumero(){
        player = new DumbPlayer(1);
        assertEquals(1, player.getNumero());

    } 

    @Test
    public void testGetPlayerStatus(){
        player = new DumbPlayer(1);
        assertEquals("NORMAL",player.getPlayerStatus().name());

    }

   @Test
    public void testSetPlayerStatus(){
        player = new DumbPlayer(1);
        player.setPlayerStatus(PlayerStatus.LOSER);
        assertEquals("LOSER", player.getPlayerStatus().name());
    }

    
    @Test
    public void testgetPoints(){
        player = new DumbPlayer(1);
        assertEquals(0,player.getPoints());

    }

    @Test 
    public void testAddPoints(){
        player = new DumbPlayer(1);
        player.addPoints(50);
        assertEquals(50, player.getPoints());
    }

   @Test
    public void testIsLoserFalse(){
        player = new DumbPlayer(1);
        player.addPoints(50);
        assertEquals(false, player.isLoser());
   }

   @Test 
   public void testisLoserTrue(){
        player = new DumbPlayer(1);
        player.addPoints(100);
        assertEquals(true,player.isLoser());

   }

  @Test 
    public void testGetHand(){
        player = new DumbPlayer(1);
        List<Card> cards = new LinkedList<Card>();
        Card cardHand = new Card(CardColor.CLUB, CardValue.ACE);
        Card cardHand2 = new Card(CardColor.HEART, CardValue.TEN);
        cards.add(cardHand);
        cards.add(cardHand2);
        player.setHand(cards);
        assertEquals(cards,player.getHand());
    }

    @Test
    public void testPointsHand(){
        player = new DumbPlayer(1);
        List<Card> hand = new LinkedList<Card>();
        Card card = new Card(CardColor.HEART, CardValue.KING);
        Card card2 = new Card(CardColor.DIAMOND,CardValue.ACE);
        hand.add(card);
        hand.add(card2);
        player.setHand(hand);
        assertEquals(11,player.pointsHand());

    }

    @Test
    public void testSumPoint(){
        player = new DumbPlayer(1);
        List<Card> hand = new LinkedList<Card>();
        Card card = new Card(CardColor.HEART, CardValue.JACK);
        Card card2 = new Card(CardColor.DIAMOND,CardValue.EIGHT);
        hand.add(card);
        hand.add(card2);
        player.setHand(hand);
        assertEquals(19,player.sumPoints(hand));
    }

    @Test 
    public void testAddPoint(){
        player = new DumbPlayer(1);
        player.addPoints(16);
        List<Card> hand = new LinkedList<Card>();
        Card card3 = new Card(CardColor.SPADE,CardValue.TWO);
        hand.add(card3);
        player.setHand(hand);
        player.addPoint(hand);
        assertEquals(18,player.getPoints());

    }

    @Test
    public void testpickDeckPile(){
        player = new DumbPlayer(1);
        DeckPile deck = new DeckPile(); 
        List<Card> hand = new LinkedList<Card>();
        Card card = new Card(CardColor.HEART,CardValue.TWO);
        hand.add(card);
        player.pickDeckPile(deck, hand);
        assertEquals(2,hand.size());
    }
    
}