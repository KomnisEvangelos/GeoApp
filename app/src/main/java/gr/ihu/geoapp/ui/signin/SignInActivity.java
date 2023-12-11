package gr.ihu.geoapp.ui.signin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;

import gr.ihu.geoapp.MainActivity;
import gr.ihu.geoapp.managers.Validator;
import gr.ihu.geoapp.models.users.RegularUser;
import gr.ihu.geoapp.databinding.ActivitySignInBinding;
import gr.ihu.geoapp.ui.signup.RegisterActivity;


public class SignInActivity extends AppCompatActivity{

    private ActivitySignInBinding binding;
    private EditText emailEditText;
    private EditText passwordEditText;

    private SignInViewModel signInViewModel;
    private Validator signInValidator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        signInViewModel = new ViewModelProvider(this).get(SignInViewModel.class);

        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        emailEditText = binding.emailEditText;
        passwordEditText = binding.passwordEditText;

        signInValidator = new Validator();


        Button signinButton = binding.buttonSignin;
        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                if (signInValidator.isSignInDataValid(emailEditText,passwordEditText)){
                    RegularUser user = RegularUser.getInstance();
                    user.setEmail(email);
                    user.setPassword(password);

                    user.login().addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(getApplicationContext(),"Login successful",Toast.LENGTH_SHORT).show();
                            navigateToMainActivity();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"Login failed. Try again.",Toast.LENGTH_SHORT).show();
                            Log.d("FirebaseError",e.getMessage());
                        }
                    });

                }else{
                    Toast.makeText(getApplicationContext(),"All fields are required",Toast.LENGTH_SHORT).show();

                }
            }
        });

        Button signupButton = binding.buttonSignup;
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToRegisterActivity();
            }
        });
    }

    private void navigateToMainActivity(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void navigateToRegisterActivity(){
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
        finish();
    }

}