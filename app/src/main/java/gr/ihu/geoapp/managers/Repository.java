package gr.ihu.geoapp.managers;

import com.google.firebase.auth.FirebaseAuth;

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
}
