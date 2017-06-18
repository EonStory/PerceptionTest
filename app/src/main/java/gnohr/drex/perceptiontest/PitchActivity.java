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

  double firstStimulus = 500;
  double secondStimulus = 900;
  boolean isFirstStimuliLowerPitched = false;

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    pitchView = new PitchView(this);
    setContentView(pitchView);
    initiatePitchSequence();
  }


  //plays the 2 pitch sounds
  public void initiatePitchSequence() {
    //TODO: ensure these numbers are not equal to each other
    firstStimulus = (int) (Math.random() * 600 + 200);
    secondStimulus = (int) (Math.random() * 600 + 200);

    if (firstStimulus <= secondStimulus) {
      isFirstStimuliLowerPitched = true;
    } else {
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
