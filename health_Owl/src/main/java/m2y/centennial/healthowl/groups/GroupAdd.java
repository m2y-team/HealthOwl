package m2y.centennial.healthowl.groups;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import m2y.centennial.healthowl.R;

/**M2Y*/
public class GroupAdd extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_add);

        //Set up Menu with back button
        mToolbar = (Toolbar) findViewById(R.id.toolbarGroupAdd);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.titleNewGroup);

    }
}

