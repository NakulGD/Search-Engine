package cpen221.mp1.ratemyprofessor;

import cpen221.mp1.datawrapper.DataWrapper;

import java.io.FileNotFoundException;
import java.util.Map;

public class DataAnalyzer {
    /**
     * Create an object to analyze a RateMyProfessor dataset
     * @param dataSourceFileName the name of the file that contains the data
     * @throws FileNotFoundException if the file does not exist or is not found
     */
    public DataAnalyzer(String dataSourceFileName) throws FileNotFoundException {
        DataWrapper dw = new DataWrapper(dataSourceFileName);
        // TODO: Implement the rest of this method as appropriate
    }

    /**
     * Obtain a histogram with the number of occurrences of the
     * query term in the RMP comments, categorized as men-low (ML),
     * women-low (WL), men-medium (MM), women-medium (WM),
     * men-high (MH), and women-high (WH).
     * @param query the search term, which contains between one and three words
     * @return the histogram with the number of occurrences of the
     * query term in the RMP comments, categorized as men-low (ML),
     * women-low (WL), men-medium (MM), women-medium (WM),
     * men-high (MH), and women-high (WH)
     */
    public Map<String, Long> getHistogram(String query) {
        return null; // TODO: Implement this method
    }

    /**
     * Display the histogram data as a chart
     * @param histogram with entries for men-low (ML),
     * women-low (WL), men-medium (MM), women-medium (WM),
     * men-high (MH), and women-high (WH)
     */
    public void showHistogramChart(Map<String, Long> histogram) {
        // TODO: This is an optional component but is
        //  instructive in that graphing may not be that hard!
        //  See the histogram package.
    }

}
