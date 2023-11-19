package gr.ihu.geoapp.ui.dashboard;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;

import gr.ihu.geoapp.Managers.UploadManager;
import gr.ihu.geoapp.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {
    private FragmentDashboardBinding binding;
    private ImageView imageView;
    public static final int GALLERY_REQUEST_CODE = 1000;
    private static final int CAMERA_REQUEST_CODE = 2000;
    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final ImageView imageView = binding.image;

        dashboardViewModel.getImagePath().observe(getViewLifecycleOwner(), imagePath -> {
            if (imagePath != null && !imagePath.isEmpty()) {
                Glide.with(requireContext()).load(imagePath).into(imageView);
            }
        });

        dashboardViewModel.getImageBitmap().observe(getViewLifecycleOwner(), imageBitmap -> {
            if (imageBitmap != null) {
                imageView.setImageBitmap(imageBitmap);

            }
        });

        Button galleryButton = binding.buttonGallery;
        galleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);
            }
        });

        Button cameraButton = binding.buttonCamera;
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            dashboardViewModel.setImagePath(selectedImageUri.toString());
        } else if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
                if (data != null) {
                    Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
                    dashboardViewModel.setImageBitmap(imageBitmap);
                }
        }

    }


}