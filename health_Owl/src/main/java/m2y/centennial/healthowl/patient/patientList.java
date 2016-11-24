package m2y.centennial.healthowl.patient;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import m2y.centennial.healthowl.R;


/*M2Y*/
public class patientList extends AppCompatActivity {

    public static final String TAG = patientList.class.getSimpleName();
    private ProgressDialog pDialog;
    ListView lv;

    private FloatingActionButton mAddPatient;

    // URL to get contacts JSON
    private static String url = "https://m2y-healthowl.herokuapp.com/patients";

    ArrayList<HashMap<String, String>> contactList;
    ArrayList<HashMap<String, String>> nameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);

        mAddPatient = (FloatingActionButton)findViewById(R.id.add_patient_button);

        //Set up Menu with back button
        Toolbar myDetailsToolbar = (Toolbar)findViewById(R.id.toolbar3);
        setSupportActionBar(myDetailsToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Patients");

        contactList = new ArrayList<>();
        nameList = new ArrayList<>();

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

                            // tmp hash map for single contact
                            HashMap<String, String> contact = new HashMap<>();
                            HashMap<String, String> contact_name = new HashMap<>();

                            // adding each child node to HashMap key => value
                            contact.put("id", id);
                            contact.put("firstName", firstName);
                            contact.put("lastName", lastName);
                            contact.put("name", name);
                            contact.put("gender", gender);

                            contact_name.put("id", id);
                            contact_name.put("name", name);

                            // adding contact to contact list
                            contactList.add(contact);
                            nameList.add(contact_name);
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
                        String itemValue = (String) map.get("name");

                        // Show Alert
                        Toast.makeText(getApplicationContext(),
                                "Position :"+itemPosition+"  ListItem : " +"itemValue", Toast.LENGTH_LONG)
                                .show();
                        Log.i(TAG, "****************************");
                        goNext(itemValue);

                    }

                    private void goNext(String patientChoice){
                        Intent intent = new Intent(patientList.this, patientMain.class);
                        intent.putExtra("patientChoice", patientChoice);
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

}
