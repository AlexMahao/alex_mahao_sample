package com.mahao.alex.systemwidgetdemo.listView;

import android.widget.TextView;

import com.mahao.alex.systemwidgetdemo.R;

import java.util.List;

/**
 * 最基础的adapter
 * Created by Alex_MaHao on 2016/5/17.
 */

public class SimpleBaseAdapter extends BaseAppAdapter<String> {

    public SimpleBaseAdapter(List<String> datas) {
        super(datas);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_listview_sample;
    }

    @Override
    protected void bindData(BaseViewHolder vHolder, String data) {

        ((TextView) vHolder.getView(R.id.listview_sample_tv)).setText(data);

    }


}
