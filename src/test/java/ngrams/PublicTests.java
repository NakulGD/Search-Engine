package ngrams;
import cpen221.mp1.ngrams.NGrams;
import cpen221.mp1.ratemyprofessor.DataAnalyzer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PublicTests {
    @Test
    public void test_NGrams1() throws Exception {
        String[] test = {"The the big boy."};
        List<Map<String, Long>> expectedListofMaps = new ArrayList<Map<String, Long>>();
        expectedListofMaps.add(new HashMap<String, Long>());
        expectedListofMaps.get(0).put("the", 2L);
        expectedListofMaps.get(0).put("big", 1L);
        expectedListofMaps.get(0).put("boy", 1L);

        expectedListofMaps.add(new HashMap<String, Long>());
        expectedListofMaps.get(1).put("the the", 1L);
        expectedListofMaps.get(1).put("the big", 1L);
        expectedListofMaps.get(1).put("big boy", 1L);

        expectedListofMaps.add(new HashMap<String, Long>());
        expectedListofMaps.get(2).put("the the big", 1L);
        expectedListofMaps.get(2).put("the big boy", 1L);

        expectedListofMaps.add(new HashMap<String, Long>());
        expectedListofMaps.get(3).put("the the big boy", 1L);

        NGrams ngrams1 = new NGrams(test);
        assertEquals(expectedListofMaps, ngrams1.getAllNGrams());
    }

    @Test
    public void test_NGrams2() throws Exception {
        String[] test = {"The big boy and the big girl jumped on foo."};
        List<Map<String, Long>> expectedListofMaps = new ArrayList<Map<String, Long>>();
        expectedListofMaps.add(new HashMap<String, Long>());
        expectedListofMaps.get(0).put("the", 2L);
        expectedListofMaps.get(0).put("big", 2L);
        expectedListofMaps.get(0).put("boy", 1L);
        expectedListofMaps.get(0).put("and", 1L);
        expectedListofMaps.get(0).put("girl", 1L);
        expectedListofMaps.get(0).put("jumped", 1L);
        expectedListofMaps.get(0).put("on", 1L);
        expectedListofMaps.get(0).put("foo", 1L);

        expectedListofMaps.add(new HashMap<String, Long>());
        expectedListofMaps.get(1).put("the big", 2L);
        expectedListofMaps.get(1).put("big boy", 1L);
        expectedListofMaps.get(1).put("boy and", 1L);
        expectedListofMaps.get(1).put("and the", 1L);
        expectedListofMaps.get(1).put("big girl", 1L);
        expectedListofMaps.get(1).put("girl jumped", 1L);
        expectedListofMaps.get(1).put("jumped on", 1L);
        expectedListofMaps.get(1).put("on foo", 1L);

        expectedListofMaps.add(new HashMap<String, Long>());
        expectedListofMaps.get(2).put("the big boy", 1L);
        expectedListofMaps.get(2).put("big boy and", 1L);
        expectedListofMaps.get(2).put("boy and the", 1L);
        expectedListofMaps.get(2).put("and the big", 1L);
        expectedListofMaps.get(2).put("the big girl", 1L);
        expectedListofMaps.get(2).put("big girl jumped", 1L);
        expectedListofMaps.get(2).put("girl jumped on", 1L);
        expectedListofMaps.get(2).put("jumped on foo", 1L);

        expectedListofMaps.add(new HashMap<String, Long>());
        expectedListofMaps.get(3).put("the big boy and", 1L);
        expectedListofMaps.get(3).put("big boy and the", 1L);
        expectedListofMaps.get(3).put("boy and the big", 1L);
        expectedListofMaps.get(3).put("and the big girl", 1L);
        expectedListofMaps.get(3).put("the big girl jumped", 1L);
        expectedListofMaps.get(3).put("big girl jumped on", 1L);
        expectedListofMaps.get(3).put("girl jumped on foo", 1L);

        expectedListofMaps.add(new HashMap<String, Long>());
        expectedListofMaps.get(4).put("the big boy and the", 1L);
        expectedListofMaps.get(4).put("big boy and the big", 1L);
        expectedListofMaps.get(4).put("boy and the big girl", 1L);
        expectedListofMaps.get(4).put("and the big girl jumped", 1L);
        expectedListofMaps.get(4).put("the big girl jumped on", 1L);
        expectedListofMaps.get(4).put("big girl jumped on foo", 1L);

        expectedListofMaps.add(new HashMap<String, Long>());
        expectedListofMaps.get(5).put("the big boy and the big", 1L);
        expectedListofMaps.get(5).put("big boy and the big girl", 1L);
        expectedListofMaps.get(5).put("boy and the big girl jumped", 1L);
        expectedListofMaps.get(5).put("and the big girl jumped on", 1L);
        expectedListofMaps.get(5).put("the big girl jumped on foo", 1L);

        expectedListofMaps.add(new HashMap<String, Long>());
        expectedListofMaps.get(6).put("the big boy and the big girl", 1L);
        expectedListofMaps.get(6).put("big boy and the big girl jumped", 1L);
        expectedListofMaps.get(6).put("boy and the big girl jumped on", 1L);
        expectedListofMaps.get(6).put("and the big girl jumped on foo", 1L);

        expectedListofMaps.add(new HashMap<String, Long>());
        expectedListofMaps.get(7).put("the big boy and the big girl jumped", 1L);
        expectedListofMaps.get(7).put("big boy and the big girl jumped on", 1L);
        expectedListofMaps.get(7).put("boy and the big girl jumped on foo", 1L);

        expectedListofMaps.add(new HashMap<String, Long>());
        expectedListofMaps.get(8).put("the big boy and the big girl jumped on", 1L);
        expectedListofMaps.get(8).put("big boy and the big girl jumped on foo", 1L);

        expectedListofMaps.add(new HashMap<String, Long>());
        expectedListofMaps.get(9).put("the big boy and the big girl jumped on foo", 1L);

        NGrams ngrams1 = new NGrams(test);
        assertEquals(expectedListofMaps, ngrams1.getAllNGrams());
    }

    @Test
    public void test_TotalNGramCount1() throws Exception {
        String[] test = {"The the big boy."};
        NGrams ngrams1 = new NGrams(test);

        assertEquals(9, ngrams1.getTotalNGramCount(4));
    }

    @Test
    public void test_TotalNGramCount2() throws Exception {
        String[] test = {"The the big boy."};
        NGrams ngrams1 = new NGrams(test);

        assertEquals(8, ngrams1.getTotalNGramCount(3));
    }

    @Test
    public void test_TotalNGramCount3() throws Exception {
        String[] test = {"The the big boy."};
        NGrams ngrams1 = new NGrams(test);

        assertEquals(6, ngrams1.getTotalNGramCount(2));
    }

    @Test
    public void test_TotalNGramCount4() throws Exception {
        String[] test = {"The the big boy."};
        NGrams ngrams1 = new NGrams(test);

        assertEquals(3, ngrams1.getTotalNGramCount(1));
    }

    @Test
    public void test_TotalNGramCount5() throws Exception {
        String sentence1 = "Test sentence.";
        String sentence2 = "Also test.";
        String[] test = {sentence1, sentence2};
        NGrams ngrams1 = new NGrams(test);

        List<Map<String, Long>> expectedListofMaps = new ArrayList<Map<String, Long>>();
        expectedListofMaps.add(new HashMap<String, Long>());
        expectedListofMaps.get(0).put("test", 2L);
        expectedListofMaps.get(0).put("sentence", 1L);
        expectedListofMaps.get(0).put("also", 1L);

        expectedListofMaps.add(new HashMap<String, Long>());
        expectedListofMaps.get(1).put("test sentence", 1L);
        expectedListofMaps.get(1).put("also test", 1L);

        assertEquals(expectedListofMaps, ngrams1.getAllNGrams());
    }

    @Test
    public void simpleTestCount() throws Exception {
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

    @Test
    public void testReadFile() throws FileNotFoundException {
        List<String> testList = new ArrayList<String>();
        testList.add("5.0,W,jans is great she is a fantastic teacher and her class was both interesting and lively she does expect you to read a lot out of the book and other sources as well she is a very lively teacher and you can tell she loves the subject she teaches");
        testList.add("1.0,M,this teacher will not inspire you to be a better programmer he takes pride in deducting as many points as possible do you want to get on an assignment because you forgot to comment the number and use correct naming conventions even though your program runs this professor is for you");
        testList.add("2.0,W,i took her for research methods and had to drop her class half way nice lady but veryyyyy tough grader and gives you an f even if you do the work i had fs even though i did my work and gave everything on time usually gives assignments and the final research project the lectures are pretty long overall i do not reccomend");

        DataAnalyzer analyzer = new DataAnalyzer("data/test_data.txt");

        assertEquals(testList, analyzer.stringList);
    }

    @Test
    public void testSearchForNGramInLine1() throws Exception {
        String fileName = "data/test_data.txt";

        DataAnalyzer analyzer = new DataAnalyzer(fileName);

        assertEquals(analyzer.numOccurences(analyzer.stringList.get(0), "jans is great"), 1);
    }

    @Test
    public void testGetGender1() throws Exception {
        String fileName = "data/test_data.txt";

        DataAnalyzer analyzer = new DataAnalyzer(fileName);

        assertEquals(analyzer.getGender(analyzer.stringList.get(0)), "w");
    }

    @Test
    public void testGetScore1() throws Exception {
        String fileName = "data/test_data.txt";

        DataAnalyzer analyzer = new DataAnalyzer(fileName);

        assertEquals(analyzer.getRating(analyzer.stringList.get(0)), 5.0);
    }


//    @Test
//    public void testStringList() throws Exception {
//        String fileName = "data/test_data.txt";
//
//        DataAnalyzer analyzer = new DataAnalyzer(fileName);
//
//        assertTrue(analyzer.containsGram(testLine, "the"));
//    }
}

