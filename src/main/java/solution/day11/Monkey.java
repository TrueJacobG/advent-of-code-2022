package solution.day11;

import java.util.List;

public class Monkey {

    private long numberOfInspections = 0;
    private List<Long> items;
    private char operationType;
    private String operationNumber;
    private int test;
    private int indexIfTrue;
    private int indexIfFalse;

    public Monkey() {
    }

    public Monkey(List<Long> items, char operationType, String operationNumber, int test, int indexIfTrue, int indexIfFalse) {
        this.items = items;
        this.operationType = operationType;
        this.operationNumber = operationNumber;
        this.test = test;
        this.indexIfTrue = indexIfTrue;
        this.indexIfFalse = indexIfFalse;
    }

    public void addItem(long item) {
        items.add(item);
    }

    public void removeItem() {
        items.remove(0);
    }

    public long calculatePriorityAfterDivision(boolean part1) {
        numberOfInspections++;
        long n = this.items.get(0);

        long priorityNumber;
        if (operationNumber.equals("old")) {
            priorityNumber = n;
        } else {
            priorityNumber = Integer.parseInt(operationNumber);
        }

        if (operationType == '+') {
            n += priorityNumber;
        } else {
            n *= priorityNumber;
        }

        if (part1) {
            return n / 3;
        }
        return n;
    }

    public int getIndexNewMonkey(long n) {
        if (n % test == 0) {
            return indexIfTrue;
        }
        return indexIfFalse;
    }

    public List<Long> getItems() {
        return items;
    }

    public long getNumberOfInspections() {
        return numberOfInspections;
    }

    public void print() {
        System.out.println(items);
        System.out.println(operationType + operationNumber);
        System.out.println(test);
        System.out.printf("%d %d %n", indexIfTrue, indexIfFalse);
        System.out.println();
    }

}
