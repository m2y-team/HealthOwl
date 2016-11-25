package m2y.centennial.healthowl.appointment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import m2y.centennial.healthowl.R;

/**
 * Created by yesha on 2016-10-21.
 */
/*M2Y*/
public class PageFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";

    Button newAppointment, appSave;
    private int mPage;
    Switch switchButton;
    Boolean emergency_state;
    SeekBar levelOfPain;
    String switchOn = "Switch is ON";
    String switchOff = "Switch is OFF";
    private RadioGroup radioDeptGroup;
    private RadioButton radioDeptButton;
    String deptSelected;
    EditText reasonForVisit, temperature, bloodPressure, heartRate , diagnosisDetail;

    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.fragment_emergency, container, false);
        View view;
        if(mPage == 1){
            view = inflater.inflate(R.layout.fragment_emergency, container, false);

            switchButton = (Switch) view.findViewById(R.id.switchButton1);
            switchButton.setChecked(true);
            switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                    if (bChecked) {
                        emergency_state = true;
                    } else {
                        emergency_state = false;
                    }
                }
            });

            levelOfPain = (SeekBar) view.findViewById(R.id.levelOfPain);

            levelOfPain.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    Log.e("level of pain","" + levelOfPain.getProgress());

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

            radioDeptGroup = (RadioGroup) view.findViewById(R.id.radiodepartments);
            radioDeptGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    int selectedId = radioDeptGroup.getCheckedRadioButtonId();
                    // find the radiobutton by returned id
                    radioDeptButton = (RadioButton) group.findViewById(selectedId);
                    deptSelected = radioDeptButton.getText().toString();
                }
            });

            // savedInstanceState.putString("hello","Hi");


            return view;
        }
        if(mPage == 2){
            view = inflater.inflate(R.layout.fragment_clinicalsigns, container, false);

            reasonForVisit = (EditText) view.findViewById(R.id.reasonForVisit);
            reasonForVisit.getText();


            heartRate = (EditText) view.findViewById(R.id.heartRate);
            heartRate.getText();

            bloodPressure = (EditText) view.findViewById(R.id.bloodPressure);
            bloodPressure.getText();

            temperature = (EditText) view.findViewById(R.id.temperature);
            temperature.getText();

            //Log.e("You passed:",savedInstanceState.getString("hello"));

            return view;
        }
        else{
            view = inflater.inflate(R.layout.fragment_diagnosis, container, false);

            diagnosisDetail = (EditText) view.findViewById(R.id.doctorReco);
            appSave = (Button) view.findViewById(R.id.appDetailSave);

            appSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //diagnosisDetail = (EditText) view.findViewById(R.id.doctorReco);
                    Log.e("Diagnosis Detail","" + diagnosisDetail.getText());
                    // Log.e("blood Pressure","" + bloodPressure.getText());
                    //Toast.makeText(this ,diagnosisDetail.getText().toString() ,Toast.LENGTH_LONG).show();
                }
            });

            newAppointment = (Button) view.findViewById(R.id.btnNewApp);

            return view;
        }
        //TextView textView = (TextView) view.findViewById(R.id.textview);
        //textView.setText("Fragment #" + mPage);
        //return view;
    }
}
