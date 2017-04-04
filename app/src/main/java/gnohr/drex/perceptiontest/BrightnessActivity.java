package gnohr.drex.perceptiontest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Talx on 04/04/2017.
 */

public class BrightnessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(new BrightnessView((this)));
        //setContentView(R.layout.activity_main);
    }
}
