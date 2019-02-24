package cc.catface.api.view.demo102_path_text;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
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
