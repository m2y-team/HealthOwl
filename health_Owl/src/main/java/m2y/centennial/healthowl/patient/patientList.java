package m2y.centennial.healthowl.patient;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import m2y.centennial.healthowl.HttpHandler;
import m2y.centennial.healthowl.LoginActivity;
import m2y.centennial.healthowl.R;
import m2y.centennial.healthowl.appointment.MainAppointments;


/**M2Y*/
public class patientList extends AppCompatActivity {

    public static final String TAG = patientList.class.getSimpleName();
    private ProgressDialog pDialog;
    ListView lv;
    Toolbar myToolbar;

    private FloatingActionButton mAddPatient;

    // URL to get contacts JSON
    private static String url = "https://m2y-healthowl.herokuapp.com/patients";

    ArrayList<HashMap<String, String>> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);

        mAddPatient = (FloatingActionButton)findViewById(R.id.add_patient_button);

        //Set up Menu
        initToolBar();

        //Set up Bottom Bar
        final BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);
        bottomNavigationView.getMenu().getItem(0).setChecked(false);
        bottomNavigationView.getMenu().getItem(1).setChecked(false);
        bottomNavigationView.getMenu().getItem(2).setChecked(true);



         bottomNavigationView.setOnNavigationItemSelectedListener(

                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Intent myAct = new Intent();
                        switch (item.getItemId()) {
                            case R.id.action_appointments:
                                myAct = new Intent(findViewById(item.getItemId()).getContext(), MainAppointments.class);

                                break;
                            case R.id.action_patients:
                                myAct = new Intent(findViewById(item.getItemId()).getContext(), patientList.class);




                                break;
                            case R.id.action_logout:
                                myAct = new Intent(findViewById(item.getItemId()).getContext(), LoginActivity.class);
                                break;

                        }
                        startActivity(myAct);
                        return false;
                    }
                });

        contactList = new ArrayList<>();

        // Get ListView object from xml
        lv = (ListView) findViewById(R.id.list);

        new GetContacts().execute();

        mAddPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passValue = "add";
                addPatientStart(passValue);
            }
        });

    }


        /**
         * Async task class to get json by making HTTP call
         */
        private class GetContacts extends AsyncTask<Void, Void, Void> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                // Showing progress dialog
                pDialog = new ProgressDialog(patientList.this);
                pDialog.setMessage("Please wait...");
                pDialog.setCancelable(false);
                pDialog.show();

            }

            @Override
            protected Void doInBackground(Void... arg0) {
                HttpHandler sh = new HttpHandler();

                // Making a request to url and getting response
                String jsonStr = sh.makeServiceCall(url);

                Log.e(TAG, "Response from url: " + jsonStr);

                if (jsonStr != null) {
                    try {

                        //JSONObject jsonObj = new JSONObject(jsonStr);
                        JSONArray contacts = new JSONArray(jsonStr);

                        // looping through All Contacts
                        for (int i = 0; i < contacts.length(); i++) {
                            JSONObject c = contacts.getJSONObject(i);

                            String id = c.getString("_id");
                            String firstName = c.getString("firstName");
                            String lastName = c.getString("lastName");

                            String name = firstName + " " + lastName;
                            String gender = c.getString("gender");
                            String dateOfBirth = c.getString("dateOfBirth");
                            String healthInsuranceNumber = c.getString("healthInsuranceNumber");
                            String cellPhoneNumber = c.getString("cellPhoneNumber");
                            String address = c.getString("address");


                            // tmp hash map for single contact
                            HashMap<String, String> contact = new HashMap<>();

                            // adding each child node to HashMap key => value
                            contact.put("id", id);
                            contact.put("firstName", firstName);
                            contact.put("lastName", lastName);

                            contact.put("name", name);
                            contact.put("gender", gender);
                            contact.put("healthInsuranceNumber", healthInsuranceNumber);
                            contact.put("dateOfBirth", dateOfBirth);
                            contact.put("cellPhoneNumber", cellPhoneNumber);
                            contact.put("address", address);


                            // adding contact to contact list
                            contactList.add(contact);
                        }
                    } catch (final JSONException e) {
                        Log.e(TAG, "Json parsing error: " + e.getMessage());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),
                                        "Json parsing error: " + e.getMessage(),
                                        Toast.LENGTH_LONG)
                                        .show();
                            }
                        });

                    }
                } else {
                    Log.e(TAG, "Couldn't get json from server.");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Couldn't get json from server. Check LogCat for possible errors!",
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
                // Dismiss the progress dialog
                if (pDialog.isShowing())
                    pDialog.dismiss();
                /**
                 * Updating parsed JSON data into ListView
                 * */
                ListAdapter adapter = new SimpleAdapter(
                        patientList.this, contactList,
                        R.layout.list_patient_entry, new String[]{"name", "gender"
                        }, new int[]{R.id.name,
                        R.id.department});

                lv.setAdapter(adapter);


                //get to work onClickListener

                // ListView Item Click Listener

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // TODO Auto-generated method stub
                        // ListView Clicked item index
                        int itemPosition = position;

                        Map<String, String> map = (Map<String, String>) lv.getItemAtPosition(position);

                        // ListView Clicked item value
                        //String itemValue = lv.getItemAtPosition(position).toString();
                        String mName = (String) map.get("name");
                        String mGender = (String) map.get("gender");
                        String mPhone = (String) map.get("cellPhoneNumber");
                        String mOhip = (String) map.get("healthInsuranceNumber");
                        String mDob = (String) map.get("dateOfBirth");
                        String mAddress = (String) map.get("address");

                        // Show Alert
                        /*Toast.makeText(getApplicationContext(),
                                mPhone+" "+mOhip+" "+mDob+" "+mAddress+" "+itemPosition+"  ListItem : " +mName, Toast.LENGTH_LONG)
                                .show();*/
                        Log.i(TAG, "****************************");
                        goNext(mName, mGender, mOhip, mDob, mPhone, mAddress);

                    }

                    private void goNext(String name, String gender, String ohip, String dob, String phone, String address){
                        //String[] passArray = {gender, ohip, dob, phone, address};

                        Intent intent = new Intent(patientList.this, patientMain.class);
                        intent.putExtra("name", name);
                        intent.putExtra("gender", gender);
                        intent.putExtra("dob", dob);
                        intent.putExtra("ohip", ohip);
                        intent.putExtra("phone", phone);
                        intent.putExtra("address", address);
                        startActivity(intent);
                    }

                });

            }
        }

    private void addPatientStart(String pass){
        Intent intent = new Intent(this, MainAddPatient1.class);
        intent.putExtra("pass", pass);
        startActivity(intent);
    }

    //Setting up toolbar
    public void initToolBar() {
        myToolbar = (Toolbar)findViewById(R.id.toolbar3);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Health Owl");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
