import Shingles.HashCodeFiller;
import Shingles.ShingleParser;
import main.ShinglesComparator;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ShinglesComparatorTest {

    @Before
    public void setup() {

    }

    @Test
    public void compareEqualFiles() throws IOException {
        assertEquals(0f, new ShinglesComparator().compare(
                new HashCodeFiller
                        (new ShingleParser
                                (new File(new File(".").getCanonicalPath() + "/src/test/java/resources/sourceTest/test1.cpp"), 1)
                                .getShingles())
                        .getHashCodes(), new HashCodeFiller
                        (new ShingleParser
                                (new File(new File(".").getCanonicalPath() + "/src/test/java/resources/targetTest/test1.cpp"), 1)
                                .getShingles())
                        .getHashCodes()), 0.1);
    }

    @Test
    public void compareUnequalFiles() throws IOException {
        assertEquals(1f, new ShinglesComparator().compare(
                new HashCodeFiller
                        (new ShingleParser
                                (new File(new File(".").getCanonicalPath() + "/src/test/java/resources/sourceTest/test2.cpp"), 1)
                                .getShingles())
                        .getHashCodes(), new HashCodeFiller
                        (new ShingleParser
                                (new File(new File(".").getCanonicalPath() + "/src/test/java/resources/targetTest/test2.cpp"), 1)
                                .getShingles())
                        .getHashCodes()), 0.1);
    }
}

