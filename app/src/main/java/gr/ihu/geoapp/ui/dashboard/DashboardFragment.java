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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import gr.ihu.geoapp.Managers.UploadManager;
import gr.ihu.geoapp.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {
    private FragmentDashboardBinding binding;
    private ImageView imageView;
    public static final int GALLERY_REQUEST_CODE = 1000;
    private static final int CAMERA_REQUEST_CODE = 2000;
    private DashboardViewModel dashboardViewModel;
    private ChipGroup chipGroup;
    private String currentDescription = "";
    private String currentTitle = "";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final ImageView imageView = binding.image;
        chipGroup = binding.chipGroup;
//       // chipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(ChipGroup group, int checkedId) {
//
//                Chip selectedChip = findViewById(checkedId);
//                if (selectedChip != null) {
//                    String category = selectedChip.getText().toString();
//
//                }
//            }
//        });

        Button addButton= binding.addButton;
        EditText tagEditText= binding.tagEditText;
        EditText descrEditText = binding.descrEditText;
        EditText titleEditText = binding.titleEditText;
        Button addDescrBtn = binding.addDescrBtn;
        Button saveDescrBtn = binding.saveDescrBtn;
        Button editDescrBtn = binding.editDescrBtn;
        Button addTitleBtn = binding.addTitleBtn;
        Button saveTitleBtn = binding.saveTitleBtn;
        Button editTitleBtn = binding.editTitleBtn;

        addTitleBtn.setVisibility(View.GONE);
        addDescrBtn.setVisibility(View.GONE);
        saveDescrBtn.setVisibility(View.GONE);
        editDescrBtn.setVisibility(View.GONE);
        saveTitleBtn.setVisibility(View.GONE);
        editTitleBtn.setVisibility(View.GONE);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTag = tagEditText.getText().toString().trim();
                if (!newTag.isEmpty()) {

                    Chip newChip = new Chip(getContext());
                    newChip.setText(newTag);
                    chipGroup.addView(newChip);


                    tagEditText.setText("");
                }
            }
        });

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

        addDescrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                descrEditText.setVisibility(View.VISIBLE);
                saveDescrBtn.setVisibility(View.VISIBLE);
                editDescrBtn.setVisibility(View.GONE);
                addDescrBtn.setVisibility(View.GONE);
            }
        });

        addTitleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titleEditText.setVisibility(View.VISIBLE);
                saveTitleBtn.setVisibility(View.VISIBLE);
                editTitleBtn.setVisibility(View.GONE);
                addTitleBtn.setVisibility(View.GONE);
            }
        });

        saveDescrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentDescription = descrEditText.getText().toString();
                descrEditText.setText(currentDescription);
                descrEditText.setEnabled(false);
                descrEditText.setTextIsSelectable(true);
                descrEditText.setVisibility(View.VISIBLE);
                saveDescrBtn.setVisibility(View.GONE);
                editDescrBtn.setVisibility(View.VISIBLE);
                addDescrBtn.setVisibility(View.GONE);
            }
        });

        saveTitleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentTitle = titleEditText.getText().toString();
                titleEditText.setText(currentDescription);
                titleEditText.setEnabled(false);
                titleEditText.setTextIsSelectable(true);
                titleEditText.setVisibility(View.VISIBLE);
                saveTitleBtn.setVisibility(View.GONE);
                editTitleBtn.setVisibility(View.VISIBLE);
                addTitleBtn.setVisibility(View.GONE);
            }
        });

        editDescrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                descrEditText.setEnabled(true);
                descrEditText.setFocusable(true);
                descrEditText.setClickable(true);
                descrEditText.requestFocus();
                saveDescrBtn.setVisibility(View.VISIBLE);
                editDescrBtn.setVisibility(View.GONE);
            }
        });

        editTitleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titleEditText.setEnabled(true);
                titleEditText.setFocusable(true);
                titleEditText.setClickable(true);
                titleEditText.requestFocus();
                saveTitleBtn.setVisibility(View.VISIBLE);
                editTitleBtn.setVisibility(View.GONE);
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
            binding.addTitleBtn.setVisibility(View.VISIBLE);
            binding.addDescrBtn.setVisibility(View.VISIBLE);
        } else if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
                if (data != null) {
                    Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
                    dashboardViewModel.setImageBitmap(imageBitmap);
                    binding.addTitleBtn.setVisibility(View.VISIBLE);
                    binding.addDescrBtn.setVisibility(View.VISIBLE);
                }
        }

    }


}