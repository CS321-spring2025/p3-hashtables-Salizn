public class HashObject {
    private final Object key;
    private int frequency;
    private int probeCount;

    public HashObject(Object key) {
        this.key = key;
        this.frequency = 1;
        this.probeCount = 0;
    }

    public Object getKey() {
        return key;
    }

    public int getFrequency() {
        return frequency;
    }

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

    @Override
    public String toString() {
        return key + " " + frequency + " " + probeCount;
    }
 }
