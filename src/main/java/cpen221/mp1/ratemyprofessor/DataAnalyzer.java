package cpen221.mp1.ratemyprofessor;

import cpen221.mp1.datawrapper.DataWrapper;
import cpen221.mp1.ngrams.NGrams;

import java.io.FileNotFoundException;
import java.text.BreakIterator;
import java.util.Map;
import java.util.*;

public class DataAnalyzer {

    DataWrapper dw;

    /**
     * Create an object to analyze a RateMyProfessor dataset
     * @param dataSourceFileName the name of the file that contains the data
     * @throws FileNotFoundException if the file does not exist or is not found
     */
    public DataAnalyzer(String dataSourceFileName) throws FileNotFoundException {
        DataWrapper dw = new DataWrapper(dataSourceFileName);

        //Initialize String List, each line is its own entry
        List<String> stringList = new ArrayList<String>();
        String nextLine = dw.nextLine();

        while(nextLine != null) {
            stringList.add(nextLine);
        }

        //Copy List to an array
        String[] stringArr = new String[stringList.size()];

        for(int i = 0; i < stringList.size(); i++) {
            stringArr[i] = stringList.get(i);
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
    public Map<String, Long> getHistogram(String query) {

        Map<String, Long> histogram = new HashMap<String, Long>();
        String nextLine = dw.nextLine();

        //Iterate through each line of the file
//        while(nextLine != null) {
//            if(nextLine.)
//        }
//
        return null;
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
    public boolean containsGram(String[] line, String gram) throws Exception {
        NGrams currentLine = new NGrams(line);
        String[] gramWords = getWords(gram);

        //Save all possible NGrams for the line
        List<Map<String, Long>> possibleGrams = currentLine.getAllNGrams();

        if(possibleGrams.get(gramWords.length - 1).containsKey(gram)) {
            return true;
        }
        return false;
    }

    // Add specs for getWords method
    public String[] getWords(String text) {
        ArrayList<String> words = new ArrayList<>();
        BreakIterator wb = BreakIterator.getWordInstance();
        wb.setText(text);
        int start = wb.first();
        for (int end = wb.next();
             end != BreakIterator.DONE;
             start = end, end = wb.next()) {
            String word = text.substring(start, end).toLowerCase();
            word = word.replaceAll("^\\s*\\p{Punct}+\\s*", "").replaceAll("\\s*\\p{Punct}+\\s*$", "");
            if (!word.equals(" ")) {
                words.add(word);
            }
        }
        String[] wordsArray = new String[words.size()];
        words.toArray(wordsArray);
        return wordsArray;
    }
}
