package com.andreiciubotaru.holidayplanner.controller.BusinessLayer;

import com.andreiciubotaru.holidayplanner.controller.DataLayer.Users;
import com.andreiciubotaru.holidayplanner.model.User;

public class Login {
    private User u;
    private String email;
    private String password;

    public void setUser(String email, String password) {
        this.email = email;
        this.password = password;
        this.u = Users.getUserDataByEmail(email);
    }

    private boolean userExists() {
        if (u.getEmail() != null)
            return true;
        return false;
    }

    public boolean canLogin() {
        try {
            if (u.getMustResetPassword()) {
                ForgotPassword.resetPassword(u);
            } else if (userExists() && Users.getUserDataByEmail(email).getPassword().equals(password))
                return true;
        } catch (Exception e) {
            System.out.println("Email or password not correct!\nReason: " + e.getMessage());
        }
        return false;
    }

    /*public static void main(String[] args) {
    }*/
}