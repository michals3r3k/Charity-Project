package pl.coderslab.charity.utils;

import pl.coderslab.charity.user.User;

public interface PasswordUtils {
    boolean checkOldPassword(User user, String oldPassword);
    boolean isPasswordMatches(String newPassword, String confirmPassword);
}
