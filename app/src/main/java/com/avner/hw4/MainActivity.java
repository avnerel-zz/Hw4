package com.avner.hw4;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;


public class MainActivity extends Activity implements TextWatcher, CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    private EditText sheepName;

    private EditText sheepAge;

    private Switch sheepColor;

    private Button viewAll;

    private Button insertData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sheepName = (EditText) findViewById(R.id.et_sheep_name);
        sheepName.addTextChangedListener(this);

        sheepAge = (EditText) findViewById(R.id.et_sheep_age);
        sheepAge.addTextChangedListener(this);

        sheepColor = (Switch) findViewById(R.id.s_sheep_color);
        sheepColor.setOnCheckedChangeListener(this);

        viewAll= (Button) findViewById(R.id.b_view_all);
        viewAll.setOnClickListener(this);

        insertData = (Button) findViewById(R.id.b_insert_data);
        insertData.setOnClickListener(this);

        updateInsertData();

//        getApplicationContext().deleteDatabase(SQLUtils.DB_NAME);
    }


    public void updateInsertData(){

        boolean emptyName = (sheepName.getText() == null) || (sheepName.getText().length() == 0);

        boolean emptyAge = (sheepAge.getText() == null) || (sheepAge.getText().length() == 0);

        if(!emptyName && !emptyAge){

            insertData.setEnabled(true);

        }else{

            insertData.setEnabled(false);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        updateInsertData();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if(buttonView.getId() == R.id.s_sheep_color){

        }
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.b_insert_data)            {

            String name = sheepName.getText().toString();

            String age = sheepAge.getText().toString();

            String color;
            if(sheepColor.isChecked()){

                color = getResources().getString(R.string.switch_on);

            }else{

                color = getResources().getString(R.string.switch_off);
            }

            SheepData newData = new SheepData(name, age, color);

            SheepApplication app = (SheepApplication)getApplicationContext();

            app.sqlUtils.insertData(newData);

            Toast.makeText(getApplicationContext(), "New sheep added", Toast.LENGTH_SHORT).show();

            clearData();


        }else if(v.getId() == R.id.b_view_all){

        Intent intent = new Intent(getApplicationContext(),
                SheepListActivity.class);

        startActivity(intent);

        }
    }

    private void clearData() {

        sheepName.getText().clear();

        sheepAge.getText().clear();

        sheepColor.setChecked(false);

        updateInsertData();

    }
}
