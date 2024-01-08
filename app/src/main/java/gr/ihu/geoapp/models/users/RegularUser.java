package gr.ihu.geoapp.models.users;



import com.google.firebase.auth.FirebaseAuth;

import gr.ihu.geoapp.managers.Repository;

public class RegularUser implements User{

    private static RegularUser single_instance = null;
    private String fullName = null;
    private String email = null;
    private String password = null;
    private String dateOfBirth = null;
    private String profession = null;
    private String Diploma = null;


    public static synchronized RegularUser getInstance()
    {
        if (single_instance == null)
            single_instance = new RegularUser();

        return single_instance;
    }

    private RegularUser(){

    }

    @Override
    public com.google.android.gms.tasks.Task<com.google.firebase.auth.AuthResult> login() {
        Repository repository = new Repository();

        return repository.checkUser(email,password);
    }

    @Override
    public com.google.android.gms.tasks.Task<com.google.firebase.auth.AuthResult> register() {

        Repository repository = new Repository();

        return repository.createUser(email,password);
    }

    @Override
    public void logout() {
        FirebaseAuth.getInstance().signOut();
    }


    @Override
    public void fetchData() {

    }

    //Getters and Setters
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getDiploma() {
        return Diploma;
    }

    public void setDiploma(String diploma) {
        Diploma = diploma;
    }


}