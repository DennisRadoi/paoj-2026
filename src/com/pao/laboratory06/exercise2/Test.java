package com.pao.laboratory06.exercise2;

import com.pao.test.IOTest;
import com.pao.laboratory06.exercise2.Main;
public class Test {
    public static void main(String[] args) {
        IOTest.runParts("paoj-2026/src/com/pao/laboratory06/exercise2/tests",
                com.pao.laboratory06.exercise2.Main::main);
//        IOTest.runPart("src/com/pao/laboratory06/exercise2/tests", "partA", Main::main);
//        IOTest.runPart("src/com/pao/laboratory06/exercise2/tests", "partB", Main::main);
//        IOTest.runPart("src/com/pao/laboratory06/exercise2/tests", "partC", Main::main);
    }
}
