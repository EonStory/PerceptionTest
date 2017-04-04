package gnohr.drex.perceptiontest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Talx on 04/04/2017.
 */

public class BrightnessView extends View {

    Context context;


    public BrightnessView(Context context) {
        super(context);
        this.context = context;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int x = getWidth();
        int y = getHeight();
        int radius = Math.min(x / 2, y / 2) / 2;
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        canvas.drawPaint(paint);
        paint.setColor(Color.BLUE);
        canvas.drawCircle(x / 2, y / 2, radius, paint);

        int maxSize = Util.maximumSquareSize(x, y);

        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int rotation = display.getRotation();

        int circle1radius = 55;
        int circle2radius = 77;


        if (rotation == Surface.ROTATION_0) {
            paint.setColor(Color.GREEN);
            canvas.drawCircle(x / 2, y / 4, circle1radius, paint);

            canvas.drawCircle(x / 2, y / 4 * 3, circle2radius, paint);
        }
        if (rotation == Surface.ROTATION_90) {
            paint.setColor(Color.CYAN);
            canvas.drawCircle(x / 2, y / 4, circle1radius, paint);

            canvas.drawCircle(x / 2, y / 4 * 3, circle2radius, paint);
        }
        if (rotation == Surface.ROTATION_180) {
            paint.setColor(Color.LTGRAY);
            canvas.drawCircle(x / 2, y / 4, circle1radius, paint);

            canvas.drawCircle(x / 2, y / 4 * 3, circle2radius, paint);
        }
        if (rotation == Surface.ROTATION_270) {
            paint.setColor(Color.YELLOW);
            canvas.drawCircle(x / 2, y / 4, circle1radius, paint);

            canvas.drawCircle(x / 2, y / 4 * 3, circle2radius, paint);
        }
    }





}
