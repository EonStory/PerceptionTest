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
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        bv = new SizeView(this);
        giveStimuliThenQuestions(bv);
    }

    public void giveStimuliThenQuestions(View view) {
        setContentView(bv);
        bv.initiateCircleSequence();

        Runnable r = new Runnable() {
            @Override
            public void run() {
                PitchGenerator.playSound(300, Settings.millisecondsDisplayed);
            }
        };

        handler.postDelayed(r, Settings.initialDelay);

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                PitchGenerator.playSound(1200, Settings.millisecondsDisplayed);
            }
        };

        handler.postDelayed(r2, Settings.initialDelay + Settings.millisecondsDisplayed + Settings.gapBetweenStimuli);

        long totalWaitTime = Settings.gapBetweenStimuli + Settings.initialDelay + 2 * Settings.millisecondsDisplayed;

        Runnable r3 = new Runnable() {
            @Override
            public void run() {
                setContentView(R.layout.question);
                TextView phrase = (TextView)findViewById(R.id.stimuliQuestion);
                phrase.setText("Which stimulus was bigger and lower pitched?");
            }
        };

        handler.postDelayed(r3, totalWaitTime);
    }

    public void firstButton(View view) {
        if (continueSelected == true) {
            giveStimuliThenQuestions(bv);
        }
        else {
            Intent thingy = new Intent(CombinedActivity.this, MainActivity.class);
            startActivity(thingy);
        }
    }

    public void secondButton(View view) {
        if (continueSelected == true) {
            giveStimuliThenQuestions(bv);
        }
        else {
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
