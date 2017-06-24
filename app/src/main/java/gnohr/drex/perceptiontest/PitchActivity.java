package gnohr.drex.perceptiontest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

//contains code about generating the tones
public class PitchActivity extends Activity {


  boolean continueSelected = true;
  PitchView pitchView;
  Handler handler = new Handler();

  boolean isFirstStimuliLowerPitched = false;

  double firstStimulus;
  double secondStimulus;

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    pitchView = new PitchView(this);
    setContentView(pitchView);
    initiatePitchSequence();
  }


  //plays the 2 pitch sounds
  public void initiatePitchSequence() {
    //TODO: ensure these numbers are not equal to each other
    double delta = PerceptionData.pitchData.generateDelta(0.66);

    double lowPitchStim = (int) (Math.random() * 100 + 300);
    double highPitchStim = (int) (lowPitchStim * (delta + 1));

    if (Math.random() < 0.5) {
      firstStimulus = lowPitchStim;
      secondStimulus = highPitchStim;
      isFirstStimuliLowerPitched = true;
    }
    else {
      firstStimulus = highPitchStim;
      secondStimulus = lowPitchStim;
      isFirstStimuliLowerPitched = false;
    }

    Runnable r = new Runnable() {
      @Override
      public void run() {
        PitchGenerator.playSound(firstStimulus, Settings.millisecondsDisplayed);
      }
    };

    handler.postDelayed(r, Settings.initialDelay);

    Runnable r2 = new Runnable() {
      @Override
      public void run() {
        PitchGenerator.playSound(secondStimulus, Settings.millisecondsDisplayed);
      }
    };

    handler.postDelayed(r2, Settings.initialDelay + Settings.millisecondsDisplayed + Settings.gapBetweenStimuli);

    long totalWaitTime = Settings.gapBetweenStimuli + Settings.initialDelay + 2 * Settings.millisecondsDisplayed;

    Runnable q = new Runnable() {
      @Override
      public void run() {
        setContentView(R.layout.question);
        TextView phrase = (TextView) findViewById(R.id.stimuliQuestion);
        phrase.setText("Which stimulus was lower pitched?");
      }
    };

    handler.postDelayed(q, totalWaitTime);
  }

  public void firstButton(View view) {
    PerceptionDatum pitchDatum = new PerceptionDatum(0, isFirstStimuliLowerPitched, firstStimulus, secondStimulus);
    PerceptionData.pitchData.addDatum(pitchDatum);
    if (continueSelected) {
      setContentView(pitchView);
      initiatePitchSequence();
    } else {
      Intent thingy = new Intent(PitchActivity.this, MainActivity.class);
      startActivity(thingy);
    }
  }

  public void secondButton(View view) {
    //flip the result of isFirstStimuliLowerPitched to see if its the right answer
    PerceptionDatum pitchDatum = new PerceptionDatum(0, !isFirstStimuliLowerPitched, firstStimulus, secondStimulus);
    PerceptionData.pitchData.addDatum(pitchDatum);
    if (continueSelected) {
      setContentView(pitchView);
      initiatePitchSequence();
    } else {
      Intent thingy = new Intent(PitchActivity.this, MainActivity.class);
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
