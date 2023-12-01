package gr.ihu.geoapp.ui.login;

import static gr.ihu.geoapp.ui.login.Register.EmailValidator.validateEmail;
import static gr.ihu.geoapp.ui.login.Register.NameValidator.pattern;
import static gr.ihu.geoapp.ui.login.Register.NameValidator.validateName;
import static gr.ihu.geoapp.ui.login.Register.PasswordValidator.validatePassword;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gr.ihu.geoapp.R;
import gr.ihu.geoapp.databinding.ActivityRegisterBinding;

public class Register extends AppCompatActivity {

    EditText mfullName,memail,mpassword,mconPassword,mbirthDate,mprofession,mdiploma;
    ProgressBar progressBar;
    private ActivityRegisterBinding binding;
    private RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mfullName = binding.fullName;
        memail = binding.email;
        mpassword = binding.password;
        progressBar = binding.progressBar;
        mconPassword = binding.conPassword;
        mbirthDate = binding.birthDate;
        mprofession = binding.profession;
        mdiploma = binding.diploma;

        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        Button registerButton = binding.buttonRegister;
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fullName = mfullName.getText().toString();
                final String email = memail.getText().toString().trim();
                String password = mpassword.getText().toString().trim();
                String conPassword = mconPassword.getText().toString();

                if (registerViewModel.isRegisterDataValid(fullName,email,password,conPassword)){

                    registerViewModel.performRegister(fullName,email,password,conPassword);

                }else{
                    System.out.println("Error while entering data");
                }

                progressBar.setVisibility(View.VISIBLE);

            }
        });

        Button signInButton = binding.buttonSignIn;
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getApplicationContext().SignIn.class));
            }
        });
    }

    public static class NameValidator{

        private static final String NAME_PATTERN = "^[a-zA-Z]+$";
        static final Pattern pattern = Pattern.compile(NAME_PATTERN);

        public static boolean validateName(String fullName) {
            Matcher matcher = pattern.matcher(fullName);
            return matcher.matches();
        }
    }

    public static class EmailValidator{
        private static final String EMAIL_PATTERN =
                "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        static boolean validateEmail(String email){
            Pattern pattern = Pattern.compile(EMAIL_PATTERN);
            Matcher matcher = pattern.matcher(email);
            return matcher.matches();
        }
    }

    public static class PasswordValidator{
        private static final String PASSWORD_PATTERN =
                "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

        public static boolean validatePassword(String password){
            Matcher matcher = pattern.matcher(password);
            return matcher.matches();
        }
    }
}