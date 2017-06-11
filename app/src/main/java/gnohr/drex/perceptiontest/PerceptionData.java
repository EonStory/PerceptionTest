package gnohr.drex.perceptiontest;

import java.util.ArrayList;


//this class stores all the data the user has done
public class PerceptionData {

    //size data is a number that represents the size in pixels of the stimulus
    private ArrayList<PerceptionDatum<Integer>> sizeData = new ArrayList<>();

    //pitch data is a double that represents the frequency of the stimulus
    private ArrayList<PerceptionDatum<Double>> pitchData = new ArrayList<>();

    public PerceptionData(){

    }

    public void addSizeDatum(PerceptionDatum<Integer> sizeDatum) {
        sizeData.add(sizeDatum);
    }

    public void addPitchDatum(PerceptionDatum<Double> pitchDatum) {
        pitchData.add(pitchDatum);
    }
}
