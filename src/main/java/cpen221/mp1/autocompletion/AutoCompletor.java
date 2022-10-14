package cpen221.mp1.autocompletion;

import cpen221.mp1.ngrams.NGrams;
import cpen221.mp1.ratemyprofessor.DataAnalyzer;
import cpen221.mp1.searchterm.SearchTerm;

import java.io.FileNotFoundException;
import java.text.BreakIterator;
import java.util.*;

import static cpen221.mp1.searchterm.SearchTerm.byReverseWeightOrder;

public class AutoCompletor {

    private static final int DEFAULT_SEARCH_LIMIT = 10;

    private SearchTerm[] searchText;

    /**
     * Initializes an AutoCompletor with the given search terms.
     * @param searchTerms not null and not empty
     */
    public AutoCompletor(SearchTerm[] searchTerms) {

        this.searchText = searchTerms;
    }

    /**
     * Returns all search terms that match the given prefix.
     * @param prefix
     * @return all search terms that match the given prefix
     */
    public SearchTerm[] allMatches(String prefix) {
        List<SearchTerm> matches = new ArrayList<>();
        for (int i = 0; i < searchText.length; i++) {
            if (searchText[i].getQuery().startsWith(prefix)) {
                matches.add(searchText[i]);
            }
        }

        SearchTerm[] matchesArray = new SearchTerm[matches.size()];
        matchesArray = matches.toArray(matchesArray);
        return matchesArray;
    }

    /**
     * Returns the top k search terms that match the given prefix.
     * @param prefix
     * @param limit
     * @return the top k search terms that match the given prefix
     * k is indicated by the parameter 'limit'.
     */
    public SearchTerm[] topKMatches(String prefix, int limit) {
        SearchTerm[] allMatches = allMatches(prefix);
        if(allMatches.length < limit){
            return allMatches;
        }
        else{
            SearchTerm[] topKMatches = new SearchTerm[limit];
            for(int i = 0; i < limit; i++){
                topKMatches[i] = allMatches[i];
            }
            return topKMatches;
        }
    }

    /**
     * Returns the top k search terms that match the given prefix.
     * @param prefix
     * @return the top k search terms that match the given prefix
     * k is indicated by the constant DEFAULT_SEARCH_LIMIT.
     */
    public SearchTerm[] topKMatches(String prefix) {
        return topKMatches(prefix, DEFAULT_SEARCH_LIMIT);
    }

    /**
     * Returns the number of search terms that match the given prefix.
     * @param prefix
     * @return the number of search terms that match the given prefix
     */
    public int numberOfMatches(String prefix) {
        return allMatches(prefix).length;
    }

}

