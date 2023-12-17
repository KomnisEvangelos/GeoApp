package gr.ihu.geoapp.ui.dashboard;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import gr.ihu.geoapp.R;
import gr.ihu.geoapp.databinding.FragmentDashboardBinding;
import gr.ihu.geoapp.databinding.DataLayoutBinding;

public class DashboardFragment extends Fragment {
    private FragmentDashboardBinding binding;
    private DataLayoutBinding dataLayoutBinding;
    private ImageView imageView;
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