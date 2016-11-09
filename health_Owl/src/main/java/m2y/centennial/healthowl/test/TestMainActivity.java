package m2y.centennial.healthowl.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import m2y.centennial.healthowl.R;

public class TestMainActivity extends AppCompatActivity {

    ListView listView_Patients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

   //     Toolbar toolbar_Test = (Toolbar) findViewById(R.id.toolbar_Test);
   //     setSupportActionBar(toolbar_Test);
   //     getSupportActionBar().setHomeButtonEnabled(true);
   //     getSupportActionBar().setDisplayHomeAsUpEnabled(true);
   //     toolbar_Test.setTitle("Tests");


        // Get ListView object from xml
        listView_Patients = (ListView) findViewById(R.id.listView_Patients);

        // Defined Array values to show in ListView
        String[] values = new String[]{ "Sara Smith",
                "Michelle Brown",
                "Sam Sad",
                "Anna Smith",
                "Android Example",
                "Micheal Jackson",
                "Dalia Gigliotti[",
                "Andre Aghassi",
                "James Hetfield",
                "Sandra Gigliotti[",
                "Brad Pitt",
                "Angelina Jolie",
                "Leonard Cohen",
                "Dustin Hoffman",
                "Morgan Freeman"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_2, android.R.id.text1, values);

        // Assign adapter to ListView
        listView_Patients.setAdapter(adapter);

        // ListView Item Click Listener
        listView_Patients.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) listView_Patients.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Row :" + itemPosition + "  Patient's name : " + itemValue, Toast.LENGTH_LONG)
                        .show();

                goToDetails(itemValue);

            }

        });
    }


    private void goToDetails(String patientChoice) {
        Intent intent = new Intent(this, TestDetails.class);
        intent.putExtra("patientChoice", patientChoice);
        startActivity(intent);
    }
}