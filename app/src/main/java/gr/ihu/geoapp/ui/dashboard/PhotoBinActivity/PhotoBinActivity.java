package gr.ihu.geoapp.ui.dashboard.PhotoBinActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import com.bumptech.glide.Glide;

import gr.ihu.geoapp.R;
import gr.ihu.geoapp.ui.dashboard.DashboardFragment;


public class PhotoBinActivity extends AppCompatActivity {
    private GridView gridView;
    private ArrayList<String> imagePaths;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_bin_fragment_layout);

        gridView = findViewById(R.id.gridView);
        imagePaths = getTodayImages();

        ImageAdapter imageAdapter = new ImageAdapter();
        gridView.setAdapter(imageAdapter);
    }

    private ArrayList<String> getTodayImages() {
        ArrayList<String> paths = new ArrayList<>();
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Images.Media.DATA, MediaStore.Images.Media.DATE_TAKEN};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, MediaStore.Images.Media.DATE_TAKEN + " DESC");

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String imagePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                long dateTaken = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_TAKEN));

                if (isToday(dateTaken)) {
                    paths.add(imagePath);
                }
            }
            cursor.close();
        }

        return paths;
    }

    private boolean isToday(long dateTaken) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        String todayDate = sdf.format(new Date());
        String imageDate = sdf.format(new Date(dateTaken));

        return todayDate.equals(imageDate);
    }

    private class ImageAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return imagePaths.size();
        }

        @Override
        public Object getItem(int position) {
            return imagePaths.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(PhotoBinActivity.this);
                imageView.setLayoutParams(new GridView.LayoutParams(300, 300));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClick(position);
                    }
                });
            } else {
                imageView = (ImageView) convertView;
            }

            Glide.with(PhotoBinActivity.this)
                    .load(imagePaths.get(position))
                    .into(imageView);

            return imageView;
        }

        private void onItemClick(int position) {
            String selectedImagePath = imagePaths.get(position);

            Intent resultIntent = new Intent();
            resultIntent.setData(Uri.parse(selectedImagePath));
            setResult(Activity.RESULT_OK, resultIntent);

            finish();

        }





    }
}

