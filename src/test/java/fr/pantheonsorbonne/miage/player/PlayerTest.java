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
import fr.pantheonsorbonne.miage.card.enums.PowerCardStatus;

public class PlayerTest{

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
        assertEquals(11,player.sumPointsHand());

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
        assertEquals(18,player.sumPointsHand());
    }

    @Test 
    public void testAddPoint(){
        player = new DumbPlayer(1);
        player.addPoints(16);
        List<Card> hand = new LinkedList<Card>();
        Card card3 = new Card(CardColor.SPADE,CardValue.TWO);
        hand.add(card3);
        player.setHand(hand);
        player.addPoints(hand);
        assertEquals(18,player.getPoints());

    }

    @Test
    public void testpickDeckPile(){
        player = new DumbPlayer(1);
        List<Card> hand = new LinkedList<Card>();
        Card card = new Card(CardColor.HEART,CardValue.TWO);
        hand.add(card);
        player.setHand(hand);
        DeckPile deck = new DeckPile(); 
        deck.randomDeck();
        player.pickDeckPile(deck);
        assertEquals(2,hand.size());
    }

    @Test
    public void testGetPowerCardStatus(){
        player = new DumbPlayer(1);
        assertEquals("NOTHING",player.getPowerCardStatus().name());
    }

    @Test
    public void testSetPowerCardStatus(){
        player = new DumbPlayer(1);
        player.setPowerCardStatus(PowerCardStatus.DOUBLE7);
        assertEquals("DOUBLE7", player.getPowerCardStatus().name());
    }

    @Test
    public void testHasAssafDeclarationTrue(){
        Player player1 = new DumbPlayer(1);
        Card cardHandPlayer1 = new Card(CardColor.HEART,CardValue.FOUR);
        Card cardHand1 = new Card(CardColor.DIAMOND,CardValue.ACE);

        player1.setHand(List.of(cardHandPlayer1,cardHand1));
        Player playerSayYaniv = new DumbPlayer(2);
        Card cardPlayerSayYaniv = new Card(CardColor.SPADE,CardValue.SEVEN);
        playerSayYaniv.setHand(List.of(cardPlayerSayYaniv));

        assertEquals(true,player1.hasAssafDeclaration(playerSayYaniv));
        
    }

    @Test
    public void testHasAssafDeclarationFalse(){
        Player player1 = new DumbPlayer(1);
        Card cardHandPlayer1 = new Card(CardColor.HEART,CardValue.SEVEN);
        Card cardHand1 = new Card(CardColor.DIAMOND,CardValue.ACE);

        player1.setHand(List.of(cardHandPlayer1,cardHand1));
        Player playerSayYaniv = new DumbPlayer(2);
        Card cardPlayerSayYaniv = new Card(CardColor.SPADE,CardValue.SEVEN);
        playerSayYaniv.setHand(List.of(cardPlayerSayYaniv));

        assertEquals(false,player1.hasAssafDeclaration(playerSayYaniv));
        
    }

    @Test
    public void testPickDiscardPile(){
        player = new DumbPlayer(1);
        DiscardPile discardPile = new DiscardPile();
        Card discard1 = new Card(CardColor.CLUB, CardValue.TEN);
        Card discard2 = new Card(CardColor.CLUB, CardValue.TWO);
        discardPile.add(discard1);
        discardPile.add(discard2);

        Card card = new Card(CardColor.HEART,CardValue.FOUR);
        Card card2 = new Card(CardColor.DIAMOND,CardValue.ACE);
        Card card3 = new Card(CardColor.HEART,CardValue.QUEEN);

        List<Card> hand = new LinkedList<Card>();
        hand.add(card);
        hand.add(card2);
        hand.add(card3);
        player.setHand(hand);
        player.pickDiscardPile(discardPile);
        assertEquals(4, player.getHand().size());



    }
    
}