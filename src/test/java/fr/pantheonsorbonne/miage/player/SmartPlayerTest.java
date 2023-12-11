package fr.pantheonsorbonne.miage.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.card.Card;
import fr.pantheonsorbonne.miage.card.DiscardPile;
import fr.pantheonsorbonne.miage.card.enums.CardColor;
import fr.pantheonsorbonne.miage.card.enums.CardValue;

public class SmartPlayerTest {
    SmartPlayer player = new SmartPlayer(1);
    
    @Test
    public void testChooseDiscardCardsHasDouble(){ //cartes à défausser quand le joueur a un double
        List<Card> hand = new LinkedList<Card>();
        Card card = new Card(CardColor.HEART, CardValue.TEN);
        Card card2 = new Card(CardColor.DIAMOND,CardValue.ACE);
        Card card3 = new Card(CardColor.SPADE, CardValue.TEN);
        hand.add(card);
        hand.add(card2);
        hand.add(card3);
        
        player.setHand(hand);
        player.chooseDiscardCards();
        assertEquals(2, player.getHand().size());
        
    }

    @Test 
    public void testChooseDeck(){
        DiscardPile discardPile = new DiscardPile();
        discardPile.add(new Card(CardColor.HEART, CardValue.TWO));
        discardPile.add(new Card(CardColor.CLUB, CardValue.FOUR));
        List<Card> hand = new LinkedList<Card>();
        Card card = new Card(CardColor.HEART, CardValue.TEN);
        Card card2 = new Card(CardColor.DIAMOND,CardValue.ACE);
        Card card3 = new Card(CardColor.SPADE, CardValue.TEN);
        hand.add(card);
        hand.add(card2);
        hand.add(card3);
        
        player.setHand(hand);
        assertTrue(player.chooseDeck(discardPile));

    }


    
}
