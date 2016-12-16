package m2y.centennial.healthowl.patient;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import m2y.centennial.healthowl.R;

/**M2Y*/
public class AppointmentsInfo extends Fragment {

    public AppointmentsInfo() {
        // Required empty public constructor
    }

    TextView email, cellphone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_appointments_info, container, false);


        String mEmail= getActivity().getIntent().getStringExtra("email");
        String mPhone = getActivity().getIntent().getStringExtra("phone");


        //TODO: update ids

        email = (TextView)view.findViewById(R.id.addEmailTV);
        AppointmentsInfo.this.email.setText(mEmail);

        cellphone = (TextView)view.findViewById(R.id.addPhoneTV);
        AppointmentsInfo.this.cellphone.setText(mPhone);

        return view;
    }






}
