package solution.day6;

import solution.Reader;
import solution.Solution;

public class TuningTrouble implements Solution {
    // Day 6
    // Tuning Trouble

    Reader reader = new Reader();

    @Override
    public void solve() {
        String line = reader.getItems("day6/input.txt").get(0);

        int index1 = 0;
        for (int i = 4; i < line.length(); i++) {
            if (!isRepetitive(line.substring(i - 4, i))) {
                index1 = i;
                break;
            }
        }

        int index2 = 0;
        for (int i = 14; i < line.length(); i++) {
            if (!isRepetitive(line.substring(i - 14, i))) {
                index2 = i;
                break;
            }
        }

        System.out.printf("Part 1 - result: %d %n", index1);
        System.out.printf("Part 2 - result: %d %n", index2);

    }

    private boolean isRepetitive(String substring) {
        for (int i = 0; i < substring.length(); i++) {
            for (int j = i + 1; j < substring.length(); j++) {
                if (substring.charAt(i) == substring.charAt(j)) {
                    return true;
                }
            }
        }

        return false;
    }
}
