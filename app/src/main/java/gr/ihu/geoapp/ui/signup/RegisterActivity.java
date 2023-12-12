package gr.ihu.geoapp.ui.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;

import gr.ihu.geoapp.managers.Validator;
import gr.ihu.geoapp.models.users.RegularUser;
import gr.ihu.geoapp.databinding.ActivityRegisterBinding;
import gr.ihu.geoapp.ui.signin.SignInActivity;

public class RegisterActivity extends AppCompatActivity {


    private EditText fullNameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText passwordConfirmEditText;
    private EditText birthDateEditText;
    private EditText professionEditText;
    private EditText diplomaEditText;
    ProgressBar progressBar;
    private ActivityRegisterBinding binding;
    private RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        registerViewModel =
                new ViewModelProvider(this).get(RegisterViewModel.class);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        fullNameEditText = binding.fullNameEditText;
        emailEditText = binding.emailEditText;
        passwordEditText = binding.passwordEditText;
        progressBar = binding.progressBar;
        passwordConfirmEditText = binding.conPasswordEditText;
        birthDateEditText = binding.birthDateEditText;
        professionEditText = binding.professionEditText;
        diplomaEditText = binding.diplomaEditText;

        Button registerButton = binding.buttonRegister;
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                if (Validator.validateName(fullNameEditText) && Validator.validateEmail(emailEditText) && Validator.validatePassword(passwordEditText)
                     && Validator.validateBirth(birthDateEditText) && Validator.validateProfession(professionEditText) && Validator.validateDiploma(diplomaEditText)) {
                       //Successful register TODO
                    String newUserEmail = emailEditText.getText().toString().trim();
                    String newUserPassword = passwordEditText.getText().toString().trim();
                    String newUserFullName = fullNameEditText.getText().toString().trim();
                    String newUserBirthDate = birthDateEditText.getText().toString().trim();
                    String newUserProfession = professionEditText.getText().toString().trim();
                    String newUserDiploma = diplomaEditText.getText().toString().trim();

                    RegularUser user = RegularUser.getInstance();

                    user.setEmail(newUserEmail);
                    user.setPassword(newUserPassword);
                    user.setFullName(newUserFullName);
                    user.setDateOfBirth(newUserBirthDate);
                    user.setProfession(newUserProfession);
                    user.setDiploma(newUserDiploma);

                    user.register().addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(getApplicationContext(),"Register Successful",Toast.LENGTH_SHORT).show();
                            navigateToSignInActivity();
                            progressBar.setVisibility(View.GONE);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"Register failed. Try again.",Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);

                        }
                    });

                    navigateToSignInActivity();
                } else {
                    Toast.makeText(getApplicationContext(),"Check for errors",Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);

                }

          }
        });

        Button signInButton = binding.buttonSignIn;
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSignInActivity();
            }
        });
    }

    private void navigateToSignInActivity(){
        Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
        startActivity(intent);
        finish();

    }

}