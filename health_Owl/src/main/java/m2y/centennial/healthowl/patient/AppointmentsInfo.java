package m2y.centennial.healthowl.patient;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import m2y.centennial.healthowl.R;


public class AppointmentsInfo extends Fragment {

    public AppointmentsInfo() {
        // Required empty public constructor
    }

    public static final String TAG = patientList.class.getSimpleName();
    private ProgressDialog pDialog;
    ListView lv;

    // URL to get contacts JSON
    private static String url = "https://m2y-healthowl.herokuapp.com/appointments";

    ArrayList<HashMap<String, String>> contactList;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_appointments_info, container, false);

        //lv = (ListView) getActivity().findViewById(R.id.listPatientApp);

        //ListviewContactItem contact = new ListviewContactItem();


        return view;
    }
}
