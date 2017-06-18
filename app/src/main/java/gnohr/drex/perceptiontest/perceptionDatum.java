package gnohr.drex.perceptiontest;

import java.sql.Time;

public class PerceptionDatum {
  //records the time at which the answer was submitted (or timeout reached)
  private long timeCompletedAt;
  //Did the user select the correct answer:
  private boolean passed;
  //Stores the values of the 2 stimuli
  private Number value1;
  private Number value2;


  private PerceptionDatum() {
  }

  public PerceptionDatum(long timeCompletedAt, boolean passed, Number value1, Number value2) {
    this.timeCompletedAt = timeCompletedAt;
    this.passed = passed;
    this.value1 = value1;
    this.value2 = value2;
  }

  public boolean isSuccess() {
    return passed;
  }

  public Number getValue1() {
    return value1;
  }

  public Number getValue2() {
    return value2;
  }

  public long getTimeAsLong() {
    return timeCompletedAt;
  }

  //returns how many times bigger the bigger one is than the smaller one
  public double getDelta() {
    double a1 = value1.doubleValue();
    double b1 = value2.doubleValue();

    double min = Math.min(a1, b1);
    double max = Math.max(a1, b1);

    return (max / min);
  }
}
