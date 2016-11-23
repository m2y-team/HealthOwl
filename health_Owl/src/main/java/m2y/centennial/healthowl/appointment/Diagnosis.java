package m2y.centennial.healthowl.appointment;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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
                // Log.e("blood Pressure","" + bloodPressure.getText());
                //   Toast.makeText(getActivity().getApplication(),areaOfPain.getText().toString(),Toast.LENGTH_LONG).show();
            }
        });

        newAppointment = (Button) view.findViewById(R.id.btnNewApp);
        newAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddAppointment.class);
//                intent.putExtra("patientChoice", patientChoice);
                startActivity(intent);
            }
        });

        return view;
    }

}
