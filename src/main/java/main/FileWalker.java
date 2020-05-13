package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileWalker {
    private final String directory;

    public FileWalker(String directory) {
        this.directory = directory;
    }

    public ArrayList<String> getAllPathsToFilesInDirectory() {
        try (Stream<Path> walk = Files.walk(Paths.get(directory))) {
            return  (ArrayList<String>) walk.map(x -> x.toString())
                    .filter(f -> (f.endsWith(".cpp") || f.endsWith(".h"))).collect(Collectors.toList());

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
