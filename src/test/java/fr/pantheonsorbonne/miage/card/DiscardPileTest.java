package fr.pantheonsorbonne.miage.card;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.card.Card;
import fr.pantheonsorbonne.miage.card.DiscardPile;
import fr.pantheonsorbonne.miage.card.enums.CardColor;
import fr.pantheonsorbonne.miage.card.enums.CardValue;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;



class DiscardPileTest{

    @Test
    public void addTest(){
        DiscardPile discardPile = new DiscardPile();
        Card card = new Card(CardColor.HEART, CardValue.TEN);
        Card card2 = new Card(CardColor.CLUB,CardValue.ACE);
        discardPile.add(List.of(card, card2));
        assertEquals(2,discardPile.size());

    }
}