package cc.catface.api.room;

import java.util.List;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiItemRvRoomBinding;
import cc.catface.api.room.domain.User;
import cc.catface.ctool.view.recyclerview.AdapterList;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class RoomAdapter extends AdapterList<User, ApiItemRvRoomBinding> {


    RoomAdapter(List<User> datas) {
        super(datas);
    }

    @Override public int layoutId() {
        return R.layout.api_item_rv_room;
    }

    @Override public void onBindHolder(ApiItemRvRoomBinding binding, int position) {
        User user = getDatas().get(position);
        binding.tvContent.setText(user.toString());
    }
}
