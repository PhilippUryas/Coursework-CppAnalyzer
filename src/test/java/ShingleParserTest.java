import Shingles.HashCodeFiller;
import Shingles.ShingleParser;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;


public class ShingleParserTest {

    final ArrayList<String> testShingles = new ArrayList<>();

    @Before
    public void setup() {
        testShingles.add("intmain()std::cout");
        testShingles.add("main()std::cout<<");
        testShingles.add("std::cout<<\"Hello");
        testShingles.add("<<\"HelloWorld\";");
        testShingles.add("\"HelloWorld\";");
        testShingles.add("World\";");
    }

    @Test
    public void getShingles() throws IOException {

        ArrayList<String> shingles =
                new ShingleParser(new File(
                        new File(".").getCanonicalPath() + "/src/test/java/resources/ShingleTest"),
                        3)
                        .getShingles();

        assertEquals(testShingles, shingles);
    }

    @Test
    public void getHashCodes() {
        final ArrayList<Integer> testHashes = new ArrayList<>();
        for(String s : testShingles)
        {
            testHashes.add(s.hashCode());
        }
        assertEquals(testHashes, new HashCodeFiller(testShingles).getHashCodes());
    }

}