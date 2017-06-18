package gnohr.drex.perceptiontest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.view.View;

public class SizeView extends View {

  Context context;
  Handler handler = new Handler();
  int circleRadius = 0;
  Paint paint = new Paint();

  public SizeView(Context context) {
    super(context);
    this.context = context;
  }

  //this is the sequence of initial blank, then first cirle, then pause, then second circle
  public void initiateCircleSequence(int firstSize, int secondSize) {
    drawCircleOfRadius(Settings.initialDelay, handler, firstSize);
    drawCircleOfRadius(Settings.initialDelay + Settings.millisecondsDisplayed, handler, 0);
    drawCircleOfRadius(Settings.initialDelay + Settings.millisecondsDisplayed + Settings.gapBetweenStimuli, handler, secondSize);

    //Get rid of the final circle in preparation for next time
    drawCircleOfRadius(Settings.initialDelay + 2 * Settings.millisecondsDisplayed + Settings.gapBetweenStimuli, handler, 0);
  }

  public void drawCircleOfRadius(long delay, Handler h, final int radius) {
    Runnable r = new Runnable() {
      @Override
      public void run() {
        circleRadius = radius;
      }
    };

    h.postDelayed(r, delay);
  }

  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    SizeGenerator.drawSize(this, canvas, circleRadius);
  }
}
