package de.labitzke.rps_nf.game;

import de.labitzke.rps_nf.player.Player;

public class Game {

    private Player firstPlayer;
    private Player secondPlayer;
    private int roundsToPlay;

    public Game(Player firstPlayer, Player secondPlayer, int roundsToPlay) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.roundsToPlay = roundsToPlay;
    }

    public String simulate() {
        return "";
    }
}
