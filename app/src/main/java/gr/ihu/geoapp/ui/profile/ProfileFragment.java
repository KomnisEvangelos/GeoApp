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

        // Fetch user data from RegularUser instance
        RegularUser user = RegularUser.getInstance();

        // Update UI with fetched data
        nameTextView.setText(user.getFullName());
        emailTextView.setText(user.getEmail());
        birthdayTextView.setText(user.getDateOfBirth());
        professionTextView.setText(user.getProfession());
        diplomaTextView.setText(user.getDiploma());

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