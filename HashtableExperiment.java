import java.util.Date;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;

public class HashtableExperiment {

    public static void main(String[] args) {
        // Check for args
        if (args.length < 2 || args.length > 3) {
            printUsage();
            return;
        }

        int dataSource = Integer.parseInt(args[0]);
        double loadFactor = Double.parseDouble(args[1]);
        int debugLevel = (args.length == 3) ? Integer.parseInt(args[2]) : 0;

        int tableSize = TwinPrimeGenerator.generateTwinPrime(95500, 96000);
        int maxSize = (int) Math.ceil(tableSize * loadFactor);

        // Tables
        LinearProbing linearTable = new LinearProbing(tableSize, loadFactor);
        DoubleHashing doubleTable = new DoubleHashing(tableSize, loadFactor);

        loadData(dataSource, linearTable, doubleTable, maxSize, debugLevel);
        
        if (debugLevel == 0) {
            debugSummary(linearTable, dataSource, loadFactor, 1, maxSize);
            debugSummary(doubleTable, dataSource, loadFactor, 2, maxSize);
        } else if (debugLevel == 1) {
            linearTable.dumpToFile("linear-dump.txt");
            doubleTable.dumpToFile("double-dump.txt");
            debugSummary(linearTable, dataSource, loadFactor, 1, maxSize);
            System.out.println("HashtableExperiment: Saved dump of hash table\n");

            debugSummary(doubleTable, dataSource, loadFactor, 2, maxSize);
            System.out.println("HashtableExperiment: Saved dump of hash table\n");
        }
    }

    /**
     * Loads the selected data type into the tables
     *
     * @param dataSource (type of data to insert)
     * @param linearTable (linear probing table)
     * @param doubleTable (double hashing table)
     * @param maxSize (maximum size of the table)
     */
    private static void loadData(int dataSource, HashTable linearTable, HashTable doubleTable, int maxSize, int debugLevel) {
        Random random = new Random();
        long current = new Date().getTime();
        List<String> words = new ArrayList<>();

        // Data source is words
        if (dataSource == 3) {
            words = loadWords("word-list.txt");
        }
        int wordIndex = 0;

        while (linearTable.size < maxSize && doubleTable.size < maxSize) {
            Object key;

            // Data source is random integers
            if (dataSource == 1) {
                key = random.nextInt();
            } else if (dataSource == 2) {
                // Data source is longs
                current += 1000;
                Date date = new Date(current);
                key = new HashObject(date);
            } else {
                // Words
                key = words.get(wordIndex);
                wordIndex++;
            }

            boolean linearInserted = linearTable.insert(key);
            boolean doubleInserted = doubleTable.insert(key);

            if (debugLevel == 2) {
                String statusLinear = linearInserted ? "Inserted" : "Duplicate";
                String statusDouble = doubleInserted ? "Inserted" : "Duplicate";

                System.out.println("[Linear Probing] Key: " + key + "  " + statusLinear);
                System.out.println("[Double Hashing] Key: " + key + "  " + statusDouble);
            }
        }
    }

    /**
     * Returns an array of words from the text file
     *
     * @param fileName
     * @returns array of words from the text file
     */
    private static List<String> loadWords(String fileName) {
        List<String> words = new ArrayList<>();
        
        // Scan the text file
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            while (line != null) {
                words.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            System.err.println("Error reading the file: " + fileName);
        }
        return words;
    }

    /**
     * Prints a summary of the experiments to the output
     *
     * @param linearTable (linear probing table)
     * @param doubleTable (double hashing table)
     * @param dataSource (type of data used)
     * @param loadFactor
     */
    private static void debugSummary(HashTable table, int dataSource, double loadFactor, int iteration, int maxSize) {
        String dataSouceName = (dataSource == 1) ? "Random-Numbers" : (dataSource == 2) ? "Date-Values" : "Word-List";
        int attempts = table.getInsertions() + table.getDuplicates();

        if (iteration == 1) {
            System.out.println("HashtableExperiment: Found a twin prime table capacity: " + table.capacity);
            System.out.println("HashtableExperiment: Input: " + dataSouceName + "   Loadfactor: " + String.format("%.2f", loadFactor));
            System.out.println("\n        Using Linear Probing");
        } else {
            System.out.println("\n        Using Double Hashing");
        }
        
        System.out.println("HashtableExperiment: size of hash table is " + maxSize);
        System.out.println("        Inserted " + attempts + " elements, of which " + table.getDuplicates() + " were duplicates");
        System.out.println("        Avg. no. of probes = " + String.format("%.2f", table.getProbeAverage()));
    }

    // Usage message
    private static void printUsage() {
        System.out.println("Usage: java HashtableExperiment <dataSource> <loadFactor> [<debugLevel>]");
        System.out.println("   <dataSource>: 1 ==> random numbers");
        System.out.println("                 2 ==> date value as a long");
        System.out.println("                 3 ==> word list");
        System.out.println("   <loadFactor>: The ratio of objects to table size, denoted by α = n/m");
        System.out.println("   <debugLevel>: 0 ==> print summary of experiment");
        System.out.println("                 1 ==> save the two hash tables to a file at the end");
        System.out.println("                 2 ==> print debugging output for each insert");
    }
}