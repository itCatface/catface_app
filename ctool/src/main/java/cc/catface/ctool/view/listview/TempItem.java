package cc.catface.ctool.view.listview;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

import cc.catface.ctool.R;
import cc.catface.ctool.databinding.WidgetTempItemBinding;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TempItem extends ConstraintLayout {
    private WidgetTempItemBinding mBinding;

    public TempItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.widget_temp_item, this, true);

        TypedArray ty = context.obtainStyledAttributes(attrs, R.styleable.TempItem);
        String itemName = ty.getString(R.styleable.TempItem_item_title);
        String itemValue = ty.getString(R.styleable.TempItem_item_content);
        boolean hasLine = ty.getBoolean(R.styleable.TempItem_item_line, true);
        ty.recycle();
        mBinding.tvTitle.setText(!TextUtils.isEmpty(itemName) ? itemName : "");
        mBinding.tvContent.setText(!TextUtils.isEmpty(itemValue) ? itemValue : "");
        mBinding.vHorizontalLine.setVisibility(hasLine ? VISIBLE : GONE);
    }

    public void setContent(String content) {
        mBinding.tvContent.setText(content);
    }
}
