package m2y.centennial.healthowl.appointment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import m2y.centennial.healthowl.R;

import static m2y.centennial.healthowl.R.id.editAreaOfPain;
import static m2y.centennial.healthowl.R.id.view;

public class Add_Appointment_Main2 extends AppCompatActivity {

    public static EditText reasonForVisit, temperature, bloodPressure, heartRate;
    Button updateDetails;
    public static String deptSelected, areaOfPain, emergency_state;
    public static int levelOfPain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__appointment__main2);

        reasonForVisit = (EditText) findViewById(R.id.reasonForVisit);
        reasonForVisit.getText();

        heartRate = (EditText) findViewById(R.id.heartRate);
        heartRate.getText();

        bloodPressure = (EditText) findViewById(R.id.bloodPressure);
        bloodPressure.getText();

        temperature = (EditText) findViewById(R.id.temperature);
        temperature.getText();

        //Get items from previous part of the form
        Intent intent = getIntent();
        emergency_state = intent.getExtras().get("emergency").toString();
        areaOfPain = intent.getExtras().get("areaOfPain").toString();
        levelOfPain= Integer.parseInt(intent.getExtras().get("levelOfPain").toString());
        deptSelected = intent.getExtras().get("deptSelected").toString();


        Toast.makeText(getApplicationContext(), emergency_state+" "+areaOfPain+" "+levelOfPain+" "+deptSelected, Toast.LENGTH_LONG).show();

        updateDetails = (Button) findViewById(R.id.appDetailSave);
        updateDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
