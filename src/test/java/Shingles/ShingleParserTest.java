package Shingles;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;


public class ShingleParserTest {

    @org.junit.Test
    public void test() throws IOException {
        ArrayList<String> testShingles = new ArrayList<>();
        testShingles.add("intmain()std::cout");
        testShingles.add("main()std::cout<<");
        testShingles.add("std::cout<<\"Hello");
        testShingles.add("<<\"HelloWorld\";");
        testShingles.add("\"HelloWorld\";");
        testShingles.add("World\";");

        ArrayList<String> shingles =
                new ShingleParser( new File(
                                new File(".").getCanonicalPath() + "/src/test/java/resources/ShingleTest"),
                        3)
                        .getShingles();

        assertEquals(testShingles, shingles);
    }

}