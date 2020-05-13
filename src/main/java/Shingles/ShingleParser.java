/**
 * @author Philipp Uryas
 */

package Shingles;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ShingleParser {

    private final File file;
    private final ArrayList<String> shingles;

    private final int shingleSize;

    /**
     * @param shingleSize can't be less than 1
     */
    public ShingleParser(File file, int shingleSize) {
        this.file = file;
        if (shingleSize < 1)
            this.shingleSize = 1;
        else
            this.shingleSize = shingleSize;
        shingles = new ArrayList<>();
        setShingles();
    }

    /**
     * @return parsed shingles
     */
    public ArrayList<String> getShingles() {
        return shingles;
    }

    private void setShingles() {
        System.out.println();
        ArrayList<String> tempStringArray = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
                tempStringArray.add(fileScanner.next());
            }
            for (int i = 0; i < tempStringArray.size(); i++) {
                StringBuilder t = new StringBuilder();
                for (int j = i; j < i + shingleSize && j < tempStringArray.size(); j++) {
                    t.append(tempStringArray.get(j));
                }
                System.out.println(t.toString());
                shingles.add(t.toString());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}