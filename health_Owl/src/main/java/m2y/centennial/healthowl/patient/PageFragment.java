package m2y.centennial.healthowl.patient;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import m2y.centennial.healthowl.R;

public class PageFragment extends Fragment {


    private static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;
    private String mParam1;
    private String mParam2;

    public PageFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static PageFragment newInstance(int page) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_page_general, container, false);
    }

}
