package gnohr.drex.perceptiontest;

import java.util.ArrayList;
import java.util.Arrays;

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

  double stepSize = 0.002;
  int stepCount = 40;

  //size data is a number that represents the size in pixels of the stimulus
  private ArrayList<PerceptionDatum> data = new ArrayList<>();

  static {
    new PerceptionData();//wat
  }

  private PerceptionData() {
  }

  public void addDatum(PerceptionDatum sizeDatum) {
    data.add(sizeDatum);
  }


  //input the difficulty between 2 stimuli you would like the user to face
  //(difficulty means probability user answers correctly)
  //and it outputs the estimated delta between the 2 stimuli to achieve this

  //TODO: ensure delta > 0 so stimuli arent identical
  public double generateDelta(double desiredDifficulty) {

    double[] result = new double[stepCount];
    int[] successCount = new int[result.length];
    int[] failCount = new int[result.length];

    //assign a success at the hardest point and a fail at the easiest point
    //to avoid NaN problems (and problems dealing with theory of no information)
    successCount[0] = 1;
    failCount[failCount.length - 1] = 1;


    for (int i = 0; i < data.size(); i++) {

      int indexOfDatum = (int) (data.get(i).getDelta() / stepSize);

      System.out.println("data.get(i).getDelta() are " + data.get(i).getDelta());
      System.out.println("indexOfDatum are " + indexOfDatum);

      if (indexOfDatum >= stepCount) {
        //if you failed above the possible range, assign it to max
        indexOfDatum = stepCount - 1;
      }

      if (data.get(i).isSuccess()) {
        successCount[indexOfDatum]++;
      }
      else {
        failCount[indexOfDatum]++;
      }
    }

    int[] successAccumulated = Util.accumulate(successCount, true);
    int[] failAccumulated = Util.accumulate(failCount, false);

    for (int i = 0; i < result.length; i++) {
      result[i] = successAccumulated[i] / ((double) successAccumulated[i] + failAccumulated[i]);
    }

    System.out.println("successCount are " + Arrays.toString(successCount));
    System.out.println("failCount are " + Arrays.toString(failCount));
    System.out.println("successAccumulated are " + Arrays.toString(successAccumulated));
    System.out.println("failAccumulated are " + Arrays.toString(failAccumulated));
    System.out.println("difficulties are " + Arrays.toString(result));

    double[] distanceFromDesired = new double[result.length];
    for (int i = 0; i < distanceFromDesired.length; i++) {
      distanceFromDesired[i] = Math.abs(desiredDifficulty - result[i]);
      //if the distance is 0, IMMEDAITELY pick this one!
      if (distanceFromDesired[i] == 0) {
        return i * stepSize;
      }
    }
    //arbitrary score function, values higher based on how close it is
    //used to generate probabilities
    double[] score = new double[distanceFromDesired.length];
    for (int i = 0; i < score.length; i++) {
      score[i] = 1.0 / Math.pow(distanceFromDesired[i], 2);
    }
    score = Util.accumulate(score, true);




    double randomSelect = Math.random() * score[score.length - 1];

    System.out.println("distanceFromDesired are " + Arrays.toString(distanceFromDesired));
    System.out.println("score are " + Arrays.toString(score));
    System.out.println("randomSelect are " + String.valueOf(randomSelect));

    //find the index that ratndomselecty corespondsd to
    for (int i = 0; i < score.length; i++) {
      if (randomSelect < score[i]) {
        System.out.println("randomSelect PICKED is " + String.valueOf(i * stepSize));
        return i * stepSize;
      }
    }

    throw  new IllegalStateException("state should never be reached");
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
      return 0;
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


  //these 2 methods could be changed into 1 with .isSuccess() == booleanValue
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
