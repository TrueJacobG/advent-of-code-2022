package solution.day7;

import solution.Reader;
import solution.Solution;

import java.util.List;

public class NoSpace implements Solution {
    // Day 7
    // No Space Left On Device

    Reader reader = new Reader();

    private int part1Solution = 0;
    private int part2Solution = Integer.MAX_VALUE;

    @Override
    public void solve() {
        List<String> lines = reader.getItems("day7/input.txt");

        Node tree = new Node("/");

        int i = 1;
        while (i < lines.size()) {
            if (lines.get(i).equals("$ cd ..")) {
                tree = tree.parent;
            } else if (lines.get(i).startsWith("$ cd ")) {
                String dirName = lines.get(i).split(" ")[2];

                if (!tree.hasChild(dirName)) {
                    tree.addChild(dirName);
                }
                tree = tree.changeToChild(dirName);

            } else {
                int j = i + 1;

                while (!(j == lines.size() || lines.get(j).startsWith("$"))) {
                    if (lines.get(j).startsWith("dir ")) {
                        String dirName = lines.get(j).split(" ")[1];
                        if (!tree.hasChild(dirName)) {
                            tree.addChild(dirName);
                        }
                    } else {
                        int size = Integer.parseInt(lines.get(j).split(" ")[0]);
                        tree.increaseParentsSizes(size);
                    }

                    j++;
                }

                i = j - 1;
            }

            i++;
        }

        tree = tree.goToTop();
        part1(tree);
        System.out.printf("Part 1 - solution: %d %n", part1Solution);

        tree = tree.goToTop();
        int unusedSpace = 70000000 - tree.size;
        int reqSize = 30000000 - unusedSpace;
        part2(tree, reqSize);
        System.out.printf("Part 2 - solution: %d %n", part2Solution);
    }

    private void part1(Node tree) {
        for (Node child : tree.children) {
            if (child.size <= 100000) {
                part1Solution += child.size;
            }
            part1(child);
        }
    }

    private void part2(Node tree, int reqSize) {
        for (Node child : tree.children) {
            if (child.size > reqSize && child.size < part2Solution) {
                part2Solution = child.size;
            }
            part2(child, reqSize);
        }
    }
}
