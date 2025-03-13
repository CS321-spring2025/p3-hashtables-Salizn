public abstract class HashTable {
    protected int size;
    protected int capacity;
    protected double loadFactor;
    protected HashObject[] table;

    public HashTable(int capacity, double loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.size = 0;
        this.table = new HashObject[capacity];
    }

    public boolean insert(Object key, Object value) {
        int probe = 0;
        int index;
        while (probe < capacity) {
            index = h(key, probe);
            if (table[index] == null) {
                table[index] = new HashObject(key, value);
                size++;
                return true;
            }
            probe++;
        }

        //Table is full, object not inserted
        return false;
    }

    public Object search(Object key) {
        int probe = 0;
        int index;
        while (probe < capacity) {
            index = h(key, probe);
            if (table[index] == null) {
                return null;
            }
            if (table[index].getKey().equals(key)) {
                return table[index].getValue();
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
                table[index].markDeleted();
                size--;
                return true;
            }
            probe++;
        }
        return false;
    }

    public abstract int h(Object key, int probe);
}