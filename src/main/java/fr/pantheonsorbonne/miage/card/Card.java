package fr.pantheonsorbonne.miage.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import fr.pantheonsorbonne.miage.card.enums.CardColor;
import fr.pantheonsorbonne.miage.card.enums.CardValue;

public class Card {

    private final CardColor color;
    private final CardValue value;


    public Card(CardColor color, CardValue value) { //créer une nouvelle instance de la classe Card avec une couleur et une valeur spécifiées.
        this.color = color;
        this.value = value;
    }

    public static String cardsToString(Card[] cards) { // Convertit un tableau de cartes en une seule chaîne de caractères, où les cartes sont séparées par des points-virgules (;).
        return Arrays.stream(cards).map(Card::toString).collect(Collectors.joining(";"));
    }

    /**
     * get the value of the card
     *
     * @return
     */
    public CardValue getCardValue() {
        return value;
    }

    /**
     * Get the color of the card
     *
     * @return
     */
    public CardColor getColor() {
        return color;
    }

    public static Card[] stringToCards(String cards) {  //Convertit une chaîne de caractères représentant des cartes (séparées par des points-virgules) en un tableau d'objets Card.
        if (cards.isEmpty()) {
            return new Card[0];
        }
        return Arrays.stream(cards.split(";")).map(Card::valueOf).toArray(Card[]::new);
    }

    /**
     * For a String representation of a card, return the card
     *
     * @param str
     * @return the card
     * @throws RuntimeException if the String representation is Invaliid
     */
    public static Card valueOf(String str) { //Convertit une représentation textuelle d'une carte en un objet Card. La méthode prend en charge la représentation des cartes avec des valeurs à deux chiffres (comme 10).
        CardValue value;
        CardColor color;
        if (str.length() == 3) {//it's a 10
            value = CardValue.valueOfStr(str.substring(0, 2));
            color = CardColor.valueOfStr(str.substring(2, 3));
        } else {
            value = CardValue.valueOfStr(str.substring(0, 1));
            color = CardColor.valueOfStr(str.substring(1, 2));
        }
        return new Card(color, value);

    }

    public static List<Card> getAllPossibleCards() { //Génère et renvoie une liste de toutes les cartes possibles en combinant toutes les valeurs et couleurs disponibles.
        List<Card> possibleCards = new ArrayList<>(CardColor.values().length * CardValue.values().length);
        for (CardColor color : CardColor.values()) {
            for (CardValue value : CardValue.values()) {
                possibleCards.add(new Card(color, value));
            }
        }
        return possibleCards;
    }

    public String toFancyString() {  // Renvoie une représentation stylisée de la carte sous forme de chaîne de caractères
        int rank = this.getCardValue().ordinal();
        if (rank > 10) {
            rank++;
        }
        return new String(Character.toChars(this.color.getCode() + rank));
    }

    @Override
    public int hashCode() {  // retourne un code de hachage basé sur la couleur et la valeur de la carte.
        return Objects.hash(getColor(), getCardValue());
    }

    @Override
    public boolean equals(Object o) { // verifier l'égalité entre deux cartes basée sur leur couleur et leur valeur.
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return getColor() == card.getColor() && getCardValue() == card.getCardValue();
    }

    @Override
    public String toString() { //Renvoie une représentation textuelle de la carte sous forme de chaîne de caractères, combinant la valeur et la couleur.
        return this.getCardValue().getStringRepresentation() + this.getColor().getStringRepresentation();
    }
}
