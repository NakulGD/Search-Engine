package cpen221.mp1.sentimentanalysis;

import cpen221.mp1.autocompletion.gui.In;
import cpen221.mp1.datawrapper.DataWrapper;

import java.io.FileNotFoundException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SentimentAnalyzer {



        List<Map<String, Integer>> bagOfWords = new ArrayList<>();
        String[] line;
        String[] currentLine;
        public DataWrapper dw;
        public List<String> stringList = new ArrayList<String>();
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

                //initializes an empty ArrayList
                for(int i = 0; i < 9; i++) {
                        bagOfWords.add(null);
                }

                //Skip the first line of the file
                String nextLine = dw.nextLine();
                nextLine = dw.nextLine();

                //Add each line to the List of Strings
                while(nextLine != null) {
                        stringList.add(nextLine);

                        //Obtains review text and rating of each line
                        String[] review = getReview(nextLine);
                        var rating = getRating(nextLine);

                        //index position of HashMap in bagOfWords List
                        var position = (int) (2*(rating - 1));

                        //Creates HashMap of 1-grams in line, adds the 1-grams for each rating to the bagOfWords List
                        var map = get1Grams(review);
                        bagOfWords.set(position,map);
                        nextLine = dw.nextLine();
                }

                List<Float> test = wordProbability("bad");
        }


        //returns the probability of a single word in every rating, where the element at index i corresponds to
        //the (i/2) + 1 rating.
        //applies Laplacian smoothing if word is not in the Map
        public List<Float> wordProbability (String wordToFind){
                List<Float> wordProb = new ArrayList<>();

                for (int i = 0; i < bagOfWords.size(); i++) {
                        Map<String, Integer> mapToCheck = bagOfWords.get(i);

                        //If there's a rating missing, the List will set a value of 0 to avoid adding values to the index corresponding to that rating
                        if (bagOfWords.get(i) == null) {
                                wordProb.add(i, 0F);
                        } else {

                                float count;
                                float sum = 0;

                                for (int j : mapToCheck.values()) {
                                        sum += j;
                                }

                                if (mapToCheck.containsKey(wordToFind)) {
                                        count = mapToCheck.get(wordToFind);
                                } else {

                                        count = 1;
                                        sum += mapToCheck.size();
                                }

                                float likelihood = count / sum;
                                wordProb.add(likelihood);
                        }
                }

                return wordProb;
        }


        //returns the number of occurrences of given rating relative to the total number of reviews
        public float ratingProbability (double inputRating) {
                float totalReviews = stringList.size();
                float count = 0;

                for (int i = 0; i < stringList.size(); i++) {
                        double currentRating = getRating(stringList.get(i));
                        if (currentRating == inputRating) {
                                count++;
                        }
                }

                float ratingProb = count/totalReviews;
                return ratingProb;
        }


        /**
         *
         * @param reviewText
         * @return
         */
        public float getPredictedRating(String reviewText) {
            // TODO: Implement this method for predicting
            // the rating given the text of a review using
            // the simple Bayesian approach outlined in the
            // MP statement. Also write the specification for
            // the method.
            return 0;
        }

        

       public Map<String, Integer> get1Grams (String[] line) {
                Map<String, Integer> oneGrams = new HashMap<>();

                for (int i = 0; i < line.length; i++) {
                        String word = line[i];
                        if(oneGrams.containsKey(word)) {
                                int value = oneGrams.get(word);
                                oneGrams.put(word, value + 1);
                        } else {
                                oneGrams.put(word, 1);
                        }
                }

                return oneGrams;
       }


        public double getRating(String line) {
                String[] lineArray = getWords(line);
                double rating = Double.parseDouble(lineArray[0]);
                return rating;
        }

        public String[] getReview(String line) {
                String[] reviewArray = getWords(line.substring(6));
                return reviewArray;
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






