package gnohr.drex.perceptiontest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

public class StatsInfoActivity extends Activity {

  TextView minValueText;
  TextView maxValueText;
  TextView minValueText2;
  TextView maxValueText2;

  SeekBar minSeek;
  SeekBar maxSeek;
  SeekBar minSeek2;
  SeekBar maxSeek2;

  PerceptionData relevantData;
  PerceptionData relevantData2;

  TextView correctText;
  TextView incorrectText;
  TextView percentText;

  int typeClicked;

  TextView titleText;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    typeClicked = this.getIntent().getIntExtra("buttonclicked", 666);


    //these fields have the same id in both layouts

    if (typeClicked == StatisticsActivity.SIZE_CLICKED || typeClicked == StatisticsActivity.PITCH_CLICKED) {
      setContentView(R.layout.statistics_info);

      correctText = (TextView) findViewById(R.id.textView18);
      incorrectText = (TextView) findViewById(R.id.textView19);
      percentText = (TextView) findViewById(R.id.textView20);

      minValueText = (TextView) findViewById(R.id.textView11);
      maxValueText = (TextView) findViewById(R.id.textView13);

      minSeek = (SeekBar) findViewById(R.id.seekBar4);
      maxSeek = (SeekBar) findViewById(R.id.seekBar5);

      titleText = (TextView) findViewById(R.id.textView10);
      final PerceptionData relevantData;
      if (typeClicked == StatisticsActivity.SIZE_CLICKED) {
        relevantData = PerceptionData.sizeData;
        titleText.setText("Size Test");
      }
      else {
        relevantData = PerceptionData.pitchData;
        titleText.setText("Pitch Test");
      }

      minSeek.setMax((int) Math.ceil(relevantData.findMaxDelta()));
      maxSeek.setMax((int) Math.ceil(relevantData.findMaxDelta()));
      maxSeek.setProgress((int) Math.ceil(relevantData.findMaxDelta()));


      minSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
          minValueText.setText(String.valueOf(minSeek.getProgress()));

          double minProgress = (double)minSeek.getProgress();
          double maxProgress = (double)maxSeek.getProgress();

          correctText.setText(String.valueOf(relevantData.getCorrectWithinDeltaRange(minProgress, maxProgress)));
          incorrectText.setText(String.valueOf(relevantData.getIncorrectWithinDeltaRange(minProgress, maxProgress)));
          percentText.setText(String.valueOf(relevantData.getCorrectWithinDeltaRange(minProgress, maxProgress) /
                  ((double)( relevantData.getCorrectWithinDeltaRange(minProgress, maxProgress) +
                          relevantData.getIncorrectWithinDeltaRange(minProgress, maxProgress)))));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
      });

      maxSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
          maxValueText.setText(String.valueOf(maxSeek.getProgress()));

          double minProgress = (double)minSeek.getProgress();
          double maxProgress = (double)maxSeek.getProgress();

          correctText.setText(String.valueOf(relevantData.getCorrectWithinDeltaRange(minProgress, maxProgress)));
          incorrectText.setText(String.valueOf(relevantData.getIncorrectWithinDeltaRange(minProgress, maxProgress)));
          percentText.setText(String.valueOf(relevantData.getCorrectWithinDeltaRange(minProgress, maxProgress) /
                  ((double)( relevantData.getCorrectWithinDeltaRange(minProgress, maxProgress) +
                          relevantData.getIncorrectWithinDeltaRange(minProgress, maxProgress)))));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
      });
    }
    else {//must have been combined clicked!
      setContentView(R.layout.combined_info);

      titleText = (TextView) findViewById(R.id.textView10);

      correctText = (TextView) findViewById(R.id.textView18);
      incorrectText = (TextView) findViewById(R.id.textView19);
      percentText = (TextView) findViewById(R.id.textView20);

      minValueText = (TextView) findViewById(R.id.textView11);
      maxValueText = (TextView) findViewById(R.id.textView13);

      minSeek = (SeekBar) findViewById(R.id.seekBar4);
      maxSeek = (SeekBar) findViewById(R.id.seekBar5);


      minValueText2 = (TextView) findViewById(R.id.textView23);
      maxValueText2 = (TextView) findViewById(R.id.textView26);

      minSeek2 = (SeekBar) findViewById(R.id.seekBar7);
      maxSeek2 = (SeekBar) findViewById(R.id.seekBar8);

      relevantData = PerceptionData.combinedSizeData;
      relevantData2 = PerceptionData.combinedPitchData;

      minSeek.setMax((int) relevantData.findMaxDelta());
      maxSeek.setMax((int) relevantData.findMaxDelta());
      minSeek2.setMax((int) relevantData2.findMaxDelta());
      maxSeek2.setMax((int) relevantData2.findMaxDelta());



      maxSeek.setProgress((int) Math.ceil(relevantData.findMaxDelta()));
      maxSeek2.setProgress((int) Math.ceil(relevantData2.findMaxDelta()));

      titleText.setText(String.valueOf("Combined Test"));

      //set initial deltavalues, correct,incorrect, percent texts. further updates are done via listener
      int correctCount = PerceptionData.getCombinedCorrectWithinDeltaRange(relevantData, 0, relevantData.findMaxDelta(), relevantData2, 0, relevantData2.findMaxDelta());
      int incorrectCount = PerceptionData.getCombinedIncorrectWithinDeltaRange(relevantData, 0, relevantData.findMaxDelta(), relevantData2, 0, relevantData2.findMaxDelta());

      correctText.setText(String.valueOf(correctCount));
      incorrectText.setText(String.valueOf(incorrectCount));
      percentText.setText(String.valueOf(correctCount / (double) (correctCount + incorrectCount)));

      minValueText.setText(String.valueOf(minSeek.getProgress()));
      minValueText2.setText(String.valueOf(minSeek2.getProgress()));
      maxValueText.setText(String.valueOf(maxSeek.getProgress()));
      maxValueText2.setText(String.valueOf(maxSeek2.getProgress()));


      minSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
          minValueText.setText(String.valueOf(minSeek.getProgress()));

          double minProgress = (double)minSeek.getProgress();
          double maxProgress = (double)maxSeek.getProgress();
          double minProgress2 = (double)minSeek2.getProgress();
          double maxProgress2 = (double)maxSeek2.getProgress();

          Log.i("minprogres is ", String.valueOf(minProgress));



          int correctCount = PerceptionData.getCombinedCorrectWithinDeltaRange(relevantData, minProgress, maxProgress, relevantData2, minProgress2, maxProgress2);
          int incorrectCount = PerceptionData.getCombinedIncorrectWithinDeltaRange(relevantData, minProgress, maxProgress, relevantData2, minProgress2, maxProgress2);

          correctText.setText(String.valueOf(correctCount));
          incorrectText.setText(String.valueOf(incorrectCount));
          percentText.setText(String.valueOf(correctCount / (double) (correctCount + incorrectCount)));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
      });

      maxSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
          maxValueText.setText(String.valueOf(maxSeek.getProgress()));

          double minProgress = (double)minSeek.getProgress();
          double maxProgress = (double)maxSeek.getProgress();
          double minProgress2 = (double)minSeek2.getProgress();
          double maxProgress2 = (double)maxSeek2.getProgress();

          Log.i("maxprogres is ", String.valueOf(maxProgress));

          int correctCount = PerceptionData.getCombinedCorrectWithinDeltaRange(relevantData, minProgress, maxProgress, relevantData2, minProgress2, maxProgress2);
          int incorrectCount = PerceptionData.getCombinedIncorrectWithinDeltaRange(relevantData, minProgress, maxProgress, relevantData2, minProgress2, maxProgress2);

          correctText.setText(String.valueOf(correctCount));
          incorrectText.setText(String.valueOf(incorrectCount));
          percentText.setText(String.valueOf(correctCount / (double) (correctCount + incorrectCount)));


        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
      });

      minSeek2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
          minValueText2.setText(String.valueOf(minSeek2.getProgress()));

          double minProgress = (double)minSeek.getProgress();
          double maxProgress = (double)maxSeek.getProgress();
          double minProgress2 = (double)minSeek2.getProgress();
          double maxProgress2 = (double)maxSeek2.getProgress();

          int correctCount = PerceptionData.getCombinedCorrectWithinDeltaRange(relevantData, minProgress, maxProgress, relevantData2, minProgress2, maxProgress2);
          int incorrectCount = PerceptionData.getCombinedIncorrectWithinDeltaRange(relevantData, minProgress, maxProgress, relevantData2, minProgress2, maxProgress2);

          correctText.setText(String.valueOf(correctCount));
          incorrectText.setText(String.valueOf(incorrectCount));
          percentText.setText(String.valueOf(correctCount / (double) (correctCount + incorrectCount)));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
      });

      maxSeek2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
          maxValueText2.setText(String.valueOf(maxSeek2.getProgress()));

          double minProgress = (double)minSeek.getProgress();
          double maxProgress = (double)maxSeek.getProgress();
          double minProgress2 = (double)minSeek2.getProgress();
          double maxProgress2 = (double)maxSeek2.getProgress();

          int correctCount = PerceptionData.getCombinedCorrectWithinDeltaRange(relevantData, minProgress, maxProgress, relevantData2, minProgress2, maxProgress2);
          int incorrectCount = PerceptionData.getCombinedIncorrectWithinDeltaRange(relevantData, minProgress, maxProgress, relevantData2, minProgress2, maxProgress2);

          correctText.setText(String.valueOf(correctCount));
          incorrectText.setText(String.valueOf(incorrectCount));
          percentText.setText(String.valueOf(correctCount / (double) (correctCount + incorrectCount)));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
      });

    }

  }

}
