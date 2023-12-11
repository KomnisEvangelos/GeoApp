package gr.ihu.geoapp.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import gr.ihu.geoapp.databinding.FragmentProfileBinding;
import gr.ihu.geoapp.ui.profile.ProfileViewModel;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private ImageView profileImageView;
    private TextView nameTextView;
    private TextView emailTextView;
    private TextView birthdayTextView;
    private TextView occupationTextView;

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
        occupationTextView = binding.occupationTextView;

        // Dummy data
        String dummyName = "John Doe";
        String dummyEmail = "john.doe@example.com";
        String dummyBirthday = "January 1, 1990";
        String dummyOccupation = "Software Developer";
        String dummyProfilePictureName = "baseline_person_24";

        // Update UI with dummy data
        nameTextView.setText(dummyName);
        emailTextView.setText(dummyEmail);
        birthdayTextView.setText(dummyBirthday);
        occupationTextView.setText(dummyOccupation);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}