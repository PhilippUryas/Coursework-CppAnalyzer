import main.FileWalker;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;


public class FileWalkerTest {

    @Test
    public void getAllPathsToFilesInDirectory() {
        final ArrayList<String> test = new ArrayList<>();
        test.add("src\\test\\java\\resources\\cppFolder\\main.cpp");
        test.add("src\\test\\java\\resources\\cppFolder\\main.h");

        assertEquals(test, new FileWalker("src\\test\\java\\resources\\cppFolder").getAllPathsToFilesInDirectory());
    }
}
