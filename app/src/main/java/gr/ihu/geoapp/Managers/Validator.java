package gr.ihu.geoapp.Managers;

import android.content.Context;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static final String NAME_PATTERN = "^[a-zA-Z]+$";
    private static final String EMAIL_PATTERN =
            "^[A-Za-z0-9\\+\\_.\\-]+@(gmail|outlook|yahoo|hotmail)\\.(com|gr)$";
    private static final String PASSWORD_PATTERN =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    public static boolean validateName(EditText fullNameEditText) {
        String Input = (fullNameEditText != null) ? fullNameEditText.getText().toString():"";
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        Matcher matcher = pattern.matcher(Input);

        if (Input.isEmpty()) {
            fullNameEditText.setError("Full name is Required");
            return false;
        } else if (!matcher.matches()) {
            fullNameEditText.setError("Give your full name without numbers");
            return false;
        } else {
            fullNameEditText.setError(null);
            return true;
        }
    }

    public static boolean validateEmail(EditText emailEditText){
        String Input = (emailEditText != null) ? emailEditText.getText().toString().trim() : "";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(Input);

        if (Input.isEmpty()) {
            emailEditText.setError("Email is Required");
            return false;
        } else if (!matcher.matches()) {
            emailEditText.setError("Email must be someone@example.com");
            return false;
        } else {
            emailEditText.setError(null);
            return true;
        }
    }

    public static boolean validatePassword(EditText passwordEditText){
        String Input = (passwordEditText != null) ? passwordEditText.getText().toString().trim():"";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(Input);

        if (Input.isEmpty()) {
            passwordEditText.setError("Password is Required");
            return false;
        } else if (!matcher.matches()) {
            passwordEditText.setError("Password must be 8 or more characters and must be a lowercase letter, an uppercase letter, a number and a symbol (!@#$%&)");
            return false;
        } else {
            passwordEditText.setError(null);
            return true;
        }
    }
}
