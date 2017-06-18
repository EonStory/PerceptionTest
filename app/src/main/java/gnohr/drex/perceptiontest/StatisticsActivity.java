package gnohr.drex.perceptiontest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class StatisticsActivity extends Activity {

  public static final int SIZE_CLICKED = 33;
  public static final int PITCH_CLICKED = 34;
  public static final int COMBINED_CLICKED = 35;


  View curView;

  //this.getWindow().getDecorView().findViewById(android.R.id.content)
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.statistics_select_layout);

    curView = this.getWindow().getDecorView().findViewById(android.R.id.content);
    Log.i("current view is ", curView.toString());
  }

  public void sizeStatsEar(View view) {

    Intent thingy = new Intent(StatisticsActivity.this, StatsInfoActivity.class);
    thingy.putExtra("buttonclicked", SIZE_CLICKED);
    startActivity(thingy);

  }

  public void pitchStatsEar(View view) {
    Intent thingy = new Intent(StatisticsActivity.this, StatsInfoActivity.class);
    thingy.putExtra("buttonclicked", PITCH_CLICKED);
    startActivity(thingy);

  }

  public void combinedStatsEar(View view) {
    Intent thingy = new Intent(StatisticsActivity.this, StatsInfoActivity.class);
    thingy.putExtra("buttonclicked", COMBINED_CLICKED);
    startActivity(thingy);

  }

}
