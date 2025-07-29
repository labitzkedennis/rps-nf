package de.labitzke.rps_nf.player;

import de.labitzke.rps_nf.game.Move;

import java.util.Random;

public record RandomPlayer(String name) implements Player {
    @Override
    public Move makeMove() {
        return Move.values()[new Random().nextInt(Move.values().length)];
    }
}
