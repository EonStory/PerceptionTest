package gnohr.drex.perceptiontest;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

public class PitchGenerator {

    private PitchGenerator(){}

    public static void playSound(double frequency, double timePlayed) {
        //Multiplied by 2 because sine waves are symmetrical... or something. (sound plays too short otherwise)
        int duration = (int) (timePlayed * 44.1 * 2);

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

}
