package fr.pantheonsorbonne.miage.engine;

import java.util.Random;

import fr.pantheonsorbonne.miage.player.Player;

public abstract class AlternateTurnGame extends GameImpl {

    private final Random random = new Random();
    protected Player currentPlayer;

    public AlternateTurnGame(int nb) {
        super(nb);
        currentPlayer = players.get(random.nextInt(nbPlayers));
    }


    @Override
    public Player getNextPlayer() {
        for(Player player:players){
            if(player.sayYaniv()){
                currentPlayer=player;
            }
        }
        return currentPlayer;
    }
}
