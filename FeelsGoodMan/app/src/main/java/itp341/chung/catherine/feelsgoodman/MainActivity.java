package itp341.chung.catherine.feelsgoodman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button laughButton;
    private Button relaxButton;
    private Button smileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create references to widgets
        laughButton = (Button) findViewById(R.id.mainButton1);
        relaxButton = (Button) findViewById(R.id.mainButton2);
        smileButton = (Button) findViewById(R.id.mainButton3);

        // laugh button onclick listener
        laughButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create intent for LaughActivity
                Intent laughIntent = new Intent(getApplicationContext(), LaughActivity.class);
                // start LaughActivity
                startActivityForResult(laughIntent, 1);
            }
        });

        // relax button onclick listener
        relaxButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create intent for RelaxActivity
                Intent relaxIntent = new Intent(getApplicationContext(), RelaxActivity.class);
                // start RelaxActivity
                startActivityForResult(relaxIntent, 2);
            }
        });

        // smile button onclick listener
        smileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create intent for SmileActivity
                Intent smileIntent = new Intent(getApplicationContext(), SmileActivity.class);
                // start SmileActivity
                startActivityForResult(smileIntent, 3);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
