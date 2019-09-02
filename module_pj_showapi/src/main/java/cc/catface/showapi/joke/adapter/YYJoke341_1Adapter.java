package cc.catface.showapi.joke.adapter;

import java.util.List;

import cc.catface.ctool.view.recyclerview.ListBindingAdapter;
import cc.catface.showapi.R;
import cc.catface.showapi.databinding.ShowapiItemYyJoke3411Binding;
import cc.catface.showapi.joke.domain.YYJoke341_1;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class YYJoke341_1Adapter extends ListBindingAdapter<YYJoke341_1.Showapi_res_body.Contentlist, ShowapiItemYyJoke3411Binding> {

    public YYJoke341_1Adapter(List<YYJoke341_1.Showapi_res_body.Contentlist> datas) {
        super(datas);
    }

    @Override public int layoutId() {
        return R.layout.showapi_item_yy_joke_341_1;
    }

    @Override public void onBindHolder(ShowapiItemYyJoke3411Binding binding, int position) {
        YYJoke341_1.Showapi_res_body.Contentlist data = getDatas().get(position);
        binding.tvTitle.setText(data.getTitle());
        binding.tvDt.setText(data.getCt());
        binding.tvText.setText(data.getText().replaceAll("<br />", "\r\n").replaceAll("<br/>", "\r\n"));
    }
}
