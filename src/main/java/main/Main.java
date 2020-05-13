package main;

import Shingles.HashCodeFiller;
import Shingles.ShingleParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@SpringBootApplication
@RestController
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @RequestMapping(value = "/getUniqResults", method = RequestMethod.GET)
    public String main(@RequestParam("sourceDirectory") String source, @RequestParam("targetDirectory") String target) {
        return getUniqResults(source, target);
    }

    private String getUniqResults(String source, String target) {
        String temp = "";
        for (int i = 0; i < 10; i++) {
            for (String s :
                    new FileWalker(source).getAllPathsToFilesInDirectory()) {
                for (String t :
                        new FileWalker(target).getAllPathsToFilesInDirectory()) {

                    temp += ("<p>" + new ShinglesComparator().compare(
                            new HashCodeFiller
                                    (new ShingleParser
                                            (new File(s), i)
                                            .getShingles())
                                    .getHashCodes(),
                            new HashCodeFiller
                                    (new ShingleParser
                                            (new File(t), i)
                                            .getShingles())
                                    .getHashCodes()) * 100 + "% " + s + " " + t + "</p>");
                }
            }
        }
        return temp;
    }

}