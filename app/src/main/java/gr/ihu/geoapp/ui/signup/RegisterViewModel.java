package gr.ihu.geoapp.ui.signup;

import android.widget.EditText;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegisterViewModel extends ViewModel {

    private MutableLiveData<Boolean> registerResult = new MutableLiveData<>();

    public LiveData<Boolean> getRegisterResult(){
        return registerResult;
    }

    public void registerUser(EditText fullnameEditText,EditText emailEditText,EditText passwordEditText){
        boolean registrationSuccessful = performRegistration(fullnameEditText,emailEditText,passwordEditText);

        registerResult.setValue(registrationSuccessful);
    }

    private boolean performRegistration(EditText fullNameEditText,EditText emailEditText,EditText passwordEditText){
        return true;
    }
}
