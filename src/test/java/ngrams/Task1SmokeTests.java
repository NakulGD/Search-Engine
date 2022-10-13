package ngrams;

import cpen221.mp1.ngrams.NGrams;
import cpen221.mp1.ratemyprofessor.DataAnalyzer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task1SmokeTests {

    @Test
    public void simpleTestCount() throws Exception{
        String text1 = "the blue cow jumped over the blue cow moon!";
        String text2 = "The Blue Period of Picasso is the period between 1900 and 1904, when he painted essentially monochromatic paintings in shades of blue and blue-green, only occasionally warmed by other colors.";

        long expectedCount = 130;

        NGrams ng = new NGrams(new String[]{text1, text2});

        assertEquals(expectedCount, ng.getTotalNGramCount(4));
    }

    @Test
    public void simpleTestGetNGrams() throws Exception {
        String text1 = "great class";
        String text2 = "good textbook written by him";

        List<Map<String, Long>> expectedNGrams = List.of(
                Map.of("great", 1L, "class", 1L, "good", 1L, "textbook", 1L, "written", 1L, "by", 1L, "him", 1L),
                Map.of("great class", 1L, "good textbook", 1L, "textbook written", 1L, "written by", 1L, "by him", 1L),
                Map.of("good textbook written", 1L, "textbook written by", 1L, "written by him", 1L),
                Map.of("good textbook written by", 1L, "textbook written by him", 1L),
                Map.of("good textbook written by him", 1L)
        );

        NGrams ng = new NGrams(new String[]{text1, text2});
        assertEquals(expectedNGrams, ng.getAllNGrams());
    }

}
