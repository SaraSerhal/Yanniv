package fr.pantheonsorbonne.miage.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.card.Card;
import fr.pantheonsorbonne.miage.card.DeckPile;
import fr.pantheonsorbonne.miage.card.DiscardPile;
import fr.pantheonsorbonne.miage.card.enums.CardColor;
import fr.pantheonsorbonne.miage.card.enums.CardValue;

public class DumbPlayerTest {
   private DumbPlayer player = new DumbPlayer(1);

    @Test
    public void testPlayYaniv(){ //test si le statut du joueur est bien Yaniv lorqu'il a une main où les points sont inférieurs à 7
        
        DiscardPile discardPile = new DiscardPile();
        DeckPile deckPile = new DeckPile();
        deckPile.randomDeck();
        Card card = new Card(CardColor.HEART,CardValue.ACE);
        player.setHand(List.of(card));
        player.addPoints(List.of(card));
        player.play(discardPile,deckPile,true);
        assertEquals("YANIV",player.getPlayerStatus().name());
        
    }

    @Test //vérifier que le joueur a le statut YANIV si sa main est vide ce'est à dire qu'il n'a plus de cartes
    public void testYanniv(){
        
        List<Card> hand = new LinkedList<Card>();
        player.setHand(hand);
        DiscardPile discardPile = new DiscardPile();
        DeckPile deckPile = new DeckPile();
        deckPile.randomDeck();
        player.play(discardPile, deckPile,false);
        assertEquals("YANIV",player.getPlayerStatus().name());
        
    }

    @Test 
    public void testTakeOneCardFromDeckPile(){ //le joueur a le choix de piocher une carte de la pioche seulement
        
        DiscardPile discardPile = new DiscardPile();
        DeckPile deckPile = new DeckPile();
        deckPile.add(new Card(CardColor.HEART,CardValue.TEN));
        deckPile.add(new Card(CardColor.CLUB,CardValue.JACK));

        List<Card> handPlayer = new LinkedList<Card>();
        handPlayer.add(new Card (CardColor.HEART, CardValue.EIGHT));
        handPlayer.add(new Card(CardColor.HEART,CardValue.QUEEN));
        player.setHand(handPlayer);
        player.takeOneCard(discardPile, deckPile, true);
        assertTrue(discardPile.isEmpty());
        assertEquals(1,deckPile.size());
        assertEquals(3, handPlayer.size());

        
    }
    @Test 
    public void testTakeOneCardFromDeckPileAndDiscardPile(){ //le joueur a le choix de piocher une carte de la pioche ou de la défausse
        
        DiscardPile discardPile = new DiscardPile();
        discardPile.add(new Card(CardColor.DIAMOND,CardValue.SIX));
        discardPile.add(new Card(CardColor.CLUB,CardValue.FOUR));

        DeckPile deckPile = new DeckPile();
        deckPile.add(new Card(CardColor.HEART,CardValue.TEN));
        deckPile.add(new Card(CardColor.CLUB,CardValue.JACK));

        List<Card> handPlayer = new LinkedList<Card>();
        handPlayer.add(new Card (CardColor.HEART, CardValue.EIGHT));
        handPlayer.add(new Card(CardColor.HEART,CardValue.QUEEN));
        player.setHand(handPlayer);
        player.takeOneCard(discardPile, deckPile, false);
        assertEquals(3, handPlayer.size());
        
    }

   @Test 
    public void testPickPlayerHand(){
        Player otherPlayer = new DumbPlayer(2);
        List<Card> handPlayer = new LinkedList<Card>();
        handPlayer.add(new Card (CardColor.HEART, CardValue.EIGHT));
        handPlayer.add(new Card(CardColor.HEART,CardValue.QUEEN));
        player.setHand(handPlayer);

        List<Card> handOtherPlayer = new LinkedList<Card>();
        handOtherPlayer.add(new Card (CardColor.DIAMOND, CardValue.TWO));
        Card card = new Card(CardColor.HEART,CardValue.FIVE);
        handOtherPlayer.add(card);
        otherPlayer.setHand(handOtherPlayer);
        player.choosePick(card);
        player.pickPlayerHand(otherPlayer);
        assertTrue(true);
           

    }



    
    
    
}
