package gnohr.drex.perceptiontest;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class SizeGenerator {

    //this may cause problems. TODO: ensure this is the best way to do this
    private static Paint paint = new Paint();

    private SizeGenerator() {
    }

     public static void drawSize(View view, Canvas canvas, int circleRadius) {
        int x = view.getWidth();
        int y = view.getHeight();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        canvas.drawPaint(paint);
        paint.setColor(Color.BLUE);
        canvas.drawCircle(x / 2, y / 2, circleRadius, paint);

        //Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        //int rotation = display.getRotation();

        //This makes it redraw so animation can be used
        view.invalidate();
    }
}
