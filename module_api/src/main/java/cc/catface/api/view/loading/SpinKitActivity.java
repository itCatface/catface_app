package cc.catface.api.view.loading;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cc.catface.api.R;
import cc.catface.api.view.loading.view.DoubleBounceView;
import cc.catface.api.view.loading.view.RotatingCircleView;
import cc.catface.api.view.loading.view.RotatingPlaneView;
import cc.catface.api.view.loading.view.WanderingCubesViewGroup;

public class SpinKitActivity extends AppCompatActivity {

    private RotatingPlaneView loading_rpv;
    private DoubleBounceView loading_dbv;
    private RotatingCircleView loading_rcv;
    private WanderingCubesViewGroup loading_wcvg;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.api_activity_spin_kit);

        loading_rpv = (RotatingPlaneView) findViewById(R.id.loading_rpv);
        loading_dbv = (DoubleBounceView) findViewById(R.id.loading_dbv);
        loading_rcv = (RotatingCircleView) findViewById(R.id.loading_rcv);
        loading_wcvg = (WanderingCubesViewGroup) findViewById(R.id.loading_wcvg);

        loading_rpv.setOnClickListener(view -> loading_rpv.startAnim());
        loading_dbv.setOnClickListener(view -> loading_dbv.startAnim());
        loading_rcv.setOnClickListener(view -> loading_rcv.startAnim());
        loading_wcvg.setOnClickListener(view -> loading_wcvg.startAnim());
    }
}
