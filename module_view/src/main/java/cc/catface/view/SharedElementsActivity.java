package cc.catface.view;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 共享元素 效果
 */
public class SharedElementsActivity extends AppCompatActivity {

    //分享元素1按钮
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_elements);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(v->{
            startActivity(new Intent(this, MainActivity.class),
                    ActivityOptions.makeSceneTransitionAnimation
                            (this, button, "myButton1")
                            .toBundle());
        });
    }
}
