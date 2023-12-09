package gr.ihu.geoapp.ui.signup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import gr.ihu.geoapp.Managers.Validator;
import gr.ihu.geoapp.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

//    private TextInputEditText fullNameTextInput;
//    private TextInputEditText emailTextInput;
//    private TextInputEditText passwordTextInput;
//    private TextInputEditText conPasswordTextInput;
//    private TextInputEditText birthDateTextInput;
//    private TextInputEditText professionTextInput;
//    private TextInputEditText diplomaTextInput;
    private EditText fullName;
    private EditText email;
    private EditText password;
    private EditText conPassword;
    private EditText birthDate;
    private EditText profession;
    private EditText diploma;
    ProgressBar progressBar;
    private ActivityRegisterBinding binding;
    private RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_register);

        registerViewModel =
                new ViewModelProvider(this).get(RegisterViewModel.class);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        fullName = binding.fullNameEditText;
        email = binding.emailEditText;
        password = binding.passwordEditText;
        progressBar = binding.progressBar;
        conPassword = binding.conPasswordEditText;
        birthDate = binding.birthDateEditText;
        profession = binding.professionEditText;
        diploma = binding.diplomaEditText;

        Button registerButton = binding.buttonRegister;
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   //final String fullName = fullNameTextInput.getText().toString();
                  //final String email = emailTextInput.getText().toString().trim();
                  //String password = passwordTextInput.getText().toString().trim();
                  // String conPassword = conPasswordTextInput.getText().toString();




                if (Validator.validateName(fullName) && Validator.validateEmail(email) && Validator.validatePassword(password)) {
                       //Successful register TODO
                } else {
                        //Fail register TODO
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

}