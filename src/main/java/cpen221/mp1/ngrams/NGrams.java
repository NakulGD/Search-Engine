package cpen221.mp1.ngrams;

import java.util.*;
import java.util.List;
import java.util.Map;

public class NGrams {

    String[] line;

    /**
     * Create an NGrams object
     *
     * @param text all the text to analyze and create n-grams from;
     *             is not null and is not empty.
     */
    public NGrams(String[] text) {
        this.line = text;
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
    public long getTotalNGramCount(int n) {
        return -1; // TODO: Implement this method
    }

    /**
     * Get the n-grams, as a List, with the i-th entry being
     * all the (i+1)-grams and their counts.
     * @return a list of n-grams and their associated counts,
     * with the i-th entry being all the (i+1)-grams and their counts
     */
    public List<Map<String, Long>> getAllNGrams() {

        //Initialize List of Maps
        List<Map<String, Long>> listOfGrams = new ArrayList<Map<String, Long>>();

        //Iterate 1 through to longest possible gram, add map to list
        for(int glength = 1; glength <= this.line.length; glength++) {

            listOfGrams.add(new HashMap<String, Long>());

            //Iterate through line with each word as starting word in gram
            for(int j = 0; j <= this.line.length - glength; j++) {

                //Using StringBuilder, construct current String of glength words
                StringBuilder current = new StringBuilder();

                for (int k = j; k < j + glength; k++) {
                    current.append(this.line[k]);
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

        return listOfGrams;
    }
}
