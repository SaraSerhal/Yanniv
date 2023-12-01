package fr.pantheonsorbonne.miage.engine.local;

<<<<<<< HEAD
=======
<<<<<<< HEAD
import java.util.ArrayList;
//import java.util.Arrays;
=======
>>>>>>> d9ea15af58b5e2d1cd203a8dd37243b85e70eed2
import java.util.LinkedList;
import java.util.List;
>>>>>>> a645df478e83179e46dcbfc0dc4b2c18981e0610
import fr.pantheonsorbonne.miage.engine.AlternateTurnGame;

public class GameLocal extends AlternateTurnGame {
    public GameLocal() {
        super();
    }

    public static void main(String... args) {

        GameLocal localWarGame = new GameLocal();
        localWarGame.installation();
        localWarGame.start();
        System.exit(0);
    }

}
