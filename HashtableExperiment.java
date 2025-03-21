public class HashtableExperiment {
    
    private void summary(String method, Results result) {
        System.out.printf("\n        Using %s%n", method);
        System.out.printf("HashtableExperiment: size of hash table is %d%n", capacity / 2);
        System.out.printf("        Inserted %d elements, of which %d were duplicates%n",
                result.totalInserted, result.duplicates);
        System.out.printf("        Avg. no. of probes = %.2f%n", result.getAverageProbes());
    }

    public static void main(String[] args) {
        if (args.length < 2 || args.length > 3) {
            printUsage();
            return;
        }

        int dataSource = Integer.parseInt(args[0]);
        double loadFactor = Double.parseDouble(args[1]);
        int debugLevel = (args.length == 3) ? Integer.parseInt(args[2]) : 0;

        HashtableExperiment experiment = new HashtableExperiment(dataSource, loadFactor, debugLevel);
        experiment.run();
    }

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

    private static class Results {
        int totalInserted;
        int duplicates;
        int totalProbes;

        public Results(int totalInserted, int duplicates, int totalProbes) {
            this.totalInserted = totalInserted;
            this.duplicates = duplicates;
            this.totalProbes = totalProbes;
        }

        public double getAverageProbes() {
            return (totalInserted - duplicates) == 0 ? 0 : (double) totalProbes / (totalInserted - duplicates);
        }
    }
}