package cc.catface.module_apis.sticky_list_headers.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cc.catface.module_apis.R;

/**
 * Created by louisgeek on 2016/4/7.
 */
public class StickyListHeaderAdapter extends BaseAdapter implements se.emilsjolander.stickylistheaders.StickyListHeadersAdapter {

    private List<String> listStr;
    private LayoutInflater inflater;
    private OnMyItemClickListener listener;

    public StickyListHeaderAdapter(Context context, List<String> listStr) {
        inflater = LayoutInflater.from(context);
        this.listStr = listStr;
    }

    @Override
    public int getCount() {
        return listStr.size();
    }

    @Override
    public Object getItem(int position) {
        return listStr.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.apis_item_sticky_list_items, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.id_tv_item);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(null != listener) listener.onMyItemClick(position, listStr.get(position));
                }
            });
            convertView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(null != listener)
                        listener.onMyItemLongClick(position, listStr.get(position));
                    return true;
                }
            });
            convertView.setTag(holder);


        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text.setText(listStr.get(position));

        return convertView;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder;
        if(convertView == null) {
            holder = new HeaderViewHolder();
            convertView = inflater.inflate(R.layout.apis_item_sticky_list_headers, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.tv_header);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }
        //set header text as first char in name
        String headerText = listStr.get(position).subSequence(0, 1).charAt(0) + "";
        holder.text.setText(headerText);
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        //return the first character of the country as ID because this is what headers are based upon
        return listStr.get(position).subSequence(0, 1).charAt(0);
      /*  if (position<3){
            return 0;
        }else if (position<5){
            return 1;
        }else if (position<8){
            return 2;
        }else {
            return 3;
        }*/


    }

    class HeaderViewHolder {
        TextView text;
    }

    class ViewHolder {
        TextView text;
    }

    /**
     * 内部接口回调方法
     */
    public interface OnMyItemClickListener {
        void onMyItemClick(int position, Object object);

        void onMyItemLongClick(int position, Object object);
    }

    /**
     * 设置监听方法
     *
     * @param listener
     */
    public void setOnMyItemClickListener(OnMyItemClickListener listener) {
        this.listener = listener;
    }

    void addItem() {
        listStr.add("新增数据");
        notifyDataSetChanged();
    }

    void deleteItem(int position) {
        listStr.remove(position);
        notifyDataSetChanged();
    }

}