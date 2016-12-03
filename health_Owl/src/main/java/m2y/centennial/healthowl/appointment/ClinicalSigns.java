package m2y.centennial.healthowl.appointment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import m2y.centennial.healthowl.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClinicalSigns extends Fragment {

    public static EditText reasonForVisit, temperature, bloodPressure, heartRate;
    public ClinicalSigns() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view;
        view = inflater.inflate(R.layout.clinical_signs, container, false);

        reasonForVisit = (EditText) view.findViewById(R.id.reasonForVisit);
        reasonForVisit.getText();

        heartRate = (EditText) view.findViewById(R.id.heartRate);
        heartRate.getText();

        bloodPressure = (EditText) view.findViewById(R.id.bloodPressure);
        bloodPressure.getText();

        temperature = (EditText) view.findViewById(R.id.temperature);
        temperature.getText();

        // Inflate the layout for this fragment
        return view;
    }

}
