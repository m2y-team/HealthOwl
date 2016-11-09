package m2y.centennial.healthowl.patient;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import m2y.centennial.healthowl.R;

/*M2Y*/
public class PageFragment1 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    private static final String ARG_PARAM1 = "param1";
    private int mPage;

    public static PageFragment1 newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, page);
        PageFragment1 fragment = new PageFragment1();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PARAM1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        TextView textView = (TextView) view;
        textView.setText("Fragment #" + mPage);
        return view;
    }




}