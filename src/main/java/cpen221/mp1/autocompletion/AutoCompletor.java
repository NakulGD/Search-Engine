package cpen221.mp1.autocompletion;

import cpen221.mp1.ratemyprofessor.DataAnalyzer;
import cpen221.mp1.searchterm.SearchTerm;

import java.io.FileNotFoundException;
import java.text.BreakIterator;
import java.util.*;

public class AutoCompletor {

    private static final int DEFAULT_SEARCH_LIMIT = 10;

    private SearchTerm[] searchText;
    public SearchTerm[] allMatches;
    public List<String> stringListNames = new ArrayList<String>();


    // TODO: Write the spec using Javadoc format.
    //  You may assume that searchTerms is not null
    //  and not empty as a precondition.
    public AutoCompletor(SearchTerm[] searchTerms) {

        this.searchText = searchTerms;

        //creates a list of just the city names
        for (int i = 0; i < searchTerms.length; i++) {
            stringListNames.add(searchText[i].getQuery());
        }

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
        List<SearchTerm> test1 = new ArrayList<>();
        int count = 0;
        int occurances = numberOfMatches(prefix);

        //Iterates through searchText and adds elements that contain prefix into a new ArrayList
        //Terminates if index is reached or # of elements added is equal to # of prefix occurances in file
        for (int i = 0; i < searchText.length && count < occurances; i++) {
            if (searchText[i].getQuery().contains(prefix)) {
                test1.add(searchText[i]);
                count++;
            }
        }

        //Converts ArrayList to SearchTerms[]
        Object[] middle = test1.toArray();
        var matches = new SearchTerm[middle.length];
        for (int i = 0; i < middle.length; i++) {
            matches[i] = (SearchTerm) middle[i];
        }

        return matches;
    }

    // TODO: Write the spec using Javadoc format.
    //  This method should return the top-K SearchTerms
    //  that match the given prefix.
    //  K is indicated by the parameter 'limit'.
    public SearchTerm[] topKMatches(String prefix, int limit) {
        var allOptions = allMatches(prefix);
        var allOptionsList = new ArrayList<SearchTerm>();

        //Creates an ArrayList with entry of allOptions array
        for (int i = 0; i < allOptions.length; i++) {
            allOptionsList.add(allOptions[i]);
        }

        allOptionsList.sort(Comparator.reverseOrder());

        Object[] middle = allOptionsList.toArray();
        var topMatches = new SearchTerm[middle.length];
        for (int i = 0; i < middle.length; i++) {
            topMatches[i] = (SearchTerm) middle[i];
        }

        //Comparator sorts the List based on weight in descending order
        Collections.sort(allOptionsList, SearchTerm.byReverseWeightOrder());


        return topMatches; // TODO: Change this
    }

    // TODO: Write the spec using Javadoc format.
    public SearchTerm[] topKMatches(String prefix) {
        return topKMatches(prefix, DEFAULT_SEARCH_LIMIT);
    }


    // TODO: Write the spec using Javadoc format.
    public int numberOfMatches(String prefix) {
        int count = 0;
        try {
            //for loop iterates through city names list and checks to see how many elements contain the given prefix
            for (int i = 0; i < this.stringListNames.size(); i++) {
                String currentLine = this.stringListNames.get(i);
                if (numOccurences(currentLine, prefix) > 0) {
                    count++;
                }
            }
        } //*********************************
        catch (Exception e) {
            System.out.println("Something went wrong!");
        }
        return count; // TODO: Change this
    }

    public long numOccurences(String line, String gram) throws Exception {

        String[] lineArray = getWords(line);
        String[] gramArray = getWords(gram);
        int count = 0;
        long total = 0;

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
                total++;
            }
        }
        return total;
    }

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

        for (int i = 0; i < words.size(); i++) {
            if(words.get(i).equals("")) {
                words.remove(i);
                i--;
            }
        }

        String[] wordsArray = new String[words.size()];
        words.toArray(wordsArray);
        return wordsArray;
    }
}

