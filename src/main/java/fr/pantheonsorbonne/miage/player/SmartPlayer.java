package fr.pantheonsorbonne.miage.player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import fr.pantheonsorbonne.miage.card.Card;
import fr.pantheonsorbonne.miage.card.DiscardPile;
import fr.pantheonsorbonne.miage.card.enums.DiscardedCards;

public class SmartPlayer extends DumbPlayer {

    public SmartPlayer(int numero) {
        super(numero);
    }

    @Override
    public List<Card> chooseDiscardCards() { // choisi les cartes a defausser, les retire de sa main et renvoie la liste
                                             // des cartes a défausser
        List<Card> newHand = new ArrayList<>(hand.subList(0, hand.size() - 1)); // le joueru ne peut pas défausser la
                                             // carte qu'il a prise ( soit de la
                                             // pioche soit de la pile de défausse)        
        discardedCards.reset();
        discardedCards = DiscardedCards.EMPTY;
        List<Card> discardedCardsLocal = new ArrayList<>();

        if (discardedCards.hasQuadruple(newHand)) {
            discardedCardsLocal = discardedCards.getList();
            discardedCards = DiscardedCards.QUADRUPLE;
            DiscardedCards.QUADRUPLE.setList(discardedCardsLocal);

        } else if (discardedCards.hasTriple(newHand)) {
            discardedCardsLocal = discardedCards.getList();
            discardedCards = DiscardedCards.TRIPLE;
            DiscardedCards.TRIPLE.setList(discardedCardsLocal);

        } else if (discardedCards.hasDouble(newHand)) {
            discardedCardsLocal = discardedCards.getList();
            discardedCards = DiscardedCards.DOUBLE;
            DiscardedCards.DOUBLE.setList(discardedCardsLocal);

        } else if (discardedCards.hasSequence(newHand)) {
            discardedCardsLocal = discardedCards.getList();
            discardedCards = DiscardedCards.SEQUENCE;
            DiscardedCards.SEQUENCE.setList(discardedCardsLocal);

        } else if (discardedCards.hasSingle(newHand)) {
            discardedCardsLocal = discardedCards.getList();
            discardedCards = DiscardedCards.SINGLE;
            DiscardedCards.SINGLE.setList(discardedCardsLocal);
        }

        System.out.println("Carte(s) défaussée(s): " + discardedCardsLocal.toString());
        hand.removeAll(discardedCardsLocal);
        return discardedCardsLocal;
    }

    @Override
    public boolean chooseDeck(DiscardPile discardPile) {
        if(!discardPile.isEmpty()){
            return !hand.stream()
                .anyMatch(e -> e.getCardValue().getRank() == discardPile.getFirst().getCardValue().getRank());
            // si le numero de la premiere carte de la pile de defausse n'est pas egale a l'un des
            // numero des cartes de la main du joueur
        }
        else return true;
    }

    @Override
    public boolean choosePick(Card card){
        //en ajoutant cette carte a la main du joueur, on vérifie si le joueur peut former une suite avec cette carte. Si oui, il choisit de prendre la carte
        boolean pick=false;
        List<Card> hand = new LinkedList<Card>();
        DiscardedCards discardedCards = DiscardedCards.SEQUENCE;
        List <Card> newHand=new ArrayList<>(hand);
        newHand.add(card);
        if(discardedCards.hasSequence(newHand)){
            if(discardedCards.getList().contains(card)&&discardedCards.getList().size()==3){
                pick= true;
            }
        }
        discardedCards.reset();
        discardedCards=DiscardedCards.EMPTY;
        return pick;
    }
    

}
