package solution.day4;

import solution.Reader;
import solution.Solution;

import java.util.List;

public class CampCleanup implements Solution {
    // Day 4
    // Camp Cleanup

    Reader reader = new Reader();

    @Override
    public void solve() {
        List<String> numbers = reader.getItems("day4/input.txt");

        int part1 = 0;
        int part2 = 0;

        for (String number : numbers) {
            String comp1 = number.split(",")[0];
            String comp2 = number.split(",")[1];

            int n1 = Integer.parseInt(comp1.split("-")[0]);
            int n2 = Integer.parseInt(comp1.split("-")[1]);
            int n3 = Integer.parseInt(comp2.split("-")[0]);
            int n4 = Integer.parseInt(comp2.split("-")[1]);

            if (isInsideCompartment(n1, n2, n3, n4) || isInsideCompartment(n3, n4, n1, n2)) {
                part1 += 1;
            }

            if (isOverlapping(n1, n2, n3, n4) || isOverlapping(n3, n4, n1, n2)) {
                part2 += 1;
            }
        }

        System.out.printf("Part 1 - sum: %d %n", part1);
        System.out.printf("Part 2 - sum: %d %n", part2);

    }

    private boolean isInsideCompartment(int n1, int n2, int n3, int n4) {
        // compartment n1 - n2 is inside n3 - n4
        return n1 >= n3 && n1 <= n4 && n2 >= n3 && n2 <= n4;
    }

    private boolean isOverlapping(int n1, int n2, int n3, int n4) {
        return (n1 >= n3 && n1 <= n4) || (n2 <= n4 && n2 >= n3);
    }
}
