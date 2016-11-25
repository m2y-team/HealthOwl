package m2y.centennial.healthowl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import m2y.centennial.healthowl.appointment.MainAppointments;
import m2y.centennial.healthowl.groups.GroupMainActivity;
import m2y.centennial.healthowl.patient.patientList;

/*M2Y*/
public class menu extends AppCompatActivity {

    private Button mPatient;
    private Button mAppointment;
    private Button mGroup;
    Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Initialize toolbar
        initToolBar();

        mPatient = (Button) findViewById(R.id.patientsButton);
        mPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String testChoice = "test";
                goPatientList(testChoice);

            }
        });

        mAppointment = (Button) findViewById(R.id.appointmentsButton);
        mAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String testChoice = "test";
                goAppointment(testChoice);
            }
        });

        mGroup = (Button) findViewById(R.id.GroupsButton);
        mGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String testChoice = "test";
                goGroup(testChoice);

            }
        });


    }

    private void goPatientList(String testChoice){
        Intent intent = new Intent(this, patientList.class);
        intent.putExtra("testChoice", testChoice);
        startActivity(intent);
    }

    private void goAppointment(String testChoice){
        Intent intent = new Intent(this, MainAppointments.class);
        intent.putExtra("testChoice", testChoice);
        startActivity(intent);
    }

    private void goGroup(String testChoice){
        Intent intent = new Intent(this, GroupMainActivity.class);
        intent.putExtra("testChoice", testChoice);
        startActivity(intent);
    }

    //Setting up toolbar
    public void initToolBar() {
        myToolbar = (Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Health Owl");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
                case R.id.settings:
                Intent myIntent = new Intent(this, LoginActivity.class);
                startActivity(myIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}