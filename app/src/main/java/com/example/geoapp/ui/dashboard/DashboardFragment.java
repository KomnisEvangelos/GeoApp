package com.example.geoapp.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.geoapp.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {
    private ImageView imageView;
    private EditText descriptionEditText,titleEditText;
    private Button addDescrButton, saveDescrButton, editDescrButton, addTitleButton, saveTitleButton, editTitleButton;
    private FragmentDashboardBinding binding;
    private String currentDescription = "";
    private String currentTitle = "";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        final ImageView imageView = binding.imageView;

        final EditText descriptionEditText = binding.descriptionEditText;
        final EditText titleEditText = binding.titleEditText;

        final Button addDescrButton = binding.addDescrButton;
        final Button addTitleButton = binding.addTitleButton;

        final Button saveDescrButton = binding.saveDescrButton;
        final Button saveTitleButton = binding.saveTitleButton;
        saveDescrButton.setVisibility(View.GONE);
        saveTitleButton.setVisibility(View.GONE);

        final Button editDescrButton = binding.editDescrButton;
        final Button editTitleButton = binding.ediTitleButton;
        editDescrButton.setVisibility(View.GONE);
        editTitleButton.setVisibility(View.GONE);

        addDescrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                descriptionEditText.setVisibility(View.VISIBLE);
                saveDescrButton.setVisibility(View.VISIBLE);
                editDescrButton.setVisibility(View.GONE); // hide edit button while adding
            }
        });

        addTitleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titleEditText.setVisibility(View.VISIBLE);
                saveTitleButton.setVisibility(View.VISIBLE);
                editTitleButton.setVisibility(View.GONE);
            }
        });

        saveDescrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save the description and make the EditText non-editable
                currentDescription = descriptionEditText.getText().toString();
                descriptionEditText.setText(currentDescription);
                descriptionEditText.setEnabled(false);
                descriptionEditText.setTextIsSelectable(true);
                descriptionEditText.setVisibility(View.VISIBLE);
                saveDescrButton.setVisibility(View.GONE);
                editDescrButton.setVisibility(View.VISIBLE);
                addDescrButton.setVisibility(View.GONE);
            }
        });

        saveTitleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentTitle = titleEditText.getText().toString();
                titleEditText.setText(currentTitle);
                titleEditText.setEnabled(false);
                titleEditText.setTextIsSelectable(true);
                titleEditText.setVisibility(View.VISIBLE);
                saveTitleButton.setVisibility(View.GONE);
                editTitleButton.setVisibility(View.VISIBLE);
                addTitleButton.setVisibility(View.GONE);
            }
        });

        editDescrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                descriptionEditText.setEnabled(true);
                descriptionEditText.setFocusable(true);
                descriptionEditText.setClickable(true);
                descriptionEditText.requestFocus();
                saveDescrButton.setVisibility(View.VISIBLE);
                editDescrButton.setVisibility(View.GONE);
            }
        });

        editTitleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titleEditText.setEnabled(true);
                titleEditText.setFocusable(true);
                titleEditText.setClickable(true);
                titleEditText.requestFocus();
                saveTitleButton.setVisibility(View.VISIBLE);
                editTitleButton.setVisibility(View.GONE);
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