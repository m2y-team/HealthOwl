package m2y.centennial.healthowl.groups;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import m2y.centennial.healthowl.R;
/*M2Y*/
public class GroupDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_details);

        //Set up Menu with back button
        Toolbar myPatientToolbar = (Toolbar)findViewById(R.id.toolbarGroup2);
        setSupportActionBar(myPatientToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Cardiology");



    }
}
