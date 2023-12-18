package gr.ihu.geoapp.managers;

import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import gr.ihu.geoapp.models.users.RegularUser;

public class Repository {
    private FirebaseAuth auth;


    public Repository() {
        auth = FirebaseAuth.getInstance();
    }

    public com.google.android.gms.tasks.Task<com.google.firebase.auth.AuthResult> createUser(String email, String password){

        return auth.createUserWithEmailAndPassword(email,password);
    }

    public com.google.android.gms.tasks.Task<com.google.firebase.auth.AuthResult> checkUser(String email, String password){

        return auth.signInWithEmailAndPassword(email,password);
    }

    public void uploadUserData(EditText fullNameEditText,EditText emailEditText,EditText birthDateEditText,EditText professionEditText,EditText diplomaEditText){
        String fullName = fullNameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String birthDate = birthDateEditText.getText().toString().trim();
        String profession = professionEditText.getText().toString().trim();
        String diploma = diplomaEditText.getText().toString().trim();

        RegularUser user = RegularUser.getInstance();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setDateOfBirth(birthDate);
        user.setProfession(profession);
        user.setDiploma(diploma);

        FirebaseDatabase.getInstance().getReference("users").child(auth.getUid()).setValue(user);
    }

    public String getID(){
        return auth.getUid();
    }
}
