public class LinearProbing<T> extends HashTable<T> {

    public LinearProbing(int capacity, double loadFactor) {
        super(capacity, loadFactor);
    }
    
    @Override
    public int h(T key, int probe) {
        int h1 = positiveMod(key.hashCode(), capacity);
        return positiveMod(h1 + probe, capacity);
    }
}
