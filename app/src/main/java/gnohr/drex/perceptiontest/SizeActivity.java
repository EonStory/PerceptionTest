package gnohr.drex.perceptiontest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class SizeActivity extends Activity {

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

        long totalWaitTime = Settings.gapBetweenStimuli + Settings.initialDelay + 2 * Settings.millisecondsDisplayed;

        Runnable r = new Runnable() {
            @Override
            public void run() {
                setContentView(R.layout.question);
                TextView phrase = (TextView)findViewById(R.id.stimuliQuestion);
                phrase.setText("Which stimulus was bigger?");
            }
        };

        handler.postDelayed(r, totalWaitTime);
    }

    public void firstButton(View view) {
        giveStimuliThenQuestions(bv);
    }

    public void secondButton(View view) {
        giveStimuliThenQuestions(bv);
    }



}
