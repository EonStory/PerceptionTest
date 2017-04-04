package gnohr.drex.perceptiontest;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);




        Button brightnessButton = (Button) findViewById(R.id.button4);
        brightnessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("hahaha");
                BrightnessActivity ba = new BrightnessActivity();
                Intent thingy = new Intent(MainActivity.this, BrightnessActivity.class);

                startActivity(thingy);

                //final int result = 1;



            }
        });
    }


}
