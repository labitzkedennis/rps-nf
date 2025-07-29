package de.labitzke.rps_nf.player;

import de.labitzke.rps_nf.game.Move;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PlayerTest {


    @Test
    public void playersReturningTheirNames() {
        Player paperPlayer = new PaperPlayer("A");
        Assertions.assertEquals("A", paperPlayer.name());

        Player randomPlayer = new RandomPlayer("B");
        Assertions.assertEquals("B", randomPlayer.name());
    }

    @Test
    public void paperPlayerAlwaysPlayingPaper() {
        Player paperPlayer = new PaperPlayer("PP");
        IntStream.range(0,100).forEach(i -> Assertions.assertEquals(Move.PAPER, paperPlayer.makeMove()));
    }

    @Test
    public void randomPlayerPlaysSomehowRandomly() {
        int numberOfRounds = 100000;
        Player randomPlayer = new RandomPlayer("RP");
        List<Move> randomPlayerMoves = IntStream.range(0, numberOfRounds).mapToObj(i -> randomPlayer.makeMove()).toList();

        Set<Map.Entry<Move, Long>> moves = randomPlayerMoves.stream()
                .collect(Collectors.groupingBy(v -> v, Collectors.counting()))
                .entrySet();

        Assertions.assertEquals(Move.values().length, moves.size());

        moves.forEach((entry) -> Assertions.assertAll(
                () -> Assertions.assertNotEquals(0, entry.getValue()),
                () -> Assertions.assertNotEquals(numberOfRounds, entry.getValue())
        ));
    }

}
