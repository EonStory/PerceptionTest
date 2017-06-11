package gnohr.drex.perceptiontest;

import android.app.Activity;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

//contains code about generating the tones
public class PitchActivity extends Activity {

    PitchView pitchView;
    Handler handler = new Handler();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pitchView = new PitchView(this);
        setContentView(pitchView);
        initiatePitchSequence(500, 900);
    }

    private void playSound(double frequency, double timePlayed) {

        int duration = (int) (timePlayed * 44.1);

        AudioTrack mAudioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
                AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_16BIT,
                duration, AudioTrack.MODE_STATIC);

        // Sine wave
        double[] mSound = new double[duration];
        short[] mBuffer = new short[duration];
        for (int i = 0; i < mSound.length; i++) {
            mSound[i] = Math.sin((2.0*Math.PI * i/(44100/frequency)));
            mBuffer[i] = (short) (mSound[i]*Short.MAX_VALUE);
        }

        mAudioTrack.write(mBuffer, 0, mBuffer.length);

        mAudioTrack.setStereoVolume(AudioTrack.getMaxVolume() / 4, AudioTrack.getMaxVolume() / 4);
        mAudioTrack.play();
    }


    //plays the 2 pitch sounds
    public void initiatePitchSequence(final int firstFrequency, final int secondFrequency) {

        Runnable r = new Runnable() {
            @Override
            public void run() {
                playSound(firstFrequency, Settings.millisecondsDisplayed);
            }
        };

        handler.postDelayed(r, Settings.initialDelay);

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                playSound(secondFrequency, Settings.millisecondsDisplayed);
            }
        };

        handler.postDelayed(r2, Settings.initialDelay + Settings.millisecondsDisplayed + Settings.gapBetweenStimuli);

        long totalWaitTime = Settings.gapBetweenStimuli + Settings.initialDelay + 2 * Settings.millisecondsDisplayed;

        Runnable q = new Runnable() {
            @Override
            public void run() {
                setContentView(R.layout.question);
                TextView phrase = (TextView)findViewById(R.id.stimuliQuestion);
                phrase.setText("Which stimulus was higher pitched?");
            }
        };

        handler.postDelayed(q, totalWaitTime);
    }

    public void firstButton(View view) {
        setContentView(pitchView);
        initiatePitchSequence(500, 900);
    }

    public void secondButton(View view) {
        setContentView(pitchView);
        initiatePitchSequence(500, 900);
    }
}
