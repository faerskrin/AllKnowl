package com.example.allknowledge.main;

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
import com.example.testzadanie.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class AdapterRecipe extends RecyclerView.Adapter<AdapterRecipe.GroupViewHolder> {

    private List<ArrayList<Recipe>> recipes;
    private View view;
    //private List<Recipe> iRec;
    private OnitemClick onitemClick;

    public void setOnitemClick(OnitemClick onitemClick) {
        this.onitemClick = onitemClick;
    }

    public AdapterRecipe(List<ArrayList<Recipe>> lists) {
        //iRec = App.dm.getRecipes();
        recipes = lists;
    }

    @Override
    public int getItemViewType(int position) {
        return recipes.get(position).size();
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType==1) {
             view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_onefrag, parent, false);
        }
        else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_twofrags, parent, false);
        }
        return new GroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {

        if (getItemViewType(position)==1)
        {
            TextView title = holder.itemView.findViewById(R.id.title);
            TextView sometext = holder.itemView.findViewById(R.id.someinfo);
            ImageView img = holder.itemView.findViewById(R.id.image);

            title.setText(recipes.get(position).get(0).getName());
            sometext.setText(recipes.get(position).get(0).getDescription());
            Glide.with(img)
                    .load(recipes.get(position).get(0).getImages().get(0))
                    .apply(RequestOptions
                    .errorOf(R.drawable.ic_search_black_24dp)
                    .placeholder(R.drawable.ic_launcher_foreground))
            .into(img);
        } else {
            TextView title1 = holder.itemView.findViewById(R.id.title1);
            TextView title2 = holder.itemView.findViewById(R.id.title2);
            TextView sometext1 = holder.itemView.findViewById(R.id.someinfo1);
            TextView sometext2 = holder.itemView.findViewById(R.id.someinfo2);
            ImageView img1 = holder.itemView.findViewById(R.id.image1);
            ImageView img2 = holder.itemView.findViewById(R.id.image2);

            title1.setText(recipes.get(position).get(0).getName());
            title2.setText(recipes.get(position).get(1).getName());

            sometext1.setText(recipes.get(position).get(0).getDescription());
            sometext2.setText(recipes.get(position).get(1).getDescription());

            Glide.with(img1)
                    .load(recipes.get(position).get(0).getImages().get(0))
                    .apply(RequestOptions
                            .errorOf(R.drawable.ic_search_black_24dp)
                            .placeholder(R.drawable.ic_launcher_foreground))
                    .into(img1);
            Glide.with(img2)
                    .load(recipes.get(position).get(1).getImages().get(0))
                    .apply(RequestOptions
                            .errorOf(R.drawable.ic_search_black_24dp)
                            .placeholder(R.drawable.ic_launcher_foreground))
                    .into(img2);

        }
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public void setSearch(List<Recipe> rep) {
            recipes = new ArrayList<>();
            //recipes.addAll( );
            notifyDataSetChanged();
    }

    public class GroupViewHolder extends RecyclerView.ViewHolder {
        public GroupViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    onitemClick.start(recipes.get(getAdapterPosition()).get(0));
                }
            });
        }
    }

    interface OnitemClick{
        void start (Recipe recipe);
    }
}
