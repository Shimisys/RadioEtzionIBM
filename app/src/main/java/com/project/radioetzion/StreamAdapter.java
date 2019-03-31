package com.project.radioetzion;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class StreamAdapter extends RecyclerView.Adapter<StreamAdapter.StreamViewHolder> {

    private ArrayList<StreamItems> mStreamItem;
    private OnItemClickListener listener;

    public static class StreamViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;

        public StreamViewHolder(View itemView){
            super(itemView);
            mImageView = itemView.findViewById(R.id.imgStream);
            mTextView1 = itemView.findViewById(R.id.txtStreamName);
            mTextView2 = itemView.findViewById(R.id.txtCreate);
            mTextView3 = itemView.findViewById(R.id.txtDuretion);
        }
        public void bind(final StreamItems item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
    public StreamAdapter(ArrayList<StreamItems> streamList, OnItemClickListener listener) {
        mStreamItem = streamList;
        this.listener = listener;
    }


    @NonNull
    @Override
    public StreamViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item, viewGroup, false);
        StreamViewHolder streamViewHolder = new StreamViewHolder(v);
        return streamViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StreamViewHolder streamViewHolder, int i) {
        StreamItems currentItem = mStreamItem.get(i);
        streamViewHolder.mImageView.setImageResource(currentItem.getImgStream());
        streamViewHolder.mTextView1.setText(currentItem.getTxtStream());
        streamViewHolder.mTextView2.setText(currentItem.getCreatedTime());
        streamViewHolder.mTextView3.setText(Integer.toString(currentItem.getDuration()));

        streamViewHolder.bind(mStreamItem.get(i), listener);

    }

    @Override
    public int getItemCount() {
        return mStreamItem.size();
    }


    public interface OnItemClickListener {
        void onItemClick(StreamItems item);
    }

}
