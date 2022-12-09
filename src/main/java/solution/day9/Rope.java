package solution.day9;

import solution.Reader;
import solution.Solution;

import java.util.ArrayList;
import java.util.List;

public class Rope implements Solution {
    // Day 9
    // Rope Bridge

    Reader reader = new Reader();

    int xH = 500;
    int yH = 500;
    int xT = 500;
    int yT = 500;

    @Override
    public void solve() {
        List<String> lines = reader.getItems("day9/input.txt");
        List<List<Boolean>> grid = getGrid();


        for (String line : lines) {
            String direction = line.split(" ")[0];
            int n = Integer.parseInt(line.split(" ")[1]);

            while (n != 0) {
                moveHead(direction);

                if (!ifSticks()) {
                    xT = xH;
                    yT = yH;
                    if (direction.equals("U")) {
                        xT += 1;
                    } else if (direction.equals("D")) {
                        xT -= 1;
                    } else if (direction.equals("L")) {
                        yT += 1;
                    } else {
                        yT -= 1;
                    }
                    grid.get(xT).set(yT, true);
                }

                n--;
            }
        }


        System.out.printf("Part 1 - solution %d %n", countPoints(grid));
    }

    private void moveHead(String direction) {
        if (direction.equals("U")) {
            xH -= 1;
        } else if (direction.equals("D")) {
            xH += 1;
        } else if (direction.equals("L")) {
            yH -= 1;
        } else {
            yH += 1;
        }
    }

    private boolean ifSticks() {
        return Math.abs(xH - xT) <= 1 && Math.abs(yH - yT) <= 1;
    }

    private List<List<Boolean>> getGrid() {
        List<List<Boolean>> result = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            List<Boolean> temp = new ArrayList<>();
            for (int j = 0; j < 1000; j++) {
                temp.add(false);
            }
            result.add(temp);
        }

        return result;
    }

    private int countPoints(List<List<Boolean>> grid) {
        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                if (grid.get(i).get(j)) {
                    sum++;
                }
            }
        }
        return sum;
    }
}
