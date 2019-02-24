package cc.catface.api.view.loading;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import cc.catface.api.R;
import cc.catface.api.view.loading.round_progress.RoundProgressActivity;
import cc.catface.base.utils.android.common_intent.TIntent;
import cc.catface.api.view.loading.round_smile.RoundSmileActivity;

public class LoadingIndexActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.api_activity_loading_index);

        findViewById(R.id.bt_android_spinkit).setOnClickListener(view -> TIntent.startActivity(this, SpinKitActivity.class, true));
        findViewById(R.id.bt_round_smile).setOnClickListener(view -> TIntent.startActivity(this, RoundSmileActivity.class, true));
        findViewById(R.id.bt_round_progress).setOnClickListener(v -> TIntent.startActivity(this, RoundProgressActivity.class, true));
    }
}
