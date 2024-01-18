package com.example.thelastone.Model;

import java.util.List;

public class UserModel {

        private String userId;
        private String Name;
        private String Email;
        private String role;
        private String UserType;

        // Empty constructor
        public UserModel() {

        }

        public UserModel(String userId, String name, String email, String role, String userType) {
            this.userId = userId;
            this.Name = name;
            this.Email = email;
            this.role = role;
            this.UserType = userType;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }
}
