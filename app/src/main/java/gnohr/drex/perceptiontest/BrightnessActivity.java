package gnohr.drex.perceptiontest;

import android.app.Activity;
import android.os.Bundle;

public class BrightnessActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(new BrightnessView((this)));
    }
}
