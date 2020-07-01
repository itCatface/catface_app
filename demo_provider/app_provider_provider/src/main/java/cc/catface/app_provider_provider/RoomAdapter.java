package cc.catface.app_provider_provider;

import java.util.List;

import cc.catface.app_provider_provider.databinding.ItemUserBinding;
import cc.catface.app_provider_provider.domain.User;
import cc.catface.ctool.view.recyclerview.AdapterList;


/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class RoomAdapter extends AdapterList<User, ItemUserBinding> {


    RoomAdapter(List<User> datas) {
        super(datas);
    }

    @Override
    public int layoutId() {
        return R.layout.item_user;
    }

    @Override
    public void onBindHolder(ItemUserBinding binding, int position) {
        User user = getDatas().get(position);
        binding.tvContent.setText(user.toString());
    }
}
