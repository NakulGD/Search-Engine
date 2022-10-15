package cpen221.mp1.sentimentanalysis;

import cpen221.mp1.datawrapper.DataWrapper;
import java.io.FileNotFoundException;
import java.util.*;
import cpen221.mp1.ngrams.NGrams;
import cpen221.mp1.ratemyprofessor.DataAnalyzer;


/**
 * @author Nakul Dharan
 *
 * Represents an object containing text from the RateMyProfessor dataset.
 */
public class SentimentAnalyzer {

    /**
     * List with Map elements that contain all 1-gram's for each rating value. Index, i, in List
     * corresponds to the rating = (i/2) + 1.
     */
    List<Map<String, Float>> bagOfWords = new ArrayList<>();

    /**
     * List containing String elements representing lines of data.
     */
    public List<String> stringList = new ArrayList<>();

    /**
     * List containing String elements representing the rating value of each line of data.
     */
    public List<Float> ratingList = new ArrayList<>();

    /**
     * int object representing the total number of words in file.
     */
    int totalWords;

    /**
     * Series of Map initializations that will contain the 1-gram's and associated count for each respective rating value.
     */
    HashMap<String, Float> rating1 = new HashMap<>();
    HashMap<String, Float> rating1_5 = new HashMap<>();
    HashMap<String, Float> rating2 = new HashMap<>();
    HashMap<String, Float> rating2_5 = new HashMap<>();
    HashMap<String, Float> rating3 = new HashMap<>();
    HashMap<String, Float> rating3_5 = new HashMap<>();
    HashMap<String, Float> rating4 = new HashMap<>();
    HashMap<String, Float> rating4_5 = new HashMap<>();
    HashMap<String, Float> rating5 = new HashMap<>();

    /**
    * Creates a List object with Map elements that contain all 1-gram's for each rating value. Index, i, in List
    * corresponds to the rating = (i/2) + 1
    *
    * @param filename File that contains reviews
    * @return a List object with Map elements that contain all 1-gram's
    * for each rating value. Index, i, in List corresponds to the rating = (i/2) + 1
    */
    public SentimentAnalyzer(String filename) throws FileNotFoundException {
        DataWrapper dw = new DataWrapper(filename);
        String nextLine = dw.nextLine();
        nextLine = dw.nextLine();

        bagOfWords.add(rating1);
        bagOfWords.add(rating1_5);
        bagOfWords.add(rating2);
        bagOfWords.add(rating2_5);
        bagOfWords.add(rating3);
        bagOfWords.add(rating3_5);
        bagOfWords.add(rating4);
        bagOfWords.add(rating4_5);
        bagOfWords.add(rating5);

        while (nextLine != null) {
            stringList.add(nextLine);
            String[] review = getReview(nextLine);
            totalWords += review.length;
            float rating = (float) DataAnalyzer.getRating(nextLine);
            ratingList.add(rating);
            if (rating == 1F) {
                addToHashMap(review, rating1);

            } else if (rating == 1.5F) {
                addToHashMap(review, rating1_5);

            } else if (rating == 2.0F) {
                addToHashMap(review, rating2);

            } else if (rating == 2.5F) {
                addToHashMap(review, rating2_5);

            } else if (rating == 3.0F) {
                addToHashMap(review, rating3);

            } else if (rating == 3.5F) {
                addToHashMap(review, rating3_5);

            } else if (rating == 4.0F) {
                addToHashMap(review, rating4);

            } else if (rating == 4.5F) {
                addToHashMap(review, rating4_5);

            } else if (rating == 5.0F) {
                addToHashMap(review, rating5);
            }

            nextLine = dw.nextLine();
        }
    }

    /**
     * Obtain a float value representing the predicted rating
     *
     * @param reviewText, String representing the search query.
     * @return The predicted rating.
     */
    public float getPredictedRating(String reviewText) {
        String[] reviewTextArray = NGrams.getWords(reviewText);
        float wordP;
        float ratingP;
        float wordR;
        float maxVal = -1;
        float predictedRating = -1;

        for (float i = 1; i < 5.5; i += 0.5) {
            wordR = pWordRating(reviewTextArray, i);
            wordP = pBagOfWords(reviewTextArray);
            ratingP = pRating(i);

            float totalVal = (wordR * ratingP) / wordP;
            if (i == 1 || totalVal > maxVal) {
                maxVal = totalVal;
                predictedRating = i;
            }
        }

        return predictedRating;
    }

    /**
     * Obtain float value represent the number of occurrences of a given rating in a file
     * relative to the total number of reviews.
     *
     * @param inputRating which is a value in the set {1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0}.
     * @return The number of occurrences of a given rating in a file.
     * relative to the total number of reviews.
     */
    public float pRating(float inputRating) {
        float totalReviews = ratingList.size();
        float count = Collections.frequency(ratingList, inputRating);
        return count / totalReviews;
    }

    /**
     * Obtain a float value representing P(bag-of-words).
     *
     * @param inputTextArray, a String[] array representing the 1-gram words in the file line.
     * @return P(bag-of-words) = the product of all P(word) = number of occurrences of word relative
     * to the number of words in file.
     */
    public float pBagOfWords(String[] inputTextArray) {
        float totalVal = 1;
        for (int i = 0; i < inputTextArray.length; i++) {
            totalVal *= wordProb(inputTextArray[i]);
        }
        return totalVal;
    }


    /**
     * Obtain a float value representing the P(bag-of-words|rating) = the number of occurrences of a word in rating
     * relative to the total number of words in reviews at this rating.
     *
     * @param inputTextArray, a String[] array representing the 1-gram words in the file line.
     * @param rating, a float value in the set {1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0}.
     * @return P(bag-of-words|rating) = the number of occurrences of a word in rating
     * relative to the total number of words in reviews at this rating.
     */
    public float pWordRating(String[] inputTextArray, float rating) {
        float totalVal = 1;
        for (int i = 0; i < inputTextArray.length; i++) {
            totalVal *= wordRating(rating, inputTextArray[i]);
        }
        return totalVal;
    }

    /**
     * Obtain a float value representing
     * the P(word) = number of occurrences of word in relation to total number of words in file.
     *
     * @param word, a String value representing the word to scan for in file.
     * @return P(word) = number of occurrences of word in relation to total number of words in file.
     */
    public float wordProb(String word) {
        float count = 0;

        for (int i = 0; i < bagOfWords.size(); i++) {
            Map<String, Float> currentMap = bagOfWords.get(i);
            for (Map.Entry<String, Float> entry : currentMap.entrySet()) {
                if (currentMap.containsKey(word)) {
                    count = entry.getValue();
                }
            }
        }
        return count / totalWords;
    }

    /**
     * Obtain a float value representing the number of occurrences of word in a single rating in relation to the total
     * number of words in reviews at this rating.
     *
     * @param word, a String value representing the word to scan for in file.
     * @param rating, a float value in the set {1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0}.
     * @return P(word|rating) = number of occurrences of word in a single rating in relation to the total
     * number of words in reviews at this rating.
     */
    public float wordRating(float rating, String word) {
        int index = (int) (2 * (rating - 1));
        Map<String, Float> currentRatingMap = bagOfWords.get(index);
        float count;
        float totalCount = 0F;
        for (float vals : currentRatingMap.values()) {
            totalCount += vals;
        }

        if (currentRatingMap.containsKey(word)) {
            count = currentRatingMap.get(word) + 1;
        } else {
            count = 1;
            totalCount += 1;
        }
        return count / totalCount;
    }

    /**
     * Adds the next line of 1-gram's to the given Map, if 1-gram key already exists in Map,
     * increments its value by 1.
     *
     * @param inputArray, a String[] array representing the 1-gram words in the file line.
     * @param inputMap, a Map representing the 1-gram's and their counts as key-value pairs.
     */
    private void addToHashMap(String[] inputArray, Map<String, Float> inputMap) {
        for (int i = 0; i < inputArray.length; i++) {
            String currentWord = inputArray[i];
            if (inputMap.containsKey(currentWord)) {
                inputMap.put(currentWord, inputMap.get(currentWord) + 1);
            } else {
                inputMap.put(currentWord, 1F);
            }
        }
    }

    /**
     * Obtain a String[] array with all 1-gram words within line.
     *
     * @param line, String representation of a line in file.
     * @return An array with all 1-gram words within line.
     */
    private String[] getReview(String line) {
        String[] reviewArray = NGrams.getWords(line.substring(6));
        return reviewArray;
    }
}






