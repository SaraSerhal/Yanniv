package fr.pantheonsorbonne.miage.card;

import fr.pantheonsorbonne.miage.card.enums.CardColor;
import fr.pantheonsorbonne.miage.card.enums.CardValue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardTest {

    @Test
    void cardsToString() {
        {
            Card card = new Card(CardColor.CLUB, CardValue.ACE);
            assertEquals("1C", card.toString());
        }
        {
            Card card = new Card(CardColor.HEART, CardValue.TEN);
            assertEquals("10H", card.toString());
        }
    }

    @Test
    void getValue() {
        assertEquals(CardValue.ACE, Card.valueOf("1S").getCardValue());

    }

    @Test
    void getColor() {
        assertEquals(CardColor.SPADE, Card.valueOf("1S").getColor());
    }

    @Test
    void stringToCards() {
        Card[] cards = Card.stringToCards("10S;KH");
        assertEquals(new Card(CardColor.SPADE, CardValue.TEN), cards[0]);
        assertEquals(new Card(CardColor.HEART, CardValue.KING), cards[1]);
    }

    @Test
    void valueOf() {
        assertEquals(CardValue.TEN, Card.valueOf("10D").getCardValue());
        assertEquals(CardColor.DIAMOND, Card.valueOf("10D").getColor());
    }

   @Test
    public void testToFancyString(){
        Card card = new Card(CardColor.HEART,CardValue.TEN);
        assertEquals("🂺",card.toFancyString());
    }

    @Test
    public void testIsNextCard(){
        Card currentCard = new Card(CardColor.HEART, CardValue.EIGHT);
        Card nextCard = new Card(CardColor.HEART,CardValue.NINE);
        Card nextCardInvalid = new Card(CardColor.HEART, CardValue.SIX);

        assertEquals(true,currentCard.isNextCard(nextCard));
        assertEquals(false,currentCard.isNextCard(nextCardInvalid));

    }

    @Test
    public void testIsPreviousCard(){
        Card currentCard = new Card(CardColor.HEART, CardValue.EIGHT);
        Card previousCard = new Card(CardColor.HEART,CardValue.SEVEN);
        Card previousCardInvalid = new Card(CardColor.HEART, CardValue.ACE);

        assertEquals(true,currentCard.isPreviousCard(previousCard));
        assertEquals(false,currentCard.isPreviousCard(previousCardInvalid));

    }

    
}