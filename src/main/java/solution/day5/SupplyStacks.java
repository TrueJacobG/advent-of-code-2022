package solution.day5;

import solution.Reader;
import solution.Solution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class SupplyStacks implements Solution {
    // Day 5
    // Supply Stacks

    Reader reader = new Reader();

    @Override
    public void solve() {
        List<Stack<String>> stacks1 = getStacks();
        List<Stack<String>> stacks2 = getStacks();

        List<String> lines = reader.getItems("day5/input.txt");

        for (String line : lines) {
            String[] moves = line.split(" ");
            int number = Integer.parseInt(moves[1]);
            int from = Integer.parseInt(moves[3]);
            int to = Integer.parseInt(moves[5]);

            List<String> items1 = new ArrayList<>();
            List<String> items2 = new ArrayList<>();
            for (int i = 0; i < number; i++) {
                items1.add(stacks1.get(from - 1).pop());
                items2.add(stacks2.get(from - 1).pop());
            }

            Collections.reverse(items2);

            Stack<String> destinationStack1 = stacks1.get(to - 1);
            for (String item : items1) {
                destinationStack1.push(item);
            }

            Stack<String> destinationStack2 = stacks2.get(to - 1);
            for (String item : items2) {
                destinationStack2.push(item);
            }

            stacks1.set(to - 1, destinationStack1);
            stacks2.set(to - 1, destinationStack2);
        }

        String part1 = "";
        for (Stack<String> stack : stacks1) {
            part1 += stack.pop();
        }

        String part2 = "";
        for (Stack<String> stack : stacks2) {
            part2 += stack.pop();
        }

        System.out.printf("Part 1 - result: %s %n", part1);
        System.out.printf("Part 2 - result: %s %n", part2);

    }

    private List<Stack<String>> getStacks() {
        List<Stack<String>> stacks = new ArrayList<>();

        List<String> lines = reader.getItems("day5/input1.txt");

        for (String line : lines) {
            String[] letters = line.split(" ");
            Stack<String> stack = new Stack<>();
            for (String letter : letters) {
                stack.push(letter);
            }
            stacks.add(stack);
        }

        return stacks;
    }
}
