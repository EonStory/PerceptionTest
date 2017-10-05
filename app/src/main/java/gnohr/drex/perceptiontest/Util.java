package gnohr.drex.perceptiontest;

/**
 * Contains mostly mathematical calcualtions to help throughout the program
 */

public class Util {

  //static class
  private Util() {

  }

  //input a rectangle, output the maximum size of a square that can be placed TWICE inside it
  public static int maximumSquareSize(int x, int y) {
    int longSide = Math.max(x, y);
    int shortSide = Math.min(x, y);

    if (shortSide <= longSide / 2) {
      return shortSide;
    }

    return longSide / 2;
  }


  public static int[] accumulate(int[] count, boolean ascending) {
    int[] result = new int[count.length];

    if (ascending) {
      result[0] = count[0];
      for (int i = 1; i < result.length; i++) {
        result[i] += result[i - 1] + count[i];
      }
    }
    else {
      result[result.length - 1] = count[result.length - 1];
      for (int i = result.length - 2; i >= 0; i--) {
        result[i] += result[i + 1] + count[i];
      }
    }
    return result;
  }

  public static double[] accumulate(double[] count, boolean ascending) {
    double[] result = new double[count.length];

    if (ascending) {
      result[0] = count[0];
      for (int i = 1; i < result.length; i++) {
        result[i] += result[i - 1] + count[i];
      }
    }
    else {
      result[result.length - 1] = count[result.length - 1];
      for (int i = result.length - 2; i >= 0; i--) {
        result[i] += result[i + 1] + count[i];
      }
    }
    return result;
  }

}