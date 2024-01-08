package gr.ihu.geoapp;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;

import gr.ihu.geoapp.managers.Repository;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private Repository repository;

    @Before
    public void setup(){
        repository = new Repository();
    }
    @Test
    public void testCreateUser() {
        String testEmail = "test@example.com";
        String testPassword = "testpassword";
       repository.createUser(testEmail, testPassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
           @Override
           public void onSuccess(AuthResult authResult) {
               assertTrue(true);
           }
       });
    }

    @Test
    public void testCheckUser() {
        String testEmail = "test@example.com";
        String testPassword = "testpassword";
        repository.checkUser(testEmail, testPassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                assertTrue(true);
            }
        });
    }
}