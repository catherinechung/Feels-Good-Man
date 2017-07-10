package itp341.chung.catherine.feelsgoodman;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;


/**
 * Created by catherinechung on 5/7/17.
 */

public class RelaxFragment extends Fragment {

    Chronometer chronometer;
    TextView inhale1;
    TextView inhale2;
    TextView hold1;
    TextView hold2;
    TextView exhale1;
    TextView exhale2;
    TextView repeat;
    int counter;
    Button again;

    public RelaxFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_relax, container, false);

        // get references to widgets
        chronometer = (Chronometer) v.findViewById(R.id.chronometer2);
        inhale1 = (TextView) v.findViewById(R.id.inhaleTextView);
        inhale2 = (TextView) v.findViewById(R.id.inhaleInstructionsTextView);
        hold1 = (TextView) v.findViewById(R.id.holdTextView);
        hold2 = (TextView) v.findViewById(R.id.holdInstructionsTextView);
        exhale1 = (TextView) v.findViewById(R.id.exhaleTextView);
        exhale2 = (TextView) v.findViewById(R.id.exhaleInstructionsTextView);
        repeat =  (TextView) v.findViewById(R.id.repeatTextView);
        again = (Button) v.findViewById(R.id.relaxAgainButton);

        // hide text views
        inhale1.setVisibility(View.VISIBLE);
        inhale2.setVisibility(View.VISIBLE);
        hold1.setVisibility(View.GONE);
        hold2.setVisibility(View.GONE);
        exhale1.setVisibility(View.GONE);
        exhale2.setVisibility(View.GONE);
        repeat.setVisibility(View.GONE);

        startExercise();

        // chronometer on tick listener
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener()
        {
            @Override
            public void onChronometerTick(Chronometer chronometer)
            {
                int time = (int) (SystemClock.elapsedRealtime() - chronometer.getBase())/1000 ;
                if (time == 5) {
                    hold1.setVisibility(View.VISIBLE);
                    hold2.setVisibility(View.VISIBLE);
                } else if (time == 12) {
                    exhale1.setVisibility(View.VISIBLE);
                    exhale2.setVisibility(View.VISIBLE);
                } else if (time == 20) {
                    repeat.setVisibility(View.VISIBLE);
                    hold1.setVisibility(View.GONE);
                    hold2.setVisibility(View.GONE);
                    exhale1.setVisibility(View.GONE);
                    exhale2.setVisibility(View.GONE);
                    counter++;
                    if (counter == 1) {
                        repeat.setText(R.string.repeat2);
                        chronometer.setBase(SystemClock.elapsedRealtime());
                        chronometer.start();
                    } else if (counter == 2) {
                        repeat.setText(R.string.repeat1);
                        chronometer.setBase(SystemClock.elapsedRealtime());
                        chronometer.start();
                    } else if (counter == 3) {
                        chronometer.stop();
                        inhale1.setVisibility(View.GONE);
                        inhale2.setVisibility(View.GONE);
                        repeat.setVisibility(View.GONE);
                    }
                }
            }
        });

        // set again on click listener
        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startExercise();
            }
        });

        return v;
    }

    public void startExercise() {
        // hide text views
        inhale1.setVisibility(View.VISIBLE);
        inhale2.setVisibility(View.VISIBLE);
        hold1.setVisibility(View.GONE);
        hold2.setVisibility(View.GONE);
        exhale1.setVisibility(View.GONE);
        exhale2.setVisibility(View.GONE);
        repeat.setVisibility(View.GONE);

        // start chronometer
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
        counter = 0;
    }

}
