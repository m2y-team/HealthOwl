package m2y.centennial.healthowl.appointment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import m2y.centennial.healthowl.R;
import m2y.centennial.healthowl.appointment.Add_Appointment_Main1;
import m2y.centennial.healthowl.appointment.appointmentModel;

import static m2y.centennial.healthowl.R.id.tvIsConnected;

public class Add_Appointment_Main3 extends AppCompatActivity implements View.OnClickListener{


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
        setContentView(R.layout.activity_add__appointment__main3);

        Toolbar addAppointmentToolbar = (Toolbar) findViewById(R.id.addAppointmentToolbar);
        setSupportActionBar(addAppointmentToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("Add Appointments");

        btnDatePicker = (Button) findViewById(R.id.btn_date);
        btnTimePicker = (Button) findViewById(R.id.btn_time);
        txtDate = (EditText) findViewById(R.id.in_date);
        txtTime = (EditText) findViewById(R.id.in_time);
        appName = (EditText) findViewById(R.id.editPatientName);
        ohip = (EditText) findViewById(R.id.editOhip);
        withWhome = (EditText) findViewById(R.id.with);
        anyComments = (EditText) findViewById(R.id.textComments);
        onSave = (Button) findViewById(R.id.btnsave);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);

        //Check network connection
        mtvIsConnected = (ProgressBar) findViewById(tvIsConnected);
        if (isConnected()) {
            mtvIsConnected.setBackgroundColor(0xFF388E3C);
        }

        onSave.setOnClickListener(this);

    }

//    private void proceedForm1(String appontmentName)
//    {
//        Intent intent = new Intent(this, Add_Appointment_Main1.class);
//        intent.putExtra("emergency", app);
//
//    }
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

                Intent i = new Intent(this, Add_Appointment_Main1.class);
                i.putExtra("appointmentName",appontmentName);
                i.putExtra("setDate",setDate);
                i.putExtra("setTime",setTime);
                i.putExtra("comments",comments);
                i.putExtra("insurance",insurance);
                startActivity(i);
                //Toast.makeText(getBaseContext(), "Hello", Toast.LENGTH_LONG).show();
                //call AsynTask to perform network operation on separate thread
         //       new AddAppointment.HttpAsyncTask().execute("https://m2y-healthowl.herokuapp.com/appointments");
            }
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


}
