package de.labitzke.rps_nf;

import de.labitzke.rps_nf.game.Game;
import de.labitzke.rps_nf.player.PaperPlayer;
import de.labitzke.rps_nf.player.RandomPlayer;

public class Application {
    public static void main(String[] args) {
        Game game = new Game(new PaperPlayer("A"), new RandomPlayer("B"), 100);
        System.out.println(game.simulate());
    }
}
