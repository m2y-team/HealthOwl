package m2y.centennial.healthowl.patient;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import m2y.centennial.healthowl.R;

/**M2Y*/
/*test comment for commit*/
public class Information extends Fragment {


    public Information() {
        // Required empty public constructor
    }

    TextView dob, gender, ohip, address, phone;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_p_info, container, false);


        String mGender= getActivity().getIntent().getStringExtra("gender");
        String mDOB= getActivity().getIntent().getStringExtra("dob");
        String mOhip= getActivity().getIntent().getStringExtra("ohip");

        String mAddress= getActivity().getIntent().getStringExtra("address");

        gender = (TextView)view.findViewById(R.id.addGenderTV);
        Information.this.gender.setText(mGender);

        dob = (TextView)view.findViewById(R.id.addDobTV);
        Information.this.dob.setText(mDOB);

        ohip = (TextView)view.findViewById(R.id.addInsuranceTV);
        Information.this.ohip.setText(mOhip);

        address = (TextView)view.findViewById(R.id.otherAddressTV);
        Information.this.address.setText(mAddress);



        return view;
    }




}