package m2y.centennial.healthowl.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import m2y.centennial.healthowl.R;
import m2y.centennial.healthowl.appointment.AddAppointment;

/**M2Y*/
public class MainAddPatient3 extends AppCompatActivity {

    private Toolbar mToolbar3;
    private Button mToList;
    private Button mToAppointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient_main3);

        //Set up Menu with back button
        mToolbar3 = (Toolbar) findViewById(R.id.toolbar3);

        setSupportActionBar(mToolbar3);
        getSupportActionBar().setTitle("Add New Patient");

        mToList = (Button)findViewById(R.id.btnToPatients);
        mToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toPatientlist();
            }
        });

        mToAppointment = (Button)findViewById(R.id.btnToAppointment);
        mToAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toAppointment();
            }
        });
    }

    private void toPatientlist(){
        Intent intent = new Intent(this, patientList.class);
        startActivity(intent);
    }

    private void toAppointment(){
        Intent intent = new Intent(this, AddAppointment.class);
        startActivity(intent);
    }
}
