public abstract class HashTable<T> {
    protected int size;
    protected int capacity;
    protected double loadFactor;
    protected HashObject<T>[] table;

    public HashTable(int capacity, double loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.size = 0;
        this.table = new HashObject[capacity];
    }

    public boolean insert(T key) {
        int probe = 0;
        int index;
        while (probe < capacity) {
            index = h(key, probe);
            if (table[index] == null) {
                table[index] = new HashObject<>(key);
                size++;
                return true;
            } else if (table[index].getKey().equals(key)) {
                // There is a duplicate, increment rather than insert
                table[index].incrementFrequency();
                return false;
            }
            probe++;
        }

        //Table is full, object not inserted
        return false;
    }

    public HashObject<T> search(T key) {
        int probe = 0;
        int index;
        while (probe < capacity) {
            index = h(key, probe);
            if (table[index] == null) {
                return null;
            }
            if (table[index].getKey().equals(key)) {
                return table[index];
            }
            probe++;
        }
        return null;
    }

    public boolean delete(T key) {
        int probe = 0;
        int index;
        while (probe < capacity) {
            index = h(key, probe);
            if (table[index] == null) {
                return false;
            }
            if (table[index].getKey().equals(key)) {
                table[index] = null;
                size--;
                return true;
            }
            probe++;
        }
        return false;
    }

    protected int positiveMod(int dividend, int divisor) {
        int quotient = dividend % divisor;
        if (quotient < 0) {
            quotient += divisor;
        }
        return quotient;
    }

    public abstract int h(T key, int probe);
}