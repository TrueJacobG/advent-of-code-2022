package solution.day10;

import solution.Reader;
import solution.Solution;

import java.util.ArrayList;
import java.util.List;

public class Tube implements Solution {
    // Day 10
    // Cathode-Ray Tube

    Reader reader = new Reader();

    @Override
    public void solve() {
        List<String> lines = reader.getItems("day10/input.txt");

        part1(lines);
        part2(lines);
    }

    private void part1(List<String> lines){
        int sum = 0;
        int cycle = 1;
        int x = 1;

        for(String line : lines){
            if(line.equals("noop")){
                cycle++;
                sum += checkIfCountable(cycle, x);
            } else {
                int n = Integer.parseInt(line.split(" ")[1]);
                cycle++;
                sum += checkIfCountable(cycle, x);
                cycle++;
                x += n;
                sum += checkIfCountable(cycle, x);
            }

            if(cycle > 221){
                break;
            }
        }

        System.out.printf("Part 1 - solution: %d %n", sum);
    }

    private int checkIfCountable(int cycle, int x) {
        if((cycle - 20) % 40 == 0){
            return cycle * x;
        }
        return 0;
    }

    int cycle = 0;
    int row = 0;
    int x = 1;
    List<List<String>> grid = setUpGrid();
    private void part2(List<String> lines){
        for(String line : lines){
            if(line.equals("noop")){
                if(doMove()){
                    break;
                }
            } else {
                int n = Integer.parseInt(line.split(" ")[1]);
                if(doMove()){
                    break;
                }
                if(doMove()){
                    break;
                }
                x += n;
                checkIfDrawable(grid, cycle, x, row);
            }
        }

        for(int i = 0; i < grid.size(); i++){
            for(int j = 0; j < grid.get(i).size(); j++){
                System.out.print(grid.get(i).get(j));
            }
            System.out.println();
        }
    }

    private boolean doMove() {
        cycle++;
        if(cycle % 40 == 0){
            cycle = 0;
            row++;
            if(row == 6){
                return true;
            }
        }
        checkIfDrawable(grid, cycle, x, row);
        return false;
    }

    private List<List<String>> setUpGrid() {
        List<List<String>> grid = new ArrayList<>();
        for(int i = 0; i < 6; i++){
            List<String> temp = new ArrayList<>();
            for(int j = 0; j < 40; j++){
                temp.add("0");
            }
            grid.add(temp);
        }
        grid.get(0).set(0, "#");
        return grid;
    }

    private void checkIfDrawable(List<List<String>> grid, int cycle, int x, int row){
        if(cycle == x || cycle == x - 1 || cycle == x + 1){
            grid.get(row).set(cycle, "#");
        } else {
            grid.get(row).set(cycle, ".");
        }
    }
}
