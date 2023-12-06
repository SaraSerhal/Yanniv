package fr.pantheonsorbonne.miage.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.pantheonsorbonne.miage.card.Card;
import fr.pantheonsorbonne.miage.card.DeckPile;
import fr.pantheonsorbonne.miage.card.DiscardPile;
import fr.pantheonsorbonne.miage.card.enums.PowerCardStatus;

public class DumbPlayer extends Player {

    Random random = new Random();

    public DumbPlayer(int numero) {
        super(numero);
    }

    @Override
    public void play(DiscardPile discardPile, DeckPile deckPile, boolean canChooseOnlyDeck) {

        if (pointsHand() <= 7 || hand.isEmpty()) {
            playerStatus = PlayerStatus.YANIV;
            System.out.println("Joueur " + numero + " déclare 'Yanniv'.");
            return;
        }
        takeOneCard(discardPile, deckPile, canChooseOnlyDeck);
        discardCards(discardPile);
        winPower();
        // On voit le power qu'il a appliqué
    }

    public void takeOneCard(DiscardPile discardPile, DeckPile deckPile, boolean canChooseOnlyDeck) {

        boolean wantDeck = this.random.nextBoolean(); // renvoie true si il veut piocher et false si il veut pendre
                                                      // une
                                                      // carte défaussée
        if (wantDeck || discardPile.isEmpty() || canChooseOnlyDeck) {
            // discardPile est vide lorsque c'est le premier du round il n'a pas le choix de
            // prendre de la pioche
            /// Dans le cas où le joueur d'avant a eu un double 9, le joueur n'a pas le
            // choix que de prendre une carte du deck
            if (canChooseOnlyDeck) {
                System.out.println("Joueur " + numero + " n'a pas le choix que de piocher");
            } else {
                System.out.println("Joueur " + numero + " décide de piocher");
            }
            pickDeckPile(deckPile);
            System.out.print("(" + hand.get(hand.size() - 1).toString() + ")");
        } else {
            System.out.println("Joueur " + numero + " décide de prendre la carte de la pile de défausse");
            pickDiscardPile(discardPile);
        }
    }

    @Override
    public void discardCards(DiscardPile discardPile) { // defausser
        List<Card> discardedCardsLocal = new ArrayList<>();
        discardedCardsLocal.addAll(chooseDiscardCards());
        discardedCards = discardedCardsLocal;
        discardPile.add(discardedCardsLocal);
    }

    public void winPower() {
        if (discardedCards.size() == 2 &&
                (discardedCards.stream().allMatch(e -> e.getCardValue().getRank() == 7))) {
            powerCardStatus = PowerCardStatus.DOUBLE7;
            System.out.println("Le sens du jeu va être changer !");
        }
        if (discardedCards.size() == 2 && discardedCards.stream().allMatch(e -> e.getCardValue().getRank() == 8)) {
            powerCardStatus = PowerCardStatus.DOUBLE8;
        }
        if (discardedCards.size() == 2 && discardedCards.stream().allMatch(e -> e.getCardValue().getRank() == 9)) {
            powerCardStatus = PowerCardStatus.DOUBLE9;
        }
        if (discardedCards.size() == 2 && discardedCards.stream().allMatch(e -> e.getCardValue().getRank() == 10)) {
            powerCardStatus = PowerCardStatus.DOUBLE10;
        }
        if (discardedCards.size() == 3
                && discardedCards.stream().anyMatch(e -> e.getCardValue().getRank() == 10)
                && discardedCards.stream().anyMatch(e -> e.getCardValue().getRank() == 11)
                && discardedCards.stream().anyMatch(e -> e.getCardValue().getRank() == 12)) {
            powerCardStatus = PowerCardStatus.SEQUENCE;
        }
    }

    @Override
    public List<Card> chooseDiscardCards() { // choisi cartes a defausser
        List<Card> discardedCards = new ArrayList<>();
        Card card = hand.get(random.nextInt(hand.size())); // choisir une carte au hasard a défausser
        discardedCards.add(card);
        System.out.print("Carte(s) défaussée(s): " + card.toString() + " ");
        for (Card cardHand : hand) { // verifier si ya un double ou un triple
            if (cardHand.getCardValue().getRank() == card.getCardValue().getRank() && !(cardHand.equals(card))) {
                discardedCards.add(cardHand);
                System.out.print(cardHand.toString() + " ");
            }
        }
        System.out.println();
        hand.removeAll(discardedCards);
        return discardedCards;
    }

    @Override
    public void pickPlayerHand(Player player) { // player est le joueur suivant
        Card card = player.getHand().get(random.nextInt(player.getHand().size()));
        boolean wantPick = random.nextBoolean();
        if (wantPick) {
            // TODO: A verifier si le joueur a bien récupéré la carte de l'adversaire et que
            // l'adversaire a bien perdu sa carte
            hand.add(card);
            List<Card> handPlayer = player.getHand();
            handPlayer.remove(card);
            player.setHand(handPlayer);
            System.out.println("Le joueur a choisi de prendre la carte " + card.toString() + " du joueur suivant");
        } else {
            System.out.println("Le joueur a choisi de ne pas prendre la carte " + card.toString());
        }
    }

}