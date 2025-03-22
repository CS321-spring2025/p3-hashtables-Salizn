public class DoubleHashing extends HashTable {

    public DoubleHashing(int capacity, double loadFactor) {
        super(capacity, loadFactor);
    }
    
    /**
     * Overrides the abstract method for the HashTable class using double hashing
     *
     * @param key (key of the object)
     * @param probe (number of probes)
     * @returns index of the HashTable where the item should be stored
     */
    @Override
    public int h(Object key, int probe) {
        int k = key.hashCode();
        int h1 = positiveMod(k, capacity);
        int h2 = 1 + positiveMod(k, capacity - 2);

        return positiveMod(h1 + probe * h2, capacity);
    }
}
