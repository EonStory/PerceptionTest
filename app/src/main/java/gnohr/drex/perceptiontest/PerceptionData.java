package gnohr.drex.perceptiontest;

import java.util.ArrayList;

/*in this class Delta represents the difference between the 2 stimuli
for example if one stimulus has value 10 and another 15, the delta is calculated as
15/10 = 1.5, with the bigger value always being divided by the smaller value
 */

//this class stores all the data the user has done
public class PerceptionData {

  public static PerceptionData sizeData = new PerceptionData();
  public static PerceptionData pitchData = new PerceptionData();
  public static PerceptionData combinedSizeData = new PerceptionData();
  public static PerceptionData combinedPitchData = new PerceptionData();

  //size data is a number that represents the size in pixels of the stimulus
  private ArrayList<PerceptionDatum> data = new ArrayList<>();

  static {
    new PerceptionData();
  }

  private PerceptionData() {
  }

  public void addDatum(PerceptionDatum sizeDatum) {
    data.add(sizeDatum);
  }

  public double findMaxDelta() {
    //TODO change this
    if (data.size() == 0) {
      return 50;
    }
    double max = 0;

    for (PerceptionDatum p : data) {
      double current = p.getDelta();

      if (current > max) {
        max = current;
      }
    }
    return max;
  }

  public double findMinDelta(ArrayList<PerceptionDatum> data) {
    //TODO: change this
    if (data.size() == 0) {
      return 77;
    }
    //start out with min set as the first in the array
    double min = data.get(0).getDelta();

    for (PerceptionDatum p : data) {
      double current = p.getDelta();

      if (current < min) {
        min = current;
      }
    }
    return min;
  }

  //returns the number of correctly answered tests
  public int getCorrect() {
    int correctAnswers = 0;

    for (PerceptionDatum p : data) {
      if (p.isSuccess()) {
        correctAnswers++;
      }
    }
    return correctAnswers;
  }

  //returns the number of incorrectly answered tests
  public int getIncorrect() {
    return data.size() - getCorrect();
  }

  //returns the correct within a range
  public int getCorrectWithinDeltaRange(double minDelta, double maxDelta) {
    int correctAnswers = 0;

    for (PerceptionDatum p : data) {
      if (p.isSuccess()) {
        if (p.getDelta() >= minDelta && p.getDelta() <= maxDelta) {
          correctAnswers++;
        }
      }
    }
    return correctAnswers;
  }

  //returns the incorrect within a range
  public int getIncorrectWithinDeltaRange(double minDelta, double maxDelta) {
    int incorrectAnswers = 0;

    for (PerceptionDatum p : data) {
      if (!p.isSuccess()) {
        if (p.getDelta() >= minDelta && p.getDelta() <= maxDelta) {
          incorrectAnswers++;
        }
      }
    }
    return incorrectAnswers;
  }


  //used for combined perceptions
  public static int getCombinedCorrectWithinDeltaRange(PerceptionData a, double minDeltaA, double maxDeltaA, PerceptionData b, double minDeltaB, double maxDeltaB) {
    int correctAnswers = 0;
    for (int i = 0; i < a.data.size(); i++) {
      if (a.data.get(i).isSuccess() && b.data.get(i).isSuccess()) {
        if (a.data.get(i).getDelta() >= minDeltaA && a.data.get(i).getDelta() <= maxDeltaA &&
                b.data.get(i).getDelta() >= minDeltaB && b.data.get(i).getDelta() <= maxDeltaB) {
          correctAnswers++;
        }
      }
    }
    return correctAnswers;
  }

  //used for combined perceptions
  public static int getCombinedIncorrectWithinDeltaRange(PerceptionData a, double minDeltaA, double maxDeltaA, PerceptionData b, double minDeltaB, double maxDeltaB) {
    int incorrectAnswers = 0;
    for (int i = 0; i < a.data.size(); i++) {
      if (!a.data.get(i).isSuccess() && !b.data.get(i).isSuccess()) {
        if (a.data.get(i).getDelta() >= minDeltaA && a.data.get(i).getDelta() <= maxDeltaA &&
                b.data.get(i).getDelta() >= minDeltaB && b.data.get(i).getDelta() <= maxDeltaB) {
          incorrectAnswers++;
        }
      }
    }
    return incorrectAnswers;
  }

  public void eraseAllData() {
    data.clear();
  }
}
