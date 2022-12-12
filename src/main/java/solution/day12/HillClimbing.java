package solution.day12;

import solution.Reader;
import solution.Solution;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HillClimbing implements Solution {
    // Day 12
    // Hill Climbing Algorithm
    // Don't work

    Reader reader = new Reader();

    @Override
    public void solve() {
        List<String> lines = reader.getItems("day12/test_input.txt");

        int startX = 0;
        int startY = 0;

        int endX = 0;
        int endY = 0;

        int x = 0;
        int y = 0;
        List<List<Integer>> grid = new ArrayList<>();
        for (String line : lines) {
            List<Integer> row = new ArrayList<>();
            char[] letters = line.toCharArray();
            y = 0;
            for (char c : letters) {
                if (c == 'S') {
                    row.add(0);
                    startX = x;
                    startY = y;
                } else if (c == 'E') {
                    row.add('z' - 'a');
                    endX = x;
                    endY = y;
                } else {
                    row.add(c - 'a');
                }

                y++;
            }
            grid.add(row);
            x++;
        }

        Point startPoint = new Point(startX, startY);
        Point endPoint = new Point(endX, endY);
        Set<Point> visited = new HashSet<>();

        part1(grid, startPoint, endPoint, 0, visited);

        System.out.printf("Part 1 - solution: %d %n", smallest);
    }


    int smallest = Integer.MAX_VALUE;

    private void part1(List<List<Integer>> grid, Point p, Point endPoint, int steps, Set<Point> visited) {

        System.out.printf("%d %d %n", p.getX(), p.getY());

        if (steps > grid.size() * grid.get(0).size()) {
            return;
        }

        if (p.equals(endPoint)) {
            if (steps < smallest) {
                smallest = steps;
                System.out.println(smallest);
            }
            return;
        }

        if (visited.contains(p)) {
            return;
        }

        visited.add(p);

        int x = p.getX();
        int y = p.getY();
        int n = grid.get(x).get(y);

        // right
        if (y + 1 < grid.get(x).size() && Math.abs(grid.get(x).get(y + 1) - n) <= 1) {
            Point newPoint = new Point(x, y + 1);
            part1(grid, newPoint, endPoint, steps + 1, visited);
        }

        // left
        if (y - 1 >= 0 && Math.abs(grid.get(x).get(y - 1) - n) <= 1) {
            Point newPoint = new Point(x, y - 1);
            part1(grid, newPoint, endPoint, steps + 1, visited);
        }

        // top
        if (x - 1 >= 0 && Math.abs(grid.get(x - 1).get(y) - n) <= 1) {
            Point newPoint = new Point(x - 1, y);
            part1(grid, newPoint, endPoint, steps + 1, visited);
        }

        // down
        if (x + 1 < grid.size() && Math.abs(grid.get(x + 1).get(y) - n) <= 1) {
            Point newPoint = new Point(x + 1, y);
            part1(grid, newPoint, endPoint, steps + 1, visited);
        }
    }

    private void printGrid(List<List<Integer>> grid) {
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                System.out.printf("%d ", grid.get(i).get(j));
            }
            System.out.println();
        }
    }
}
