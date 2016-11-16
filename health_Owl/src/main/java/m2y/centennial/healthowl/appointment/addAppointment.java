package m2y.centennial.healthowl.appointment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

import m2y.centennial.healthowl.R;

public class AddAppointment extends AppCompatActivity {

    Button datePicker;
    Button timePicker;
    int year_x,month_x,day_x;
    static final int Dialog_ID = 0;
    static final int DiaLogTime_ID = 0;
    int hour_x, minute_x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointment);

        Toolbar addAppointmentToolbar = (Toolbar)findViewById(R.id.addAppointmentToolbar);
        setSupportActionBar(addAppointmentToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("Add Appointments");

        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);
        showDialogOnButtonClick();
    }
    //DatePicker
    public void showDialogOnButtonClick(){
        datePicker = (Button)findViewById(R.id.date_button);

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(Dialog_ID);
            }
        });

    }

    @Override
    protected Dialog onCreateDialog(int id){
        if(id == Dialog_ID)
            return new DatePickerDialog(this, dpickerListener, year_x,month_x,day_x);
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListener= new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year_x = year;
            month_x = monthOfYear + 1;
            day_x = dayOfMonth;
            Toast.makeText(AddAppointment.this, year_x + "/" + month_x + " / " + day_x,Toast.LENGTH_LONG).show();
        }
    };
}
