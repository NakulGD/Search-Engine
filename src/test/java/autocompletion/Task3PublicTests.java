package autocompletion;

import cpen221.mp1.autocompletion.AutoCompletor;
import cpen221.mp1.cities.DataAnalyzer;
import cpen221.mp1.searchterm.SearchTerm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class Task3PublicTests {

    private static String citiesData = "data/cities.txt";
    private static DataAnalyzer cityAnalyzer;
    private static AutoCompletor ac;

    private static String letterData = "data/letters.txt";
    private static DataAnalyzer letterAnalyzer;
    private static AutoCompletor ac2;

    @BeforeAll
    public static void setupTests() {
        cityAnalyzer = new DataAnalyzer(citiesData);
        ac = new AutoCompletor(cityAnalyzer.getSearchTerms());
        letterAnalyzer = new DataAnalyzer(letterData);
        ac2 = new AutoCompletor(letterAnalyzer.getSearchTerms());
    }

    @Test
    public void test_letterUnOrderedWeight(){
        SearchTerm[] st = ac2.topKMatches("M", 2);

        SearchTerm mum = new SearchTerm("Mumbai, India", 12691836);
        SearchTerm mex = new SearchTerm("Mexico City, Distrito Federal, Mexico", 12294193);

        SearchTerm[] expectedST = new SearchTerm[] {mum, mex};

        Assertions.assertArrayEquals(expectedST, st);
    }

    @Test
    public void test_letterSameWeight(){
        SearchTerm[] st = ac2.topKMatches("M", 4);

        SearchTerm mum = new SearchTerm("Mumbai, India", 12691836);
        SearchTerm mex = new SearchTerm("Mexico City, Distrito Federal, Mexico", 12294193);
        SearchTerm mano = new SearchTerm("Manola, Philippines", 10444527);
        SearchTerm mani = new SearchTerm("Manila, Philippines", 10444527);

        SearchTerm[] expectedST = new SearchTerm[] {mum, mex, mano, mani};

        Assertions.assertArrayEquals(expectedST, st);
    }

    @Test
    public void test_Trichi() {
        SearchTerm[] st = ac.allMatches("Trichi");

        SearchTerm Trichiana = new SearchTerm("Trichiana, Italy", 4498);

        SearchTerm[] expectedST = new SearchTerm[] {Trichiana};

        Assertions.assertArrayEquals(expectedST, st);
    }

    @Test
    public void test_Westmor() {
        SearchTerm[] st = ac.allMatches("Westmor");

        SearchTerm Westmor1 = new SearchTerm("Westmorland, California, United States", 2225);
        SearchTerm Westmor2 = new SearchTerm("Westmoreland, Tennessee, United States", 2206);
        SearchTerm Westmor3 = new SearchTerm("Westmoreland, New Hampshire, United States", 1861);
        SearchTerm Westmor4 = new SearchTerm("Westmoreland, Kansas, United States", 778);

        SearchTerm[] expectedST = new SearchTerm[] {Westmor1, Westmor2, Westmor3, Westmor4};

        Assertions.assertArrayEquals(expectedST, st);
    }

    @Test
    public void test_Sunny() {
        SearchTerm[] st = ac.topKMatches("Sunny");

        SearchTerm Sunny1 = new SearchTerm("Sunnyvale, California, United States", 140081);
        SearchTerm Sunny2 = new SearchTerm("Sunny Isles Beach, Florida, United States", 20832);
        SearchTerm Sunny3 = new SearchTerm("Sunnybank, Queensland, Australia", 16108);
        SearchTerm Sunny4 = new SearchTerm("Sunnyside, Washington, United States", 15858);
        SearchTerm Sunny5 = new SearchTerm("Sunnyside, Oregon, United States", 6791);
        SearchTerm Sunny6 = new SearchTerm("Sunnyslope, California, United States", 5153);
        SearchTerm Sunny7 = new SearchTerm("Sunnyvale, Texas, United States", 5130);
        SearchTerm Sunny8 = new SearchTerm("Sunnyside, California, United States", 4235);
        SearchTerm Sunny9 = new SearchTerm("Sunnyslope, Washington, United States", 3252);
        SearchTerm Sunny10 = new SearchTerm("Sunnyside-Tahoe City, California, United States", 1557);
        SearchTerm[] expectedST = new SearchTerm[] { Sunny1, Sunny2, Sunny3, Sunny4, Sunny5, Sunny6, Sunny7, Sunny8, Sunny9, Sunny10 };

        Assertions.assertArrayEquals(expectedST, st);
    }

    @Test
    public void test_Val(){
        int matches = ac.numberOfMatches("Val-");

        Assertions.assertEquals(5, matches);
    }

    @Test
    public void test_San_3() {
        SearchTerm[] st = ac.topKMatches("San", 3);

        SearchTerm santiago = new SearchTerm("Santiago, Chile", 4837295);
        SearchTerm santoDomingo = new SearchTerm("Santo Domingo, Dominican Republic", 2201941);
        SearchTerm sanaa = new SearchTerm("Sanaa, Yemen", 1937451);
        SearchTerm[] expectedST = new SearchTerm[] { santiago, santoDomingo, sanaa };

        Assertions.assertArrayEquals(expectedST, st);
    }

    @Test
    public void testCities2() {
        SearchTerm[] st = ac.topKMatches("Saint Petersburg", 3);

        SearchTerm russia = new SearchTerm("Saint Petersburg, Russia", 4039745);
        SearchTerm usa = new SearchTerm("Saint Petersburg, Florida, United States", 244769);
        SearchTerm[] expectedST = new SearchTerm[] { russia, usa };

        Assertions.assertArrayEquals(expectedST, st);
    }

}
