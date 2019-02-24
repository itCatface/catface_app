package cc.catface.api.view.demo104_bezier;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import cc.catface.api.R;


public class WaveActivity extends AppCompatActivity {
    private cc.catface.api.view.demo104_bezier.Wave1View w1v;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.api_activity_wave);
        w1v = (cc.catface.api.view.demo104_bezier.Wave1View) findViewById(R.id.w1v);

        findViewById(R.id.bt_startX).setOnClickListener(view -> w1v.startAnimX());
        findViewById(R.id.bt_startY).setOnClickListener(view -> w1v.startAnimY());
    }
}
