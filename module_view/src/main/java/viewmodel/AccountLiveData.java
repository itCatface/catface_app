package viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;

public class AccountLiveData extends LiveData<String> {

    private static final class Holder {
        private static final AccountLiveData instance =new AccountLiveData();
    }

    public static AccountLiveData getInstance() {
        return Holder.instance;
    }

    private AccountLiveData() {

    }

    public void set(String account) {
        Log.d("catface", getClass().getName()+"-->set: " + account);
        setValue(account);
    }



}
