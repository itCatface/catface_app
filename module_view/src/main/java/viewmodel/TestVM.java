package viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.Map;

public class TestVM extends ViewModel {

    private MutableLiveData<String> mNameEvent = new MutableLiveData<>();
    private MutableLiveData<Map<String, Integer>> mMap = new MutableLiveData<>();

    public MutableLiveData<String> getNameEvent() {
        return mNameEvent;
    }

    public MutableLiveData<Map<String, Integer>> getMap() {
        return mMap;
    }

    /* 传入参数 */
    private String mKey;

    public TestVM(String key) {
        mKey = key;
    }

    public static class Factory implements ViewModelProvider.Factory {
        private String mKey;

        public Factory(String key) {
            mKey = key;
        }

        @NonNull @Override public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new TestVM(mKey);
        }
    }

    public String getKey() {
        return mKey;
    }

}
