package com.librarymanagement.model;

/**
 * Created by keval on 12-04-2018.
 */

public class LoginModel extends AppModel {

    DetailsData data = new DetailsData();

    public DetailsData getData() {
        return data;
    }

    public class DetailsData {

        String id = "";
        String username = "";
        String password = "";
        String email = "";
        String mobileno = "";

        public String getId() {
            return id;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getEmail() {
            return email;
        }

        public String getMobileno() {
            return mobileno;
        }
    }

}
