package fr.pantheonsorbonne.miage.engine;

import fr.pantheonsorbonne.miage.player.Player;

public interface Game {

    Player getNextPlayer();

    void nextPlayerTurns();

    void goNextRound();

    void start();

    void endOfRound();

    void endOfGame();

    void giveCardsToPlayer();

    void installation();

}