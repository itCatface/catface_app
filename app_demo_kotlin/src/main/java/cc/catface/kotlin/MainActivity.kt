package cc.catface.kotlin

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(R.layout.activity_main) {


    override fun create() {
        bt.setOnClickListener { tv.text = System.currentTimeMillis().toString() }
    }


}
