package org.merry.simulation;

public class CreateGAAircraft {

    static Aircraft ac1 = new Aircraft();

    public static String returnCS() {
        return Aircraft.generatedCallsign();
    }
    public static String returnType() {
        return ac1.getType();
    }
    public static String returnStatus() {
        return ac1.getStatus();
    }
}
