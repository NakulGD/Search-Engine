package cpen221.mp1.ngrams;

import java.text.BreakIterator;
import java.util.*;
import java.util.List;
import java.util.Map;

public class NGrams {

    String[] line;
    String[] currentLine;

    /**
     * Create an NGrams object
     *
     * @param text all the text to analyze and create n-grams from;
     *             is not null and is not empty.
     */
    public NGrams(String[] text) {

        this.line = text;
        currentLine = null;
    }

    /**
     * Obtain the total number of unique 1-grams,
     * 2-grams, ..., n-grams.
     *
     * Specifically, if there are m_i i-grams,
     * obtain sum_{i=1}^{n} m_i.
     *
     * @return the total number of 1-grams,
     * 2-grams, ..., n-grams
     */
    public long getTotalNGramCount(int n) throws Exception {

        int count = 0;

        // First get list of possible grams
        List<Map<String, Long>> listOfGrams = this.getAllNGrams();

        //Check whether inputted n is valid
        if(n < 1 || n > listOfGrams.size()) {
            throw new Exception("Invalid input");
        }

        //Iterate through each entry in the list up to n
        for(int i = 0; i < n; i++) {
            count += listOfGrams.get(i).size();
        }

        return count;
    }

    /**
     * Get the n-grams, as a List, with the i-th entry being
     * all the (i+1)-grams and their counts.
     * @return a list of n-grams and their associated counts,
     * with the i-th entry being all the (i+1)-grams and their counts
     */
    public List<Map<String, Long>> getAllNGrams() throws Exception {

        //Initialize List of Maps
        List<Map<String, Long>> listOfGrams = new ArrayList<Map<String, Long>>();

        //Iterate through array and turn sentences into a separate string array

        for(int i = 0; i < line.length; i++){
            currentLine = getWords(this.line[i]);

            //Throw exception in the case of empty array
            if(this.currentLine.length == 0) {
            
                throw new Exception("Invalid String");
            }

            //Iterate 1 through to longest possible gram, add map to list

            for(int glength = 1; glength <= this.currentLine.length; glength++) {

                listOfGrams.add(new HashMap<String, Long>());

                //Iterate through line with each word as starting word in gram

                for(int j = 0; j <= this.currentLine.length - glength; j++) {

                    //Using StringBuilder, construct current String of glength words
                    StringBuilder current = new StringBuilder();

                    for (int k = j; k < j + glength; k++) {

                        current.append(this.currentLine[k]);

                        //Insert space unless last word
                        if (k != j + glength - 1) {
                            current.append(" ");
                        }
                    }
                    String currentStr = current.toString();

                    //Check whether the current String is in the current map
                    if (listOfGrams.get(glength - 1).containsKey(currentStr)) {
                        Long count = listOfGrams.get(glength - 1).get(currentStr);
                        listOfGrams.get(glength - 1).put(currentStr, ++count);
                    } else {
                        listOfGrams.get(glength - 1).put(currentStr, 1L);
                    }
                }
            }
        }

        for(int i = 0; i < listOfGrams.size(); i++){
            if(listOfGrams.get(i).size() == 0){
                listOfGrams.remove(i);
                i--;
            }
        }
        return listOfGrams;
    }

    // Add specs for getWords method
    private String[] getWords(String text) {
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
        
        for (int i = 0; i < words.size(); i++) {
            if(words.get(i) == "") {
                words.remove(i);
                i--;
            }
        }

        String[] wordsArray = new String[words.size()];
        words.toArray(wordsArray);
        return wordsArray;
    }
}