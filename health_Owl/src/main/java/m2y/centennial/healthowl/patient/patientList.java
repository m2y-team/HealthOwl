package m2y.centennial.healthowl.patient;

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
/*M2Y*/
public class patientList extends AppCompatActivity {


    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);

        //Set up Menu with back button
        Toolbar myDetailsToolbar = (Toolbar)findViewById(R.id.patientToolBar);
        setSupportActionBar(myDetailsToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Patients");


        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);

        // Defined Array values to show in ListView
        String[] values = new String[] {
                "John Dorian",
                "Elliot Reid",
                "Christopher Turk",
                "Perry Cox",
                "Bob Kelso",
                "Todd Quinlan",
                "Ted Buckland",
                "Laverne Roberts",
                "Jordan Sullivan",
                "Carla Espinosa",
                "Kim Briggs"
        };

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data
        // simple_list_item_1 Android built in XML layout document
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_2, android.R.id.text1, values);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) listView.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show();

                goNext(itemValue);

            }

        });


    }

    private void goNext(String patientChoice){
        Intent intent = new Intent(this, patientMain.class);
        intent.putExtra("patientChoice", patientChoice);
        startActivity(intent);
    }







}
