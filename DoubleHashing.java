public class DoubleHashing extends HashTable {

    public DoubleHashing(int capacity, double loadFactor) {
        super(capacity, loadFactor);
    }
    
    @Override
    public int h(Object key, int probe) {
        int k = key.hashCode();
        int h1 = k % capacity;
        int h2 = 1 + (k % (capacity - 2));

        return positiveMod(h1 + probe * h2, capacity);
    }

    protected int positiveMod (int dividend, int divisor) {
        int quotient = dividend % divisor;
        if (quotient < 0)
        quotient += divisor;
        return quotient;
    }
}
