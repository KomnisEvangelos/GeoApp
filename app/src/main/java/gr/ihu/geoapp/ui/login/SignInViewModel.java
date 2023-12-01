package gr.ihu.geoapp.ui.login;

import android.text.TextUtils;
import android.widget.EditText;

import androidx.lifecycle.ViewModel;

public class SignInViewModel extends ViewModel {
    EditText mEmail,mPassword;
    public boolean isSignInDataValid(String email,String password){

        if (TextUtils.isEmpty(email)) {
            mEmail.setError("Email is required");
            //return;
        }
        if (TextUtils.isEmpty(password)) {
            mPassword.setError("Password is required");
            //return;
        }
        if (password.length() < 8) {
            mPassword.setError("Password must be 8 or more characters");
            //return;
        }
        return true;
    }

    public void performSignIn(String email, String password){
        //connect
    }
}
