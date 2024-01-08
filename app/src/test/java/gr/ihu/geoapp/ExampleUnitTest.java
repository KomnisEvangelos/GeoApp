package gr.ihu.geoapp;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import gr.ihu.geoapp.managers.Repository;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    Task<AuthResult> successTask;
    Task<AuthResult> failureTask;

    @Mock
    FirebaseAuth mAuth;


    @Before
    public void onBefore() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testVerifyUserLoggedWithValidEmailAndPassword() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String email = "goku@gmail.com";
        String password = "testtest";
        Mockito.when(mAuth.signInWithEmailAndPassword(email, password)).thenReturn(successTask);


    }

}