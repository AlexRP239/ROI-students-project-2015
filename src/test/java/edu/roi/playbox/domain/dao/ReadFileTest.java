package edu.roi.playbox.domain.dao;

import org.junit.Test;

import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author apavelchuk
 * @since 07.07.2015.
 */
public class ReadFileTest {

    @Test
    public void readDarkenery() throws Exception {
        System.out.println("Java 8 style круче");
        final List<String> lines = Files.readAllLines(Paths.get("src/test/resources/Darkenery.txt"), Charset.forName("windows-1251"));
        lines.forEach(System.out::println);
    }

    @Test(expected = CharacterCodingException.class)
    public void readDarkeneryFailed() throws Exception {
        System.out.println("Java 6 style проще");
        final List<String> lines = Files.readAllLines(Paths.get("src/test/resources/Darkenery.txt"));
        for (String line : lines) {
            System.out.println(line);
        }
    }

}
