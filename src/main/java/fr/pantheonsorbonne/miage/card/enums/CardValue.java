package fr.pantheonsorbonne.miage.card.enums;

/**
 * An enum that represend the possible cards value from a deck
 */
public enum CardValue {
    ACE("1", 1, 1),
    TWO("2", 2,2),
    THREE("3", 3,3),
    FOUR("4", 4,4),
    FIVE("5", 5,5),
    SIX("6", 6,6),
    SEVEN("7", 7,7),
    EIGHT("8", 8,8),
    NINE("9", 9,9),
    TEN("10", 10,10),
    JACK("J", 10,11),
    QUEEN("Q", 10,12),
    KING("K", 10,13);

    final private String stringRepresentation;
    final private int value;
    final private int rank;

    CardValue(String stringRepresentation, int value, int rank) {
        this.stringRepresentation = stringRepresentation;
        this.value=value;
        this.rank = rank;
    }

    /**
     * From a string representation, return the cad
     *
     * @param str
     * @return the corresponding card
     * @throws RuntimeException if the representation is invalid
     */
    public static CardValue valueOfStr(String str) {
        for (CardValue value : CardValue.values()) {
            if (str.equals(value.getStringRepresentation())) {
                return value;
            }
        }

        throw new RuntimeException("failed to parse value");

    }

    public String getStringRepresentation() {
        return stringRepresentation;
    }

    /**
     * the rank of the card for comparison purpose. The higher the rank, the better the card
     *
     * @return
     */
    public int getRank() {
        return rank;
    }

    public int getValue(){
        return value;
    }
}
