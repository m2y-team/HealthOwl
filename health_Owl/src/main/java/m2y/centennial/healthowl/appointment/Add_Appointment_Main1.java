package m2y.centennial.healthowl.appointment;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;

import m2y.centennial.healthowl.R;
import m2y.centennial.healthowl.patient.MainAddPatient2;

import static m2y.centennial.healthowl.R.id.view;
import static m2y.centennial.healthowl.R.string.address;
import static m2y.centennial.healthowl.R.string.phone;

public class Add_Appointment_Main1 extends AppCompatActivity {

    public static Switch switchButton;
    public static  Boolean emergency_state;
    public static SeekBar levelOfPain;
    String switchOn = "Switch is ON";
    String switchOff = "Switch is OFF";
    public static RadioGroup radioDeptGroup;
    public static RadioButton radioDeptButton;
    public static String deptSelected, painArea, eme_state;
    public static int painLevel;
    public static EditText areaOfPain;
    Button proceed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__appointment__main1);

        switchButton = (Switch) findViewById(R.id.switchButton1);
        switchButton.setChecked(true);
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (bChecked) {
                    emergency_state = true;
                    eme_state = "yes";
                } else {
                    emergency_state = false;
                    eme_state = "no";
                }
            }
        });

        //areaOfPain.setText("hello");
        areaOfPain = (EditText) findViewById(R.id.editAreaOfPain);


        levelOfPain = (SeekBar) findViewById(R.id.levelOfPain);

        levelOfPain.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Log.e("level of pain","" + levelOfPain.getProgress());
                painLevel = levelOfPain.getProgress();
                Log.e("level of pain","" + painLevel);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        radioDeptGroup = (RadioGroup) findViewById(R.id.radiodepartments);
        radioDeptGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int selectedId = radioDeptGroup.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                radioDeptButton = (RadioButton) group.findViewById(selectedId);

            }
        });
        proceed = (Button) findViewById(R.id.btnProceedApp);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.e("State","" + eme_state);

                painArea = areaOfPain.getText().toString();
                Log.e("Areaof Pain","" + painArea);
                deptSelected = radioDeptButton.getText().toString();
                Log.e("Dept Selected","" + deptSelected);
                proceedForm(eme_state,painArea, painLevel, deptSelected);
            }
        });
    }

    private void proceedForm(String eme_state, String painArea, int painLevel, String deptSelected){
        Intent intent = new Intent(this, Add_Appointment_Main2.class);
        intent.putExtra("emergency", eme_state);
        intent.putExtra("areaOfPain", painArea);
        intent.putExtra("levelOfPain", painLevel);
        intent.putExtra("deptSelected", deptSelected);
        startActivity(intent);
    }

}
