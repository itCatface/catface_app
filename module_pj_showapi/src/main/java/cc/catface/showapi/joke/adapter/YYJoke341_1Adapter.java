package cc.catface.showapi.joke.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import cc.catface.showapi.R;
import cc.catface.showapi.joke.domain.YYJoke341_1;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class YYJoke341_1Adapter extends RecyclerView.Adapter<YYJoke341_1Adapter.Holder> {

    private List<YYJoke341_1.Showapi_res_body.Contentlist> mDatas;

    public YYJoke341_1Adapter(List<YYJoke341_1.Showapi_res_body.Contentlist> datas) {
        this.mDatas = datas;
    }


    static class Holder extends RecyclerView.ViewHolder {
        TextView tv_title, tv_text;

        Holder(@NonNull View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_text = (TextView) itemView.findViewById(R.id.tv_text);
        }
    }

    @NonNull @Override public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Holder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.showapi_item_yy_joke_341_1, viewGroup, false));
    }

    @Override public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.tv_title.setText(mDatas.get(i).getTitle());
        holder.tv_text.setText(mDatas.get(i).getText().replaceAll("<br />", "\r\n").replaceAll("<br/>", "\r\n"));
    }

    @Override public int getItemCount() {
        return mDatas.size();
    }
}
