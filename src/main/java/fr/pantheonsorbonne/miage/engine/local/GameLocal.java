package fr.pantheonsorbonne.miage.engine.local;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import fr.pantheonsorbonne.miage.card.Card;
import fr.pantheonsorbonne.miage.card.DeckPile;
import fr.pantheonsorbonne.miage.card.DiscardPile;
import fr.pantheonsorbonne.miage.engine.AlternateTurnGame;
import fr.pantheonsorbonne.miage.player.DumbPlayer;
import fr.pantheonsorbonne.miage.player.Player;

public class GameLocal extends AlternateTurnGame {
    public GameLocal(int nb, List<Player> players) {
        super(nb, players);
    }

    public static void main(String... args) {

        DumbPlayer mayane = new DumbPlayer("Mayane");
        DumbPlayer sara = new DumbPlayer("Sara");
        List<Player> players = new LinkedList<>();
        players.add(mayane);
        players.add(sara);

        DiscardPile discardPile = new DiscardPile();
        DeckPile deckPile = new DeckPile();
        List<Card> cards = new ArrayList<Card>();
        deckPile.RandomDeck(cards);

        GameLocal localWarGame = new GameLocal(2, players);
        localWarGame.giveCardsToPlayer(players,deckPile);
        localWarGame.start(discardPile, deckPile);
        System.exit(0);
    }
}
