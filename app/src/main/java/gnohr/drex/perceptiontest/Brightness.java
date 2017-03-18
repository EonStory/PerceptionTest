package gnohr.drex.perceptiontest;

import java.util.ArrayList;

public class Brightness {

    private ArrayList<perceptionDatum<Integer>> data;

    public Brightness() {

    }

    public void addDatum(perceptionDatum<Integer> d) {
        data.add(d);
    }

}
