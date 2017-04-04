package gnohr.drex.perceptiontest;

//Assumes that a human's difficulty in spotting the difference in 2 stimuli
//is percentage based (ie if the difference between 2 pairs of stimuli is the same
//in terms of percent, they will be picked correctly the same amount too)

//if human succeeds in picking one, it marks a score for all bigger gaps in stimuli
//similarly, if human fails then it assumes you would fail if the difference was smaller

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class DifficultyProducer {

    /*
    This works by assuming that if you passed a test, you would pass all easier tests
    and if you failed a test, you would fail all harder tests
    it sums up all the tests and failures to provide a % chance of failing all previous tests
     */
    public static double[] difficultyCalculator(Number num, double difficulltyMinimum, double difficultyMaximum, ArrayList<perceptionDatum> previousTestResults) {
        //the list must be sorted first because it coutns successes and failures in a cumulative way
        //at each point in the list it records the runnning  number of fails or successes up to that point
        Collections.sort(previousTestResults, new Comparator<perceptionDatum>() {
            public int compare(perceptionDatum pd1, perceptionDatum pd2) {
                if (pd1.getValue1().doubleValue() / pd1.getValue2().doubleValue() == pd2.getValue1().doubleValue() / pd2.getValue2().doubleValue())
                    return 0;
                else if (pd1.getValue1().doubleValue() / pd1.getValue2().doubleValue() > pd2.getValue1().doubleValue() / pd2.getValue2().doubleValue())
                    return 1;
                else
                    return -1;
            }
        });

        perceptionDatum[] stimuli = new perceptionDatum[2];

        int[] successful = new int[previousTestResults.size()];

        int successCount = 0;
        for (int i = 0; i < successful.length; i++) {
            if (previousTestResults.get(i).isSuccess() == true) {
                successCount++;
            }
            successful[i] = successCount;
        }

        int[] failure = new int[previousTestResults.size()];

        //descending now!
        int failureCount = 0;
        for (int i = successful.length; i >= 0; i--) {
            if (previousTestResults.get(i).isSuccess() == false) {
                failureCount++;
            }
            failure[i] = failureCount;
        }

        double[] difficulty = new double[previousTestResults.size()];
        for (int i = 0; i < difficulty.length; i++) {
            difficulty[i] = successful[i] / (failure[i] + successful[i]);
        }

        return difficulty;
    }

}
