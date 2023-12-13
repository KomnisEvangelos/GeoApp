package gr.ihu.geoapp.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Optional;

import gr.ihu.geoapp.databinding.FragmentProfileBinding;
import gr.ihu.geoapp.models.users.RegularUser;
import gr.ihu.geoapp.ui.signin.SignInActivity;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private ImageView profileImageView;
    private TextView nameTextView;
    private TextView emailTextView;
    private TextView birthdayTextView;
    private TextView professionTextView;
    private TextView diplomaTextView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        profileImageView = binding.profileImageView;
        nameTextView = binding.nameTextView;
        emailTextView = binding.emailTextView;
        birthdayTextView = binding.birthdayTextView;
        professionTextView = binding.professionTextView;
        diplomaTextView = binding.diplomaTextView;

        RegularUser user = RegularUser.getInstance();


        nameTextView.setText(Optional.ofNullable(user.getFullName()).orElse("No data found"));
        emailTextView.setText(Optional.ofNullable(user.getEmail()).orElse("No data found"));
        birthdayTextView.setText(Optional.ofNullable(user.getDateOfBirth()).orElse("No data found"));
        professionTextView.setText(Optional.ofNullable(user.getProfession()).orElse("No data found"));
        diplomaTextView.setText(Optional.ofNullable(user.getDiploma()).orElse("No data found"));

        Button logoutButton = binding.logoutButton;
        logoutButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                RegularUser.getInstance().logout();
                Intent intent = new Intent(getActivity(), SignInActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        FirebaseAuth.getInstance().addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    Toast.makeText(getActivity(), "User is logged out", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}