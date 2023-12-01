package gr.ihu.geoapp.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import gr.ihu.geoapp.R;
import gr.ihu.geoapp.databinding.ActivitySignInBinding;


public class SignIn extends AppCompatActivity{

    private ActivitySignInBinding binding;

    EditText mEmail, mPassword;

    private SignInViewModel signInViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mEmail = binding.Email;
        mPassword = binding.Password;

        signInViewModel = new ViewModelProvider(this).get(SignInViewModel.class);


        Button signinButton = binding.buttonSignin;
        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (signInViewModel.isSignInDataValid(email,password)){
                    signInViewModel.performSignIn(email,password);
                }else{
                    System.out.println("Error with email or password. Try again.");
                }
            }

        });

        Button signupButton = binding.buttonSignup;
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getApplicationContext().Register.class));
            }
        });
    }

}