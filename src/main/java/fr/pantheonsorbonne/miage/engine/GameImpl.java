package fr.pantheonsorbonne.miage.engine;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import fr.pantheonsorbonne.miage.card.Card;
import fr.pantheonsorbonne.miage.card.DeckPile;
import fr.pantheonsorbonne.miage.card.DiscardPile;
import fr.pantheonsorbonne.miage.player.Player;
import fr.pantheonsorbonne.miage.player.SmartPlayer;

public abstract class GameImpl implements Game {

    protected final Random random = new Random();
    protected List<Player> players;
    protected int nbPlayers;
    protected GameStatus gameStatus;
    protected int numRound;
    protected DeckPile deckPile;
    protected DiscardPile discardPile;
    protected boolean hasNextRound;

    public GameImpl() {
        players = new LinkedList<Player>();
        nbPlayers = random.nextInt(4) + 2; // Génère un nombre aléatoire entre 0 et 3, puis on ajoute 2 pour obtenir un
                                           // nombre entre 2 et 5;
        gameStatus = GameStatus.ONGOING;
        numRound = 1;
        deckPile = new DeckPile();
        discardPile = new DiscardPile();
        hasNextRound = true;

    }

    @Override
    public void giveCardsToPlayer() {
        for (Player player : players) {
            List<Card> cards = deckPile.takeCards(7);
            player.setHand(cards);
        }
    }

    @Override
    public void start() {
        initializePlayers();
        System.out.println("Nous avons " + nbPlayers + " joueurs");
        System.out.println("La partie commence: ");
        while (hasNextRound) { // boucle qui s'arrete quand la partie est finie, chaque itération est une
                               // manche
            installation();
            goNextRound();
        }
    }

    @Override
    public void installation() {
        deckPile.clear();
        deckPile.randomDeck();
        discardPile.clear();
        giveCardsToPlayer();
    }

    public void initializePlayers() { // changer pour smartPlayer
        for (int i = 0; i < nbPlayers; i++) {
            Player player = new SmartPlayer(i + 1);
            players.add(player);
        }
    }
}