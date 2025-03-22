public class HashObject {
    private final Object key;
    private int frequency;
    private int probeCount;

    public HashObject(Object key) {
        this.key = key;
        this.frequency = 1;
        this.probeCount = 0;
    }

    /**
     * Returns the key of the HashObject.
     *
     * @returns key of the HashObject
     */
    public Object getKey() {
        return key;
    }

    /**
     * Returns the frequency of the HashObject.
     *
     * @returns frequency of the HashObject
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * Returns the probe count of the HashObject.
     *
     * @returns probe count of the HashObject
     */
    public int getProbeCount() {
        return probeCount;
    }

    public void incrementFrequency() {
        frequency++;
    }

    public void incrementProbes() {
        probeCount++;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        HashObject other = (HashObject) object;
        return key.equals(other.key);
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    // String method for Debug 1
    @Override
    public String toString() {
        return key + " " + frequency + " " + probeCount;
    }
 }
