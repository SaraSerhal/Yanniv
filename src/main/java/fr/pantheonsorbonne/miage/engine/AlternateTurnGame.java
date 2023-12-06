package fr.pantheonsorbonne.miage.engine;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import fr.pantheonsorbonne.miage.card.Card;
import fr.pantheonsorbonne.miage.card.DiscardPile;
import fr.pantheonsorbonne.miage.card.enums.PowerCardStatus;
import fr.pantheonsorbonne.miage.player.Player;
import fr.pantheonsorbonne.miage.player.PlayerStatus;

public abstract class AlternateTurnGame extends GameImpl {
    protected Player currentPlayer;
    private boolean reverseOrder;

    public AlternateTurnGame() {
        super();
        reverseOrder = false;
    }

    public Player getFirstPlayerRound() {
        if (numRound == 1) {
            currentPlayer = players.get(random.nextInt(nbPlayers));
        } else {
            for (Player player : players) {
                if (player.getPlayerStatus() == PlayerStatus.YANIV) {
                    currentPlayer = player;
                    break;
                }
            }
        }
        return currentPlayer;
    }

    public Player getPreviousPlayer() {
        int indexCurrentPlayer = players.indexOf(currentPlayer);
        if (indexCurrentPlayer == 0)
            return players.get(players.size() - 1);
        return players.get(indexCurrentPlayer - 1);
    }

    public Player getNextPlayer() {
        // À partir de la liste récupère le joueur qui se trouve à l'index + 1 du joueur
        // précédent
        // Dans le cas où le joueur précédent était le dernier on revient au début de la
        // liste
        return players.get((players.indexOf(currentPlayer) + 1) % players.size());
    }

    @Override
    public void goNextRound() {
        if (!hasNextRound) {
            return;
        }
        System.out.println("\n______________________________________________________\nManche " + numRound + " :\n");
        if (reverseOrder) { // Dans le cas où un des joueur de la manche précédente à inverser l'ordre des
                            // joueurs avec un double 7, alors dans la manche suivante on remet les joueurs
                            // dans l'ordre
            Collections.reverse(players);
            reverseOrder = false;
        }
        currentPlayer = getFirstPlayerRound();
        currentPlayer.setPlayerStatus(PlayerStatus.NORMAL);
        nextPlayerTurns();
    }

    @Override
    public void nextPlayerTurns() {
        for (;;) {// boucle qui s'arrete quand la manche est finie, chaque iteration est un joueur
                  // qui joue
            if (deckPile.isEmpty()) {
                remakeDeckPile();
            }
            System.out.println("Tour du joueur " + currentPlayer.getNumero() + ":");
            System.out.println("Main du joueur: " + currentPlayer.getHand().toString());
            if (!discardPile.isEmpty()) {
                System.out.println("Première carte sur la pile de défausse: " + discardPile.getFirst().toString());
            }

            Player previousPlayer = getPreviousPlayer();
            Boolean canChooseOnlyDeck = previousPlayer.getPowerCardStatus() == PowerCardStatus.DOUBLE9;

            Boolean canPlay = usePowerOfPreviousPlayer(previousPlayer);
            if (canPlay) {
                currentPlayer.play(discardPile, deckPile, canChooseOnlyDeck);
            }
            System.out.println(currentPlayer.getPowerCardStatus());

            if (currentPlayer.getPowerCardStatus() != PowerCardStatus.NOTHING) {
                System.out.println("***************");
                usePowerOfCurrentPlayer();
            }

            if (currentPlayer.getPlayerStatus() == PlayerStatus.YANIV) {
                gameStatus = GameStatus.FINISHEDROUND;
                endOfRound();
                numRound++;
                endOfGame();
                break;
            }
            currentPlayer = getNextPlayer();
            System.out.println();
        }
    }

    public boolean usePowerOfPreviousPlayer(Player player) {
        PowerCardStatus powerCardStatus = player.getPowerCardStatus();
        boolean canPlay = true;

        if (powerCardStatus == PowerCardStatus.SEQUENCE) {
            // Alors si le currentPlayer a un k de la même couleur il le met sinon il pioche
            // et il joue pas
            Optional<Card> optionalCardKSameColor = optionalCardKSameColor(discardPile);
            boolean mustDeck = !(optionalCardKSameColor.isPresent());
            player.setPowerCardStatus(PowerCardStatus.NOTHING);

            if (mustDeck) {
                System.out.println("Le joueur ne peut pas continuer la suite, il est donc obligé de piocher une carte");
                currentPlayer.pickDeckPile(deckPile);
            } else {
                System.out.println("Le joueur possède la carte qui complète la suite !");
                System.out.print("Carte défaussée: " + optionalCardKSameColor.get().toString() + " ");
                discardPile.add(optionalCardKSameColor.get());
                List<Card> handPlayer = currentPlayer.getHand();
                handPlayer.remove(optionalCardKSameColor.get());
                currentPlayer.setHand(handPlayer);
            }
            canPlay = false;
        }
        if (powerCardStatus == PowerCardStatus.DOUBLE9) {
            player.setPowerCardStatus(PowerCardStatus.NOTHING);
        }

        return canPlay;
    }

    public Optional<Card> optionalCardKSameColor(DiscardPile discardPile) {
        Optional<Card> cardKSameColor = currentPlayer.getHand().stream()
                .filter(e -> e.getCardValue().getRank() == 13 && e.getColor() == discardPile.takeFist().getColor())
                .findFirst();
        return cardKSameColor;
    }

    public void usePowerOfCurrentPlayer() {
        PowerCardStatus powerCardStatus = currentPlayer.getPowerCardStatus();
        if (powerCardStatus == PowerCardStatus.DOUBLE7) {
            currentPlayer.setPowerCardStatus(PowerCardStatus.NOTHING);
            System.out.println("Le sens des tours des joueurs est inversé.");
            Collections.reverse(players);
            reverseOrder = !reverseOrder;
        }
        if (powerCardStatus == PowerCardStatus.DOUBLE8) {
            currentPlayer.setPowerCardStatus(PowerCardStatus.NOTHING);
            currentPlayer = getNextPlayer();

            System.out.println("Le joueur " + currentPlayer.getNumero() + " a été sauté");

        }
        if (powerCardStatus == PowerCardStatus.DOUBLE10) {
            currentPlayer.setPowerCardStatus(PowerCardStatus.NOTHING);
            currentPlayer.pickPlayerHand(getNextPlayer());
        }
        if (powerCardStatus == PowerCardStatus.DOUBLE9) {
            System.out.println("Le joueur suivant est obligé de prendre une carte de la pioche");
        }
    }

    @Override
    public void endOfRound() {
        System.out.println("\nLa manche " + numRound + " est terminée.");
        System.out.println("Décompte des points:");
        for (Player player : players) {
            player.resetDiscardedCards();
            if (player != currentPlayer) {
                player.addPoint(player.getHand());
                System.out.println("Joueur " + player.getNumero() + " : " + player.getPoints());
                if (player.hasAssafDeclaration(currentPlayer)) {
                    currentPlayer.addPoints(30);
                }
            }
        }
        System.out.println("Joueur " + currentPlayer.getNumero() + " : " + currentPlayer.getPoints());
        removeLosers();
    }

    @Override
    public void endOfGame() {
        if (this.nbPlayers == 1) {
            System.out.println("Le joueur " + currentPlayer.getNumero() + " remporte la  partie!");
            hasNextRound = false;
        }
    }

    @Override
    public void removeLosers() {
        Iterator<Player> playersIterator = players.iterator();
        while (playersIterator.hasNext()) {
            Player player = playersIterator.next();
            if (player.isLoser()) {
                System.out.println("Le joueur " + player.getNumero() + " a perdu et est éliminé.");
                playersIterator.remove(); // Utilisation de l'itérateur pour supprimer en toute sécurité
                nbPlayers--;
            }
        }
    }

    public void remakeDeckPile() {
        Card discardCard = discardPile.takeFist();
        deckPile.addAll(discardPile.getPile());
        deckPile.randomDeck();
        discardPile.add(discardCard);
    }
}
