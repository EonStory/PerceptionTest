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
  double firstSizeStimulus = 500;
  double secondSizeStimulus = 900;
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

    double sizeDelta = PerceptionData.combinedSizeData.generateDelta(0.56);
    double smallSizeStim = Math.random() * 100 + 300;
    double bigSizeStim =  smallSizeStim * (sizeDelta + 1);

    double pitchDelta = PerceptionData.combinedPitchData.generateDelta(0.56);
    double lowPitchStim = Math.random() * 100 + 300;
    double highPitchStim =  lowPitchStim * (pitchDelta + 1);

    if (Math.random() < 0.5) {
      firstSizeStimulus = bigSizeStim;
      secondSizeStimulus = smallSizeStim;
      firstPitchStimulus = lowPitchStim;
      secondPitchStimulus = highPitchStim;

      isFirstStimuliLowerPitchedAndBigger = true;
    }
    else {
      firstSizeStimulus = smallSizeStim;
      secondSizeStimulus = bigSizeStim;
      firstPitchStimulus = highPitchStim;
      secondPitchStimulus = lowPitchStim;

      isFirstStimuliLowerPitchedAndBigger = false;
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

    if (continueSelected) {
      giveStimuliThenQuestions(bv);
    } else {
      Intent thingy = new Intent(CombinedActivity.this, MainActivity.class);
      startActivity(thingy);
    }
  }

  public void secondButton(View view) {
    PerceptionDatum pitchDatum = new PerceptionDatum(0, !isFirstStimuliLowerPitchedAndBigger, firstPitchStimulus, secondPitchStimulus);
    PerceptionDatum sizeDatum = new PerceptionDatum(0, !isFirstStimuliLowerPitchedAndBigger, firstSizeStimulus, secondSizeStimulus);
    PerceptionData.combinedSizeData.addDatum(sizeDatum);
    PerceptionData.combinedPitchData.addDatum(pitchDatum);

    if (continueSelected) {
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
