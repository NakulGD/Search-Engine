package cpen221.mp1.autocompletion; 

import cpen221.mp1.searchterm.SearchTerm; 
import java.util.*; 
import static cpen221.mp1.searchterm.SearchTerm.byPrefixOrder; 
import static cpen221.mp1.searchterm.SearchTerm.byReverseWeightOrder; 

/**
 * @author Anu Ponnusamy
 *
 * Returns search terms that match a given prefix with a search term String array input.
 */
public class AutoCompletor {

    /**
     * Given no search limit for TopKMatches, use DEFAULT_SEARCH_LIMIT constant as limit.
     */
    private static final int DEFAULT_SEARCH_LIMIT = 10;

    /**
     * String array of SearchTerms.
     */
    private SearchTerm[] searchText;

    /**
     * Initializes an AutoCompletor with the given search terms.
     * @param searchTerms not null and not empty.
     */
    public AutoCompletor(SearchTerm[] searchTerms) {

        this.searchText = searchTerms; 
    }

    /**
     * Returns all search terms that match the given prefix.
     * @param prefix String representing search query.
     * @return all search terms that match the given prefix.
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

        for (int j = 0; j < matchesArray.length - 1; j++) {
            int comparisonWeight = byReverseWeightOrder().compare(matchesArray[j], matchesArray[j + 1]); 
            if (comparisonWeight == -1) {
                SearchTerm temp = matchesArray[j]; 
                matchesArray[j] = matchesArray[j + 1]; 
                matchesArray[j + 1] = temp; 
                j = -1; 
            }
        }

        for (int k = 0; k < matchesArray.length - 1; k++) {
            int comparisonLexicographic = byPrefixOrder().compare(matchesArray[k], matchesArray[k + 1]); 
            if (matchesArray[k].getWeight() == matchesArray[k + 1].getWeight()) {
                if (comparisonLexicographic < 0) {
                    SearchTerm temp = matchesArray[k]; 
                    matchesArray[k] = matchesArray[k + 1]; 
                    matchesArray[k + 1] = temp; 
                    k = -1; 
                }
            }
        }
        return matchesArray; 
    }

    /**
     * Returns the top k search terms that match the given prefix.
     * @param prefix String representing search query.
     * @param limit Maximum matches returned.
     * @return the top k search terms that match the given prefix.
     * k is indicated by the parameter 'limit'.
     */
    public SearchTerm[] topKMatches(String prefix, int limit) {
        SearchTerm[] allMatches = allMatches(prefix); 
        if (allMatches.length < limit) {
            return allMatches; 
        } else {
            SearchTerm[] topKMatches = new SearchTerm[limit]; 
            for (int i = 0; i < limit; i++) {
                topKMatches[i] = allMatches[i]; 
            }
            return topKMatches; 
        }
    }

    /**
     * Returns the top k search terms that match the given prefix.
     * @param prefix String representing search query.
     * @return the top k search terms that match the given prefix.
     * k is indicated by the constant DEFAULT_SEARCH_LIMIT.
     */
    public SearchTerm[] topKMatches(String prefix) {
        return topKMatches(prefix, DEFAULT_SEARCH_LIMIT); 
    }

    /**
     * Returns the number of search terms that match the given prefix.
     * @param prefix String representing search query.
     * @return the number of search terms that match the given prefix.
     */
    public int numberOfMatches(String prefix) {
        return allMatches(prefix).length; 
    }
}

