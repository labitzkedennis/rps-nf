package de.labitzke.rps_nf.game;

import de.labitzke.rps_nf.player.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static de.labitzke.rps_nf.game.Move.*;

@ExtendWith(MockitoExtension.class)
public class GameTest {

    @Mock
    Player firstPlayer;
    @Mock
    Player secondPlayer;

    @Test
    public void testGame() {
        Mockito.when(firstPlayer.name()).thenReturn("A");
        Mockito.when(firstPlayer.makeMove()).thenReturn(PAPER);

        Mockito.when(secondPlayer.name()).thenReturn("B");
        Mockito.when(secondPlayer.makeMove()).thenReturn(ROCK, PAPER, SCISSORS, ROCK, PAPER, SCISSORS, ROCK, PAPER, SCISSORS, ROCK);

        Game game = new Game(firstPlayer, secondPlayer, 10);
        String result = game.simulate();

        String expectedResult = "A: Gewonnen: 4, Verloren: 3, Unentschieden: 3\nB: Gewonnen: 3, Verloren: 4, Unentschieden: 3";

        Assertions.assertEquals(expectedResult, result);
    }
}
