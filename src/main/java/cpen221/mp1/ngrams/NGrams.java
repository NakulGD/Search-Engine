package cpen221.mp1.ngrams;

import java.util.List;
import java.util.Map;

public class NGrams {

    /**
     * Create an NGrams object
     *
     * @param text all the text to analyze and create n-grams from;
     *             is not null and is not empty.
     */
    public NGrams(String[] text) {

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
        return null; // TODO: Implement this method
    }
}
