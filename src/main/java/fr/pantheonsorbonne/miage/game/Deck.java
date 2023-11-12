package fr.pantheonsorbonne.miage.game;

public interface Deck {
    default Card getCard() { //Cette méthode par défaut retourne une carte à partir du deck
        Card[] res = getCards(1);

        return getCards(1)[0];  //tableau de cartes (ici, un tableau de longueur 1) et renvoie la première carte de ce tableau.
    }

    /**
     * return an array of random cards
     *
     * @param length the size of the array
     * @return
     */
    Card[] getCards(int length);

}
