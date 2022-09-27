package cpen221.mp1.cities;

import cpen221.mp1.datawrapper.DataWrapper;
import cpen221.mp1.searchterm.SearchTerm;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/*
   This DataAnalyzer for the cities dataset is intended to be
   a simple example of a dataset that can be used with the
   AutoCompletor. The example also illustrates how to process
   the dataset using a DataWrapper, and also how to extract
   the different components of the dataset.
 */

public class DataAnalyzer {

    private static final String citiesData = "data/cities.txt";

    public static void main(String[] args) {
        try {
            DataWrapper dw = new DataWrapper(citiesData);
            List<SearchTerm> stList = new ArrayList<>();
            for (String line = dw.nextLine(); line != null; line = dw.nextLine()) {
                line.trim();
                String[] lineComponents = line.split("\t", 2);
                SearchTerm st = new SearchTerm(lineComponents[1], Integer.parseInt(lineComponents[0].trim()));
                stList.add(st);
            }
        }
        catch (FileNotFoundException fe) {
            System.out.printf("%s: File not found!\n", citiesData);
        }
    }
}
