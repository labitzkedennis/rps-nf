package de.labitzke.rps_nf.game;

import de.labitzke.rps_nf.player.Player;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static de.labitzke.rps_nf.game.Move.*;

public class Game {

    private final Player firstPlayer;
    private final Player secondPlayer;
    private final int roundsToPlay;

    private final Map<Map.Entry<Move, Move>, Player> resultLookUpTable;
    private final Map<Player, Integer> winTable;

    public Game(Player firstPlayer, Player secondPlayer, int roundsToPlay) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.roundsToPlay = roundsToPlay;

        this.resultLookUpTable = Map.of(
                Map.entry(ROCK, PAPER), this.secondPlayer,
                Map.entry(ROCK, SCISSORS), this.firstPlayer,
                Map.entry(PAPER, SCISSORS), this.secondPlayer,
                Map.entry(PAPER, ROCK), this.firstPlayer,
                Map.entry(SCISSORS, ROCK), this.secondPlayer,
                Map.entry(SCISSORS, PAPER), this.firstPlayer
        );

        this.winTable = new HashMap<>() {{
            put(firstPlayer, 0);
            put(secondPlayer, 0);
        }};
    }

    public String simulate() {
        int round = 0;
        while (round < roundsToPlay) {
            playRound();
            round++;
        }

        return buildResultOutput();
    }

    private void playRound() {
        Optional.ofNullable(
                resultLookUpTable.get(Map.entry(firstPlayer.makeMove(), secondPlayer.makeMove()))
        ).ifPresent(winningPlayer -> winTable.computeIfPresent(winningPlayer, (player, wins) -> wins + 1));
    }

    private String buildResultOutput() {
        int tieCount = roundsToPlay - winTable.values().stream().reduce(0, Integer::sum);
        StringBuilder sb = new StringBuilder();

        String lineBreak = "";
        for (Map.Entry<Player, Integer> entry : winTable.entrySet().stream().sorted(Comparator.comparing(entry -> entry.getKey().name())).toList()) {
            sb.append(lineBreak)
                    .append(String.format("%s: Gewonnen: %d, Verloren: %d, Unentschieden: %d",
                            entry.getKey().name(),
                            entry.getValue(),
                            roundsToPlay - entry.getValue() - tieCount,
                            tieCount));
            lineBreak = "\n";
        }
        return sb.toString();
    }
}
