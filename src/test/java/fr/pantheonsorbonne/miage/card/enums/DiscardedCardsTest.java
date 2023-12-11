package fr.pantheonsorbonne.miage.card.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.card.Card;

public class DiscardedCardsTest {

   @Test
    public void testSetList(){
        List<Card> listCard = new ArrayList<>();
        Card card = new Card(CardColor.CLUB, CardValue.EIGHT);
        Card card2 = new Card(CardColor.DIAMOND, CardValue.EIGHT);
        listCard.add(card);
        listCard.add(card2);
        DiscardedCards.EMPTY.setList(listCard);
        assertEquals(List.of(new Card(CardColor.CLUB, CardValue.EIGHT),new Card(CardColor.DIAMOND, CardValue.EIGHT)),DiscardedCards.EMPTY.getList());
    }

    @Test
    public void testReset(){
        DiscardedCards[] discardedCardsValues = DiscardedCards.values();

        discardedCardsValues[0].setList(List.of(new Card(CardColor.HEART, CardValue.ACE)));
        discardedCardsValues[1].setList(List.of(new Card(CardColor.DIAMOND, CardValue.TWO)));
        DiscardedCards.EMPTY.reset();
        for (DiscardedCards type : discardedCardsValues) {
            assertTrue(type.getList().isEmpty());
        }
    }

    @Test 
    public void testHasDouble(){
        List<Card> handWithDouble = new ArrayList<>();
        handWithDouble.add(new Card(CardColor.HEART, CardValue.SEVEN));
        handWithDouble.add(new Card(CardColor.CLUB,CardValue.SIX));
        handWithDouble.add(new Card(CardColor.DIAMOND, CardValue.SEVEN));
        DiscardedCards.DOUBLE.setList(new ArrayList<>());
        assertTrue(DiscardedCards.DOUBLE.hasDouble(handWithDouble));
    }

    @Test 
    public void testHasDoubleFalse(){
        List<Card> handWithoutDouble = new ArrayList<>();
        handWithoutDouble.add(new Card(CardColor.HEART, CardValue.ACE));
        handWithoutDouble.add(new Card(CardColor.CLUB,CardValue.SIX));
        handWithoutDouble.add(new Card(CardColor.DIAMOND, CardValue.SEVEN));
        DiscardedCards.DOUBLE.setList(new ArrayList<>());
        assertFalse(DiscardedCards.DOUBLE.hasDouble(handWithoutDouble));
    }

    @Test 
    public void testHasTriple(){
        List<Card> handWithTriple = new ArrayList<>();
        handWithTriple.add(new Card(CardColor.HEART, CardValue.SEVEN));
        handWithTriple.add(new Card(CardColor.CLUB,CardValue.SEVEN));
        handWithTriple.add(new Card (CardColor.CLUB, CardValue.FIVE));
        handWithTriple.add(new Card(CardColor.DIAMOND, CardValue.SEVEN));
        DiscardedCards.TRIPLE.setList(new ArrayList<>());
        assertTrue(DiscardedCards.TRIPLE.hasTriple(handWithTriple));

    }

    @Test 
    public void testHasTripleisFalse(){
        List<Card> handWithoutTriple = new ArrayList<>();
        handWithoutTriple.add(new Card(CardColor.HEART, CardValue.SEVEN));
        handWithoutTriple.add(new Card(CardColor.CLUB,CardValue.SEVEN));
        handWithoutTriple.add(new Card (CardColor.CLUB, CardValue.FIVE));
        handWithoutTriple.add(new Card(CardColor.DIAMOND, CardValue.TEN));
        DiscardedCards.TRIPLE.setList(new ArrayList<>());
        assertFalse(DiscardedCards.TRIPLE.hasTriple(handWithoutTriple));

    }

    @Test
    public void testHasQuadruple(){
        List<Card> handWithQuadruple= new ArrayList<>();
        handWithQuadruple.add(new Card(CardColor.HEART, CardValue.SEVEN));
        handWithQuadruple.add(new Card(CardColor.CLUB,CardValue.SEVEN));
        handWithQuadruple.add(new Card (CardColor.SPADE, CardValue.SEVEN));
        handWithQuadruple.add(new Card(CardColor.DIAMOND, CardValue.SEVEN));
        DiscardedCards.QUADRUPLE.setList(new ArrayList<>());
        assertTrue(DiscardedCards.QUADRUPLE.hasQuadruple(handWithQuadruple));

    }

    @Test
    public void testHasQuadrupleIsFalse(){
        List<Card> handWithoutQuadruple = new ArrayList<>();
        handWithoutQuadruple.add(new Card(CardColor.HEART, CardValue.SEVEN));
        handWithoutQuadruple.add(new Card(CardColor.CLUB,CardValue.SEVEN));
        handWithoutQuadruple.add(new Card (CardColor.SPADE, CardValue.ACE));
        handWithoutQuadruple.add(new Card(CardColor.DIAMOND, CardValue.SEVEN));
        DiscardedCards.QUADRUPLE.setList(new ArrayList<>());
        assertFalse(DiscardedCards.QUADRUPLE.hasQuadruple(handWithoutQuadruple));

    }

    @Test
    public void testHasSequenceIsFalse(){
        List<Card> handWithoutSequence = new ArrayList<>();
        handWithoutSequence.add(new Card(CardColor.CLUB, CardValue.SIX));
        handWithoutSequence.add(new Card(CardColor.CLUB, CardValue.QUEEN));
        handWithoutSequence.add(new Card(CardColor.CLUB, CardValue.EIGHT));
        handWithoutSequence.add(new Card(CardColor.CLUB, CardValue.ACE));
        DiscardedCards.SEQUENCE.setList(handWithoutSequence);    
        assertFalse(DiscardedCards.SEQUENCE.hasSequence(handWithoutSequence));
    }

    @Test
    public void testHasSequenceIsTrue(){
        List<Card> handWithSequence = new ArrayList<>();
        handWithSequence.add(new Card(CardColor.CLUB, CardValue.SIX));
        handWithSequence.add(new Card(CardColor.CLUB, CardValue.SEVEN));
        handWithSequence.add(new Card(CardColor.CLUB, CardValue.EIGHT));
        handWithSequence.add(new Card(CardColor.HEART, CardValue.ACE));
        DiscardedCards.SEQUENCE.setList(handWithSequence);    
        assertTrue(DiscardedCards.SEQUENCE.hasSequence(handWithSequence));
    }

    @Test
    public void testHasSingle(){
        List<Card> handWithSingle= new ArrayList<>();
        handWithSingle.add(new Card(CardColor.HEART, CardValue.SEVEN));
        handWithSingle.add(new Card(CardColor.HEART,CardValue.EIGHT));
        handWithSingle.add(new Card (CardColor.SPADE, CardValue.QUEEN));
        handWithSingle.add(new Card(CardColor.DIAMOND, CardValue.TEN));
        DiscardedCards.SINGLE.setList(new ArrayList<>());
        assertTrue(DiscardedCards.SINGLE.hasSingle(handWithSingle));
    }
    
    @Test
    public void testHighestCard(){
        List<Card> card = new ArrayList<>();
        Card highestCard = new Card (CardColor.HEART, CardValue.QUEEN);
        card.add(highestCard);
        card.add(new Card (CardColor.CLUB, CardValue.ACE));
        card.add(new Card(CardColor.DIAMOND,CardValue.EIGHT));
        DiscardedCards.SINGLE.setList(card);
        assertEquals(highestCard, DiscardedCards.SINGLE.highestCard(card));
    }

     @Test
    public void testHighestCardWithEmptyPile(){
        List<Card> card = new ArrayList<>();
        DiscardedCards.SINGLE.setList(card);
        assertThrows(NoSuchElementException.class, () -> {
            DiscardedCards.SINGLE.highestCard(card);
        });
    }
    
}
