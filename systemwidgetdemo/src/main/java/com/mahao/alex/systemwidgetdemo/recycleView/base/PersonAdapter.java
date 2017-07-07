package com.mahao.alex.systemwidgetdemo.recycleView.base;

import android.widget.TextView;

import com.mahao.alex.systemwidgetdemo.R;

import java.util.List;

/**
 * Created by alex_mahao on 2016/9/26.
 */
public class PersonAdapter extends BaseRecycleAdapter<Person> {

    public PersonAdapter(List<Person> data) {
        super(data);
    }

    @Override
    protected void bindData(BaseViewHolder holder, int position, int itemType) {
        ((TextView) holder.getView(R.id.name)).setText(mData.get(position).getName());
    }

    @Override
    public int getLayoutId(int itemType) {
        if (itemType == ITEM_TYPE_GROUP) {
            return R.layout.item_person2;
        }
        return R.layout.item_person;
    }

    @Override
    public int getItemType(int position) {
        if (position % 5 == 0) {
            return ITEM_TYPE_GROUP;
        }
        return super.getItemType(position);
    }
}
