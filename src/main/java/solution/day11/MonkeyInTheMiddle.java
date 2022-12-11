package solution.day11;

import solution.Reader;
import solution.Solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MonkeyInTheMiddle implements Solution {
    // Day 11
    // Monkey in the Middle

    Reader reader = new Reader();

    @Override
    public void solve() {
        List<String> lines = reader.getItems("day11/input.txt");
        List<Monkey> monkeys = new ArrayList<>();

        List<String> temp = new ArrayList<>();
        for (String line : lines) {
            if (line.isBlank()) {
                List<Long> items = Arrays
                        .stream((temp.get(1)
                                .split(": ")[1])
                                .split(", "))
                        .map(Long::parseLong)
                        .collect(Collectors.toList());
                char operationType = temp.get(2).charAt(23);
                String operationNumber = temp.get(2).split(" ")[7];
                int test = Integer.parseInt(temp.get(3).split(" ")[5]);
                int indexIfTrue = Integer.parseInt(temp.get(4).split(" ")[9]);
                int indexIfFalse = Integer.parseInt(temp.get(5).split(" ")[9]);

                Monkey monkey = new Monkey(items, operationType, operationNumber, test, indexIfTrue, indexIfFalse);
                monkeys.add(monkey);
                temp = new ArrayList<>();
            } else {
                temp.add(line);
            }
        }
        List<Long> items = Arrays
                .stream((temp.get(1)
                        .split(": ")[1])
                        .split(", "))
                .map(Long::parseLong)
                .collect(Collectors.toList());
        char operationType = temp.get(2).charAt(23);
        String operationNumber = temp.get(2).split(" ")[7];
        int test = Integer.parseInt(temp.get(3).split(" ")[5]);
        int indexIfTrue = Integer.parseInt(temp.get(4).split(" ")[9]);
        int indexIfFalse = Integer.parseInt(temp.get(5).split(" ")[9]);

        Monkey monkey = new Monkey(items, operationType, operationNumber, test, indexIfTrue, indexIfFalse);
        monkeys.add(monkey);

        //part1(monkeys);
        //part2(monkeys);
    }

    private void part1(List<Monkey> monkeys) {
        int round = 0;
        while (round < 20) {
            for (int i = 0; i < monkeys.size(); i++) {
                Monkey monkey = monkeys.get(i);
                int n = 0;
                int initItemsSize = monkey.getItems().size();
                while (n < initItemsSize) {
                    long afterDivision = monkey.calculatePriorityAfterDivision(true);
                    int indexNewMonkey = monkey.getIndexNewMonkey(afterDivision);
                    monkey.removeItem();
                    monkeys.get(indexNewMonkey).addItem(afterDivision);
                    n++;
                }
            }
            round++;
        }

        List<Long> inspections = new ArrayList<>();
        for (Monkey monkey : monkeys) {
            inspections.add(monkey.getNumberOfInspections());
        }

        Collections.sort(inspections);

        System.out.printf("Part 1 - solution: %d %n", inspections.get(inspections.size()-1) * inspections.get(inspections.size()-2));
    }

    private void part2(List<Monkey> monkeys) {
        int round = 0;
        while (round < 10000) {
            for (int i = 0; i < monkeys.size(); i++) {
                Monkey monkey = monkeys.get(i);
                int n = 0;
                int initItemsSize = monkey.getItems().size();
                while (n < initItemsSize) {
                    long afterDivision = monkey.calculatePriorityAfterDivision(false);
                    int indexNewMonkey = monkey.getIndexNewMonkey(afterDivision);
                    monkey.removeItem();
                    monkeys.get(indexNewMonkey).addItem(afterDivision);
                    n++;
                }
            }
            round++;
        }

        List<Long> inspections = new ArrayList<>();
        for (Monkey monkey : monkeys) {
            inspections.add(monkey.getNumberOfInspections());
        }

        Collections.sort(inspections);

        System.out.printf("Part 2 - solution: %d %n", inspections.get(inspections.size()-1) * inspections.get(inspections.size()-2));
    }

    private void printMonkeys(List<Monkey> monkeys) {
        for (Monkey monkey : monkeys) {
            monkey.print();
        }
        System.out.println();
    }
}
