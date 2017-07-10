package itp341.chung.catherine.feelsgoodman;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import itp341.chung.catherine.feelsgoodman.model.Memory;

/**
 * Created by catherinechung on 5/7/17.
 */

public class SmileFragment extends Fragment {
    EditText write;
    ListView listView;
    ArrayAdapter<String> adapter;
    private int memoryIndex;
    Button enter;

    public SmileFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_smile, container, false);

        // create references to widgets
        write = (EditText) v.findViewById(R.id.writeEditText);
        listView = (ListView) v.findViewById(R.id.memoryListView);
        enter = (Button) v.findViewById(R.id.enterButton);

        // set firebase
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://feelsgoodman-a2896.firebaseio.com/Memories");


        // custom adapter and a connected ListView
        FirebaseListAdapter<String> firebaseListAdapter = new FirebaseListAdapter<String>(
                this.getActivity(), String.class, android.R.layout.simple_list_item_1, databaseReference
        ) {
            @Override
            protected void populateView(View v, String model, int position) {
                TextView textView = (TextView) v.findViewById(android.R.id.text1);
                textView.setText(model);
            }
        };

        listView.setAdapter(firebaseListAdapter);

        // enter on click listener
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value = write.getText().toString();

                // create new memory
                Memory m = new Memory();
                m.setTimestamp(DateFormat.format("dd-MM-yyyy hh:mm:ss", new java.util.Date()).toString());
                m.setMemory(value);
                // save memory to Memory's object list using singleton
                Singleton.Instance().addMemory(m);

                // write to Firebase
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://feelsgoodman-a2896.firebaseio.com/Memories");
                databaseReference.push().setValue(value);
                write.setText("");

            }
        });

        return v;
    }
}
