package gr.ihu.geoapp.ui.dashboard;

import android.net.Uri;
import android.widget.ImageView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import gr.ihu.geoapp.Models.Image;

public class DashboardViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private MutableLiveData<String> imagePath = new MutableLiveData<>();

    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");

    }

    public LiveData<String> getText() {
        return mText;
    }

    public void setImagePath(String path) {
        imagePath.setValue(path);
    }

    public LiveData<String> getImagePath() {
        return imagePath;
    }
}