package cc.catface.api.databinding;

import android.content.Context;
import android.view.View;

import java.util.Date;

import cc.catface.ctool.system.TLog;
import cc.catface.base.utils.android.common_print.toast.TToast;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class DataBindingHandler {

    public void click() {
        TLog.d("root", "点击了: " + System.currentTimeMillis());
    }


    public void showToast(View view) {
        TToast.get(view.getContext()).showBShortView(new Date().toString(), TToast.B_INFO);
    }

    public void startTask(ToastTask task) {
        task.run();
    }


    public class ToastTask implements Runnable {
        private Context mCtx;

        public ToastTask(Context ctx) {
            this.mCtx = ctx;
        }

        @Override public void run() {
            TToast.get(mCtx).showBShortView("任务方法处理：" + new Date().toLocaleString(), TToast.B_WARNING);
        }
    }

}
