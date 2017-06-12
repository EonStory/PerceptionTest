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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pitchView = new PitchView(this);
        setContentView(pitchView);
        initiatePitchSequence(500, 900);
    }


    //plays the 2 pitch sounds
    public void initiatePitchSequence(final int firstFrequency, final int secondFrequency) {

        Runnable r = new Runnable() {
            @Override
            public void run() {
                PitchGenerator.playSound(firstFrequency, Settings.millisecondsDisplayed);
            }
        };

        handler.postDelayed(r, Settings.initialDelay);

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                PitchGenerator.playSound(secondFrequency, Settings.millisecondsDisplayed);
            }
        };

        handler.postDelayed(r2, Settings.initialDelay + Settings.millisecondsDisplayed + Settings.gapBetweenStimuli);

        long totalWaitTime = Settings.gapBetweenStimuli + Settings.initialDelay + 2 * Settings.millisecondsDisplayed;

        Runnable q = new Runnable() {
            @Override
            public void run() {
                setContentView(R.layout.question);
                TextView phrase = (TextView)findViewById(R.id.stimuliQuestion);
                phrase.setText("Which stimulus was lower pitched?");
            }
        };

        handler.postDelayed(q, totalWaitTime);
    }

    public void firstButton(View view) {
        if (continueSelected) {
            setContentView(pitchView);
            initiatePitchSequence(500, 900);
        }
        else {
            Intent thingy = new Intent(PitchActivity.this, MainActivity.class);
            startActivity(thingy);
        }
    }

    public void secondButton(View view) {
        if (continueSelected) {
            setContentView(pitchView);
            initiatePitchSequence(500, 900);
        }
        else {
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
