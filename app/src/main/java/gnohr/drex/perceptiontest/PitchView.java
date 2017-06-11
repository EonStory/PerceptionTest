package gnohr.drex.perceptiontest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class PitchView extends View {

    Context context;
    Paint paint = new Paint();

    public PitchView(Context context) {
        super(context);
        this.context = context;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        canvas.drawPaint(paint);

        //This makes it redraw so animation can be used
        //invalidate();
    }
}
