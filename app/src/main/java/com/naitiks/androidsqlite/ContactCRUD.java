package com.naitiks.androidsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.naitiks.androidsqlite.SQLiteHelper.KEY_ID;
import static com.naitiks.androidsqlite.SQLiteHelper.KEY_NAME;
import static com.naitiks.androidsqlite.SQLiteHelper.KEY_PH_NO;
import static com.naitiks.androidsqlite.SQLiteHelper.TABLE_CONTACTS;

/**
 * Created by Naitik on 9/10/2017.
 */

public class ContactCRUD {
    private SQLiteHelper helper = null;

    public ContactCRUD(Context context){
        helper = SQLiteHelper.getInstance(context);
    }

    // Adding new contact
    public void addContact(ContactModel contact) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PH_NO, contact.getPhoneNo());
        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }

    // Getting single contact
    public ContactModel getContact(int id) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                        KEY_NAME, KEY_PH_NO }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        ContactModel contact = new ContactModel(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        db.close();
        // return contact
        return contact;
    }

    // Getting All Contacts
    public List<ContactModel> getAllContacts() {
        List<ContactModel> contactList = new ArrayList<ContactModel>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ContactModel contact = new ContactModel();
                contact.set_id(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNo(cursor.getString(2));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        db.close();
        // return contact list
        return contactList;
    }

    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        db.close();
        // return count
        return cursor.getCount();
    }
    // Updating single contact
    public int updateContact(ContactModel contact) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PH_NO, contact.getPhoneNo());

        // updating row
        int result = db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.get_id()) });
        db.close();
        return result;
    }

    // Deleting single contact
    public void deleteContact(ContactModel contact) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.get_id()) });
        db.close();
    }
}
