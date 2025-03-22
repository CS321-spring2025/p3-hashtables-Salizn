public abstract class HashTable {
    protected int size;
    protected int capacity;
    protected double loadFactor;
    protected HashObject[] table;

    protected int insertions = 0;
    protected int duplicates = 0;
    protected int totalProbes = 0;

    public HashTable(int capacity, double loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.size = 0;
        this.table = new HashObject[capacity];
    }

    public boolean insert(Object key) {
        int probe = 0;
        int index;

        while (probe < capacity) {
            index = h(key, probe);
            if (table[index] == null) {
                table[index] = new HashObject(key);
                size++;
                insertions++;
                totalProbes += probe + 1;
                return true;
            } else if (table[index].getKey().equals(key)) {
                // There is a duplicate, increment rather than insert
                table[index].incrementFrequency();
                duplicates++;
                return false;
            }
            probe++;
        }

        //Table is full, object not inserted
        return false;
    }

    public HashObject search(Object key) {
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

    public boolean delete(Object key) {
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

    public int getInsertions() {
        return insertions;
    }

    public int getDuplicates() {
        return duplicates;
    }

    public double getProbeAverage() {
        return insertions == 0 ? 0 : (double) totalProbes / insertions;
    }

    public abstract int h(Object key, int probe);
}