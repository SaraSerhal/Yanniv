package fr.pantheonsorbonne.miage.player;
import java.util.Random;
import fr.pantheonsorbonne.miage.card.DeckPile;
import fr.pantheonsorbonne.miage.card.DiscardPile;

public class DumbPlayer extends Player {

    Random random = new Random();
    public DumbPlayer(String name) {
        super(name);
    }

    @Override
    public void play(DiscardPile discardPile, DeckPile deckPile) {
        
    }

}