import Shingles.HashCodeFiller;
import Shingles.ShingleParser;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);

            for (String s :
                    new FileWalker("src\\test\\java\\resources\\sourceTest").getAllPathsToFilesInDirectory()) {
                for (String t :
                        new FileWalker("src\\test\\java\\resources\\targerTest").getAllPathsToFilesInDirectory()) {

                    System.out.print(new ShinglesComparator().compare(
                            new HashCodeFiller
                                    (new ShingleParser
                                            (new File(s), i)
                                            .getShingles())
                                    .getHashCodes(),
                            new HashCodeFiller
                                    (new ShingleParser
                                            (new File(t), i)
                                            .getShingles())
                                    .getHashCodes()) * 100 + "%");
                    System.out.print(" " + s + " " + t + " ");
                    System.out.println();
                }
            }

            System.out.println("\n\n\n");

        }

    }
}