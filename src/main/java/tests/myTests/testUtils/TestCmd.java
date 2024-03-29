package tests.myTests.testUtils;


import org.apache.commons.cli.*;

import java.util.ArrayList;
import java.util.Arrays;

import static bench.V2.args;

public class TestCmd {
    private static final Options opt = new Options();

    public static String testArgs(String[] args) {
        ArrayList<String> argsNew = new ArrayList<>();
        String type = "all";
        for (String arg : args) {
            if (arg.startsWith("--")) {
                type = arg.substring(2);
            } else {
                argsNew.add(arg);
            }
        }
        args(argsNew.toArray(new String[0]));
        return type;
    }
}
