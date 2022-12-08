package solution.day8;

import solution.Reader;
import solution.Solution;

import java.util.ArrayList;
import java.util.List;

public class Treetop implements Solution {
    // Day 8
    // Treetop Tree House

    Reader reader = new Reader();

    @Override
    public void solve() {
        List<String> lines = reader.getItems("day8/input.txt");
        List<List<Integer>> grid = convertLinesToGrid(lines);

        part1(grid);
        part2(grid);
    }

    private void part1(List<List<Integer>> grid) {
        int visible = grid.size() * 2 + grid.get(0).size() * 2 - 4;
        for (int i = 1; i < grid.size() - 1; i++) {
            for (int j = 1; j < grid.get(i).size() - 1; j++) {
                if (isVisible(grid, i, j)) {
                    visible++;
                }
            }
        }

        System.out.printf("Part 1 - solution: %d %n", visible);
    }

    private void part2(List<List<Integer>> grid) {
        int max = 0;
        for (int i = 1; i < grid.size() - 1; i++) {
            for (int j = 1; j < grid.get(i).size() - 1; j++) {
                max = Math.max(max, countPoints(grid, i, j));
            }
        }

        System.out.printf("Part 2 - solution: %d %n", max);
    }

    private boolean isVisible(List<List<Integer>> grid, int x, int y) {
        boolean flag = true;
        for (int i = 0; i < x; i++) {
            if (i != x && (grid.get(i).get(y) >= grid.get(x).get(y))) {
                flag = false;
            }
        }

        if (flag) {
            return true;
        }
        flag = true;

        for (int i = x + 1; i < grid.size(); i++) {
            if (i != x && (grid.get(i).get(y) >= grid.get(x).get(y))) {
                flag = false;
            }
        }

        if (flag) {
            return true;
        }
        flag = true;

        for (int i = 0; i < y; i++) {
            if (i != y && (grid.get(x).get(i) >= grid.get(x).get(y))) {
                flag = false;
            }
        }

        if (flag) {
            return true;
        }
        flag = true;

        for (int i = y + 1; i < grid.get(x).size(); i++) {
            if (i != y && (grid.get(x).get(i) >= grid.get(x).get(y))) {
                flag = false;
            }
        }

        if (flag) {
            return true;
        }

        return false;
    }

    private int countPoints(List<List<Integer>> grid, int x, int y) {
        int points = 1;
        int visible = 0;

        for (int i = x - 1; i >= 0; i--) {
            visible++;
            if (grid.get(i).get(y) >= grid.get(x).get(y)) {
                break;
            }
        }
        points *= visible;

        visible = 0;
        for (int i = x + 1; i < grid.size(); i++) {
            visible++;
            if (grid.get(i).get(y) >= grid.get(x).get(y)) {
                break;
            }
        }
        points *= visible;

        visible = 0;
        for (int i = y - 1; i >= 0; i--) {
            visible++;
            if (grid.get(x).get(i) >= grid.get(x).get(y)) {
                break;
            }
        }
        points *= visible;

        visible = 0;
        for (int i = y + 1; i < grid.get(x).size(); i++) {
            visible++;
            if (grid.get(x).get(i) >= grid.get(x).get(y)) {
                break;
            }
        }
        points *= visible;

        return points;
    }

    private List<List<Integer>> convertLinesToGrid(List<String> lines) {
        List<List<Integer>> grid = new ArrayList<>();

        for (String line : lines) {
            List<Integer> row = new ArrayList<>();
            for (char c : line.toCharArray()) {
                row.add(Character.getNumericValue(c));
            }
            grid.add(row);
        }

        return grid;
    }
}
