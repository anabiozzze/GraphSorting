public class ResolverImplTest{
    private static final ResolverImpl impl = new ResolverImpl();

    public static boolean checkResult(int[] graph, String template) {
        StringBuilder bldr = new StringBuilder();

        for (int i: impl.resolve(graph)) {bldr.append(i);}

        String result = bldr.toString();
        boolean eqls = result.equals(template);

        System.out.println((result.isEmpty())? "empty" + ": " + eqls :  result + ": " + eqls);
        return eqls;
    }

    public static long checkTime(int[] graph) {
        long startTime = System.nanoTime();
        impl.resolve(graph);
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;

        System.out.println("Execution time: " + totalTime);
        return totalTime;
    }

    public static long checkMemory(int[] graph) {
        long before = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        impl.resolve(graph);
        long after = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long total = before-after;

        System.out.println("Memory usage: " + total);
        return total;
    }

}
