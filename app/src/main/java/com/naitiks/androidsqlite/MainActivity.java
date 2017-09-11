package com.naitiks.androidsqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inserting Contact
        Log.d("Insert: ", "Inserting");
        ContactCRUD crud = new ContactCRUD(this);
        crud.addContact(new ContactModel("Naitik", "10000000"));
        crud.addContact(new ContactModel("Mayur", "20000000"));

        //reading contact
        List<ContactModel> contactList = crud.getAllContacts();
        for(ContactModel one : contactList){
            String logString = "Id: " + one.get_id() +" Name: "+ one.getName() +" Phone: "+one.getPhoneNo();
            Log.d("Reading: ", logString);
        }


    }
}
