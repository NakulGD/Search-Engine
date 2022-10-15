package cpen221.mp1.ratemyprofessor; 

import cpen221.mp1.datawrapper.DataWrapper; 
import cpen221.mp1.ngrams.NGrams; 

import java.io.FileNotFoundException; 
import java.util.Map; 
import java.util.*;

/**
 * @author Felix Ma
 *
 * Represents an object containing text from a RateMyProfessor dataset.
 */
public class DataAnalyzer {

    /**
     * DataWrapper object which parses through the given dataset.
     */
    private DataWrapper dw;

    /**
     * Contains each individual line from the dataset.
     */
    public List<String> stringList = new ArrayList<>(); 

    /**
     * Create an object to analyze a RateMyProfessor dataset
     * @param dataSourceFileName the name of the file that contains the data
     * @throws FileNotFoundException if the file does not exist or is not found
     */
    public DataAnalyzer(String dataSourceFileName) throws FileNotFoundException {
        dw = new DataWrapper(dataSourceFileName); 

        String nextLine = dw.nextLine(); 
        nextLine = dw.nextLine(); 

        while (nextLine != null) {
            stringList.add(nextLine); 
            nextLine = dw.nextLine(); 
        }
    }

    /**
     * Obtain a histogram with the number of occurrences of the
     * query term in the RMP comments, categorized as men-low (ML),
     * women-low (WL), men-medium (MM), women-medium (WM),
     * men-high (MH), and women-high (WH).
     * @param query the search term, which contains between one and three words
     * @return the histogram with the number of occurrences of the
     *         query term in the RMP comments, categorized as men-low (ML),
     *         women-low (WL), men-medium (MM), women-medium (WM),
     *         men-high (MH), and women-high (WH).
     */
    public Map<String, Long> getHistogram(String query) throws Exception {

        String gender; 
        double rating; 
        String highMediumLow = ""; 

        Map<String, Long> histogram = new HashMap<>(); 

        histogram.put("ML", 0L); 
        histogram.put("WL", 0L); 
        histogram.put("MM", 0L); 
        histogram.put("WM", 0L); 
        histogram.put("MH", 0L); 
        histogram.put("WH", 0L); 

        for (int i = 0; i < this.stringList.size(); i++) {
            String currentLine = this.stringList.get(i); 

            if (NGrams.numOccurences(currentLine, query) > 0) {

                gender = getGender(currentLine); 
                rating = getRating(currentLine); 

                if (Double.compare(rating, 0.0) >= 0 && Double.compare(rating, 2.0) <= 0) {
                    highMediumLow = "L"; 
                } else if (Double.compare(rating, 2.0) > 0 && Double.compare(rating, 3.5) <= 0) {
                    highMediumLow = "M"; 
                } else if (Double.compare(rating, 3.5) > 0 && Double.compare(rating, 5.0) <= 0) {
                    highMediumLow = "H"; 
                }

                if (histogram.containsKey(gender + highMediumLow)) {
                    long count = histogram.get(gender + highMediumLow); 

                    histogram.put(gender + highMediumLow, count += NGrams.numOccurences(currentLine, query)); 

                }
            }
        }

        return histogram; 
    }

    /**
     * Searches current line for the gender of the professor.
     * @param line, The current line being read.
     * @return The gender of the professor as a capital letter,
     *         M for male and F for female.
     */
    public String getGender(String line) {
        String[] lineArray = NGrams.getWords(line); 
        return lineArray[1].toUpperCase(); 
    }

    /**
     * Searches current line for the rating of the professor.
     * @param line, the current line being read.
     * @return The rating of the professor.
     */
    public static double getRating(String line) {
        String[] lineArray = NGrams.getWords(line);
        double rating = Double.parseDouble(lineArray[0]);
        return rating;
    }

}
