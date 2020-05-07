package Shingles;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ShingleParser {
    private final File file;

    private ArrayList<String> shingles;

    private final int shingleSize;

    public ShingleParser(File file, int shingleSize) {
        this.file = file;
        this.shingleSize = shingleSize;
        setShingles();
    }

    public ArrayList<String> getShingles() {
        return shingles;
    }

    private void setShingles() {
        shingles = new ArrayList<>();
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
                shingles.add(t.toString());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}