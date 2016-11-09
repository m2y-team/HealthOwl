package m2y.centennial.healthowl.appointment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import m2y.centennial.healthowl.R;

/**
 * Created by yesha on 2016-11-01.
 */
    /*M2Y*/
    public class CustomListViewAdapter extends BaseAdapter {
        String [] result;
        Context context;
        String [] desc;
        //int [] imageId;
        private static LayoutInflater inflater=null;
        public CustomListViewAdapter(MainAppointments mainAppointments, String[] appointment_names, String[] appointment_desc) {
            // TODO Auto-generated constructor stub
            result=appointment_names;
            context=mainAppointments;
            desc = appointment_desc;
            //imageId=prgmImages;
            inflater = (LayoutInflater)context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return result.length;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        public class Holder
        {
            TextView tv;
            TextView sub;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            Holder holder=new Holder();
            View rowView;
            rowView = inflater.inflate(R.layout.appointment_list, null);
            holder.tv=(TextView) rowView.findViewById(R.id.titleView);
            holder.sub=(TextView) rowView.findViewById(R.id.subtitleView);
            holder.tv.setText(result[position]);
            holder.sub.setText(desc[position]);
            //holder.sub.setText(imageId[position]);
            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Toast.makeText(context, "You Clicked "+result[position], Toast.LENGTH_LONG).show();
                    //goAppointmentTabs(result[position]);
                }
            });
            return rowView;

        }

    /*
    private void goAppointmentTabs(String Appointment){
        Intent intent = new Intent(this, AppointmentTabs.class);
        intent.putExtra("Appointment", Appointment);
        startActivity(intent);
    }*/



    }