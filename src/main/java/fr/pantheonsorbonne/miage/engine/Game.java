package fr.pantheonsorbonne.miage.engine;

import fr.pantheonsorbonne.miage.player.Player;

public interface Game {

    Player getNextPlayer();

    void goNextRound();

    void nextPlayerTurn();

    void start();

    void endOfRound();

    void endOfGame();

}