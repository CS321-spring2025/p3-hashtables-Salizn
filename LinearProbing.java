public class LinearProbing extends HashTable {

    public LinearProbing(int capacity, double loadFactor) {
        super(capacity, loadFactor);
    }
    
    /**
     * Overrides the abstract method for the HashTable class using linear probing
     *
     * @param key (key of the object)
     * @param probe (number of probes)
     * @returns index of the HashTable where the item should be stored
     */
    @Override
    public int h(Object key, int probe) {
        int h1 = positiveMod(key.hashCode(), capacity);
        return positiveMod(h1 + probe, capacity);
    }
}
