package m2y.centennial.healthowl.patient;

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import m2y.centennial.healthowl.R;

/**
 * M2Y
 */


public class MainAddPatient2 extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = patientList.class.getSimpleName();

    private String addFName;
    private String addLName;
    private String addName;
    private String addPhone;
    private String addAddress;
    private String addOhip;

    private String addGender;
    private String addMonth;
    private String addDay;
    private String addYear;
    private String addDob;

    private Toolbar mToolbar2;
    private ProgressBar tvIsConnected;
    private RadioGroup mRadioGroup;
    private RadioButton mRadioButton;
    private Spinner mSpinner;
    private Button btnProceed2;

    private EditText mDay;
    private EditText mYear;
    private TextInputLayout inputLayoutDay;
    private TextInputLayout inputLayoutYear;

    patientModel person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient_main2);

        //Set up Menu with back button
        mToolbar2 = (Toolbar) findViewById(R.id.toolbar2);

        setSupportActionBar(mToolbar2);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add New Patient (2/2)");

        //Check network connection
        tvIsConnected = (ProgressBar) findViewById(R.id.tvIsConnected);
        if(isConnected()){
            tvIsConnected.setBackgroundColor(0xFF00CC00);}

        // get reference to the views
        inputLayoutDay = (TextInputLayout)findViewById(R.id.input_layout_day);
        inputLayoutYear = (TextInputLayout)findViewById(R.id.input_layout_year);

        mDay=(EditText) findViewById(R.id.dayInput);
        mYear=(EditText) findViewById(R.id.yearInput);

        mDay.addTextChangedListener(new MainAddPatient2.MyTextWatcher(mDay));
        mYear.addTextChangedListener(new MainAddPatient2.MyTextWatcher(mYear));


        //Get items from previous part of the form
        Intent intent = getIntent();
        addFName = intent.getStringExtra("fName");
        addLName = intent.getStringExtra("lName");
        addPhone = intent.getStringExtra("phone");
        addAddress = intent.getStringExtra("address");
        addOhip = intent.getStringExtra("ohip");

        //Toast.makeText(getApplicationContext(), fName+" "+lName+" "+phone+" "+address+" "+ohip, Toast.LENGTH_SHORT).show();


        //add listener on Spinner
        mSpinner = (Spinner) findViewById(R.id.spinnerMonth);
        List<String> list = new ArrayList<String>();
        list.add("Jan");
        list.add("Feb");
        list.add("Mar");
        list.add("Apr");
        list.add("May");
        list.add("Jun");
        list.add("Jul");
        list.add("Aug");
        list.add("Sep");
        list.add("Oct");
        list.add("Nov");
        list.add("Dec");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,list);

        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);


        mSpinner.setAdapter(dataAdapter);
        mSpinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());


        // add click listener to Button "POST"
        btnProceed2 = (Button) findViewById(R.id.btnProceed2);
        btnProceed2.setOnClickListener(this);

    }

    public static String POST(String url, patientModel person){
        InputStream inputStream = null;
        String result = "";
        try {

            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);

            String json = "";

            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("firstName", person.getFName());
            jsonObject.accumulate("lastName", person.getLName());
            jsonObject.accumulate("gender", person.getGender());
            jsonObject.accumulate("cellPhoneNumber", person.getPhone());
            jsonObject.accumulate("healthInsuranceNumber", person.getOhip());
            jsonObject.accumulate("address", person.getAddress());
            jsonObject.accumulate("dateOfBirth", person.getDob());

            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();

            // ** Alternative way to convert Person object to JSON string usin Jackson Lib
            // ObjectMapper mapper = new ObjectMapper();
            // json = mapper.writeValueAsString(person);

            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json);

            // 6. set httpPost Entity
            httpPost.setEntity(se);

            // 7. Set some headers to inform server about the type of the content
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);

            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // 10. convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        // 11. return result
        return result;
    }

    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){

            case R.id.btnProceed2:
                if(!validate()){
                    Toast.makeText(MainAddPatient2.this, "Enter all data!", Toast.LENGTH_LONG).show();
                    break;
                }
                else {

                    Log.i(TAG, "!!!!!!!!LOOK HERE!!!!!!!");
                    //Log.i(TAG, etName.getText().toString());

                    mRadioGroup = (RadioGroup) findViewById(R.id.radioGender);

                    // get selected radio button from radioGroup
                    int selectedId = mRadioGroup.getCheckedRadioButtonId();

                    // find the radio button by returned id
                    mRadioButton = (RadioButton) findViewById(selectedId);


        //Toast.makeText(MainAddPatient2.this, String.valueOf(mSpinner.getSelectedItem()), Toast.LENGTH_SHORT).show();
        //Toast.makeText(MainAddPatient2.this, mRadioButton.getText(), Toast.LENGTH_SHORT).show();

                    addGender = mRadioButton.getText().toString();
                    addMonth = String.valueOf(mSpinner.getSelectedItem());
                    addDay = mDay.getText().toString();
                    addYear = mYear.getText().toString();
                    addDob = addMonth + " " + addDay + ", "+addYear;
                    //addName = addFName + addLName;

                    Toast.makeText(getApplicationContext(), addFName+" "+addLName+" "+addPhone+" "+addAddress+" "+addOhip+" "+addGender+" "+addDob, Toast.LENGTH_SHORT).show();
                    //call AsynTask to perform network operation on separate thread
                    new MainAddPatient2.HttpAsyncTask().execute("https://m2y-healthowl.herokuapp.com/patients");
                }
        }

    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            person = new patientModel();
            person.setFName(addFName);
            person.setLName(addLName);
            person.setPhone(addPhone);
            person.setAddress(addAddress);
            person.setOhip(addOhip);
            person.setGender(addGender);
            person.setDob(addDob);

            return POST(urls[0],person);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), "Data Sent!", Toast.LENGTH_LONG).show();
        }
    }


    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;
    }


    private boolean validate(){
        if(String.valueOf(mSpinner.getSelectedItem()).trim().equals(""))
            return false;
        else if(mDay.getText().toString().trim().equals(""))
            return false;
        else if(mYear.getText().toString().trim().equals(""))
            return false;
        else
            return true;
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_layout_first_name:
                    validateDay();
                    break;
                case R.id.input_layout_last_name:
                    validateYear();
                    break;
            }
        }
    }

    //Validation
    private boolean validateDay() {
        if (mDay.getText().toString().trim().isEmpty()) {
            inputLayoutDay.setError(getString(R.string.err_msg_fname));
            requestFocus(mDay);
            return false;
        } else {
            inputLayoutDay.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateYear() {
        if (mYear.getText().toString().trim().isEmpty()) {
            inputLayoutYear.setError(getString(R.string.err_msg_fname));
            requestFocus(mYear);
            return false;
        } else {
            inputLayoutYear.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

}