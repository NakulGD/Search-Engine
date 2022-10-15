package cpen221.mp1.sentimentanalysis;

import cpen221.mp1.datawrapper.DataWrapper;
import java.io.FileNotFoundException;
import java.util.*;
import cpen221.mp1.ngrams.NGrams;

public class SentimentAnalyzer {

    List<Map<String, Float>> bagOfWords = new ArrayList<>();
    public DataWrapper dw;
    public List<String> stringList = new ArrayList<String>();
    public List<Float> ratingList = new ArrayList<>();
    int totalWords;
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
         *
         * @param filename
         */
        //THIS IS THE TRAINING DATA
        // TODO: Implement this constructor and write the spec
        // You may assume that the file is in the format
        // of the RateMyProfessor data with each line containing
        // a rating, a second column (gender or similar) that you
        // can ignore for this task, and the text of the review.
        // The file whose name is provided here is expected to
        // contain the **training data** with which you build
        // your prediction model.



        //Constructor creates a list of hashmaps that contain the 1-grams and their respective counts
        //the index i of List contains the map corresponding to (i/2) + 1 rating
        //for example the index i = 3 in the List would correspond to a (3/2) + 1 = 2.5 rating.
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

                while(nextLine != null) {
                        stringList.add(nextLine);
                        String[] review = getReview(nextLine);
                        totalWords += review.length;
                        float rating = getRating(nextLine);
                        ratingList.add(rating);
                        if (rating == 1F) {
                                addToHashMap(review, rating1);

                        } else if (rating == 1.5F) {
                                addToHashMap(review, rating1_5);

                        } else if(rating == 2.0F) {
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

                System.out.println("sadasd");
        }

        /**
         *
         * @param reviewText
         * @return
         */
        public float getPredictedRating(String reviewText) {
                String[] reviewTextArray = NGrams.getWords(reviewText);
                float wordP;
                float ratingP;
                float wordR;
                float maxVal = -1;
                float predictedRating = -1;

                for(float i = 1; i < 5.5; i += 0.5) {
                        wordR = PWordRating(reviewTextArray, i);
                        wordP = PBagOfWords(reviewTextArray);
                        ratingP = PRating(i);

                        float totalVal = (wordR * ratingP)/wordP;
                        if (i == 1 || totalVal > maxVal) {
                                maxVal = totalVal;
                                predictedRating = i;
                        }
                }

                return predictedRating;
        }


        //returns the total number of occurrences of given rating relative to the total number of reviews (prior probability of said rating)
        public float PRating (float inputRating) {
                float totalReviews = ratingList.size();
                float count = Collections.frequency(ratingList, inputRating);
                return count/totalReviews;
        }

        //returns the product of all 1-gram words possibilities
        public float PBagOfWords (String[] inputTextArray) {
                float totalVal = 1;
                for (int i = 0; i < inputTextArray.length; i++) {
                        totalVal *= wordProb(inputTextArray[i]);
                }
                return totalVal;
        }

        //returns the product of all word|rating
        public float PWordRating (String[] inputTextArray, float rating) {
                float totalVal = 1;
                for(int i = 0; i < inputTextArray.length; i++) {
                        totalVal *= wordRating(rating, inputTextArray[i]);
                }
                return totalVal;
        }

        //returns (# of given word in file)/(# of total words in file)
        public float wordProb (String word) {
                float count = 0;

                for (int i = 0; i < bagOfWords.size(); i++) {
                        Map<String, Float> currentMap = bagOfWords.get(i);
                        for (Map.Entry<String, Float> entry : currentMap.entrySet()) {
                                if (currentMap.containsKey(word)) {
                                        count = entry.getValue();
                                }
                        }
                }

                if (count == 0.0) {
                        count = 1;
                }

                return count/totalWords;
        }

        //Returns (# of occurrences of word in a given rating)/(total # of words in the reviews with given rating)
        public float wordRating (float rating, String word) {
                int index = (int) (2*(rating - 1));
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

                return count/totalCount;
        }

        private void addToHashMap(String[] inputArray, Map<String, Float> inputMap) {
                for(int i = 0; i < inputArray.length; i++) {
                        String currentWord = inputArray[i];
                        if(inputMap.containsKey(currentWord)) {
                                inputMap.put(currentWord, inputMap.get(currentWord) + 1);
                        }
                        else {
                                inputMap.put(currentWord, 1F);
                        }
                }

        }

        private float getRating(String line) {
                String[] lineArray = NGrams.getWords(line);
                float rating = Float.parseFloat(lineArray[0]);
                return rating;
        }

        private String[] getReview(String line) {
                String[] reviewArray = NGrams.getWords(line.substring(6));
                return reviewArray;
        }
}






