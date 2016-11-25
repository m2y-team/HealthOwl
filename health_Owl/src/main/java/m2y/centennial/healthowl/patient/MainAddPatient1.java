package m2y.centennial.healthowl.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import m2y.centennial.healthowl.R;


public class MainAddPatient1 extends AppCompatActivity {

    private Toolbar mToolbar;
    private EditText inputFName, inputLN, inputAddress, inputOhip, inputPhone;
    private TextInputLayout inputLayoutFName, inputLayoutLName, inputLayoutPhone, inputLayoutOhip, inputLayoutAddress;
    private Button btnProceed;
    private ProgressBar tvIsConnected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient_main1);

        //Set up Menu with back button
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add New Patient (1/2)");

        // check if you are connected or not
        /*tvIsConnected = (ProgressBar) findViewById(R.id.tvIsConnected);

        if(isConnected()){
            tvIsConnected.setBackgroundColor(0xFF00CC00);
        }*/

        btnProceed = (Button)findViewById(R.id.btnProceed1);

        inputLayoutFName = (TextInputLayout)findViewById(R.id.input_layout_first_name);
        inputLayoutLName = (TextInputLayout)findViewById(R.id.input_layout_last_name);
        inputLayoutPhone = (TextInputLayout)findViewById(R.id.input_layout_cellphone);
        inputLayoutOhip = (TextInputLayout)findViewById(R.id.input_layout_ohip);
        inputLayoutAddress = (TextInputLayout)findViewById(R.id.input_layout_address);

        inputFName = (EditText)findViewById(R.id.etFirstName);
        inputLN = (EditText)findViewById(R.id.etLastName);
        inputAddress = (EditText)findViewById(R.id.etAddress);
        inputOhip = (EditText)findViewById(R.id.etOhip);
        inputPhone = (EditText)findViewById(R.id.etCellphone);

        inputFName.addTextChangedListener(new MyTextWatcher(inputFName));
        inputLN.addTextChangedListener(new MyTextWatcher(inputLN));
        inputAddress.addTextChangedListener(new MyTextWatcher(inputAddress));
        inputPhone.addTextChangedListener(new MyTextWatcher(inputPhone));
        inputOhip.addTextChangedListener(new MyTextWatcher(inputOhip));

        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateForm();
            }
        });
    }

    /*
    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }
*/

    private void validateForm() {
        if (!validateFName()) {
            return;}

        if (!validateLName()) {
            return;}

        if (!validateOhip()) {
            return;}

        if (!validatePhone()) {
            return;}

        if (!validateAddress()) {
            return;}

        String fName = inputFName.getText().toString();
        String lName = inputLN.getText().toString();
        String phone = inputPhone.getText().toString();
        String address = inputAddress.getText().toString();
        String ohip = inputOhip.getText().toString();

        //Toast.makeText(getApplicationContext(), fName+" "+lName+" "+phone+" "+address+" ", Toast.LENGTH_SHORT).show();
        proceedForm(fName, lName, phone, address, ohip);
    }

    //Validation
    private boolean validateFName() {
        if (inputFName.getText().toString().trim().isEmpty()) {
            inputLayoutFName.setError(getString(R.string.err_msg_fname));
            requestFocus(inputFName);
            return false;
        } else {
            inputLayoutFName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateLName() {
        if (inputLN.getText().toString().trim().isEmpty()) {
            inputLayoutLName.setError(getString(R.string.err_msg_lname));
            requestFocus(inputLN);
            return false;
        } else {
            inputLayoutLName.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateAddress() {
        if (inputAddress.getText().toString().trim().isEmpty()) {
            inputLayoutAddress.setError(getString(R.string.err_msg_address));
            requestFocus(inputAddress);
            return false;
        } else {
            inputLayoutAddress.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateOhip() {
        if (inputOhip.getText().toString().trim().isEmpty()) {
            inputLayoutOhip.setError(getString(R.string.err_msg_ohip));
            requestFocus(inputOhip);
            return false;
        } else {
            inputLayoutOhip.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePhone() {
        String phone = inputPhone.getText().toString().trim();

        if (phone.isEmpty() || !isValidPhone(phone)) {
            inputLayoutPhone.setError(getString(R.string.err_msg_phone));
            requestFocus(inputPhone);
            return false;
        } else {
            inputLayoutPhone.setErrorEnabled(false);
        }
        return true;
    }

    private static boolean isValidPhone(String phone) {
        return !TextUtils.isEmpty(phone) && Patterns.PHONE.matcher(phone).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
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
                    validateFName();
                    break;
                case R.id.input_layout_last_name:
                    validateLName();
                    break;
                case R.id.input_layout_address:
                    validateAddress();
                    break;
                case R.id.input_layout_ohip:
                    validateOhip();
                    break;
                case R.id.input_layout_cellphone:
                    validatePhone();
                    break;
            }
        }
    }

private void proceedForm(String fName, String lName, String phone, String address, String ohip){
    Intent intent = new Intent(this, MainAddPatient2.class);
    intent.putExtra("fName", fName);
    intent.putExtra("lName", lName);
    intent.putExtra("phone", phone);
    intent.putExtra("address", address);
    intent.putExtra("ohip", ohip);
    startActivity(intent);
}

}
