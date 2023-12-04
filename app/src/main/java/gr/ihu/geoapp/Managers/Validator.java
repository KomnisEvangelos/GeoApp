package gr.ihu.geoapp.Managers;

import android.renderscript.ScriptGroup;
import android.text.TextUtils;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class Validator {

    private TextInputLayout emailTextInput,passwordTextInput;
    private EditText emailEditText;
    private EditText passwordEditText;
    public boolean isSignInDataValid(EditText emailEditText,EditText passwordEditText){

        if (TextUtils.isEmpty(emailEditText.getText())) {
            emailEditText.setError("Email is required");
            //return;
        }
        if (TextUtils.isEmpty(passwordEditText.getText())) {
            passwordEditText.setError("Password is required");
            //return;
        }
        return false;
    }

    
}
