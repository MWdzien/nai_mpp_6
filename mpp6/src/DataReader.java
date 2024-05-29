import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
    public static List<List<Item>> readData(String filePath){
        List<List<Item>> data = new ArrayList<>();

        try {

            BufferedReader br = new BufferedReader(new FileReader(filePath));

            String line;
            while ((line = br.readLine()) != null){
                if (line.startsWith("sizes =")){
                    String[] sizes = line.substring(line.indexOf("{") + 1, line.indexOf("}")).replaceAll(" ", "").split(",");
                    line = br.readLine();
                    String[] values = line.substring(line.indexOf("{") + 1, line.indexOf("}")).replaceAll(" ", "").split(",");

                    List<Item> items = new ArrayList<>();

                    for (int i = 0; i < sizes.length; i++)
                        items.add(new Item(i+1, Integer.parseInt(sizes[i]), Integer.parseInt(values[i])));

                    data.add(items);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}
