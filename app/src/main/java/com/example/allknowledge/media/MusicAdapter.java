package com.example.allknowledge.media;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.allknowledge.R;
import com.example.allknowledge.model.UrlModel;

import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.GroupViewHolder> {

    private List<UrlModel> urlModels;
    private int selectpos;
    private SetOnClickItem clickItem;


    public void setClickItem(SetOnClickItem clickItem) {
        this.clickItem = clickItem;
    }

    public MusicAdapter(List<UrlModel> urlModels) {
        this.urlModels = urlModels;
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_media,parent,false);
        return new GroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {

        if (selectpos==position)
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#ffa500"));
            holder.play_rv.setVisibility(View.VISIBLE);
        }
        else {
            holder.itemView.setBackgroundColor(Color.WHITE);
            holder.play_rv.setVisibility(View.INVISIBLE);
        }
        holder.title.setText(urlModels.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return urlModels.size();
    }


    public class GroupViewHolder extends RecyclerView.ViewHolder {

        TextView title = itemView.findViewById(R.id.title_rv);
        ImageView play_rv = itemView.findViewById(R.id.play_rv);

        public GroupViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickItem.Start(urlModels.get(getAdapterPosition()),getAdapterPosition());
                }
            });
        }
    }

    public interface SetOnClickItem {
        void Start (UrlModel urlModel,int pos);
    }

    public int getSelectpos() {
        return selectpos;
    }

    public void setSelectpos(int selectpos) {
        this.selectpos = selectpos;
    }
}
