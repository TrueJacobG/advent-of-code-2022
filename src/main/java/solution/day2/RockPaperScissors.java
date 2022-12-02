package solution.day2;

import solution.Reader;
import solution.Solution;

import java.util.List;

public class RockPaperScissors implements Solution {
    // Day 2
    // Rock Paper Scissors

    Reader reader = new Reader();


    @Override
    public void solve() {
        List<String> items = reader.getItems("day2/input.txt");

        int part1Sum = 0;
        int part2Sum = 0;

        for (String item : items) {
            String enemy = item.split(" ")[0];
            String player = item.split(" ")[1];

            part1Sum += calculateGame(enemy, player);
            part2Sum += calculateGame2(enemy, player);
        }

        System.out.printf("Part 1 - sum: %d %n", part1Sum);
        System.out.printf("Part 2 - sum: %d %n", part2Sum);
    }


    // 1st column - enemy -  A for Rock, B for Paper, and C for Scissors
    // 2nd column - you   -  X for Rock, Y for Paper, and Z for Scissors
    // 1 for Rock, 2 for Paper, and 3 for Scissors + 0 if you lost, 3 if the round was a draw, and 6 if you won
    private int calculateGame(String enemy, String player) {
        switch (enemy + player) {
            case "AX":
                return 1 + 3;
            case "AY":
                return 2 + 6;
            case "AZ":
                return 3 + 0;
            case "BX":
                return 1 + 0;
            case "BY":
                return 2 + 3;
            case "BZ":
                return 3 + 6;
            case "CX":
                return 1 + 6;
            case "CY":
                return 2 + 0;
            case "CZ":
                return 3 + 3;
            default:
                return -999;
        }
    }

    // 1st column - enemy -  A for Rock, B for Paper, and C for Scissors
    // 2nd column - you   -  X for lose, Y for draw, and Z for win
    // 1 for Rock, 2 for Paper, and 3 for Scissors + 0 if you lost, 3 if the round was a draw, and 6 if you won
    private int calculateGame2(String enemy, String player) {
        switch (enemy + player) {
            case "AX":
                return 3 + 0;
            case "AY":
                return 1 + 3;
            case "AZ":
                return 2 + 6;
            case "BX":
                return 1 + 0;
            case "BY":
                return 2 + 3;
            case "BZ":
                return 3 + 6;
            case "CX":
                return 2 + 0;
            case "CY":
                return 3 + 3;
            case "CZ":
                return 1 + 6;
            default:
                return -999;
        }
    }
}
