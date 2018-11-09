package dmutils.com.dmutils.general;

public class DMMemory {

    public static void freeMemory() {
        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();
    }
}
