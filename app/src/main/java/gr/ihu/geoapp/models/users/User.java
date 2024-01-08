package gr.ihu.geoapp.models.users;

public interface User {

    public com.google.android.gms.tasks.Task<com.google.firebase.auth.AuthResult> login();
    public com.google.android.gms.tasks.Task<com.google.firebase.auth.AuthResult> register();
    public void logout();
    public void fetchData();

}
