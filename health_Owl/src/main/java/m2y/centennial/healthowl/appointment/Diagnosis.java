package m2y.centennial.healthowl.appointment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import m2y.centennial.healthowl.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Diagnosis extends Fragment {
    Button newAppointment, appSave;
    EditText diagnosisDetail;

    public Diagnosis() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view;
        view = inflater.inflate(R.layout.diagnosis, container, false);
        // Inflate the layout for this fragment
        diagnosisDetail = (EditText) view.findViewById(R.id.doctorReco);
        appSave = (Button) view.findViewById(R.id.appDetailSave);

        appSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //diagnosisDetail = (EditText) view.findViewById(R.id.doctorReco);
                Log.e("Diagnosis Detail","" + diagnosisDetail.getText());
               // Log.e("",((EditText)view.findViewById(R.id.reasonForVisit)).toString());
                // Log.e("blood Pressure","" + bloodPressure.getText());
                Toast.makeText(getActivity().getApplication(), Emergency.areaOfPain.getText().toString() + "\n "+ Emergency.deptSelected + "\n"+
                        Emergency.levelOfPain.getProgress() +"\n " + Emergency.emergency_state + "\n"+ ClinicalSigns.reasonForVisit.getText()+"\n "+
                                ClinicalSigns.temperature.getText() +"\n "+ ClinicalSigns.bloodPressure.getText() +"\n "+
                        ClinicalSigns.heartRate.getText()
                        ,Toast.LENGTH_LONG).show();

            }
        });

        newAppointment = (Button) view.findViewById(R.id.btnNewApp);

        return view;
    }





}
