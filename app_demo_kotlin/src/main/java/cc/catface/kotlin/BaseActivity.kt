package cc.catface.kotlin

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity {

    private var mLayoutId: Int

    constructor(layoutId: Int) {
        mLayoutId = layoutId;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mLayoutId);
        create()
    }

    abstract fun create()
}