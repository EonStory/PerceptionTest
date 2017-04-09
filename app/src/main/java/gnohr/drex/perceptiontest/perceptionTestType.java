package gnohr.drex.perceptiontest;

import java.util.ArrayList;

/**
 * Created by Talx on 19/03/2017.
 */

public abstract class perceptionTestType<T extends Number> {

    protected ArrayList<perceptionDatum<Integer>> data;

    public perceptionTestType() {
        data = new ArrayList<perceptionDatum<Integer>>();
    }

    public void addPerceptionDatum(perceptionDatum d) {
        data.add(d);
    }
}
