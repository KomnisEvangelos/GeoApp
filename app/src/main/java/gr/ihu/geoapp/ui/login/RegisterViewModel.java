package gr.ihu.geoapp.ui.login;

import static gr.ihu.geoapp.ui.login.Register.EmailValidator.validateEmail;
import static gr.ihu.geoapp.ui.login.Register.NameValidator.validateName;
import static gr.ihu.geoapp.ui.login.Register.PasswordValidator.validatePassword;

import android.text.TextUtils;
import android.widget.EditText;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegisterViewModel extends ViewModel {
    EditText mfullName, memail, mpassword, mconPassword;

    public boolean isRegisterDataValid(String fullName, String email, String password, String conPassword) {
        if (TextUtils.isEmpty(fullName) || !validateName(fullName)) {
            mfullName.setError("Full name is Required and must not contained numbers, symbols e.c");
        }
        if (TextUtils.isEmpty(email) || !validateEmail(email)) {
            memail.setError("Email is Required and meet the requirements");
            //return;
        }
        if (TextUtils.isEmpty(password)) {
            mpassword.setError("Password is Required");
            //return;
        }
        if (password.length() < 8 || !validatePassword(password)) {
            mpassword.setError("Password must be 8 or more characters and must be a lowercase letter, an uppercase letter, a number and a symbol (!@#$%&)");
            //return;
        }
        if (password.equals(mconPassword)) {
            mpassword.setText("Password is Confirmed");
            //return;
        } else {
            mpassword.setError("Password do not match");
            //return;
        }
        return true;
    }

    public void performRegister(String fullName, String email, String password, String conPassword){
        //connect to database
    }

}
