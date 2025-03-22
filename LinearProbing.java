public class LinearProbing extends HashTable {

    public LinearProbing(int capacity, double loadFactor) {
        super(capacity, loadFactor);
    }
    
    @Override
    public int h(Object key, int probe) {
        int h1 = positiveMod(key.hashCode(), capacity);
        return positiveMod(h1 + probe, capacity);
    }
}
