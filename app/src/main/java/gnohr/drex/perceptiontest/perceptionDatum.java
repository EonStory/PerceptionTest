package gnohr.drex.perceptiontest;

public class PerceptionDatum {
  //records the time at which the answer was submitted (or timeout reached)
  private long timeCompletedAt;
  //Did the user select the correct answer:
  private boolean passed;
  //Stores the values of the 2 stimuli
  private double smallStimulus;
  private double bigStimulus;

  //lower case filename? Hoping for goodness! Part II
  private PerceptionDatum() {
  }

  public PerceptionDatum(long timeCompletedAt, boolean passed, double value1, double value2) {
    this.timeCompletedAt = timeCompletedAt;
    this.passed = passed;
    if (value1 < value2) {
      this.smallStimulus = value1;
      this.bigStimulus = value2;
    }
    else {
      this.smallStimulus = value2;
      this.bigStimulus = value1;
    }

  }

  public boolean isSuccess() {
    return passed;
  }

  public Number getSmallStimulus() {
    return smallStimulus;
  }

  public Number getBigStimulus() {
    return bigStimulus;
  }

  public long getTimeAsLong() {
    return timeCompletedAt;
  }

  //returns how many times bigger the bigger one is than the smaller one
  public double getDelta() {
    return (bigStimulus / smallStimulus) - 1;
  }
}
