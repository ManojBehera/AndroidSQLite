    package com.naitiks.androidsqlite;

    /**
     * Created by Naitik on 9/9/2017.
     */

    public class ContactModel {
        //private varibles
        int _id;
        String name;
        String phoneNo;

        // Empty Constructor
        public ContactModel() {}

        // Constructor
        public ContactModel(String name, String phoneNo) {
            this.name = name;
            this.phoneNo = phoneNo;
        }

        // Constructor
        public ContactModel(int _id, String name, String phoneNo) {
            this._id = _id;
            this.name = name;
            this.phoneNo = phoneNo;
        }

        public int get_id() {
            return _id;
        }

        public void set_id(int _id) {
            this._id = _id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoneNo() {
            return phoneNo;
        }

        public void setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
        }
    }
