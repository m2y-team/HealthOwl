package m2y.centennial.healthowl.groups;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
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
public class GroupMainActivity extends AppCompatActivity {

    FloatingActionButton addGroup;
    ListView listView_Groups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        //Set up Menu with back button
        Toolbar myDetailsToolbar = (Toolbar)findViewById(R.id.toolbarGroup1);
        setSupportActionBar(myDetailsToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Groups");


        addGroup = (FloatingActionButton) findViewById(R.id.add_group_button);
        addGroup.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
            goToAddGroup("group");
            }
        });

        listView_Groups = (ListView) findViewById(R.id.list);

        // Defined Array values to show in ListView
        String[] values = new String[]{
                "Cardiology",
                "ENT",
                "Neurology",
                "Oncology",
                "Nutrition and dietetics",
                "Hepatitis",
                "Cancer"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_2, android.R.id.text1, values);

        // Assign adapter to ListView
        listView_Groups.setAdapter(adapter);

        // ListView Item Click Listener
        listView_Groups.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) listView_Groups.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Row :" + itemPosition + "  Group's name : " + itemValue, Toast.LENGTH_LONG)
                        .show();

                goToDetails(itemValue);

            }

        });
    }

    private void goToAddGroup(String group){
        Intent intent = new Intent(this, GroupAdd.class);
        intent.putExtra("groupChoice", group);
        startActivity(intent);
    }

    private void goToDetails(String group){
        Intent intent = new Intent (this, GroupDetails.class);
        intent.putExtra("groupChoice", group);
        startActivity(intent);
    }
}
