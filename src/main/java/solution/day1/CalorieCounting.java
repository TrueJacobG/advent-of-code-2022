package solution.day1;

import solution.Reader;
import solution.Solution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CalorieCounting implements Solution {
    // Calorie Counting
    // https://adventofcode.com/2022/day/1

    Reader reader = new Reader();

    private void part1(List<Integer> calories){
        System.out.printf("Part 1 - solution: %d %n", Collections.max(calories));
    }

    private void part2(List<Integer> calories){
        Collections.sort(calories);
        System.out.printf("Part 2 - solution: %d %n", calories.get(calories.size()-1) + calories.get(calories.size()-2) + calories.get(calories.size()-3));
    }

    @Override
    public void solve() {
        List<String> items = reader.getItems("day1/input.txt");
        List<Integer> calories = new ArrayList<>();

        int index = 0;
        calories.add(0);
        for(String item : items){
            if(item.equals("")){
                index++;
                calories.add(0);
                continue;
            }

            calories.set(index, calories.get(index)+Integer.parseInt(item));
        }

        part1(calories);
        part2(calories);
    }

}
