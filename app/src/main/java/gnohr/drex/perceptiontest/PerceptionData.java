package gnohr.drex.perceptiontest;

import java.util.ArrayList;


//this class stores all the data the user has done
public class PerceptionData {

    private static PerceptionData allData = new PerceptionData();

    //size data is a number that represents the size in pixels of the stimulus
    private ArrayList<PerceptionDatum<Integer>> sizeData = new ArrayList<>();

    //pitch data is a double that represents the frequency of the stimulus
    private ArrayList<PerceptionDatum<Double>> pitchData = new ArrayList<>();

    //the combined stimuli uses 2 arrays
    private ArrayList<PerceptionDatum<Double>> combinedSizeData = new ArrayList<>();
    private ArrayList<PerceptionDatum<Double>> combinedPitchData = new ArrayList<>();


    private PerceptionData(){

    }

    public static void addSizeDatum(PerceptionDatum<Integer> sizeDatum) {
        allData.sizeData.add(sizeDatum);
    }

    public static void addPitchDatum(PerceptionDatum<Double> pitchDatum) {
        allData.pitchData.add(pitchDatum);
    }

    public static void addCombinedDatum(PerceptionDatum<Double> combinedSizeDatum, PerceptionDatum<Double> combinedPitchDatum) {
        allData.combinedPitchData.add(combinedPitchDatum);
        allData. combinedSizeData.add(combinedSizeDatum);
    }

    public static void eraseAllData() {
        allData.sizeData.clear();
        allData.pitchData.clear();
        allData.combinedSizeData.clear();
        allData.combinedPitchData.clear();
    }




}
