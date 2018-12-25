package dmutils.com.dmutils.general;

public final class DMUtilMemory {

    public static void freeMemory() {
        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();
    }
}
