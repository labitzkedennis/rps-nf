package de.labitzke.rps_nf.player;

import de.labitzke.rps_nf.game.Move;

public record PaperPlayer(String name) implements Player {
    @Override
    public Move makeMove() {
        return Move.PAPER;
    }
}
