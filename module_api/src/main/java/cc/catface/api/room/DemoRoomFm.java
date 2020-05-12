package cc.catface.api.room;

import android.annotation.SuppressLint;
import android.os.Message;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityRoomBinding;
import cc.catface.api.room.domain.Book;
import cc.catface.api.room.domain.Cat;
import cc.catface.api.room.domain.User;
import cc.catface.app_base.TestDataSource;
import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.base.utils.android.common_print.dialog.normal.TDialogNormal;
import cc.catface.ctool.system.TLog;
import cc.catface.base.utils.android.common_recyclerview.TRV;
import cc.catface.ctool.java.TList;
import cc.catface.ctool.system.TWeakHandler;
import cc.catface.ctool.view.recyclerview.ItemClickSupport;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class DemoRoomFm extends LightFm<LightPresenter, ApiActivityRoomBinding> {

    @Override public void handleMessage(Message msg) {
        mAdapter.notifyDataSetChanged();
    }

    @Override public int layoutId() {
        return R.layout.api_activity_room;
    }

    @Override protected void initView() {
        mAdapter = new RoomAdapter(mAllUsers);
        TRV.initDefaultRV(mActivity, mBinding.rvRoom);
        mBinding.rvRoom.setAdapter(mAdapter);
    }

    @Override protected void initHandler() {
        mHandler = new TWeakHandler<>(this);
    }

    private List<User> mAllUsers = new ArrayList<>();

    private RoomAdapter mAdapter;

    @Override protected void initAction() {
        mBinding.btQueryAll.setOnClickListener(this);
        mBinding.btQueryOdd.setOnClickListener(this);
        mBinding.btQueryDesc.setOnClickListener(this);
        mBinding.btQueryXxFirst.setOnClickListener(this);
        mBinding.btInsert.setOnClickListener(this);
        mBinding.btDelete.setOnClickListener(this);

        ItemClickSupport.addTo(mBinding.rvRoom).setOnItemLongClickListener((recyclerView, position, view) -> {
            TDialogNormal.get(mActivity).notification("确认删除？", "您将删除记录：\n" + mAllUsers.get(position).toString() + "\n删除后不可恢复！", "取消", "删除", notificationType -> {
                DBHelper.getInstance().getUserDao().delete(mAllUsers.get(position));
                TList.clearAddAll(mAllUsers, DBHelper.getInstance().getUserDao().allUsers());
                mAdapter.notifyItemRemoved(position);
            });
            return true;
        });
        ItemClickSupport.addTo(mBinding.rvRoom).setOnItemClickListener((recyclerView, position, view) -> {
            int age = new Random().nextInt(100);
            mAllUsers.get(position).setAge(age);
            mAllUsers.set(position, mAllUsers.get(position));
            DBHelper.getInstance().getUserDao().update(age, mAllUsers.get(position).getId());
            mAdapter.notifyItemChanged(position);

            List<Book> books = DBHelper.getInstance().getBookDao().allBook();
            TLog.d("books: " + books.size());

            List<Cat> cats = DBHelper.getInstance().getCatDao().allCat();
            TLog.d("cats: " + cats.size());
        });

        dragAndSwipeRV();
    }

    @SuppressLint("SetTextI18n") @Override public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.bt_query_all) {
            TList.clearAddAll(mAllUsers, DBHelper.getInstance().getUserDao().allUsers());
            mBinding.btQueryAll.setText(DBHelper.getInstance().getUserDao().totalCount() + "查all");
        } else if (id == R.id.bt_query_odd) {
            TList.clearAddAll(mAllUsers, DBHelper.getInstance().getUserDao().oddUsers());
        } else if (id == R.id.bt_query_desc) {
            TList.clearAddAll(mAllUsers, DBHelper.getInstance().getUserDao().descUsers());
        } else if (id == R.id.bt_query_xx_first) {
            TList.clearAddAll(mAllUsers, DBHelper.getInstance().getUserDao().sortUsers());

        } else if (id == R.id.bt_insert) {
            User user = new User();
            user.setName(TestDataSource.words[new Random().nextInt(TestDataSource.words.length)]);
            user.setAge(new Random().nextInt(100));
            DBHelper.getInstance().getUserDao().insert(user);
            TList.clearAddAll(mAllUsers, DBHelper.getInstance().getUserDao().allUsers());
            mBinding.rvRoom.scrollToPosition(mAllUsers.size() - 1);
            mAdapter.notifyItemInserted(mAllUsers.size());
            mHandler.obtainMessage().sendToTarget();
            return;

        } else if (id == R.id.bt_delete) {
            DBHelper.getInstance().getUserDao().deleteAll();
            mAllUsers.clear();
        }
        mBinding.rvRoom.scrollToPosition(0);
        mHandler.obtainMessage().sendToTarget();
    }

    private void dragAndSwipeRV() {
        ItemTouchHelper.Callback callback = new ItemTouchHelper.Callback() {
            /// 返回允许滑动的方向
            @Override public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                int drag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                int swipe = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                return makeMovementFlags(drag, swipe);
            }

            @Override public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                Collections.swap(mAllUsers, from, to);
                mAdapter.notifyItemMoved(from, to);
                return true;
            }

            @Override public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                mAllUsers.remove(position);
                mAdapter.notifyItemRemoved(position);
            }
        };

        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mBinding.rvRoom);
    }

}
