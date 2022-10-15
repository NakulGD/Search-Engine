package ratemyprofessor;
import cpen221.mp1.ratemyprofessor.DataAnalyzer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class Task2PublicTests {

    private static DataAnalyzer da1;
    private static DataAnalyzer da2;
    private static DataAnalyzer da3;

    @BeforeAll
    public static void setUpTests() throws FileNotFoundException {
        da1 = new DataAnalyzer("data/reviews1.txt");
        da2 = new DataAnalyzer("data/reviews2.txt");
        da3 = new DataAnalyzer("data/test_data.txt");
    }

    @Test
    public void testIsAFantasticTeacher() throws Exception {
        String query = "is a fantastic teacher";
        Map<String, Long> expected = Map.of(
                "ML", 0L,
                "WL", 0L,
                "MM", 0L,
                "WM", 0L,
                "MH", 0L,
                "WH", 1L
        );
        assertEquals(expected, da3.getHistogram(query));
    }

    @Test
    public void testNoOccurence() throws Exception {
        String query = "no";
        Map<String, Long> expected = Map.of(
                "ML", 0L,
                "WL", 0L,
                "MM", 0L,
                "WM", 0L,
                "MH", 0L,
                "WH", 0L
        );
        assertEquals(expected, da3.getHistogram(query));
    }

    @Test
    public void testVery() throws Exception {
        String query = "very";
        Map<String, Long> expected = Map.of(
                "ML", 0L,
                "WL", 0L,
                "MM", 0L,
                "WM", 0L,
                "MH", 0L,
                "WH", 1L
        );
        assertEquals(expected, da3.getHistogram(query));
    }

    @Test
    public void testMean() throws Exception {
        String query = "mean";
        Map<String, Long> expected = Map.of(
                "ML", 1L,
                "WL", 0L,
                "MM", 0L,
                "WM", 0L,
                "MH", 0L,
                "WH", 0L
        );
        assertEquals(expected, da2.getHistogram(query));
    }

    @Test
    public void testHeIsAn() throws Exception {
        String query = "he is an";
        Map<String, Long> expected = Map.of(
                "ML", 0L,
                "WL", 0L,
                "MM", 1L,
                "WM", 0L,
                "MH", 0L,
                "WH", 0L
        );
        assertEquals(expected, da2.getHistogram(query));
    }

}
