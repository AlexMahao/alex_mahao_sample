package com.mahao.alex.systemwidgetdemo.recycleView.base;

import android.widget.TextView;

import com.mahao.alex.systemwidgetdemo.R;

import java.util.List;

/**
 * Created by alex_mahao on 2016/9/26.
 */
public class PersonAdapter extends BaseRecycleAdapter<Person> {

    public PersonAdapter(List<Person> datas) {
        super(datas);
    }

    @Override
    protected void bindData(BaseViewHolder holder, int position) {
        ((TextView) holder.getView(R.id.name)).setText(datas.get(position).getName());
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_person;
    }
}
