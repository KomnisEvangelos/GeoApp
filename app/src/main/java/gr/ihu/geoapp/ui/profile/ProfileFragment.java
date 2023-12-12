package gr.ihu.geoapp.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

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

        // Accessing views using View Binding
        profileImageView = binding.profileImageView;
        nameTextView = binding.nameTextView;
        emailTextView = binding.emailTextView;
        birthdayTextView = binding.birthdayTextView;
        professionTextView = binding.professionTextView;
        diplomaTextView = binding.diplomaTextView;

        // Dummy data
        String dummyName = "John Doe";
        String dummyEmail = "john.doe@example.com";
        String dummyBirthday = "January 1, 1990";
        String dummyOccupation = "Software Developer";
        String dummyDiploma = "Computer Science";
        String dummyProfilePictureName = "baseline_person_24";

        // Update UI with dummy data
        nameTextView.setText(dummyName);
        emailTextView.setText(dummyEmail);
        birthdayTextView.setText(dummyBirthday);
        professionTextView.setText(dummyOccupation);
        diplomaTextView.setText(dummyDiploma);


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

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}