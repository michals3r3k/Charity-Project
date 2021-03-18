package pl.coderslab.charity.security;

public class UserPasswordValidator {
    public static boolean isPasswordValid(String password){
        if(password.length()<8){
            return false;
        }

        if(!isContainsUpperCase(password)){
            return false;
        }

        if(!isContainsLowerCase(password)){
            return false;
        }

        if(!isContainsDigit(password)){
            return false;
        }

        if(!isContainsSpecialChar(password)){
            return false;
        }

        return true;
    }

    private static boolean isContainsUpperCase(String password){
        for(char c : password.toCharArray()){
            if(Character.isUpperCase(c)) return true;
        }
        return false;
    }

    private static boolean isContainsLowerCase(String password){
        for(char c : password.toCharArray()){
            if(Character.isLowerCase(c)) return true;
        }
        return false;
    }

    private static boolean isContainsDigit(String password){
        for(char c : password.toCharArray()){
            if(Character.isDigit(c)) return true;
        }
        return false;
    }

    private static boolean isContainsSpecialChar(String password){
        return password.matches("^.*[ .!@#$%^&*)(><;,\\.\\'].*$");
    }

}
