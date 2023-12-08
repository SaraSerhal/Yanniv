package fr.pantheonsorbonne.miage.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.pantheonsorbonne.miage.card.Card;
import fr.pantheonsorbonne.miage.card.DeckPile;
import fr.pantheonsorbonne.miage.card.DiscardPile;
import fr.pantheonsorbonne.miage.card.enums.DiscardedCards;
import fr.pantheonsorbonne.miage.card.enums.PowerCardStatus;

public class DumbPlayer extends Player {

    Random random = new Random();

    public DumbPlayer(int numero) {
        super(numero);
    }

    @Override
    public void play(DiscardPile discardPile, DeckPile deckPile, boolean canChooseOnlyDeck) {

        if (sumPointsHand() <= 7 || hand.isEmpty()) {
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

        boolean wantDeck = chooseDeck(discardPile); // renvoie true si il veut piocher et false si il veut pendre
                                                    // une carte défaussée
        if (wantDeck || discardPile.isEmpty() || canChooseOnlyDeck) {
            // discardPile est vide lorsque c'est le premier du round il n'a pas le choix de
            // prendre de la pioche
            // Dans le cas où le joueur d'avant a eu un double 9, le joueur n'a pas le
            // choix que de prendre une carte du deck
            if (canChooseOnlyDeck) {
                System.out.println("Joueur " + numero + " n'a pas le choix que de piocher");
            } else {
                System.out.print("Joueur " + numero + " décide de piocher");
            }
            pickDeckPile(deckPile);
            System.out.println("(" + hand.get(hand.size() - 1).toString() + ")");
        } else {
            System.out.println("Joueur " + numero + " décide de prendre la carte de la pile de défausse");
            pickDiscardPile(discardPile);
        }
    }

    public boolean chooseDeck(DiscardPile discardPile) {
        return this.random.nextBoolean();
    }

    @Override
    public void discardCards(DiscardPile discardPile) { // defausser cad ajouter les cartes défaussées a la discardPile
        List<Card> discardedCardsLocal = new ArrayList<>();
        discardedCardsLocal.addAll(chooseDiscardCards());
        hand.removeAll(discardedCards.getList());
        discardPile.add(discardedCardsLocal);
    }

    public void winPower() {
        if (discardedCards == DiscardedCards.DOUBLE &&
                (discardedCards.getList().get(0).getCardValue().getRank()) == 7) {
            powerCardStatus = PowerCardStatus.DOUBLE7;
        }
        if (discardedCards == DiscardedCards.DOUBLE &&
                (discardedCards.getList().get(0).getCardValue().getRank()) == 8) {
            powerCardStatus = PowerCardStatus.DOUBLE8;
        }
        if (discardedCards == DiscardedCards.DOUBLE &&
                (discardedCards.getList().get(0).getCardValue().getRank()) == 9) {
            powerCardStatus = PowerCardStatus.DOUBLE9;
        }
        if (discardedCards == DiscardedCards.DOUBLE &&
                (discardedCards.getList().get(0).getCardValue().getRank()) == 10) {
            powerCardStatus = PowerCardStatus.DOUBLE10;
        }
        if (discardedCards == DiscardedCards.SEQUENCE &&
                (discardedCards.getList().get(0).getCardValue().getRank()) == 10) {
            powerCardStatus = PowerCardStatus.SEQUENCE_10_11_12;
        }
    }

    @Override
    public List<Card> chooseDiscardCards() {
        List<Card> newHand = new ArrayList<>(hand.subList(0, hand.size() - 1)); // le joueru ne peut pas défausser la
                                                                                // carte qu'il a prise ( soit de la
                                                                                // pioche soit de la pile de défausse)
        discardedCards.reset();
        discardedCards = DiscardedCards.EMPTY;

        List<Card> discardedCardsLocal = new ArrayList<>();
        discardedCardsLocal.add(newHand.get(random.nextInt(hand.size())));
        discardedCards = DiscardedCards.SINGLE;

        discardedCards.setList(discardedCardsLocal);
        return discardedCardsLocal;
    }

    @Override
    public void pickPlayerHand(Player player) { // player est le joueur suivant
        Card card = player.getHand().get(random.nextInt(player.getHand().size()));
        boolean wantPick = choosePick(card);
        if (wantPick) {
            hand.add(card);
            List<Card> handPlayer = player.getHand();
            handPlayer.remove(card);
            player.setHand(handPlayer);
            System.out.println("Le joueur a choisi de prendre la carte " + card.toString() + " du joueur suivant");
        } else {
            System.out.println("Le joueur a choisi de ne pas prendre la carte " + card.toString()+" du joueur suivant");
        }
    }

    public boolean choosePick(Card card) {
        return random.nextBoolean();
    }

}