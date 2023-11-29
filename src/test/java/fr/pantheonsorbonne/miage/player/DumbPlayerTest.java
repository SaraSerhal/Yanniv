package fr.pantheonsorbonne.miage.player;


//import fr.pantheonsorbonne.miage.card.DeterministDeck;
//import fr.pantheonsorbonne.miage.engine.local.LocalWarGame;
//import fr.pantheonsorbonne.miage.exception.NoMoreCardException;
//import fr.pantheonsorbonne.miage.player.DumbPlayer;
//import fr.pantheonsorbonne.miage.player.Player;

import org.junit.jupiter.api.Test;

//import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
//mvn clean package site 
public class DumbPlayerTest{

    DumbPlayerTest engine;
    DumbPlayer player;



   @Test
    public void testSetPlayerStatus(){
        DumbPlayer player = new DumbPlayer("Sara");
        player.setPlayerStatus(PlayerStatus.NORMAL);
        assertEquals("NORMAL", player.getPlayerStatus().name());
    }

   @Test
    public void getPoints(){
        DumbPlayer player = new DumbPlayer("Sara");

    } 


    
}