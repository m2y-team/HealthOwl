package m2y.centennial.healthowl.appointment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TimePicker;
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
import java.util.Calendar;

import m2y.centennial.healthowl.R;

import static m2y.centennial.healthowl.R.id.tvIsConnected;

/**
 * M2Y
 */

public class AddAppointment extends AppCompatActivity implements View.OnClickListener{

    Button btnTimePicker, btnDatePicker, onSave;
    int year_x,month_x,day_x;
    static final int Dialog_ID = 0;
    EditText txtDate, txtTime, appName, withWhome, anyComments, ohip;
    private int mHour, mMinute, mYear, mMonth, mDay;
    appointmentModel appointment;
    private ProgressBar mtvIsConnected;
    String appontmentName,setTime, setDate, comments, insurance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointment);

        Toolbar addAppointmentToolbar = (Toolbar)findViewById(R.id.addAppointmentToolbar);
        setSupportActionBar(addAppointmentToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("Add Appointments");

        btnDatePicker=(Button)findViewById(R.id.btn_date);
        btnTimePicker=(Button)findViewById(R.id.btn_time);
        txtDate=(EditText)findViewById(R.id.in_date);
        txtTime=(EditText)findViewById(R.id.in_time);
        appName=(EditText)findViewById(R.id.editPatientName);
        ohip=(EditText)findViewById(R.id.editOhip);
        withWhome = (EditText) findViewById(R.id.with);
        anyComments = (EditText) findViewById(R.id.textComments);
        onSave = (Button) findViewById(R.id.btnsave);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);

        //Check network connection
        mtvIsConnected = (ProgressBar)findViewById(tvIsConnected);
        if(isConnected()){
            mtvIsConnected.setBackgroundColor(0xFF388E3C);}

        onSave.setOnClickListener(this);

    }

    public static String POST(String url, appointmentModel appointment){
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
            jsonObject.accumulate("patientName", appointment.getmAppointmentName());
            jsonObject.accumulate("patientId", 1234);
            jsonObject.accumulate("ohip", appointment.getOhip());
            jsonObject.accumulate("date", appointment.getmSetDate());
            jsonObject.accumulate("time", appointment.getmSetTime());
            jsonObject.accumulate("comments", appointment.getmComments());

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
    public void onClick(View v) {

        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }

        if(v == onSave){
            if(!validate()){
                Toast.makeText(getBaseContext(), "Enter some data!", Toast.LENGTH_LONG).show();
            }
            else {

                //Log.i(TAG, "!!!!!!!!LOOK HERE!!!!!!!");
                //Log.i(TAG, etName.getText().toString());

                appontmentName = appName.getText().toString();
                setDate = txtDate.getText().toString();
                setTime = txtTime.getText().toString();
                comments = anyComments.getText().toString();
                insurance = ohip.getText().toString();

                //call AsynTask to perform network operation on separate thread
                new HttpAsyncTask().execute("https://m2y-healthowl.herokuapp.com/appointments");
            }
        }
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            appointment = new appointmentModel();
            appointment.setmAppointmentName(appontmentName);
            appointment.setOhip(insurance);
            appointment.setmSetDate(setDate);
            appointment.setmSetTime(setTime);
            appointment.setmComments(comments);

            return POST(urls[0],appointment);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), "Data Sent!", Toast.LENGTH_LONG).show();
            txtDate.setText("");
            txtTime.setText("");
            appName.setText("");
            ohip.setText("");
            withWhome.setText("");
            anyComments.setText("");
        }
    }

    private boolean validate(){
        if(appName.getText().toString().trim().equals(""))
            return false;
        else if(anyComments.getText().toString().trim().equals(""))
            return false;
        else
            return true;
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



}
