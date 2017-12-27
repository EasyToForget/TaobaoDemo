package com.smile.taobaodemo.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smile.taobaodemo.R;
import com.smile.taobaodemo.model.entity.DescBase;
import com.smile.taobaodemo.utils.GlideUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Smile Wei
 * @since 2016/8/12.
 */
public class GoodsDescAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private Activity activity;
    private List<DescBase> list;

    public GoodsDescAdapter(Context context, Activity activity, List<DescBase> list) {
        this.context = context;
        this.activity = activity;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case DescBase.TYPE_PICTURE:
                view = LayoutInflater.from(context).inflate(R.layout.item_goods_desc_picture, parent, false);
                return new ViewHolder(view);
            default:
                view = LayoutInflater.from(context).inflate(R.layout.item_goods_desc, parent, false);
                return new DescHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DescBase bean = list.get(position);
        if (holder instanceof DescHolder) {
            ((DescHolder) holder).tvDesc.setText(bean.getName());
        } else if (holder instanceof ViewHolder) {
            GlideUtil.load(activity, bean.getUrl(), ((ViewHolder) holder).ivPicture);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_picture)
        ImageView ivPicture;


        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.onItemClick(getLayoutPosition());
                }
            });
            ButterKnife.bind(this, itemView);
        }

    }

    class DescHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_desc)
        TextView tvDesc;


        public DescHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
