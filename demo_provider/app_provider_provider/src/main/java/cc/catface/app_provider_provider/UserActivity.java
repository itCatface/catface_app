package cc.catface.app_provider_provider;

import android.annotation.SuppressLint;
import android.os.Message;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import cc.catface.app_provider_provider.dao.DBHelper;
import cc.catface.app_provider_provider.databinding.ActivityUserBinding;
import cc.catface.app_provider_provider.domain.User;
import cc.catface.app_provider_provider.provider.UserProviderListenerHelper;
import cc.catface.base.core_framework.light_mvp.LightAct;
import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.base.utils.android.common_print.dialog.normal.TDialogNormal;
import cc.catface.base.utils.android.common_recyclerview.TRV;
import cc.catface.ctool.context.TToast;
import cc.catface.ctool.java.TList;
import cc.catface.ctool.system.TLog;
import cc.catface.ctool.system.TWeakHandler;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class UserActivity extends LightAct<LightPresenter, ActivityUserBinding> {

    @Override
    public void handleMessage(Message msg) {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public int layoutId() {
        return R.layout.activity_user;
    }

    private List<User> mAllUsers = new ArrayList<>();
    private RoomAdapter mAdapter;

    @Override
    protected void initView() {
        mAllUsers = DBHelper.getInstance().getUserDao().allUsers();
        mAdapter = new RoomAdapter(mAllUsers);
        TRV.initDefaultRV(this, mBinding.rvRoom);
        mBinding.rvRoom.setAdapter(mAdapter);
    }

    @Override
    protected void initHandler() {
        mHandler = new TWeakHandler<>(this);
    }

    @Override
    protected void initAction() {
        mBinding.btInsert.setOnClickListener(this);
        mBinding.btInsertReturnList.setOnClickListener(this);
        mBinding.btInsertAbortReturnList.setOnClickListener(this);
        mBinding.btInsertReturnArray.setOnClickListener(this);
        mBinding.btInsertReplace.setOnClickListener(this);
        mBinding.btInsertRollback.setOnClickListener(this);
        mBinding.btInsertAbort.setOnClickListener(this);
        mBinding.btInsertFail.setOnClickListener(this);
        mBinding.btInsertIgnore.setOnClickListener(this);
        mBinding.btDelete.setOnClickListener(this);
        mBinding.btQuery.setOnClickListener(this);
        dragAndSwipeRV();

        // 处理数据库变化回调
        handleProviderListener();
    }


    private void handleProviderListener() {
        UserProviderListenerHelper.getInstance().setUserProviderListener(new UserProviderListenerHelper.UserProviderListener() {
            @Override
            public void onOption(int optionType, int rowId, String fileId) {

            }

            @Override
            public void onError(int code, String desc) {
                TLog.d("user activity -- handleProviderListener --> code:" + code + " &  desc:" + desc);
            }
        });
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {
        int id = view.getId();
        /* 增 */
        if (id == R.id.btInsert) {
            User user = new User(words[new Random().nextInt(words.length)], new Random().nextInt(100), System.currentTimeMillis());
            long rowId = DBHelper.getInstance().getUserDao().insert(user);
            TToast.showNormalD("btInsert --> rowId:" + rowId);
            mAllUsers.add(0, user);
            mBinding.rvRoom.smoothScrollToPosition(0);
            mAdapter.notifyItemInserted(0);
        }
        if (id == R.id.btInsertReturnList) {
            ArrayList<User> users = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                User user = new User(words[new Random().nextInt(words.length)], new Random().nextInt(100), System.currentTimeMillis());
                users.add(user);
            }
            List<Long> rowIds = DBHelper.getInstance().getUserDao().insertReturnList(users.toArray(new User[users.size()]));
            TToast.showNormalD("btInsertReturnList --> rowIds:" + rowIds.toString());
            mAllUsers.addAll(0, users);
            mBinding.rvRoom.smoothScrollToPosition(0);
            mAdapter.notifyItemRangeInserted(0, 3);
        }
        if (id == R.id.btInsertAbortReturnList) {
            ArrayList<User> users = new ArrayList<>();
            int conflictId = mAllUsers.get(mAllUsers.size() - 1).getId();
            User conflictUser = new User(words[new Random().nextInt(words.length)], new Random().nextInt(100), System.currentTimeMillis());
            conflictUser.setId(conflictId);
            users.add(conflictUser);
            for (int i = 0; i < 3; i++) {
                User user = new User(words[new Random().nextInt(words.length)], new Random().nextInt(100), System.currentTimeMillis());
                users.add(user);
            }
            List<Long> rowIds = DBHelper.getInstance().getUserDao().insertAbortReturnList(users.toArray(new User[users.size()]));
            TToast.showNormalD("btInsertAbortReturnList --> rowIds:" + rowIds.toString());
            mAllUsers.addAll(0, users);
            mBinding.rvRoom.smoothScrollToPosition(0);
            mAdapter.notifyItemRangeInserted(0, 3);
        }
        if (id == R.id.btInsertReturnArray) {
            ArrayList<User> users = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                User user = new User(words[new Random().nextInt(words.length)], new Random().nextInt(100), System.currentTimeMillis());
                users.add(user);
            }
            long[] rowIds = DBHelper.getInstance().getUserDao().insertReturnArray(users.toArray(new User[users.size()]));
            TToast.showNormalD("btInsertReturnArray --> rowIds:" + Arrays.toString(rowIds));
            mAllUsers.addAll(0, users);
            mBinding.rvRoom.smoothScrollToPosition(0);
            mAdapter.notifyItemRangeInserted(0, 2);
        }
        if (id == R.id.btInsertReplace) {
            int conflictId = mAllUsers.get(0).getId();
            User user = new User(words[new Random().nextInt(words.length)], new Random().nextInt(100), System.currentTimeMillis());
            user.setId(conflictId);
            long rowId = DBHelper.getInstance().getUserDao().insertReplace(user);
            TToast.showNormalD("btInsertReplace --> rowId:" + rowId);
            mAllUsers.set(0, user);
            mBinding.rvRoom.smoothScrollToPosition(0);
            mAdapter.notifyItemChanged(0);
        }
        if (id == R.id.btInsertRollback) {
            int conflictId = mAllUsers.get(0).getId();
            User user = new User(words[new Random().nextInt(words.length)], new Random().nextInt(100), System.currentTimeMillis());
            user.setId(conflictId);
            long rowId = DBHelper.getInstance().getUserDao().insertRollback(user);
            TToast.showNormalD("btInsertRollback --> rowId:" + rowId);
            mAllUsers.set(0, user);
            mBinding.rvRoom.smoothScrollToPosition(0);
            mAdapter.notifyItemChanged(0);
        }
        if (id == R.id.btInsertAbort) {
            int conflictId = mAllUsers.get(0).getId();
            User user = new User(words[new Random().nextInt(words.length)], new Random().nextInt(100), System.currentTimeMillis());
            user.setId(conflictId);
            long rowId = DBHelper.getInstance().getUserDao().insertAbort(user);
            TToast.showNormalD("btInsertAbort --> rowId:" + rowId);
            mAllUsers.set(0, user);
            mBinding.rvRoom.smoothScrollToPosition(0);
            mAdapter.notifyItemChanged(0);
        }
        if (id == R.id.btInsertFail) {
            int conflictId = mAllUsers.get(0).getId();
            User user = new User(words[new Random().nextInt(words.length)], new Random().nextInt(100), System.currentTimeMillis());
            user.setId(conflictId);
            long rowId = DBHelper.getInstance().getUserDao().insertFail(user);
            TToast.showNormalD("btInsertFail --> rowId:" + rowId);
            mAllUsers.set(0, user);
            mBinding.rvRoom.smoothScrollToPosition(0);
            mAdapter.notifyItemChanged(0);
        }
        if (id == R.id.btInsertIgnore) {
            int conflictId = mAllUsers.get(0).getId();
            User user = new User(words[new Random().nextInt(words.length)], new Random().nextInt(100), System.currentTimeMillis());
            user.setId(conflictId);
            long rowId = DBHelper.getInstance().getUserDao().insertIgnore(user);
            TToast.showNormalD("btInsertIgnore --> rowId:" + rowId);
            mAllUsers.set(0, user);
            mBinding.rvRoom.smoothScrollToPosition(0);
            mAdapter.notifyItemChanged(0);
        }
        /* 删 */
        if (id == R.id.bt_delete) {
            DBHelper.getInstance().getUserDao().deleteAll();
            mAdapter.notifyItemRangeRemoved(0, mAllUsers.size());
            mAllUsers.clear();
        }
        if (id == R.id.btQuery) {
            TList.clearAddAll(mAllUsers, DBHelper.getInstance().getUserDao().allUsers());
            mAdapter.notifyDataSetChanged();
            mBinding.rvRoom.smoothScrollToPosition(0);
            TToast.showNormal("当前数据量-->" + DBHelper.getInstance().getUserDao().totalCount());
        }
    }

    private void dragAndSwipeRV() {
        ItemTouchHelper.Callback callback = new ItemTouchHelper.Callback() {
            /// 返回允许滑动的方向
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                int drag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                int swipe = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                return makeMovementFlags(drag, swipe);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                Collections.swap(mAllUsers, from, to);
                mAdapter.notifyItemMoved(from, to);
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                // 左滑delete
                if (direction == ItemTouchHelper.LEFT) {
                    TDialogNormal.get(UserActivity.this).notification("确认删除？", "您将删除记录：\n" + mAllUsers.get(position).toString() + "\n删除后不可恢复！", "取消", "删除", notificationType -> {
                        if (notificationType != TDialogNormal.NotificationNegative) {
                            mAdapter.notifyDataSetChanged();
                            return;
                        }
                        User user = mAllUsers.get(position);
                        DBHelper.getInstance().getUserDao().delete(user);
                        TList.clearAddAll(mAllUsers, DBHelper.getInstance().getUserDao().allUsers());
                        mAdapter.notifyItemRemoved(position);
                    });
                    // 右划update
                } else if (direction == ItemTouchHelper.RIGHT) {
                    int age = new Random().nextInt(100);
                    TToast.showNormal("当前年龄由" + mAllUsers.get(position).getAge() + "更新为:" + age);
                    mAllUsers.get(position).setAge(age);
                    mAllUsers.get(position).setName(words[new Random().nextInt(words.length)]);
                    mAllUsers.set(position, mAllUsers.get(position));
                    DBHelper.getInstance().getUserDao().update(mAllUsers.get(position));
                    mAdapter.notifyItemChanged(position);
                }
            }
        };
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mBinding.rvRoom);
    }

    private static final String[] words = {"富强", "民主", "文明", "和谐", "自由", "平等", "公正", "法治", "爱国", "敬业", "诚信", "友善"};
}
