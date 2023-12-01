package gr.ihu.geoapp.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import gr.ihu.geoapp.R;

public class BottomSheetButtonsFragment extends BottomSheetDialogFragment {

    private static DashboardFragment dashboardFragment;

    public static BottomSheetButtonsFragment newInstance(DashboardFragment dashboardFragment1) {
        dashboardFragment= dashboardFragment1;
        return new BottomSheetButtonsFragment();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_layout, container, false);

        Button galleryButton = view.findViewById(R.id.buttonGallery);
        galleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, DashboardFragment.GALLERY_REQUEST_CODE);
            }
        });

        Button cameraButton = view.findViewById(R.id.buttonCamera);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, DashboardFragment.CAMERA_REQUEST_CODE);
            }
        });

        return view;


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        dashboardFragment.onActivityResult(requestCode,resultCode,data);

    }

}
