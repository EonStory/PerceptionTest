package gnohr.drex.perceptiontest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class CombinedActivity extends Activity {

  boolean continueSelected = true;
  SizeView bv;
  Handler handler = new Handler();
  int firstSizeStimulus = 500;
  int secondSizeStimulus = 900;
  double firstPitchStimulus = 300;
  double secondPitchStimulus = 200;
  boolean isFirstStimuliLowerPitchedAndBigger = false;

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    bv = new SizeView(this);
    giveStimuliThenQuestions(bv);
  }

  public void giveStimuliThenQuestions(View view) {
    setContentView(bv);
    //TODO: ensure these numbers are not equal to each other
    firstSizeStimulus = (int) (Math.random() * 400 + 50);
    secondSizeStimulus = (int) (Math.random() * 400 + 50);

    int tempPitchStimulus1 = (int) (Math.random() * 600 + 200);
    int tempPitchStimulus2 = (int) (Math.random() * 600 + 200);

    //pairs up the bigger size with the lower pitch.
    if (firstSizeStimulus >= secondSizeStimulus) {
      isFirstStimuliLowerPitchedAndBigger = true;
      if (tempPitchStimulus1 < tempPitchStimulus2) {
        firstPitchStimulus = tempPitchStimulus1;
        secondPitchStimulus = tempPitchStimulus2;
      } else {
        firstPitchStimulus = tempPitchStimulus2;
        secondPitchStimulus = tempPitchStimulus1;
      }
    } else {
      isFirstStimuliLowerPitchedAndBigger = false;

      if (tempPitchStimulus1 < tempPitchStimulus2) {
        firstPitchStimulus = tempPitchStimulus2;
        secondPitchStimulus = tempPitchStimulus1;
      } else {
        firstPitchStimulus = tempPitchStimulus1;
        secondPitchStimulus = tempPitchStimulus2;
      }
    }

    bv.initiateCircleSequence(firstSizeStimulus, secondSizeStimulus);

    Runnable r = new Runnable() {
      @Override
      public void run() {
        PitchGenerator.playSound(firstPitchStimulus, Settings.millisecondsDisplayed);
      }
    };

    handler.postDelayed(r, Settings.initialDelay);

    Runnable r2 = new Runnable() {
      @Override
      public void run() {
        PitchGenerator.playSound(secondPitchStimulus, Settings.millisecondsDisplayed);
      }
    };

    handler.postDelayed(r2, Settings.initialDelay + Settings.millisecondsDisplayed + Settings.gapBetweenStimuli);

    long totalWaitTime = Settings.gapBetweenStimuli + Settings.initialDelay + 2 * Settings.millisecondsDisplayed;

    Runnable r3 = new Runnable() {
      @Override
      public void run() {
        setContentView(R.layout.question);
        TextView phrase = (TextView) findViewById(R.id.stimuliQuestion);
        phrase.setText("Which stimulus was bigger and lower pitched?");
      }
    };

    handler.postDelayed(r3, totalWaitTime);
  }

  public void firstButton(View view) {
    PerceptionDatum pitchDatum = new PerceptionDatum(0, isFirstStimuliLowerPitchedAndBigger, firstPitchStimulus, secondPitchStimulus);
    PerceptionDatum sizeDatum = new PerceptionDatum(0, isFirstStimuliLowerPitchedAndBigger, firstSizeStimulus, secondSizeStimulus);
    PerceptionData.combinedSizeData.addDatum(sizeDatum);
    PerceptionData.combinedPitchData.addDatum(pitchDatum);

    if (continueSelected == true) {
      giveStimuliThenQuestions(bv);
    } else {
      Intent thingy = new Intent(CombinedActivity.this, MainActivity.class);
      startActivity(thingy);
    }
  }

  public void secondButton(View view) {
    PerceptionDatum pitchDatum = new PerceptionDatum(0, isFirstStimuliLowerPitchedAndBigger, firstPitchStimulus, secondPitchStimulus);
    PerceptionDatum sizeDatum = new PerceptionDatum(0, isFirstStimuliLowerPitchedAndBigger, firstSizeStimulus, secondSizeStimulus);
    PerceptionData.combinedSizeData.addDatum(sizeDatum);
    PerceptionData.combinedPitchData.addDatum(pitchDatum);

    if (continueSelected == true) {
      giveStimuliThenQuestions(bv);
    } else {
      Intent thingy = new Intent(CombinedActivity.this, MainActivity.class);
      startActivity(thingy);
    }
  }

  public void continueEar(View view) {
    continueSelected = true;
  }

  public void endEar(View view) {
    continueSelected = false;
  }

}
