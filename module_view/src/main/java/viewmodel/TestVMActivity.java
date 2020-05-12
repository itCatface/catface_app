package viewmodel;

import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.Random;

import cc.catface.view.R;
import cc.catface.view.databinding.ActivityTestVmBinding;

public class TestVMActivity extends AppCompatActivity {

    private ActivityTestVmBinding mBinding;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_test_vm);


        mBinding.setActions(new Actions());

        //        TestVM testVM = new ViewModelProvider(this).get(TestVM.class);
        //        MutableLiveData<String> nameEvent = testVM.getNameEvent();
        //        nameEvent.observe(this, new Observer<String>() {
        //            @Override public void onChanged(String s) {
        //                Toast.makeText(TestVMActivity.this, "---" + s, Toast.LENGTH_SHORT).show();
        //            }
        //        });
        //
        //        findViewById(R.id.btChange).setOnClickListener(v -> {
        //
        //
        //
        //            //            testVM.getNameEvent().setValue(System.currentTimeMillis() + " || " + Thread.currentThread());
        //            new Thread(new Runnable() {
        //                @Override public void run() {
        //                    testVM.getNameEvent().postValue(System.currentTimeMillis() + " || " + Thread.currentThread());
        //                }
        //            }).start();
        //        });


        TestVM testVM = new ViewModelProvider(this, new TestVM.Factory("000")).get(TestVM.class);

        mBinding.setVm(testVM);

//        testVM.getMap().setValue();

        MutableLiveData<String> nameEvent = testVM.getNameEvent();
        String key = testVM.getKey();
        nameEvent.observe(this, new Observer<String>() {
            @Override public void onChanged(String s) {
                Toast.makeText(TestVMActivity.this, key + "------" + s, Toast.LENGTH_SHORT).show();

            }
        });

        findViewById(R.id.btChangeWithArg).setOnClickListener(v -> {

            testVM.getNameEvent().setValue(SystemClock.currentThreadTimeMillis() + "arg...");


        });


        AccountLiveData.getInstance().observe(this, new Observer<String>() {
            @Override public void onChanged(String s) {
                Log.d("catface", "test vm activity-->onChanged: " + s);
            }
        });


    }


    /**
     * AppCompatActivity已经实现了LifecycleOwner所以不需要手动移除observer观察
     */
    @Override protected void onDestroy() {
        super.onDestroy();
        Log.d("catface", getClass().getName() + "-->onDestroy");
        AccountLiveData.getInstance().removeObservers(this);
    }
}
