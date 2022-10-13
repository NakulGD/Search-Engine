package sentimentanalysis;
import cpen221.mp1.ratemyprofessor.DataAnalyzer;
import cpen221.mp1.sentimentanalysis.SentimentAnalyzer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PublicTests {

    private static SentimentAnalyzer sa1;

    @BeforeAll
    public static void setUpTests() throws FileNotFoundException {
        sa1 = new SentimentAnalyzer("data/reviews1.txt");
    }

    @Test
    public void testGood() throws Exception {
        String query[] = {"yes", "no", "maybe"};
        Map<String, Long> expected = Map.of(
                "ML", 0L,
                "WL", 1L,
                "MM", 0L,
                "WM", 0L,
                "MH", 1L,
                "WH", 1L
        );
        assertEquals(expected, sa1.get1Grams(query));
    }




}
