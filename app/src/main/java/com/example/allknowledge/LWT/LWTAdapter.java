package com.example.allknowledge.LWT;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.allknowledge.R;
import com.example.allknowledge.model.ListWithText;

import java.util.ArrayList;
import java.util.List;

public class LWTAdapter extends RecyclerView.Adapter<LWTAdapter.GroupViewHolder> {

    private List<ListWithText> lwt ;
    private SetOnclicItem onclicItem;

    public void setOnclicItem(SetOnclicItem onclicItem) {
        this.onclicItem = onclicItem;
    }

    public LWTAdapter(List<ListWithText> lwt) {
        this.lwt = lwt;
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_lwt,parent,false);
        return new GroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {
            holder.lwttext.setText(lwt.get(position).getName());
        Glide.with(holder.imageView)
                .load(lwt.get(position).getImage())
                .apply(RequestOptions
                .errorOf(R.drawable.ic_launcher_background)
                .placeholder(R.drawable.ic_launcher_foreground))
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return lwt.size();
    }

    public void SetSearch(List<ListWithText> llz) {
        lwt = new ArrayList<>();
        lwt.addAll(llz);
        notifyDataSetChanged();

    }

    public class GroupViewHolder extends RecyclerView.ViewHolder {
        TextView lwttext = itemView.findViewById(R.id.TextOnList);
        ImageView imageView = itemView.findViewById(R.id.ImageOnList);
        public GroupViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onclicItem.onClicked(lwt.get(getAdapterPosition()),getAdapterPosition());
                }
            });


        }
    }

    public interface SetOnclicItem {
        void onClicked(ListWithText lwt , int pos);
    }
}
