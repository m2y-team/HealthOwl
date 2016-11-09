package m2y.centennial.healthowl.appointment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import m2y.centennial.healthowl.R;

/**
 * Created by yesha on 2016-10-21.
 */
/*M2Y*/
public class PageFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;

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
            return view;
        }
        if(mPage == 2){
            view = inflater.inflate(R.layout.fragment_clinicalsigns, container, false);
            return view;
        }
        else{
            view = inflater.inflate(R.layout.fragment_diagnosis, container, false);
            return view;
        }
        //TextView textView = (TextView) view.findViewById(R.id.textview);
        //textView.setText("Fragment #" + mPage);
        //return view;
    }
}
