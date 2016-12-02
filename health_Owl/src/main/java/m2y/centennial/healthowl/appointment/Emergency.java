package m2y.centennial.healthowl.appointment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;

import m2y.centennial.healthowl.R;

/**
 * M2Y
 */
public class Emergency extends Fragment {


    public static Switch switchButton;
    public static  Boolean emergency_state;
    public static SeekBar levelOfPain;
    String switchOn = "Switch is ON";
    String switchOff = getString(R.string.switchOf);
    public static RadioGroup radioDeptGroup;
    public static RadioButton radioDeptButton;
    public static String deptSelected;
    public static EditText areaOfPain;

    public Emergency() {
      //  Log.d("TEST","EMERGENCY FRAGMENT");
        // Required empty public constructor
    }

//    public static Emergency newInstance(){
//        Emergency emergency = new Emergency();
//        return emergency;
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view;
        view = inflater.inflate(R.layout.emergency, container, false);

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

        //areaOfPain.setText("hello");
        areaOfPain = (EditText) view.findViewById(R.id.editAreaOfPain);

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

        // Inflate the layout for this fragment
        return view;
    }




}
