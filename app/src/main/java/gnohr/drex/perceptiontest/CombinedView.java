package gnohr.drex.perceptiontest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.view.View;

public class CombinedView extends View {
  Context context;
  Handler handler = new Handler();
  int circleRadius = 0;
  Paint paint = new Paint();

  public CombinedView(Context context) {
    super(context);
    this.context = context;
  }

  //this is the sequence of initial blank, then first cirle, then pause, then second circle
  public void initiateStimulieSequence() {
    drawCircleOfRadius(Settings.initialDelay, handler, 500);
    drawCircleOfRadius(Settings.initialDelay + Settings.millisecondsDisplayed, handler, 0);
    drawCircleOfRadius(Settings.initialDelay + Settings.millisecondsDisplayed + Settings.gapBetweenStimuli, handler, 300);

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
