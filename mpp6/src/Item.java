public class Item {
    int idx;
    int size;
    int value;

    public Item(int idx, int size, int value) {
        this.idx = idx;
        this.size = size;
        this.value = value;
    }

    public double getRatio(){
        return (double) value / size;
    }
}
