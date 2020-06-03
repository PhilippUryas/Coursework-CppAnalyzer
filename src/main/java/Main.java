import Shingles.HashCodeFiller;
import Shingles.ShingleParser;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        {
            for (String s :
                    new FileWalker(args[0]).getAllPathsToFilesInDirectory()) {
                for (String t :
                        new FileWalker(args[1]).getAllPathsToFilesInDirectory()) {

                    System.out.print(new ShinglesComparator().compare(
                            new HashCodeFiller
                                    (new ShingleParser
                                            (new File(s), 3)
                                            .getShingles())
                                    .getHashCodes(),
                            new HashCodeFiller
                                    (new ShingleParser
                                            (new File(t), 3)
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