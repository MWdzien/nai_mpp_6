import java.util.ArrayList;
import java.util.List;

public class FinalKnapsack {
    List<Item> items;
    int totalSize;
    int totalValue;
    int checkedSets;
    long elapsedTime;

    public FinalKnapsack(List<Item> items, int totalSize, int totalValue, int checkedSets, long elapsedTime) {
        this.items = items;
        this.totalSize = totalSize;
        this.totalValue = totalValue;
        this.checkedSets = checkedSets;
        this.elapsedTime = elapsedTime;
    }

    public static FinalKnapsack bruteforce(List<Item> items, int capacity){
        long startTime = System.currentTimeMillis();
        int n = items.size();
        int maxVal = 0;
        int bestCombination = 0;
        int checkedSets = 0;

        for (int i = 0; i < (1 << n); i++) {
            int currentValue = 0;
            int currentSize = 0;

            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    currentValue += items.get(j).value;
                    currentSize += items.get(j).size;
                }
            }

            checkedSets++;
            if (currentSize <= capacity && currentValue > maxVal) {
                maxVal = currentValue;
                bestCombination = i;
            }
        }

        List<Item> bestItems = new ArrayList<>();
        int totalSize = 0;
        for (int j = 0; j < n; j++) {
            if ((bestCombination & (1 << j)) != 0) {
                bestItems.add(items.get(j));
                totalSize += items.get(j).size;
            }
        }

        long elapsedTime = System.currentTimeMillis() - startTime;
        return new FinalKnapsack(bestItems, totalSize, maxVal, checkedSets, elapsedTime);
    }

    public static FinalKnapsack heuristic(List<Item> items, int capacity){
        long startTime = System.currentTimeMillis();
        items.sort((a, b) -> Double.compare(b.getRatio(), a.getRatio()));
        int totalValue = 0;
        int totalSize = 0;
        int checkedSets = 0;

        List<Item> res = new ArrayList<>();
        for (Item item : items) {
            if (totalSize + item.size <= capacity) {
                res.add(item);
                totalValue += item.value;
                totalSize += item.size;
            }
            checkedSets++;
        }
        long elapsedTime = System.currentTimeMillis() - startTime;
        return new FinalKnapsack(res, totalSize, totalValue, checkedSets, elapsedTime);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Items:\n");
        for (Item item : this.items){
            res.append("Item " + item.idx + ": Size = " + item.size + ", Value = " + item.value + "\n");
        }
        res.append("Total value: " + this.totalValue+ "\n");
        res.append("Total size " + this.totalSize+ "\n");
        res.append("Checked sets: " + this.checkedSets+ "\n");
        res.append("Execution time: " + this.elapsedTime + "ms"+ "\n");

        return res.toString();
    }
}
