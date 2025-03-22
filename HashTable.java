public abstract class HashTable {
    protected int size;
    protected int capacity;
    protected double loadFactor;
    protected HashObject[] table;

    // Stats to track
    protected int insertions = 0;
    protected int duplicates = 0;
    protected int totalProbes = 0;

    public HashTable(int capacity, double loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.size = 0;
        this.table = new HashObject[capacity];
    }

    /**
     * Inserts a value into the HashTable
     *
     * @param key (key to use to insert)
     * @returns true if insert was successful, false if not
     */
    public boolean insert(Object key) {
        int probe = 0;
        int index;

        while (probe < capacity) {
            // Chose Linear or Double
            index = h(key, probe);

            //Insert if index is null
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

    /**
     * Searches through the HashTable for a certain value
     *
     * @param key (key to use to search)
     * @returns found HashObject if search was successful, null if not found
     */
    public HashObject search(Object key) {
        int probe = 0;
        int index;

        while (probe < capacity) {
            // Choose Linear or Double
            index = h(key, probe);

            // Check for key and return
            if (table[index] == null) {
                return null;
            }
            if (table[index].getKey().equals(key)) {
                return table[index];
            }
            probe++;
        }

        // Key not found
        return null;
    }

    /**
     * Deletes a value from the HashTable
     *
     * @param key (key to delete)
     * @returns true if delete was successful, false if not
     */
    public boolean delete(Object key) {
        int probe = 0;
        int index;

        while (probe < capacity) {
            // Choose Linear or Double
            index = h(key, probe);

            // Check for and delete key
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

        // Key not found
        return false;
    }

    /**
     * Gets around the possiblity of negative numbers when 
     * using the % operator.
     *
     * @param dividend
     * @param divisor
     * @returns postive quotient
     */
    protected int positiveMod(int dividend, int divisor) {
        int quotient = dividend % divisor;
        if (quotient < 0) {
            quotient += divisor;
        }
        return quotient;
    }

    /**
     * Returns the number of insertions into the HashTable.
     *
     * @returns number of insertions into the HashTable
     */
    public int getInsertions() {
        return insertions;
    }

    /**
     * Returns the amount of duplicates recorded in the Hashtable.
     *
     * @returns amount of duplicates recorded in the HashTable
     */
    public int getDuplicates() {
        return duplicates;
    }

    /**
     * Returns the probe average of the HashTable.
     *
     * @returns probe average of the HashTable
     */
    public double getProbeAverage() {
        return insertions == 0 ? 0 : (double) totalProbes / insertions;
    }

    public abstract int h(Object key, int probe);
}