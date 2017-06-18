package gnohr.drex.perceptiontest;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Handler;

public class PitchGenerator {

  private PitchGenerator() {
  }

  public static void playSound(double frequency, double timePlayed) {
    //Multiplied by 2 because sine waves are symmetrical... or something. (sound plays too short otherwise)
    int duration = (int) (timePlayed * 44.1 * 2);

    final AudioTrack mAudioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
            AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_16BIT,
            duration, AudioTrack.MODE_STATIC);

    // Sine wave
    double[] mSound = new double[duration];
    short[] mBuffer = new short[duration];
    for (int i = 0; i < mSound.length; i++) {
      mSound[i] = Math.sin((2.0 * Math.PI * i / (44100 / frequency)));
      mBuffer[i] = (short) (mSound[i] * Short.MAX_VALUE);
    }

    mAudioTrack.write(mBuffer, 0, mBuffer.length);

    mAudioTrack.setStereoVolume(AudioTrack.getMaxVolume() / 4, AudioTrack.getMaxVolume() / 4);
    mAudioTrack.play();

    //after 2x the length of the audio track, release it to avoid crashes
    Handler handler = new Handler();

    Runnable r3 = new Runnable() {
      @Override
      public void run() {
        mAudioTrack.release();
      }
    };
    long killTime = (long) (timePlayed * 2);
    handler.postDelayed(r3, killTime);
  }
}
