package gnohr.drex.perceptiontest;

import java.util.ArrayList;

public class perceptionDatum<T extends Number>{
    //records the time at which the answer was submitted (or timeout reached)
    private final long timeCompletedAt;
    //Did the user select the correct answer:
    private final boolean passed;
    //Stores the values of the 2 stimuli
    private T value1;
    private T value2;

    public perceptionDatum(long timeCompletedAt, boolean passed, T value1, T value2) {
        this.timeCompletedAt = timeCompletedAt;
        this.passed = passed;
        this.value1 = value1;
        this.value2 = value2;
    }


}
