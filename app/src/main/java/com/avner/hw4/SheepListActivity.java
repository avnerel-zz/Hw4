package com.avner.hw4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class SheepListActivity extends Activity {

    private ListView lv_myList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheep_list);

        SheepApplication app = (SheepApplication)getApplicationContext();

        ArrayList<SheepData> sheepData = app.sqlUtils.getSheepData();

        //TODO add sheep names

        lv_myList = (ListView) findViewById(R.id.lv_sheep_list);

        //ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
        SheepListAdapter adapter = new SheepListAdapter(sheepData);

        lv_myList.setClickable(true);

        lv_myList.setAdapter(adapter);

        lv_myList.setOnItemClickListener(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sheep_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public class SheepListAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {

        private ArrayList<SheepData> data;

        public SheepListAdapter(ArrayList<SheepData> sheepData) {

            data = sheepData;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view;
            ViewHolder viewHolder;

            Log.d("MY_TAG", "Position: " + position);

            if (convertView == null) {
                LayoutInflater li = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = li.inflate(R.layout.list_row_layout, null);

                viewHolder = new ViewHolder();
                viewHolder.name = (TextView) view.findViewById(R.id.tv_name);
                viewHolder.color = (TextView) view.findViewById(R.id.tv_color);
                viewHolder.age = (TextView) view.findViewById(R.id.tv_age);

                view.setTag(viewHolder);
            }
            else {
                view = convertView;

                viewHolder = (ViewHolder) view.getTag();
            }

            // Put the content in the view
            viewHolder.name.setText(data.get(position).name);

            viewHolder.age.setText(getResources().getString(R.string.age_prefix)
                    + data.get(position).age + getResources().getString(R.string.age_suffix));

            viewHolder.color.setText(getResources().getString(R.string.color_prefix)
                    + data.get(position).color + getResources().getString(R.string.color_suffix));

            return view;
        }


        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        }

        private class ViewHolder {
            TextView name;

            TextView age;

            TextView color;
        }

    }

}
