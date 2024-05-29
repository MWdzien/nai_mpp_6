import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        List<List<Item>> data = DataReader.readData("plecak.txt");

        Random r = new Random();

        int idx = r.nextInt(15);
        List<Item> items = data.get(idx);

        int knapsackCapacity = 50;

        FinalKnapsack heuristic = FinalKnapsack.heuristic(items, knapsackCapacity);
        System.out.println("Heuristic:\n" + heuristic);

        FinalKnapsack bruteforce = FinalKnapsack.bruteforce(items, knapsackCapacity);
        System.out.println("Bruteforce: \n" + bruteforce);


    }
}
