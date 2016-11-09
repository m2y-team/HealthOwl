package m2y.centennial.healthowl.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


import m2y.centennial.healthowl.R;

public class TestDetails extends AppCompatActivity {

    FloatingActionButton addTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);

        addTest = (FloatingActionButton) findViewById(R.id.faButton_AddTest);
        addTest.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                goToAddTest("test");
            }
        });
    }

    private void goToAddTest(String test){
        Intent intent = new Intent(this, TestAdd.class);
        intent.putExtra("patientChoice", test);
        startActivity(intent);
    }
}
