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

}
