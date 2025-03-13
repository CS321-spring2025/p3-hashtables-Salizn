public class LinearProbing extends HashTable {

    public LinearProbing(int capacity, double loadFactor) {
        super(capacity, loadFactor);
    }
    
    public int h(Object key, int probe) {
        return 0;
    }
}
