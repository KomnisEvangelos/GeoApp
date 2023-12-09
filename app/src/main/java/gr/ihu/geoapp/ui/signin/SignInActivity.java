package gr.ihu.geoapp.ui.signin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import gr.ihu.geoapp.Managers.Validator;
import gr.ihu.geoapp.databinding.ActivitySignInBinding;


public class SignInActivity extends AppCompatActivity{

    private ActivitySignInBinding binding;

    private TextInputLayout emailTextInput;
    private TextInputLayout passwordTextInput;

    private EditText email;
    private EditText password;

    private SignInViewModel signInViewModel;
    private Validator signInValidator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_sign_in);

        signInViewModel = new ViewModelProvider(this).get(SignInViewModel.class);

        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        email = binding.emailEditText;
        password = binding.passwordEditText;

        signInValidator = new Validator();


        Button signinButton = binding.buttonSignin;
        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String email = email.getText().toString().trim();
                //String password = password.getText().toString().trim();
                        if (signInValidator.isSignInDataValid(email,password)){
                            //succesfull login TODO
                        }else{
                            //Failed login TODO
                        }
                    }
        });

        Button signupButton = binding.buttonSignup;
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getApplicationContext().Register.class)); TODO
            }
        });
    }

}