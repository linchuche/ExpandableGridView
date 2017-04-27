package com.comslin.expandablegv;


import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private int column = 3;
    public SparseArray<String> titles = new SparseArray<>();
    private List<String> childs = new ArrayList<>();
    private String TAG = "ParticipantAdapter";
    private LayoutInflater inflater;

    public GridAdapter(Context activity) {
        inflater = LayoutInflater.from(activity);
        for (int i = 0; i < 30; i++) {
            childs.add("child" + i);
        }
        titles.put(0, typeName[0]);
        titles.put(2, typeName[1]);
        titles.put(6, typeName[2]);
        titles.put(26, typeName[2]);


    }


    private static String[] typeName = new String[]{
            "title1", "title2", "title3", "title4"
    };


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        if (viewType == typeTitle) {
            holder = new TitleHolder(new TextView(parent.getContext()));
        } else {
            holder = new ChildHolder(new ImageView(parent.getContext()));
        }
        return holder;
    }

    //    @Override
//    public int getItemViewType(int position) {
//        return checkType(0, position);//0:人 1:标签
//    }
    private final int typeTitle = 1;
    private final int typeChild = 0;

    @Override
    public int getItemViewType(int position) {
        if (titles.get(position) == null) {
            return typeChild;
        }
        return typeTitle;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final int oriP = position;
        if (getItemViewType(position) == typeTitle) {
            final TitleHolder titleViewHolder = (TitleHolder) holder;
            int p = 0;
            for (int i = 0; i < titles.size(); i++) {
                if (position == titles.keyAt(i)) {
                    p = i;
                    break;
                }
            }
            final int fp = p;
            titleViewHolder.textView.setText(titles.get(position)
            );
            return;
        }
        ChildHolder childHolder = (ChildHolder) holder;
        childHolder.imageView.setImageResource(R.mipmap.ic_launcher);
        for (int i = 0; i < titles.size(); i++) {
            int key = titles.keyAt(i);
            if (position > titles.keyAt(titles.size() - 1)) {
                position -= titles.size();
                break;
            } else if (position > key && position < titles.keyAt(i + 1)) {
                position -= (i + 1);
                break;
            }
        }
    }


    public boolean isTitle(int position) {
        return titles.get(position) != null;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        GridLayoutManager manager = (GridLayoutManager) recyclerView.getLayoutManager();
        column = manager.getSpanCount();
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (isTitle(position)) {
                    return column;
                }
                return 1;
            }
        });
    }

    @Override
    public int getItemCount() {
        return titles.size() + childs.size();
    }

    private OnItemClickListener onItemClickListener;
    private OnTitleClickListener onTitleClickListener;

    public void setOnTitleClickListener(OnTitleClickListener onTitleClickListener) {
        this.onTitleClickListener = onTitleClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public interface OnItemClickListener {
        void onItemClick(int groupPosition, int childPosition);
    }

    public interface OnTitleClickListener {
        void onTitleClick(int i);
    }

    class TitleHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public TitleHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
    }

    class ChildHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ChildHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView;
        }
    }

}
