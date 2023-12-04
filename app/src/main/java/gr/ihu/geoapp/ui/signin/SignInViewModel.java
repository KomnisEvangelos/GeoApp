package gr.ihu.geoapp.ui.signin;

import android.widget.EditText;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SignInViewModel extends ViewModel {

    private MutableLiveData<Boolean> signInResult = new MutableLiveData<>();

    public LiveData<Boolean> getSignInResult(){return signInResult;}

    public void signInUser(EditText emailEditText, EditText passwordEditText){
        boolean signInSuccessful = performSignIn(emailEditText, passwordEditText);
        signInResult.setValue(signInSuccessful);
    }

    private boolean performSignIn(EditText emailEditText, EditText passwordEditText){
        return true;
    }
}
