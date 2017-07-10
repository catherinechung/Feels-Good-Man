package itp341.chung.catherine.feelsgoodman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import itp341.chung.catherine.feelsgoodman.model.Memory;


/**
 * Created by catherinechung on 4/25/17.
 */

public class SmileActivity extends AppCompatActivity {

//    EditText write;
//    ListView listView;
//    ArrayAdapter<String> adapter;
//    private int memoryIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smile);

        Button back, enter;

        // create reference to widgets
        back = (Button) findViewById(R.id.smileBackButton);

        // start smile fragment
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = new SmileFragment();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container_smile, fragment);
        ft.commit();

//        enter = (Button) findViewById(R.id.enterButton);
//        write = (EditText) findViewById(R.id.writeEditText);
//        listView = (ListView) findViewById(R.id.memoryListView);

//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://feelsgoodman-a2896.firebaseio.com/Memories");
//
//
//        // custom adapter and a connected ListView
//        FirebaseListAdapter<String> firebaseListAdapter = new FirebaseListAdapter<String>(
//                this, String.class, android.R.layout.simple_list_item_1, databaseReference
//        ) {
//            @Override
//            protected void populateView(View v, String model, int position) {
//                TextView textView = (TextView) v.findViewById(android.R.id.text1);
//                textView.setText(model);
//            }
//        };
//
//        listView.setAdapter(firebaseListAdapter);


//        // enter on click listener
//        enter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String value = write.getText().toString();
//
//                // create new memory
//                Memory m = new Memory();
//                m.setTimestamp(DateFormat.format("dd-MM-yyyy hh:mm:ss", new java.util.Date()).toString());
//                m.setMemory(value);
//                // save memory to Memory's object list using singleton
//                Singleton.Instance().addMemory(m);
//
//                // write to Firebase
//                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://feelsgoodman-a2896.firebaseio.com/Memories");
//                databaseReference.push().setValue(value);
//                write.setText("");
//
//            }
//        });

        // back on click listener
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                setResult(Activity.RESULT_OK, i);
                finish();
                // return to main activity
            }
        });
    }

}
