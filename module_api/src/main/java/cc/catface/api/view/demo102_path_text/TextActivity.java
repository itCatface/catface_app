package cc.catface.api.view.demo102_path_text;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import cc.catface.api.R;

public class TextActivity extends AppCompatActivity {

    private FrameLayout fl;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.api_activity_text);
        fl = (FrameLayout) findViewById(R.id.fl);
        fl.addView(new CustomView(this, null));
    }
}
