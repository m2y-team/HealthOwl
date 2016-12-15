package m2y.centennial.healthowl.appointment;

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

import m2y.centennial.healthowl.R;

import static m2y.centennial.healthowl.R.id.tvIsConnected;

public class Add_Appointment_Main2 extends AppCompatActivity {

    public static EditText reasonForVisit, temperature, bloodPressure, heartRate;
    Button updateDetails;
    public static String deptSelected, areaOfPain, emergency_state,appontmentName, setDate, setTime, comments, insurance, reason , blood, temp,heart;
    public static int levelOfPain;
    private Toolbar mToolbar;
    appointmentModel appointment;
    private ProgressBar mtvIsConnected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__appointment__main2);

        //Set up Menu with back button
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add New Appointment (2/2)");

        reasonForVisit = (EditText) findViewById(R.id.reasonForVisit);
        //reasonForVisit.getText();

        heartRate = (EditText) findViewById(R.id.heartRate);
//        heartRate.getText();

        bloodPressure = (EditText) findViewById(R.id.bloodPressure);
        //bloodPressure.getText();

        temperature = (EditText) findViewById(R.id.temperature);
        //temperature.getText();

        //Check network connection
        mtvIsConnected = (ProgressBar)findViewById(tvIsConnected);
        if(isConnected()){
            mtvIsConnected.setBackgroundColor(0xFF388E3C);
        }

//        //Get items from previous part of the form
//        Intent intent = getIntent();
//        //emergency_state = intent.getExtras().get("emergency").toString();
//        areaOfPain = intent.getExtras().get("areaOfPain").toString();
//        levelOfPain= Integer.parseInt(intent.getExtras().get("levelOfPain").toString());
//        deptSelected = intent.getExtras().get("deptSelected").toString();
//        appontmentName = intent.getExtras().get("appointmentName").toString();
//        setDate = intent.getExtras().get("setDate").toString();
//        setTime= intent.getExtras().get("setTime").toString();
//        comments = intent.getExtras().get("comments").toString();
//        insurance = intent.getExtras().get("insurance").toString();


        //Toast.makeText(getApplicationContext(),areaOfPain+"  "+levelOfPain+" "+deptSelected+" "+appontmentName+" "+setDate+" "+setTime+" "+comments+" "+insurance, Toast.LENGTH_LONG).show();

        updateDetails = (Button) findViewById(R.id.appDetailSave);
        updateDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get items from previous part of the form
                Intent intent = getIntent();
                //emergency_state = intent.getExtras().get("emergency").toString();
                areaOfPain = intent.getExtras().get("areaOfPain").toString();
                levelOfPain= Integer.parseInt(intent.getExtras().get("levelOfPain").toString());
                deptSelected = intent.getExtras().get("deptSelected").toString();
                appontmentName = intent.getExtras().get("appointmentName").toString();
                setDate = intent.getExtras().get("setDate").toString();
                setTime= intent.getExtras().get("setTime").toString();
                comments = intent.getExtras().get("comments").toString();
                insurance = intent.getExtras().get("insurance").toString();

                reason = reasonForVisit.getText().toString();
                heart = heartRate.getText().toString();
                blood = bloodPressure.getText().toString();
                temp = temperature.getText().toString();


                //Toast.makeText(getApplicationContext(),areaOfPain+"  "+levelOfPain+" "+deptSelected+" "+appontmentName+" "+setDate+" "+setTime+" "+comments+" "+insurance+" "+blood+" "+temp+" "+heart+" "+reason, Toast.LENGTH_LONG).show();
                //call AsynTask to perform network operation on separate thread
                new Add_Appointment_Main2.HttpAsyncTask().execute("https://m2y-healthowl.herokuapp.com/appointments");

            }
        });
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
            jsonObject.accumulate("ohip", appointment.getmOhip());
            jsonObject.accumulate("date", appointment.getmSetDate());
            jsonObject.accumulate("time", appointment.getmSetTime());
            jsonObject.accumulate("comments", appointment.getmComments());
            jsonObject.accumulate("levelOfpain", appointment.getmLevelOfPain());
            jsonObject.accumulate("areaOfpain", appointment.getmAreaOfPain());
            jsonObject.accumulate("department", appointment.getmDeptSelected());
            jsonObject.accumulate("reason", appointment.getmReason());
            jsonObject.accumulate("hbr", appointment.getmHeart());
            jsonObject.accumulate("bp", appointment.getmBlood());
            jsonObject.accumulate("temperature", appointment.getMtemp());
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

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            appointment = new appointmentModel();
            appointment.setmAppointmentName(appontmentName);
            appointment.setmOhip(insurance);
            appointment.setmSetDate(setDate);
            appointment.setmSetTime(setTime);
            appointment.setmComments(comments);
            appointment.setmAreaOfPain(areaOfPain);
            appointment.setmLevelOfPain(levelOfPain);
            appointment.setmDeptSelected(deptSelected);
            appointment.setmHeart(heart);
            appointment.setmReason(reason);
            appointment.setmBlood(blood);
            appointment.setMtemp(temp);


            return POST(urls[0],appointment);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), "Appointment has been successfully created for "+appontmentName +"!", Toast.LENGTH_LONG).show();
//            txtDate.setText("");
//            txtTime.setText("");
//            appName.setText("");
//            ohip.setText("");
//            withWhome.setText("");
//            anyComments.setText("");
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

}
