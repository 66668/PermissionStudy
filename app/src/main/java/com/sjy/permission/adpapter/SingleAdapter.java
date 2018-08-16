package com.sjy.permission.adpapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sjy.permission.R;
import com.sjy.permission.utils.DataBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SingleAdapter extends RecyclerView.Adapter<SingleAdapter.MyHolder> {
    List<DataBean> list;
    LayoutInflater inflater;
    Context context;

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_list, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    public SingleAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setList(List<DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, final int position) {
        final DataBean bean = list.get(position);
        if (bean != null) {

            holder.tv.setText(bean.getPermission());
            //跳转监听
            holder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemListener != null) {
                        onItemListener.onItemClickListener(bean);
                    }

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    /**
     * 自定义holder
     */
    class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_item)
        TextView tv;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setTag(this);
        }
    }


    //===================================================
    public OnItemClickListener onItemListener;

    public interface OnItemClickListener {
        void onItemClickListener(DataBean permission);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemListener = onItemClickListener;
    }

}

