package itp341.chung.catherine.feelsgoodman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * Created by catherinechung on 5/7/17.
 */

public class LaughFragment extends Fragment {

    Button again, back;

    public LaughFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_laugh, container, false);

        // Get references to widgets
        again = (Button) v.findViewById(R.id.laugh_button1);
        back = (Button) v.findViewById(R.id.laugh_button2);

        // set again on click listener
        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LaughActivity)getActivity()).getGiphy();
            }
        });

        // set back on click listener
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                getActivity().setResult(Activity.RESULT_OK, i);
                getActivity().finish();
            }
        });

        return v;
    }


}
