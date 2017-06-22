package gnohr.drex.perceptiontest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/*
TODO solve the differential equation of the quadratic in order to figure out the best
value difficult to aim for so that a big effect from the combined stimuli can be measured
 */
public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_main);

    Button sizeButton = (Button) findViewById(R.id.button4);
    sizeButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent thingy = new Intent(MainActivity.this, SizeActivity.class);
        startActivity(thingy);
      }
    });


    Button pitchButton = (Button) findViewById(R.id.button);
    pitchButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        Intent thingy2 = new Intent(MainActivity.this, PitchActivity.class);
        startActivity(thingy2);

      }
    });

    Button combinedButton = (Button) findViewById(R.id.button5);
    combinedButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        Intent thingy2 = new Intent(MainActivity.this, CombinedActivity.class);
        startActivity(thingy2);

      }
    });

  }

  public void stats(View view) {
    Intent intent = new Intent(MainActivity.this, StatisticsActivity.class);
    startActivity(intent);
  }


}
