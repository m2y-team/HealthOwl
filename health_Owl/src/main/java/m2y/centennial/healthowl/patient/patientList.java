package m2y.centennial.healthowl.patient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import m2y.centennial.healthowl.HttpHandler;
import m2y.centennial.healthowl.R;



/*M2Y*/
public class patientList extends AppCompatActivity {

    private String TAG = patientList.class.getSimpleName();
    private ProgressDialog pDialog;
    ListView lv;

    // URL to get contacts JSON
    private static String url = "https://m2y-healthowl.herokuapp.com/patients";

    ArrayList<HashMap<String, String>> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);

        //Set up Menu with back button
        Toolbar myDetailsToolbar = (Toolbar)findViewById(R.id.patientToolBar);
        setSupportActionBar(myDetailsToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Patients");

        contactList = new ArrayList<>();

        // Get ListView object from xml
        lv = (ListView) findViewById(R.id.list);

        new GetContacts().execute();

        /*
        String[] values = new String[] {
                "John Dorian",
                "Elliot Reid",
                "Christopher Turk",
                "Perry Cox",
                "Bob Kelso",
                "Todd Quinlan",
                "Ted Buckland",
                "Laverne Roberts",
                "Jordan Sullivan",
                "Carla Espinosa",
                "Kim Briggs"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_2, android.R.id.text1, values);

        // Assign adapter to ListView
        lv.setAdapter(adapter);
        // ListView Item Click Listener


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) lv.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show();

                goNext(itemValue);

            }

            private void goNext(String patientChoice){
                Intent intent = new Intent(this, patientMain.class);
                intent.putExtra("patientChoice", patientChoice);
                startActivity(intent);
            }

        });
*/

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

                        // Getting JSON Array node
                        //JSONArray contacts = jsonObj.getJSONArray("contacts");

                        // looping through All Contacts
                        for (int i = 0; i < contacts.length(); i++) {
                            JSONObject c = contacts.getJSONObject(i);

                            String id = c.getString("_id");
                            String firstName = c.getString("firstName");
                            String lastName = c.getString("lastName");
                            String name = firstName + " " + lastName;
                            String department = c.getString("department");

                            // tmp hash map for single contact
                            HashMap<String, String> contact = new HashMap<>();

                            // adding each child node to HashMap key => value
                            contact.put("id", id);
                            contact.put("firstName", firstName);
                            contact.put("lastName", lastName);
                            contact.put("name", name);
                            contact.put("department", department);

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
                        R.layout.list_patient_entry, new String[]{"name", "department"
                        }, new int[]{R.id.name,
                        R.id.department});

                lv.setAdapter(adapter);
            }
        }




}
