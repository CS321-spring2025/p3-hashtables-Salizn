import java.util.Date;
import java.util.Scanner;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

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
        int maxSize = (int) (tableSize * loadFactor);

        // Tables
        LinearProbing linearTable = new LinearProbing(tableSize, loadFactor);
        DoubleHashing doubleTable = new DoubleHashing(tableSize, loadFactor);

        loadData(dataSource, linearTable, doubleTable, maxSize);
        
        if (debugLevel == 0) {
            debugSummary(linearTable, doubleTable, dataSource, loadFactor);
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
    private static void loadData(int dataSource, HashTable linearTable, HashTable doubleTable, int maxSize) {
        Random random = new Random();
        long current = new Date().getTime();
        List<String> words = new ArrayList<>();

        // Data source is words
        if (dataSource == 3) {
            words = loadWords("word-list.txt");
        }
        int wordIndex = 0;

        while (linearTable.size <= maxSize && doubleTable.size <= maxSize) {
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
                key = words.get(wordIndex % words.size());
                wordIndex++;
            }

            linearTable.insert(key);
            doubleTable.insert(key);
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
        try (Scanner scanner = new Scanner(new java.io.File(fileName))) {
            while (scanner.hasNext()) {
                words.add(scanner.next());
            }
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
    private static void debugSummary(HashTable linearTable, HashTable doubleTable, int dataSource, double loadFactor) {
        String dataSouceName = (dataSource == 1) ? "Random-Numbers" : (dataSource == 2) ? "Date-Values" : "Word-List";
        int linearAttempts = linearTable.getInsertions() + linearTable.getDuplicates();
        int doubleAttempts = doubleTable.getInsertions() + doubleTable.getDuplicates();

        System.out.println("HashtableExperiment: Found a twin prime table capacity: " + linearTable.capacity);
        System.out.println("HashtableExperiment: Input: " + dataSouceName + "   Loadfactor: " + String.format("%.2f", loadFactor));
        System.out.println("\n        Using Linear Probing");
        System.out.println("HashtableExperiment: size of hash table is " + linearTable.size);
        System.out.println("        Inserted " + linearAttempts + " elements, of which " + linearTable.getDuplicates() + " were duplicates");
        System.out.println("        Avg. no. of probes = " + String.format("%.2f", linearTable.getProbeAverage()));
        System.out.println("\n        Using Double Hashing");
        System.out.println("HashtableExperiment: size of hash table is " + doubleTable.size);
        System.out.println("        Inserted " + doubleAttempts + " elements, of which " + doubleTable.getDuplicates() + " were duplicates");
        System.out.println("        Avg. no. of probes = " + String.format("%.2f", doubleTable.getProbeAverage()));
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