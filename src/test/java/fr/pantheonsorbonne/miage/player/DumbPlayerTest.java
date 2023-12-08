package fr.pantheonsorbonne.miage.player;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.card.Card;
import fr.pantheonsorbonne.miage.card.DeckPile;
import fr.pantheonsorbonne.miage.card.DiscardPile;
import fr.pantheonsorbonne.miage.card.enums.CardColor;
import fr.pantheonsorbonne.miage.card.enums.CardValue;

public class DumbPlayerTest {

    @Test
    public void testPlayYaniv(){ //test si le statut du joueur est bien Yaniv lorqu'il a une main où les points sont inférieurs à 7
        DumbPlayer player = new DumbPlayer(1);
        DiscardPile discardPile = new DiscardPile();
        DeckPile deckPile = new DeckPile();
        deckPile.randomDeck();
        Card card = new Card(CardColor.HEART,CardValue.ACE);
        player.setHand(List.of(card));
        player.addPoint(List.of(card));
        player.play(discardPile,deckPile,true);
        assertEquals("YANIV",player.getPlayerStatus().name());
        
    }

    @Test //vérifier que le joueur a le statut YANIV si sa main est vide ce'est à dire qu'il n'a plus de cartes
    public void testYanniv(){
        DumbPlayer player = new DumbPlayer(1);
        List<Card> hand = new LinkedList<Card>();
        player.setHand(hand);
        DiscardPile discardPile = new DiscardPile();
        DeckPile deckPile = new DeckPile();
        deckPile.randomDeck();
        player.play(discardPile, deckPile,false);
        assertEquals("YANIV",player.getPlayerStatus().name());
        
    }

    @Test 
    public void testTakeOneCard(){
        
    }
    



    
    
    
}
