package com.andreiciubotaru.holidayplanner.controller.BusinessLayer;

import com.andreiciubotaru.holidayplanner.model.User;

public class ForgotPassword {
    private static User u;
    private static String oldPassword = u.getPassword();

    public static void resetPassword(User u) {
        //initializeRecoverPassForm();
    }

    private static boolean validateNewPass(String newPass, String repeatNewPass) {
        if (newPass.equals(repeatNewPass))
            return true;
        return false;
    }

    private static void setNewPassword(String oldPass, String newPass, String repeatNewPass) {
        if (oldPassword.equals(oldPass)) {
            if (validateNewPass(newPass, repeatNewPass)) {
                u.setPassword(newPass);
            } else {
                System.out.println("New password doesn't match!");
            }
        } else {
            System.out.println("Old password doesn't match!");
        }
    }
}
