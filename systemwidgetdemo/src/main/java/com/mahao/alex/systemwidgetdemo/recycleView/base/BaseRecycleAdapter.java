package com.mahao.alex.systemwidgetdemo.recycleView.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Adapter 的封装
 * <p>
 * 为基类的RecycleAdapter 添加头部和底部控件
 * <p>
 * 添加头部控件和底部控件，
 * 未添加隐藏方法，想要隐藏暂时使用Visible方法
 * Created by mdw on 2016/4/26.
 */
public abstract class BaseRecycleAdapter<T> extends RecyclerView.Adapter<BaseRecycleAdapter.BaseViewHolder> {


    protected List<T> mData;

    public BaseRecycleAdapter(List<T> data) {
        this.mData = data;
    }

    // 头部控件
    private View mHeaderView;

    // 底部控件
    private View mFooterView;


    // item 的三种类型
    public static final int ITEM_TYPE_NORMAL = 1; // 正常的item类型
    private static final int ITEM_TYPE_HEADER = 2; // header
    private static final int ITEM_TYPE_FOOTER = 3; // footer
    public static final int ITEM_TYPE_GROUP = 4;// 分组条目的item类型


    private boolean isHasHeader = false;

    private boolean isHasFooter = false;

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == ITEM_TYPE_FOOTER) {
            // 如果是底部类型，返回底部视图
            return new BaseViewHolder(mFooterView);
        }
        if (viewType == ITEM_TYPE_HEADER) {
            return new BaseViewHolder(mHeaderView);
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(viewType), parent, false);
        return new BaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseRecycleAdapter.BaseViewHolder holder, int position) {
        int type = getItemViewType(position);
        if (type == ITEM_TYPE_HEADER || getItemViewType(position) == ITEM_TYPE_FOOTER) {
            // 不做处理
            return;
        } else {
            position -= isHasHeader ? 1 : 0;
            bindData(holder, position, getItemType(position));
        }
    }


    /**
     * 添加头部视图
     */
    public void setHeaderView(View header) {
        this.mHeaderView = header;
        isHasHeader = true;
        notifyDataSetChanged();
    }

    /**
     * 移除头部视图
     */
    public void removeHeaderView() {
        if (isHasHeader) {
            this.mHeaderView = null;
            isHasHeader = false;
            notifyDataSetChanged();
        }
    }


    /**
     * 添加底部视图
     */
    public void setFooterView(View footer) {
        this.mFooterView = footer;
        isHasFooter = true;
        notifyDataSetChanged();
    }

    /**
     * 移除底部视图
     */
    public void removeFooterView() {
        if (isHasFooter) {
            this.mFooterView = null;
            isHasFooter = false;
            notifyDataSetChanged();
        }
    }


    @Override
    public int getItemViewType(int position) {
        // 根据索引获取当前View的类型，以达到复用的目的
        // 根据位置的索引，获取当前position的类型
        if (isHasHeader && position == 0) {
            return ITEM_TYPE_HEADER;
        }
        if (isHasHeader && isHasFooter && position == mData.size() + 1) {
            // 有头部和底部时，最后底部的应该等于size+!
            return ITEM_TYPE_FOOTER;
        } else if (!isHasHeader && isHasFooter && position == mData.size()) {
            // 没有头部，有底部，底部索引为size
            return ITEM_TYPE_FOOTER;
        }
        return getItemType(position - (isHasHeader ? 1 : 0));
    }

    /**
     * 刷新数据
     */
    public void refresh(List<T> data) {
        this.mData.clear();
        this.mData.addAll(data);
        notifyDataSetChanged();
    }

    public int getItemType(int position) {
        return ITEM_TYPE_NORMAL;
    }

    /**
     * 添加数据
     *
     * @param datas
     */
    public void addData(List<T> datas) {
        this.mData.addAll(datas);
        notifyDataSetChanged();
    }

    /**
     * 绑定数据
     *
     * @param holder   具体的viewHolder
     * @param position 对应的索引
     * @param itemType 当前条目对应类型
     */
    protected abstract void bindData(BaseViewHolder holder, int position, int itemType);


    @Override
    public int getItemCount() {
        int size = mData.size();
        if (isHasFooter)
            size++;
        if (isHasHeader)
            size++;
        return size;
    }


    /**
     * 封装ViewHolder ,子类可以直接使用
     */
    public class BaseViewHolder extends RecyclerView.ViewHolder {


        private Map<Integer, View> mViewMap;

        public BaseViewHolder(View itemView) {
            super(itemView);
            mViewMap = new HashMap<>();
        }

        /**
         * 根据id获取布局上的view
         */
        public View getView(int id) {
            View view = mViewMap.get(id);
            if (view == null) {
                view = itemView.findViewById(id);
                mViewMap.put(id, view);
            }
            return view;
        }
    }

    /**
     * 获取item布局
     */
    public abstract int getLayoutId(int itemType);


}
