package cpen221.mp1.ratemyprofessor;

import cpen221.mp1.datawrapper.DataWrapper;
import cpen221.mp1.ngrams.NGrams;

import java.io.FileNotFoundException;
import java.text.BreakIterator;
import java.util.Map;
import java.util.*;

public class DataAnalyzer extends NGrams {

    public DataWrapper dw;
    public List<String> stringList = new ArrayList<String>();

    /**
     * Create an object to analyze a RateMyProfessor dataset
     * @param dataSourceFileName the name of the file that contains the data
     * @throws FileNotFoundException if the file does not exist or is not found
     */
    public DataAnalyzer(String dataSourceFileName) throws FileNotFoundException {
        DataWrapper dw = new DataWrapper(dataSourceFileName);

        //Skip the first line of the file
        String nextLine = dw.nextLine();
        nextLine = dw.nextLine();

        //Add each line to the List of Strings

        while(nextLine != null) {
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
     * query term in the RMP comments, categorized as men-low (ML),
     * women-low (WL), men-medium (MM), women-medium (WM),
     * men-high (MH), and women-high (WH)
     */
    public Map<String, Long> getHistogram(String query) throws Exception {

        String gender;
        double rating;
        String highMediumLow = "";

        Map<String, Long> histogram = new HashMap<String, Long>();

        //Initialize the histogram with keys and values
        histogram.put("ML", 0L);
        histogram.put("WL", 0L);
        histogram.put("MM", 0L);
        histogram.put("WM", 0L);
        histogram.put("MH", 0L);
        histogram.put("WH", 0L);

        //Iterate through each line, checking whether it contains the search query
        for(int i = 0; i < this.stringList.size(); i++) {
            String currentLine = this.stringList.get(i);

            if(numOccurences(currentLine, query) > 0) {

                gender = getGender(currentLine);
                rating = getRating(currentLine);

                if(Double.compare(rating, 0.0) >= 0 && Double.compare(rating, 2.0) <= 0) {
                    highMediumLow = "L";
                }
                else if(Double.compare(rating, 2.0) > 0 && Double.compare(rating, 3.5) <= 0) {
                    highMediumLow = "M";
                }
                else if(Double.compare(rating, 3.5) > 0 && Double.compare(rating, 5.0) <= 0) {
                    highMediumLow = "H";
                }

                //If the histogram has a category for the gender and rating, add one to its count
                if(histogram.containsKey(gender + highMediumLow)) {
                    long count = histogram.get(gender + highMediumLow);

                    histogram.put(gender + highMediumLow, count += numOccurences(currentLine, query));

                }
            }
        }

        return histogram;
    }

    /**
     * Display the histogram data as a chart
     * @param histogram with entries for men-low (ML),
     * women-low (WL), men-medium (MM), women-medium (WM),
     * men-high (MH), and women-high (WH)
     */
    public void showHistogramChart(Map<String, Long> histogram) {
        // TODO: This is an optional component but is
        //  instructive in that graphing may not be that hard!
        //  See the histogram package.
    }

    /**
     * Searches current line for occurrence of given NGram
     * @param line, the current line being searched through
     * @param gram, the gram being searched for
     * @return true or false based on whether the gram is in the current line
     */
    public boolean containsGram(String line, String gram) throws Exception {

        String[] lineArray = getWords(line);
        String[] gramArray = getWords(gram);
        int count = 0;

        //Error if gram length is longer than line length
        if(gramArray.length > lineArray.length) {
            throw new Exception("Invalid input");
        }

        //Iterate through line to find matching terms of length equal to gram length
        for(int i = 0; i < lineArray.length - gramArray.length; i++) {
            count = 0;
            for(int j = 0; j < gramArray.length; j++) {
                if(lineArray[i + j].equals(gramArray[j])) {
                    count++;
                }
            }
            if(count == gramArray.length) {
                return true;
            }
        }
        return false;
    }

    /**
     * Searches current line for gender of professor
     * @param line
     * @return the gender of the professor as a String
     */
    public String getGender(String line) {
        String[] lineArray = getWords(line);
        return lineArray[1].toUpperCase();
    }

    /**
     * Searches current line for rating of professor
     * @param line
     * @return the rating of the progessor as an integer
     */
    public double getRating(String line) {
        String[] lineArray = getWords(line);
        double rating = Double.parseDouble(lineArray[0]);
        return rating;
    }

}