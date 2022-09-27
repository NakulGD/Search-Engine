package cpen221.mp1.autocompletion;

import cpen221.mp1.searchterm.SearchTerm;

import java.util.List;
import java.util.Map;

public class AutoCompletor {

    private static final int DEFAULT_SEARCH_LIMIT = 10;

    // TODO: Write the spec using Javadoc format.
    //  You may assume that searchTerms is not null
    //  and not empty as a precondition.
    public AutoCompletor(SearchTerm[] searchTerms) {
        // TODO: Implement this constructor
    }

    // TODO: Write the spec using Javadoc format.
    //  You may assume that searchTerms is not null
    //  and not empty as a precondition.
    //  On the other hand, searchTerms need contain n-grams
    //  exactly in the format as indicated in Task 1.
    public AutoCompletor(List<Map<String, Long>> searchTerms) {
        // TODO: Implement this constructor
    }

    // TODO: Write the spec using Javadoc format.
    //  This method should return all the SearchTerms
    //  that match the given prefix.
    public SearchTerm[] allMatches(String prefix) {
        return new SearchTerm[0]; // TODO: Implement this method
    }

    // TODO: Write the spec using Javadoc format.
    //  This method should return the top-K SearchTerms
    //  that match the given prefix.
    //  K is indicated by the parameter 'limit'.
    public SearchTerm[] topKMatches(String prefix, int limit) {
        return new SearchTerm[0]; // TODO: Change this
    }

    // TODO: Write the spec using Javadoc format.
    public SearchTerm[] topKMatches(String prefix) {
        return topKMatches(prefix, DEFAULT_SEARCH_LIMIT);
    }

    // TODO: Write the spec using Javadoc format.
    public int numberOfMatches(String prefix) {
        return -1; // TODO: Change this
    }

}
