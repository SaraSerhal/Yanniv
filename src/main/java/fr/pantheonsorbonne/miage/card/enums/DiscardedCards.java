package fr.pantheonsorbonne.miage.card.enums;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.pantheonsorbonne.miage.card.Card;

public enum DiscardedCards {
    EMPTY(null),
    SINGLE(null),
    DOUBLE(null),
    TRIPLE(null),
    QUADRUPLE(null),
    SEQUENCE(null);

    private List<Card> list = new ArrayList<>();

    DiscardedCards(List<Card> list) {
        this.list = list;
    }

    public void setList(List<Card> l) {
        list = l;
    }

    public List<Card> getList() {
        return list;
    }

    public void reset() {
        for (DiscardedCards type : DiscardedCards.values()) {
            List<Card> listLocal = new ArrayList<>();
            type.setList(listLocal);
        }
    }

    public boolean hasDouble(List<Card> hand) {// Renvoie true s'il y a un double, stocke le premier double trouvé dans
                                               // list sinon renvoie false
        Card c;
        int occ = 1;
        for (int i = 0; i < hand.size() - 1; i++) {
            c = hand.get(i);
            list.add(c);
            for (int j = i + 1; j < hand.size(); j++) {// on compare chaque carte qui suivent la carte d'indice i a
                                                       // celle ci
                if (hand.get(j).getCardValue().getRank() == c.getCardValue().getRank()) {
                    list.add(hand.get(j));
                    occ++;
                }
            }
            if (occ == 2) {
                return true;
            } else {
                list.clear();
                c = hand.get(i + 1);
                occ = 1;
            }
        }
        return false;
    }

    public boolean hasTriple(List<Card> hand) {// Renvoie true s'il y a un triple, stocke le premier triple trouvé dans
                                               // list sinon renvoie false
        Card c;
        int occ = 1;
        for (int i = 0; i < hand.size() - 1; i++) {
            c = hand.get(i);
            list.add(c);
            for (int j = i + 1; j < hand.size(); j++) {
                if (hand.get(j).getCardValue().getRank() == c.getCardValue().getRank()) {
                    list.add(hand.get(j));
                    occ++;
                }
            }
            if (occ == 3) {
                return true;
            } else {
                list.clear();
                c = hand.get(i + 1);
                occ = 1;
            }
        }
        return false;
    }

    public boolean hasQuadruple(List<Card> hand) {// Renvoie true s'il y a un quadruple, stocke le premier quadruple
                                                  // trouvé dans list sinon renvoie false
        Card c;
        int occ = 1;
        for (int i = 0; i < hand.size() - 1; i++) {
            c = hand.get(i);
            list.add(c);
            for (int j = i + 1; j < hand.size(); j++) {
                if (hand.get(j).getCardValue().getRank() == c.getCardValue().getRank()) {
                    list.add(hand.get(j));
                    occ++;
                }
            }
            if (occ == 4) {
                return true;
            } else {
                list.clear();
                c = hand.get(i + 1);
                occ = 1;
            }
        }
        return false;
    }

    public boolean hasSequence(List<Card> hand) {// Renvoie true s'il y a une suite, stocke la premiere suite trouvée
                                                 // dans list sinon renvoie false
        Card c;
        int nb = 1;
        for (int i = 0; i < hand.size() - 1; i++) {
            c = hand.get(i);
            list.add(c);
            for (int j = i + 1; j < hand.size(); j++) {
                if (hand.get(j).isNextCard(c)) {
                    list.add(hand.get(j));
                    nb++;
                    c = hand.get(j);
                }
            }
            if (nb >= 3) {
                return true;
            } else {
                list.clear();
                c = hand.get(i + 1);
                nb = 1;
            }
        }
        return false;
    }

    public boolean hasSingle(List<Card> hand) {// Renvoie true s'il n'y a aucune combinaison et stocke dans list la plus
                                               // haute carte, sinon renvoie false
        Set<Card> setHand = new HashSet<>(hand);
        if (setHand.size() == hand.size()) {
            Card card = highestCard(hand);
            list.add(card);
            return true;
        } else {
            list.clear();
            return false;
        }
    }

    public Card highestCard(List<Card> listCard) {
        if (listCard == null || listCard.isEmpty()) {
            // renvoyer une exception
        }
        Card c = listCard.get(0);
        for (Card card : listCard) {
            if (card.getCardValue().getRank() > c.getCardValue().getRank()) {
                c = card;
            }
        }
        return c;
    }

}
