package cc.catface.api.room;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityRoomBinding;
import cc.catface.api.room.domain.User;
import cc.catface.app_base.TestDataSource;
import cc.catface.base.core_framework.base_normal.NormalFragment;
import cc.catface.base.utils.android.common_print.dialog.normal.TDialogNormal;
import cc.catface.base.utils.android.common_print.toast.TToast;
import cc.catface.base.utils.android.coomon_listview.TListView;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class DemoRoomFm extends NormalFragment<ApiActivityRoomBinding> {
    @Override public int layoutId() {
        return R.layout.api_activity_room;
    }

    private List<User> mAllUsers = new ArrayList<>();
    @SuppressLint("HandlerLeak") private Handler mHandler = new Handler() {
        @Override public void handleMessage(Message msg) {
            String[] items = new String[mAllUsers.size()];
            for (int i = 0; i < mAllUsers.size(); i++) {
                items[i] = mAllUsers.get(i).toString();
            }
            TListView.str(mActivity, mBinding.lvRoom, items, pos -> {
                TDialogNormal.get(mActivity).notification("确定删除吗", "想好再做决定", "是", "否", new TDialogNormal.NotificationCallback() {
                    @Override public void onClick(int notificationType) {
                        switch (notificationType) {
                            case TDialogNormal.NotificationPositive:
                                new Thread(new Runnable() {
                                    @Override public void run() {
                                        UserDatabase.getInstance(mActivity).getUserDao().delete(mAllUsers.get(pos).getId());
                                    }
                                }).start();
                                break;
                            case TDialogNormal.NotificationNegative:
                                TToast.get(mActivity).showBShortView("打扰了", TToast.B_SUCCESS);
                                break;
                            case TDialogNormal.NotificationNeutral:

                                break;
                        }
                    }
                });
            });
        }
    };

    @Override protected void initAction() {
        mBinding.btInsert.setOnClickListener(v -> {
            new Thread(new Runnable() {
                @Override public void run() {
                    long l = System.currentTimeMillis();
                    User user = new User();
                    user.setName("name-" + TestDataSource.words[new Random().nextInt(TestDataSource.words.length)]);
                    user.setAge((int) l);
                    UserDatabase.getInstance(mActivity).getUserDao().insert(user);
                }
            }).start();
        });
        mBinding.btDelete.setOnClickListener(v -> {
            new Thread(new Runnable() {
                @Override public void run() {
                    UserDatabase.getInstance(mActivity).getUserDao().deleteAll();
                }
            }).start();
        });
        mBinding.btUpdate.setOnClickListener(v -> {
            new Thread(new Runnable() {
                @Override public void run() {
                    long l = System.currentTimeMillis();
                    User user = new User();
                    user.setName("name++" + TestDataSource.words[new Random().nextInt(TestDataSource.words.length)]);
                    user.setAge((int) l);
                    UserDatabase.getInstance(mActivity).getUserDao().update(user, 11);
                }
            }).start();
        });
        mBinding.btQueryAll.setOnClickListener(v -> {
            new Thread(new Runnable() {
                @Override public void run() {
                    mAllUsers = UserDatabase.getInstance(mActivity).getUserDao().getAllUsers();
                    mHandler.obtainMessage().sendToTarget();
                }
            }).start();

        });
        mBinding.btQueryOdd.setOnClickListener(v -> {

        });
    }

    @Override public void createView() {
        title();
    }

    private void title() {
        mBinding.tfaRoom.setTitle("room").setIcon1(R.string.fa_chevron_left);
    }

}