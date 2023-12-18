package gr.ihu.geoapp.ui.dashboard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;

import gr.ihu.geoapp.R;
import gr.ihu.geoapp.databinding.FragmentDashboardBinding;
import gr.ihu.geoapp.databinding.DataLayoutBinding;
import gr.ihu.geoapp.managers.Repository;

public class DashboardFragment extends Fragment {
    private FragmentDashboardBinding binding;
    private DataLayoutBinding dataLayoutBinding;
    public static final int GALLERY_REQUEST_CODE = 1000;
    public static final int CAMERA_REQUEST_CODE = 2000;
    private DashboardViewModel dashboardViewModel;
    private ChipGroup chipGroup;
    private String currentDescription = "";
    private String currentTitle = "";




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater,container, false);
        dataLayoutBinding = binding.include;
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
        EditText descrEditText = dataLayoutBinding.descrEditText;
        EditText titleEditText = dataLayoutBinding.titleEditText;
        Button addDescrBtn = dataLayoutBinding.addDescrBtn;
        Button saveDescrBtn = dataLayoutBinding.saveDescrBtn;
        Button editDescrBtn = dataLayoutBinding.editDescrBtn;
        Button addTitleBtn = dataLayoutBinding.addTitleBtn;
        Button saveTitleBtn = dataLayoutBinding.saveTitleBtn;
        Button editTitleBtn = dataLayoutBinding.editTitleBtn;
        Button sendPhotoBtn = binding.sendPhotoBtn;

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

        sendPhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageView != null) {
                    String imagePath = dashboardViewModel.getImagePath().getValue();
                    Bitmap imageBitmap = dashboardViewModel.getImageBitmap().getValue();

                    if (imagePath != null) {
                        uploadUriToFirebase(Uri.parse(imagePath));
                    } else if (imageBitmap != null) {
                        uploadBitmapToFirebase(imageBitmap);
                    } else {
                        Toast.makeText(getContext(), "Please upload a photo", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



        Button uploadButton = binding.buttonUpload;
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetButtonsFragment bottomSheetButtonsFragment = BottomSheetButtonsFragment.newInstance(DashboardFragment.this);
                bottomSheetButtonsFragment.show(getChildFragmentManager(), bottomSheetButtonsFragment.getTag());
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
                editTitleBtn.setVisibility(View.VISIBLE); //editDescrBtn was here
                addDescrBtn.setVisibility(View.GONE);

                descrEditText.setVisibility(View.GONE);
            }
        });

        saveTitleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentTitle = titleEditText.getText().toString();
                titleEditText.setText(currentTitle);
                titleEditText.setEnabled(false);
                titleEditText.setTextIsSelectable(true);
                titleEditText.setVisibility(View.VISIBLE);
                saveTitleBtn.setVisibility(View.GONE);
                editDescrBtn.setVisibility(View.VISIBLE); // editTitlebtn was here
                addTitleBtn.setVisibility(View.GONE);

                titleEditText.setVisibility((View.GONE));
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

                descrEditText.setVisibility(View.VISIBLE);
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

                titleEditText.setVisibility((View.VISIBLE));
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void uploadUriToFirebase(Uri imageUri){
        Repository repository = new Repository();
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("images").child(repository.getID()).child(imageUri.getLastPathSegment());

        storageReference.putFile(imageUri).addOnSuccessListener(taskSnapshot -> {
            Toast.makeText(getContext(), "Photo uploaded", Toast.LENGTH_SHORT).show();
        })
                .addOnFailureListener(e ->{
                    Toast.makeText(getContext(), "Upload failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    private void uploadBitmapToFirebase(Bitmap imageBitmap){
        Uri tempUri = getImageUri(getContext(), imageBitmap);

        if (tempUri != null) {
            Repository repository = new Repository();
            StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("images").child(repository.getID()).child(tempUri.getLastPathSegment());

            storageReference.putFile(tempUri).addOnSuccessListener(taskSnapshot -> {
                Toast.makeText(getContext(), "Photo uploaded", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(e -> {
                Toast.makeText(getContext(), "Upload failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            });
        }
    }

    private Uri getImageUri(Context context, Bitmap bitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "Title", null);
        return Uri.parse(path);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            dashboardViewModel.setImagePath(selectedImageUri.toString());
            dataLayoutBinding.addTitleBtn.setVisibility(View.VISIBLE);
            dataLayoutBinding.addDescrBtn.setVisibility(View.VISIBLE);
            binding.buttonUpload.setVisibility(View.GONE);
        } else if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
                if (data != null) {
                    Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
                    dashboardViewModel.setImageBitmap(imageBitmap);
                    dataLayoutBinding.addTitleBtn.setVisibility(View.VISIBLE);
                    dataLayoutBinding.addDescrBtn.setVisibility(View.VISIBLE);
                    binding.buttonUpload.setVisibility(View.GONE);
                }
        }

    }


}