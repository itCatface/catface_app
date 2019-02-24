package cc.catface.api.view.demo101_paint_canvas;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import cc.catface.api.R;

public class CanvasActivity extends AppCompatActivity {

    private FrameLayout fl;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.api_activity_canvas);
        fl = (FrameLayout) findViewById(R.id.fl);
        fl.addView(new CustomView(this, null));
    }
}
