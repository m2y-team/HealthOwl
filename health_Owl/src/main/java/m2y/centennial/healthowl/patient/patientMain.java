package m2y.centennial.healthowl.patient;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import m2y.centennial.healthowl.R;
/*M2Y*/
public class patientMain extends AppCompatActivity {

    private String mTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_main);

        Intent intent = getIntent();
        mTitle = intent.getStringExtra("patientChoice");

        //Set up Menu with back button
        Toolbar myPatientToolbar = (Toolbar)findViewById(R.id.patientToolBar);
        setSupportActionBar(myPatientToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(mTitle);


        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new PatientFragmentAdapter(getSupportFragmentManager(),
                patientMain.this));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

    }
}