package fr.pantheonsorbonne.miage.card;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.card.enums.CardColor;
import fr.pantheonsorbonne.miage.card.enums.CardValue;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;



class DiscardPileTest{

    @Test
    public void addTest(){
        DiscardPile discardPile = new DiscardPile();
        Card card = new Card(CardColor.HEART, CardValue.TEN);
        
        discardPile.add(List.of(card));
        assertEquals(1,discardPile.size());
    

    }
}