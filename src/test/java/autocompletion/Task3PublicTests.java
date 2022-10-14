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

    @BeforeAll
    public static void setupTests() {
        cityAnalyzer = new DataAnalyzer(citiesData);
        SearchTerm[] test = cityAnalyzer.getSearchTerms();
        ac = new AutoCompletor(cityAnalyzer.getSearchTerms());
    }

    @Test
    public void test_Sanxx() {
        SearchTerm[] st = ac.topKMatches("San");
        SearchTerm[] test1 = ac.allMatches("San");

        SearchTerm santiago = new SearchTerm("Santiago, Chile", 4837295);
        SearchTerm santoDomingo = new SearchTerm("Santo Domingo, Dominican Republic", 2201941);
        SearchTerm sanaa = new SearchTerm("Sanaa, Yemen", 1937451);
        SearchTerm santaCruz = new SearchTerm("Santa Cruz de la Sierra, Bolivia", 1364389);
        SearchTerm sanAntonio = new SearchTerm("San Antonio, Texas, United States", 1327407);
        SearchTerm sanDiego = new SearchTerm("San Diego, California, United States", 1307402);
        SearchTerm santiagoDe = new SearchTerm("Santiago de los Caballeros, Dominican Republic", 1200000);
        SearchTerm sanJose = new SearchTerm("San Jose, California, United States", 945942);
        SearchTerm sanFran = new SearchTerm("San Francisco, California, United States", 805235);
        SearchTerm sanMig = new SearchTerm("San Miguel de Tucumán, Argentina", 781023);
        SearchTerm[] expectedST = new SearchTerm[] { santiago, santoDomingo, sanaa, santaCruz, sanAntonio, sanDiego, santiagoDe, sanJose, sanFran, sanMig };

        Assertions.assertArrayEquals(expectedST, st);
    }


}
