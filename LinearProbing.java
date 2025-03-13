public class LinearProbing extends HashTable {

    public LinearProbing(int capacity, double loadFactor) {
        super(capacity, loadFactor);
    }
    
    @Override
    public int h(Object key, int probe) {
        int k = key.hashCode();
        return positiveMod(k % capacity + probe, capacity);
    }

    protected int positiveMod (int dividend, int divisor) {
        int quotient = dividend % divisor;
        if (quotient < 0)
        quotient += divisor;
        return quotient;
    }
}
