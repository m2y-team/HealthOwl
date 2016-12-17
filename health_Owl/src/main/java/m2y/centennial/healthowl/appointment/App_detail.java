package m2y.centennial.healthowl.appointment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import m2y.centennial.healthowl.R;

public class App_detail extends AppCompatActivity {

    String mTitle, areaOfPain,levelOfPain,deptSelected, comments, insurance, reason, heart, blood,temp;

    TextView painLevel, painArea, dept, reasons, temp1, heartRate, bloodP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_detail);

        //Get items from previous part of the form
        Intent intent = getIntent();

        //emergency_state = intent.getExtras().get("emergency").toString();
        areaOfPain = (String) intent.getExtras().get("areaOfpain");
        //levelOfPain= intent.getExtras().get("levelOfPain").toString();
        deptSelected = intent.getExtras().get("dept").toString();

        //comments = intent.getExtras().get("comments").toString();
        insurance = intent.getExtras().get("ohip").toString();
        reason =  intent.getExtras().get("reason").toString();
        heart =  intent.getExtras().get("heart").toString();
        blood = intent.getExtras().get("blood").toString();
        //temp =  intent.getExtras().get("temp").toString();

        //mTitle = intent.getStringExtra("patientChoice");

        //Set up Menu with back button
        Toolbar myPatientToolbar = (Toolbar)findViewById(R.id.toolbar3);
        setSupportActionBar(myPatientToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Details");


        painLevel = (TextView) findViewById(R.id.level);
        painLevel.setText(levelOfPain);
        //Information.this.gender.setText(mGender);

        painArea = (TextView) findViewById(R.id.area);
        painArea.setText(areaOfPain);
        //Information.this.dob.setText(mDOB);

        dept = (TextView) findViewById(R.id.dept);
        dept.setText(deptSelected);
        //Information.this.ohip.setText(mOhip);

        reasons = (TextView) findViewById(R.id.reason);
        reasons.setText(reason);
        //Information.this.phone.setText(mPhone);

        //temp1 = (TextView) findViewById(R.id.temp);
        //temp1.setText(temp);
        //Information.this.address.setText(mAddress);

        heartRate = (TextView) findViewById(R.id.heartRate);
        heartRate.setText(heart);
        //Information.this.address.setText(mAddress);

        bloodP = (TextView) findViewById(R.id.bloodP);
        bloodP.setText(blood);
        //Information.this.address.setText(mAddress);
    }



}

