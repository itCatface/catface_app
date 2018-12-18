package cc.catface.api.view.demo104_bezier;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cc.catface.api.R;

public class BezierMainActivity extends AppCompatActivity {
    private cc.catface.api.view.demo104_bezier.PaintView pv;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.api_activity_bezier_main);
        pv = (cc.catface.api.view.demo104_bezier.PaintView) findViewById(R.id.pv);

        findViewById(R.id.bt_reset).setOnClickListener(view -> pv.reset());
        findViewById(R.id.bt_paint2).setOnClickListener(view -> startActivity(new Intent(this, cc.catface.api.view.demo104_bezier.PaintBezierActivity.class)));
        findViewById(R.id.bt_wave).setOnClickListener(view -> startActivity(new Intent(this, cc.catface.api.view.demo104_bezier.WaveActivity.class)));
    }
}
