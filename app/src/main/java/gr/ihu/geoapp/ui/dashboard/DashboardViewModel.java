package gr.ihu.geoapp.ui.dashboard;

import android.graphics.Bitmap;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> imagePath = new MutableLiveData<>();
    private MutableLiveData<Bitmap> imageBitmap = new MutableLiveData<>();

    public DashboardViewModel() {

    }

    public void setImagePath(String path) {
        imagePath.setValue(path);
    }
    public LiveData<Bitmap> getImageBitmap(){return imageBitmap; }

    public void setImageBitmap(Bitmap bitmap){ imageBitmap.setValue(bitmap); }

    public LiveData<String> getImagePath() {
        return imagePath;
    }
}