package com.hardcastle.sqlite;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddProperty extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    SQLiteDatabase sqLiteDatabaseObj;
    EditText editTextName, editTextPhoneNumber, editTextnameofowner, editTextContact;
    String NameHolder, NumberHolder, RegistrationHolder, SQLiteDataBaseQueryHolder, OwnerHolder, ContactHolder;
    Button EnterData, ButtonDisplayData;
    Boolean EditTextEmptyHold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EnterData = (Button) findViewById(R.id.button);
        ButtonDisplayData = (Button) findViewById(R.id.button2);
        editTextName = (EditText) findViewById(R.id.editText);
        editTextPhoneNumber = (EditText) findViewById(R.id.editText2);
        editTextnameofowner = (EditText) findViewById(R.id.nameofowner);
        editTextContact = (EditText) findViewById(R.id.contactno);

        EnterData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                SQLiteDataBaseBuild();

                SQLiteTableBuild();

                CheckEditTextStatus();

                InsertDataIntoSQLiteDatabase();

                EmptyEditTextAfterDataInsert();


            }
        });


        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("UAM ");
        categories.add("EM-II");
        categories.add("IEM");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);


        ButtonDisplayData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AddProperty.this, DisplaySQLiteDataActivity.class);
                startActivity(intent);
            }
        });


    }

    public void SQLiteDataBaseBuild() {

        sqLiteDatabaseObj = openOrCreateDatabase(SQLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);

    }

    public void SQLiteTableBuild() {

        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS " + SQLiteHelper.TABLE_NAME + "(" + SQLiteHelper.Table_Column_ID + " PRIMARY KEY AUTOINCREMENT NOT NULL, " + SQLiteHelper.Table_Column_1_Name + " VARCHAR, " + SQLiteHelper.Table_Column_2_PhoneNumber + " VARCHAR, " + SQLiteHelper.Table_Column_3_Registration + " VARCHAR, " + SQLiteHelper.Table_Column_4_Owner + " VARCHAR, " + SQLiteHelper.Table_Column_5_Contact + " VARCHAR);");

    }

    public void CheckEditTextStatus() {

        NameHolder = editTextName.getText().toString();
        NumberHolder = editTextPhoneNumber.getText().toString();
        OwnerHolder = editTextnameofowner.getText().toString();
        ContactHolder = editTextContact.getText().toString();


        if (TextUtils.isEmpty(NameHolder) || TextUtils.isEmpty(NumberHolder)) {

            EditTextEmptyHold = false;

        } else {

            EditTextEmptyHold = true;
        }
    }

    public void InsertDataIntoSQLiteDatabase() {

        if (EditTextEmptyHold == true) {

            SQLiteDataBaseQueryHolder = "INSERT INTO " + SQLiteHelper.TABLE_NAME + " (name,phone_number,registartion,nameofowner,contact) VALUES('" + NameHolder + "', '" + NumberHolder + "', '" + RegistrationHolder + "', '" + OwnerHolder + "', '" + ContactHolder + "');";

            sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);

            Toast.makeText(AddProperty.this, "Data Inserted Successfully", Toast.LENGTH_LONG).show();

        } else {

            Toast.makeText(AddProperty.this, "Please Fill All The Required Fields.", Toast.LENGTH_LONG).show();

        }

    }

    public void EmptyEditTextAfterDataInsert() {

        editTextName.getText().clear();

        editTextPhoneNumber.getText().clear();

        editTextnameofowner.getText().clear();

        editTextContact.getText().clear();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        RegistrationHolder = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + RegistrationHolder, Toast.LENGTH_LONG).show();

    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

}