package de.labitzke.rps_nf.player;

import de.labitzke.rps_nf.game.Move;

public interface Player {

    String name();

    Move makeMove();
}
