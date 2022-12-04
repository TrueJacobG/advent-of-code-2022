package solution.day3;

import solution.Reader;
import solution.Solution;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Rucksack implements Solution {
    // Day 3
    // Rucksack Reorganization

    Reader reader = new Reader();

    private void part1(List<String> backpacks) {
        int sum = 0;

        for (String line : backpacks) {
            String l1 = line.substring(0, line.length() / 2);
            String l2 = line.substring(line.length() / 2);

            Set<Character> set = new HashSet<>();

            for (char c : l1.toCharArray()) {
                set.add(c);
            }

            for (char c : l2.toCharArray()) {
                if (set.contains(c)) {
                    sum += getPriorityOfLetter(c);
                    break;
                }
            }
        }

        System.out.printf("Part 1 - sum: %d %n", sum);
    }

    private void part2(List<String> backpacks) {
        int sum = 0;

        for (int i = 0; i < backpacks.size(); i += 3) {
            String backpack1 = backpacks.get(i);
            String backpack2 = backpacks.get(i + 1);
            String backpack3 = backpacks.get(i + 2);

            Set<Character> set = new HashSet<>();

            for (char c : backpack1.toCharArray()) {
                set.add(c);
            }

            boolean flag = false;
            for (char c1 : backpack2.toCharArray()) {

                if (flag) {
                    break;
                }

                if (set.contains(c1)) {
                    for (char c2 : backpack3.toCharArray()) {
                        if (c2 == c1) {
                            sum += getPriorityOfLetter(c1);
                            flag = true;
                            break;
                        }
                    }
                }
            }

        }

        System.out.printf("Part 2 - sum: %d %n", sum);
    }

    @Override
    public void solve() {
        List<String> backpacks = reader.getItems("day3/input.txt");

        part1(backpacks);
        part2(backpacks);
    }

    private int getPriorityOfLetter(char c) {
        if (Character.isLowerCase(c)) {
            return c - 'a' + 1;
        } else {
            return c - 'A' + 27;
        }
    }
}
